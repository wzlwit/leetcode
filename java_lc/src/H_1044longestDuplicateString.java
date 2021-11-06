import java.util.HashSet;

public class H_1044longestDuplicateString {
    public static String longestDupSubstring1(String s) {
        int strLen = s.length();
        int halfLen = strLen / 2;
        String longest = "";
        String subStr = "";

        for (int i = halfLen; i >= 1; i--) {   //check substring of length = i
            HashSet<String> hs = new HashSet();
            for (int j = 0; j <= strLen - i; j++) {   //end point to check
                subStr = s.substring(j, j + i);
                if (hs.contains(subStr)) {
                    if (subStr.length() > longest.length()) longest = subStr;
                    break;
                } else hs.add(subStr);
            }
        }

        return longest;
    }

    public static void diagonalSearch(String s, int[][] mtrx, int hPoint, int vPoint, int[] lsd) {
        for (int i = hPoint, j = vPoint; i < lsd[2] && j < lsd[2]; i++, j++) {
            if (lsd[2] - vPoint >= lsd[1] && lsd[2] - hPoint >= lsd[1]) {
                mtrx[i][j] = s.charAt(i) == s.charAt(j) ? (mtrx[i - 1][j - 1] + 1) : 0;

                if (mtrx[i][j] > lsd[1]) {
                    lsd[1] = mtrx[i][j];
                    lsd[0] = i;    // or lsd[0] = j, both works; or  = i>j? i:j
                }
            }
        }
    }


    public static String longestDupSubstring(String s) {
        int sLen = s.length();
        int[] lds = {-1, 0, sLen};   // {index, dupLength, strLength
        int[][] matrix = new int[sLen][sLen];

        //1. populate the first row and col of the matrix
        for (int i = 1; i < lds[2]; i++) {
            if (s.charAt(0) == s.charAt(i)) {
                matrix[0][i] = 1;
                if (lds[1] < 1) {
                    lds[1] = 1;
                    lds[0] = i;
                }
//            matrix[i][0] = matrix[0][i];          // half triangle
            }
        }

        //2. start point of diagonal search
        for (int i = sLen-1; i >1; i--) {
            diagonalSearch(s, matrix, 1, i, lds);
//            diagonalSearch(s, matrix, i, 1, lds);     // checking half triangle is enough
        }

//        for (int val : lds) System.out.println(val);
        return s.substring(lds[0] - lds[1] + 1, lds[0] + 1);
    }


    public static void main(String[] args) {
        long milliStart = System.currentTimeMillis();

//        System.out.println(longestDupSubstring("banana"));
        System.out.println(longestDupSubstring("Dynamic Programming can solve many problems, but that does not mean there isn’t a more efficient solution out there. Solving a problem with Dynamic Programming feels like magic, but remember that dynamic programming is merely a clever brute force. Sometimes it pays off well, and sometimes it helps only a little."));

        System.out.println("Time used in milli: " + (System.currentTimeMillis() - milliStart));
    }
}
