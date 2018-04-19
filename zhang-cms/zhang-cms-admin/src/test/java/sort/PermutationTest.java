package sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 字典排序
 * @date 2017/8/31
 * @history
 */
public class PermutationTest {

    public ArrayList<String> Permutation(String str){
        ArrayList<String> list = new ArrayList<String>();
        char[] ch = str.toCharArray();
        Permu(ch, 0, list);
        Collections.sort(list);
        return list;
    }

    public void Permu(char[] str, int i, ArrayList<String> list){
        if (str == null)
            return;
        if (i == str.length - 1){
            if (list.contains(String.valueOf(str))){
                return;
            }
            list.add(String.valueOf(str));
        }else{
            boolean num = true;
            for (int j = i; j < str.length; j++){
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;

                Permu(str, i + 1, list);

                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        String s = "abc";
        PermutationTest test = new PermutationTest();
        ArrayList<String> list = test.Permutation(s);
        list.forEach(string-> System.out.println(string));
        //System.out.println(list.toString());
    }


}
