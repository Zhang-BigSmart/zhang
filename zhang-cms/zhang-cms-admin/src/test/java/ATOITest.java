import org.apache.commons.lang.StringUtils;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2018/2/25
 * @history
 */
public class ATOITest {

    public int atoi(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        str = str.trim();

        int positive = 1;
        int i = 0;
        if ('+' == (str.charAt(0))){
            i++;
        }else if ('-' == (str.charAt(0))){
            positive = -1;
            i++;
        }

        double temp = 0;
        for (; i < str.length(); i++){
            int j = str.charAt(i) - '0';
            if (j < 0 || j > 9) break;

            temp = temp*10 + j*positive;
            if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE){
                return temp > Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
        return (int) temp;
    }

    public static void main(String[] args) {
        String s = "+1";
        ATOITest atoiTest = new ATOITest();
        System.out.println(atoiTest.atoi(s));
    }
}
