/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2018/2/18
 * @history
 */
public class ManacherTest {



    static String init(String s){
        char[] c = s.toCharArray();
        StringBuffer buffer = new StringBuffer("$");
        for (int i = 0; i < s.length(); i++){
            buffer.append("#").append(c[i]);
        }
        return buffer.append("#@").toString();
    }

    public static void main(String[] args) {
        String s = "a";
        System.out.println(Macacher(s));
    }

    static int Macacher(String s){
        s = init(s);
        int id = 0;
        int mx = 0;
        int maxLen = 1;
        String maxStr = null;
        int len = s.length();
        int p[] = new int[len];

        for (int i = 1; i < len - 1; i++){
            if (i < mx){
                p[i] = Math.min(p[2 * id - i], mx -i);
            }else{
                p[i] = 1;
            }
            while (s.charAt(i - p[i]) == s.charAt(i + p[i])){
                p[i]++;
            }
            if (mx < i + p[i]){
                id = i;
                mx = i + p[i];
            }
            maxLen = Math.max(maxLen, p[i] - 1);
            maxStr = maxLen > p[i] - 1 ? maxStr : s.substring(i - p[i] + 1, i + p[i]);
        }
        maxStr = maxStr.replace("#","");
        System.out.println(maxStr);
        return maxLen;
    }


}
