import java.util.Arrays;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2018/2/22
 * @history
 */
public class ZigZagTest {

    public String convert(String s, int numRows){
        int len = s.length();
        String[] ret = new String[numRows];
        Arrays.fill(ret, "");
        int direct = 1;
        int row = 0;
        for (int i = 0; i < len; i++){
            ret[row] += s.charAt(i);
            row += direct;
            if (row >= numRows){
               direct = -1;
               row = row -2;
            }
            if (row <= 0){
                direct = 1;
                row = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numRows; j ++){
            sb.append(ret[j]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        ZigZagTest test = new ZigZagTest();
        System.out.println(test.convert3(s,numRows));
    }


    public String convert1(String s, int nRows) {
        int len = s.length();
        if (len == 0 || nRows <= 1) return s;

        String[] ans = new String[nRows];
        Arrays.fill(ans, "");
        int row = 0, delta = 1;
        for (int i = 0; i < len; i++) {
            ans[row] += s.charAt(i);
            row += delta;
            if (row >= nRows) {
                row = nRows-2;
                delta = -1;
            }
            if (row < 0) {
                row = 1;
                delta = 1;
            }
        }

        String ret = "";
        for (int i = 0; i < nRows; i++) {
            ret += ans[i];
        }
        return ret;
    }

    public String convert3(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }

}
