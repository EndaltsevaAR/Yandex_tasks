package yandex.lecture._02LinearSearch;
/*
Description:
Дана строка (возможно, пустая), состоящая из букв A-Z:
AAAABBBCCXYZDDD...
Нужно написать функцию RLE, которая на выходе даст строку вида:
A4B3C2XYZD3 и сгенерирует ошибку, если на вход пришла невалидная строка.
Если символ встречается 1 раз, цифра не ставится после нее, если символ
встречается более 1 раза подряд, после буквы ставится количество повторений
 */

public class RLE {
    public String rle(String line) {
        if (line == null || line.length() == 0) {
            System.out.println("Error");
            System.exit(0);
        }
        char lastLetter = line.charAt(0);
        int lastPosition = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isLetter(line.charAt(i))) {
                System.out.println("Part of word is not letter");
                System.exit(0);
            }
            if (line.charAt(i) != lastLetter) {
                stringBuilder.append(lastLetter);
                if (i - lastPosition > 1) {
                    stringBuilder.append(i - lastPosition);
                }
                lastPosition = i;
                lastLetter = line.charAt(i);
            }
        }
        stringBuilder.append(line.charAt(lastPosition));
        if (line.length() - lastPosition > 1) {
            stringBuilder.append(line.length() - lastPosition);
        }
        return stringBuilder.toString();
    }
}
