package sort;

import java.util.Arrays;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/28
 * @history
 */
public class SortTest {

    /**
     * 快速排序法
     */
    public void quickSort(int[]a, int left, int right){
        int i,j,t,temp;
        if(left>right)return;
        temp=a[left];
        i=left;j=right;
        while(i!=j){
            while(a[j]>=temp && i<j){ //右边第一个小于判断数(temp)的数
                j--;
            }
            while(a[i]<=temp && i<j){ //左边第一个小于判断数(temp)的数
                i++;
            }
            if (i<j){  //交换两个数位置
                t=a[i];
                a[i]=a[j];
                a[j]=t;
            }
        }
        //此时i=j,与判断数交换位置
        a[left] = a[i];
        a[i] = temp;
        quickSort(a,left,i-1);
        quickSort(a,i+1,right);
    }

    /**
     * 冒泡排序优化
     * @param a
     * @param n
     */
    public void BubbleSort(int[] a, int n){
        for(int i=0; i<n-1; i++){ //最多n-1次排序
            boolean flag = false;
            for(int j=0; j<n-i-1; j++){
                if (a[j] < a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    /**
     * 插入排序
     * @param a
     * @param n
     */
    public void InsertSort(int[] a, int n){
        int i, j, temp;
        for (i=0; i<n-1; i++){
            temp = a[i+1];
            j = i;
            while(j>-1 && temp<a[j]){
                a[j+1]=a[j];
                j--;
            }
            a[j+1] = temp;
        }
    }


    public static void main(String[] args) {
        int[] a = {1,5,4,3,2,};
        SortTest sortTest = new SortTest();
        //sortTest.quickSort(a,0,a.length-1);
        sortTest.BubbleSort(a,a.length);
        System.out.println(Arrays.toString(a));
    }

}
