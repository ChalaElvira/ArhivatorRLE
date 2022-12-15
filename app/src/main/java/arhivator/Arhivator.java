package arhivator;

public class Arhivator {
    public static String arhivate(String data) {
        StringBuilder resultData = new StringBuilder();
        char[] str = data.toCharArray();
        for (int i = 0; i < str.length; i++) {
            int count = 1;
            while (i < str.length - 1 && str[i] == str[i + 1]) {
                count++;
                i++;
            }
            resultData.append(str[i]);
            resultData.append(count);
        }
        return resultData.toString();
    }

    public static String dearhivate(String data) {
        char[] tempData = data.toCharArray();
        StringBuilder tempRes = new StringBuilder();
        for (int i = 0; i < tempData.length - 1; i++) {
            int number = 0;
            char currentSymbol = tempData[i];
            while (i < tempData.length - 1 && Character.isDigit(tempData[i + 1])) {
                number = number * 10 + Character.digit(tempData[i + 1], 10);
                i++;
            }
            tempRes.append(String.valueOf(currentSymbol).repeat(Math.max(0, number)));
        }
        return tempRes.toString();
    }
}
