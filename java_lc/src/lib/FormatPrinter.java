package lib;

public class FormatPrinter {

    private static void printMatrix(String str,int colSize) {
        int strLen = str.length();
        int rowSize = (int) Math.ceil(strLen * 1.0 / colSize);
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                System.out.print(str.charAt(i * rowSize + j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void bitToMatrix(int num, Boolean... reverse) {
        StringBuilder str = new StringBuilder(Integer.toBinaryString(num));
        int strLen = str.length();
        int size = (int) Math.ceil(Math.sqrt(strLen));

//        fill high numbers with 0
        for (int i = strLen; i < size * size; i++) {
            str = str.insert(0, '0');
//            str = "0" + str;
        }

//        from Low to High, Right to Left
        if (reverse.length > 0 && reverse[0]) {
            str.reverse();
        }

        printMatrix(str.toString(), size);
    }

    public static void bitToMatrix(int num, int colSize, Boolean... reverse) {
        StringBuilder str = new StringBuilder(Integer.toBinaryString(num));
        int strLen = str.length();
        int rowSize = (int) Math.ceil(strLen * 1.0 / colSize);

//        fill high numbers with 0
        for (int i = strLen; i < rowSize * colSize; i++) {
            str = str.insert(0, '0');
//            str = "0" + str;
        }

//        from Low to High, Right to Left
        if (reverse.length > 0 && reverse[0]) {
            str.reverse();
        }

        printMatrix(str.toString(),colSize);
    }

    public static void main(String[] args) {
//        bitToMatrix(256);
        bitToMatrix(512, true);
        bitToMatrix(512, 4, true);
    }
}
