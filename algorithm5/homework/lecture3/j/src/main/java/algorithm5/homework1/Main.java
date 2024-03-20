package algorithm5.homework1;

/**
В системе умного дома под управлением голосового помощника Лариса n устройств, соединяющихся между собой по сети LoRaWAN. 
Устройство номер 1 подключено к интернету и на него было скачано обновление, которое необходимо передать на все устройства.

Сеть LoRaWAN очень медленная, поэтому для распространения протокола был придуман peer-to-peer (P2P) протокол. 
Файл обновления разбивается на k одинаковых по размеру частей, занумерованных от 1 до k.

Передача части обновления происходит во время таймслотов. Каждый таймслот занимает одну минуту. 
За один таймслот каждое устройство может получить и передать ровно одну часть обновления. 
То есть устройство во время таймслота может получать новую часть обновления и передавать уже имеющуюуся у него к началу 
таймслота часть обновления, или совершать только одно из этих действий, или вообще не осуществлять прием или передачу. 
После приема части обновления устройство может передавать эту часть обновления другим устройствам в следующих таймслотах.

Перед каждым таймслотом для каждой части обновления определяется, на скольких устройствах сети скачана эта часть. 
Каждое устройство выбирает отсутствующую на нем часть обновления, которая встречается в сети реже всего. 
Если таких частей несколько, то выбирается отсутствующая на устройстве часть обновления с наименьшим номером.

После этого устройство делает запрос выбранной части обновления у одного из устройств, на котором такая часть обновления уже скачана. 
Если таких устройств несколько — выбирается устройство, на котором скачано наименьшее количество частей обновления. 
Если и таких устройств оказалось несколько — выбирается устройство с минимальным номером.

После того, как все запросы отправлены, каждое устройство выбирает, чей запрос удовлетворить. Устройство A удовлетворяет тот запрос, 
который поступил от наиболее ценного для A устройства. Ценность устройства B для устройства A определяется как количество частей обновления, 
ранее полученных устройством A от устройства B. Если на устройство A пришло несколько запросов от одинаково ценных устройств, 
то удовлетворяется запрос того устройства, на котором меньше всего скачанных частей обновления. Если и таких запросов несколько, 
то среди них выбирается устройство с наименьшим номером.

Далее начинается новый таймслот. Устройства, чьи запросы удовлетворены, скачивают запрошенную часть обновления, а остальные не скачивают ничего.

Для каждого устройства определите, сколько таймслотов понадобится для скачивания всех частей обновления.

Формат ввода
Вводится два числа n и k (2 ≤ n ≤ 100, 1 ≤ k ≤ 200).

Формат вывода
Выведите n-1 число — количество таймслотов, необходимых для скачивания обновления на устройства с номерами от 2 до n.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String nk = scanner.nextLine();
        int nNumberDevices = Integer.parseInt(nk.split(" ")[0]);
        int knumberPackages = Integer.parseInt(nk.split(" ")[1]);
        System.out.println(getNumberSteps(nNumberDevices, knumberPackages));
        scanner.close();
    }

    public static String getNumberSteps(int nNumberDevices, int knumberPackages) {
        // подготовка
        Set<PackageP2P> packagesP2p = getPackagesP2p(knumberPackages);
        TreeSet<DeviceP2p> devicesP2p = getDevicesP2p(nNumberDevices, packagesP2p);

        // основной цикл
        while (!isAllDevicesComplex(devicesP2p)) {
            // добавляем каждому девайсу по ходу, если они еще не заполнены
            for (DeviceP2p deviceP2p : devicesP2p) {
                if (!deviceP2p.notDownloadedPackages.isEmpty()) {
                    deviceP2p.numberStep++;
                }
            }
            Map<PackageP2P, Integer> notDownloadedPackegesMap = getNotDownloadedPackegesMap(devicesP2p);
            Map<DeviceP2p, PackageP2P> candidatesToLoadToDeviceMap = getCandidatesToLoadToDeviceMap(devicesP2p,
                    notDownloadedPackegesMap);

            // ключ - кому надо загрузить, значение - от кого ожидается загрузка
            Map<DeviceP2p, DeviceP2p> requestsMap = getRequestsMap(candidatesToLoadToDeviceMap, devicesP2p);

            // ключ - кто будет грузить, значение - кому
            Map<DeviceP2p, DeviceP2p> senderChoiceMap = getSenderChoiceMap(requestsMap);

            // основная магия
            for (Map.Entry<DeviceP2p, DeviceP2p> pairLoading : senderChoiceMap.entrySet()) {
                PackageP2P pack = candidatesToLoadToDeviceMap.get(pairLoading.getValue());
                pairLoading.getValue().notDownloadedPackages.remove(pack);

                Map<DeviceP2p, Integer> values = pairLoading.getValue().values;
                DeviceP2p key = pairLoading.getKey();
                values.put(key, values.getOrDefault(key, 0) + 1);
            }
        }

        int countFirst = 0;
        StringBuilder builder = new StringBuilder();
        for (DeviceP2p deviceP2p : devicesP2p) {
            if (countFirst != 0) {
                builder.append(deviceP2p.numberStep).append(" ");
            }
            if (countFirst == 0) {
                countFirst++;
            }
        }
        return builder.toString().trim();
    }

    private static Set<PackageP2P> getPackagesP2p(int knumberPackages) {
        Set<PackageP2P> packagesP2p = new TreeSet<>();
        for (int i = 0; i < knumberPackages; i++) {
            PackageP2P packageP2P = new PackageP2P(i);
            packagesP2p.add(packageP2P);
        }
        return packagesP2p;
    }

    private static TreeSet<DeviceP2p> getDevicesP2p(int nNumberDevices, Set<PackageP2P> packagesP2p) {
        TreeSet<DeviceP2p> devicesP2p = new TreeSet<>();
        for (int i = 0; i < nNumberDevices; i++) {
            DeviceP2p device = new DeviceP2p(i);
            for (PackageP2P pack : packagesP2p) {
                device.notDownloadedPackages.add(pack);
            }
            devicesP2p.add(device);
        }
        DeviceP2p firstDevice = devicesP2p.first();
        firstDevice.notDownloadedPackages = new TreeSet<>(); // обнуление незагруженных частей у лидера
        return devicesP2p;
    }

    private static boolean isAllDevicesComplex(TreeSet<DeviceP2p> devicesP2p) {
        for (DeviceP2p deviceP2p : devicesP2p) {
            if (!deviceP2p.notDownloadedPackages.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static Map<PackageP2P, Integer> getNotDownloadedPackegesMap(TreeSet<DeviceP2p> devicesP2p) {
        Map<PackageP2P, Integer> notDownloadedPackegesMap = new HashMap<>();
        for (DeviceP2p deviceP2p : devicesP2p) {
            for (PackageP2P notDownlPack : deviceP2p.notDownloadedPackages) {
                notDownloadedPackegesMap.put(notDownlPack, notDownloadedPackegesMap.getOrDefault(notDownlPack, 0) + 1);
            }
        }

        List<Map.Entry<PackageP2P, Integer>> list = new LinkedList<>(notDownloadedPackegesMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<PackageP2P, Integer>>() {
            @Override
            public int compare(Map.Entry<PackageP2P, Integer> o1, Map.Entry<PackageP2P, Integer> o2) {
                // Сначала сравниваем по значению (от большего к меньшему)
                int compareByValue = o2.getValue().compareTo(o1.getValue());
                if (compareByValue != 0) {
                    return compareByValue;
                }
                // Если значения равны, сравниваем по idNumberPackage ключа PackageP2P
                return Integer.compare(o1.getKey().idNumberPackage, o2.getKey().idNumberPackage);
            }
        });

        // Создайте новую отсортированную LinkedHashMap с отсортированными записями
        Map<PackageP2P, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<PackageP2P, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private static Map<DeviceP2p, PackageP2P> getCandidatesToLoadToDeviceMap(TreeSet<DeviceP2p> devicesP2p,
            Map<PackageP2P, Integer> notDownloadedPackegesMap) {
        Map<DeviceP2p, PackageP2P> candidatesToLoadToDeviceMap = new TreeMap<>();
        for (DeviceP2p deviceP2p : devicesP2p) {
            for (Map.Entry<PackageP2P, Integer> sortedPackage : notDownloadedPackegesMap.entrySet()) {
                if (deviceP2p.notDownloadedPackages.contains(sortedPackage.getKey())) {
                    candidatesToLoadToDeviceMap.put(deviceP2p, sortedPackage.getKey());
                    break;
                }
            }
        }
        return candidatesToLoadToDeviceMap;
    }

    private static Map<DeviceP2p, DeviceP2p> getRequestsMap(Map<DeviceP2p, PackageP2P> candidatesToLoadToDeviceMap,
            TreeSet<DeviceP2p> devicesP2p) {
        Map<DeviceP2p, DeviceP2p> requestsMap = new TreeMap<>();
        for (Map.Entry<DeviceP2p, PackageP2P> receiving : candidatesToLoadToDeviceMap.entrySet()) {
            // мы проходимся по списку всех девайсов, проверяем, у кого в списках нет этого
            // обновления и эту мапу сортируем сначала по длине незакаченных пакетов (с
            // большого, так как чем больше незакаченных, значит тем меньше закаченных),
            // потом по номеру
            Map<DeviceP2p, Integer> senders = new HashMap<>();
            for (DeviceP2p sender : devicesP2p) {
                if (!sender.notDownloadedPackages.contains(receiving.getValue())) {
                    senders.put(sender, sender.notDownloadedPackages.size());
                }
            }
            List<Map.Entry<DeviceP2p, Integer>> list = new ArrayList<>(senders.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<DeviceP2p, Integer>>() {
                @Override
                public int compare(Map.Entry<DeviceP2p, Integer> o1, Map.Entry<DeviceP2p, Integer> o2) {
                    // Сначала сравниваем по значению (от большего к меньшему)
                    int compareByValue = o2.getValue().compareTo(o1.getValue());
                    if (compareByValue != 0) {
                        return compareByValue;
                    }
                    // Если значения равны, сравниваем по idNumberDevice ключа DeviceP2p
                    return Integer.compare(o1.getKey().idNumberDevice, o2.getKey().idNumberDevice);
                }
            });
            requestsMap.put(receiving.getKey(), list.get(0).getKey());
        }
        return requestsMap;
    }

    private static Map<DeviceP2p, DeviceP2p> getSenderChoiceMap(Map<DeviceP2p, DeviceP2p> requestsMap) {
        Map<DeviceP2p, Map<DeviceP2p, Integer>> requestMapHandle = getValueMap(requestsMap);
        return getFinalDecisionMap(requestMapHandle);
    }

    private static Map<DeviceP2p, Map<DeviceP2p, Integer>> getValueMap(Map<DeviceP2p, DeviceP2p> requestsMap) {
        Map<DeviceP2p, Map<DeviceP2p, Integer>> requestMapHandle = new HashMap<>();
        for (Map.Entry<DeviceP2p, DeviceP2p> pair : requestsMap.entrySet()) {
            Map<DeviceP2p, Integer> map = null;
            if (!requestMapHandle.containsKey(pair.getValue())) {
                map = new HashMap<>();
            } else {
                map = requestMapHandle.get(pair.getValue());
            }
            int value = 0;
            if (pair.getValue().values.containsKey(pair.getKey())) {
                value = pair.getValue().values.get(pair.getKey());
            }
            map.put(pair.getKey(), value);
            requestMapHandle.put(pair.getValue(), map);
        }
        return requestMapHandle;
    }

    private static Map<DeviceP2p, DeviceP2p> getFinalDecisionMap(
            Map<DeviceP2p, Map<DeviceP2p, Integer>> requestMapHandle) {
        Map<DeviceP2p, DeviceP2p> finalDecisionMap = new TreeMap<>();
        for (Map.Entry<DeviceP2p, Map<DeviceP2p, Integer>> pair : requestMapHandle.entrySet()) {
            List<Map.Entry<DeviceP2p, Integer>> list = new ArrayList<>(pair.getValue().entrySet());

            Collections.sort(list, new Comparator<Map.Entry<DeviceP2p, Integer>>() {

                @Override
                public int compare(Map.Entry<DeviceP2p, Integer> o1, Map.Entry<DeviceP2p, Integer> o2) {
                    int compareByValue = o2.getValue().compareTo(o1.getValue());
                    if (compareByValue != 0) {
                        return compareByValue;
                    }
                    compareByValue = Integer.compare(o2.getKey().notDownloadedPackages.size(),
                            o1.getKey().notDownloadedPackages.size());
                    if (compareByValue != 0) {
                        return compareByValue;
                    }
                    return Integer.compare(o1.getKey().idNumberDevice, o2.getKey().idNumberDevice);
                }

            });
            finalDecisionMap.put(pair.getKey(), list.get(0).getKey());
        }

        return finalDecisionMap;
    }

}

class DeviceP2p implements Comparable<DeviceP2p> {
    int idNumberDevice;
    Map<DeviceP2p, Integer> values;
    Set<PackageP2P> notDownloadedPackages;
    int numberStep;

    public DeviceP2p(int idNumberDevice) {
        this.idNumberDevice = idNumberDevice;
        values = new TreeMap<>();
        notDownloadedPackages = new TreeSet<>();
        numberStep = 0;
    }

    @Override
    public int compareTo(DeviceP2p other) {
        return Integer.compare(this.idNumberDevice, other.idNumberDevice);
    }
}

class PackageP2P implements Comparable<PackageP2P> {
    int idNumberPackage;
    Set<DeviceP2p> processedDevices;

    public PackageP2P(int idNumberPackage) {
        this.idNumberPackage = idNumberPackage;
        processedDevices = new HashSet<>();
    }

    @Override
    public int compareTo(PackageP2P other) {
        return Integer.compare(this.idNumberPackage, other.idNumberPackage);
    }
}