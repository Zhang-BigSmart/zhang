import org.junit.Test;

import java.util.BitSet;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/11/22
 * @history
 */
public class BitMapTest {

    int numSize = 10000;

    int arraySize = (int) Math.ceil(numSize/32);

    private int array[] = new int[arraySize];

    public void initBitMap(){
        for (int i=0; i<array.length; i++){
            array[i]=0;
        }
    }

    /**
     * pos >> 5 确定该数对应的数组的下标   比如i=20，通过i>>SHIFT=20>>5=0 可求得i=20的下标为0
     * pos % 32 被置为1的位置
     * @param pos
     */
    public void set(int pos){  //i & MASK相当于i % (MASK-1)
        array[ pos >> 5] |= (1 << (pos % 32));
    }

    /**
     * array[pos >> 5] 该数所在的数组
     * array[pos >> 5] 的1 << (pos % 32) 为 1
     * array[pos >> 5] & (1 << (pos % 32)),这样是1返回true，否侧返回false
     * @param pos
     * @return
     */
    public int get(int pos){
        return array[pos >> 5] & (1 << (pos % 32));
    }


    /*int getT(int i){
        if (!Boolean.parseBoolean(get(i)+"") && !Boolean.parseBoolean(get(i+1)+"")){
            return 0;
        }else if(!Boolean.parseBoolean(get(i)+"") && Boolean.parseBoolean(get(i+1)+"")){
            return 1;
        }else if(Boolean.parseBoolean(get(i)+"") && !Boolean.parseBoolean(get(i+1)+"")){
            return 2;
        }else{
            return 3;
        }
    }*/

    /*public void setT(int i){
        *//**
         * 检测对应位的当前值
         *//*
        int t = getT(i);
        switch (t){
            case 0: set(i+1);break;
            case 1: set(i);break;
            case 2: break;
        }
    }*/



    public static void main(String[] args) {
        BitMapTest test = new BitMapTest();
        test.initBitMap();
        int[] a = {1, 4, 32, 2, 6, 9};
        for (int i=0; i<a.length; i++){
            test.set(a[i]);
        }
        for (int i=0; i<test.numSize; i++){
            if (test.get(i) != 0)
                System.out.println(i + " ");
        }
    }




}
