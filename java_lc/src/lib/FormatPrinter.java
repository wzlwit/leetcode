package lib;

public class FormatPrinter {
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

        strLen = str.length();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(str.charAt(i * size + j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        bitToMatrix(131);
        bitToMatrix(131, true);
    }
}
