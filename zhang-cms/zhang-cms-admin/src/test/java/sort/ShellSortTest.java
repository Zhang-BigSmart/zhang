package sort;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/9/2
 * @history
 */
public class ShellSortTest {

    public void shellsort1(int a[], int n){
        int grap, i, j, temp;
        // 每次为原来的一半
        for (grap = n/2; grap > 0; grap /= 2){
            for (i = grap; i < n; i++){
                for (j = i - grap; j >= 0 && a[j]>a[j+grap]; j -=grap){
                    temp = a[j];
                    a[j] = a[j+grap];
                    a[j+grap] = temp;
                }
            }
        }
    }


    public void sehllsort2(int[] a, int n, int[] d, int numOFD){
        int i, j, k, m, span, temp;
        for (m = 0; m < numOFD; m ++){
            span = d[m];
            for (k = 0; k < span; k++){
                for (i = k; i<n-span; i = i+span){
                    temp = a[i+span];
                    j = i;
                    while(j>-1 && temp < a[j]){
                        a[j+span] = a[j];
                        j = j-span;
                    }
                    a[j+span] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = " ";
        System.out.println(s.length());
        System.out.println(ReverseSentence(s));
    }

    public static String ReverseSentence(String str) {
        if (str.trim().equals("")) return str;
        String[] words = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i=words.length-1; i >=0 ; i--)
            sb.append(words[i] + " ");
        return sb.substring(0,sb.length()-1);
    }
}
