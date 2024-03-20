package algorithm5.homework1;

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

    private static String getNumberSteps(int nNumberDevices, int knumberPackages) {
        // подготовка
        Set<PackageP2P> packagesP2p = getPackagesP2p(knumberPackages);
        TreeSet<DeviceP2p> devicesP2p = getDevicesP2p(nNumberDevices, packagesP2p);

        while (!isAllDevicesComplex(devicesP2p)) {
            Map<PackageP2P, Integer> notDownloadedPackegesMap = getNotDownloadedPackegesMap(devicesP2p);
            Map<DeviceP2p, PackageP2P> candidatesToLoadToDeviceMap = getCandidatesToLoadToDeviceMap(devicesP2p,
                    notDownloadedPackegesMap);

            // ключ - кому надо загрузить, значение - от кого ожидается загрузка
            Map<DeviceP2p, DeviceP2p> requestsMap = getRequestsMap(candidatesToLoadToDeviceMap, devicesP2p);

        }

        return "";
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
            device.notDownloadedPackages = packagesP2p;
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
            Map<DeviceP2p, Integer> sortedSenders = new LinkedHashMap<>();
            for (Map.Entry<DeviceP2p, Integer> entry : list) {
                sortedSenders.put(entry.getKey(), entry.getValue());
            }

            // берем в качестве кандидата загрузчика первый ключ этой мапы
            for (Map.Entry<DeviceP2p, Integer> sender : sortedSenders.entrySet()) {
                requestsMap.put(receiving.getKey(), sender.getKey());
                break;
            }
        }
        return requestsMap;
    }

}

class DeviceP2p {
    int idNumberDevice;
    Map<DeviceP2p, Integer> values;
    Set<PackageP2P> notDownloadedPackages;
    int numberStep;
    boolean isLeader;

    public DeviceP2p(int idNumberDevice) {
        this.idNumberDevice = idNumberDevice;
        values = new TreeMap<>();
        notDownloadedPackages = new TreeSet<>();
        numberStep = 0;
    }
}

class PackageP2P {
    int idNumberPackage;
    Set<DeviceP2p> processedDevices;

    public PackageP2P(int idNumberPackage) {
        this.idNumberPackage = idNumberPackage;
        processedDevices = new HashSet<>();
    }

}