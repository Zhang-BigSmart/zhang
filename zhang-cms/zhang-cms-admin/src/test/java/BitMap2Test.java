import org.junit.Test;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/11/23
 * @history
 */
public class BitMap2Test {

    private static int MASK = 5;

    //保存数据
    private int[] bits;

    private int capacity;

    public void init(int capacity){
        this.capacity = capacity;
        bits = new int[(capacity >> MASK) + 1];
    }

    public void set(int i){
        int index = i >> MASK;
        int pos = i & 31;
        bits[index] |= 1 << pos;
    }

    public int get(int i){
        int index = i >> MASK;
        int pos = i & 31;
        return bits[index] & (1 << pos);
    }

    public void clear(int i){
        int index = i >> MASK;
        int pos = i & 31;
        //将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.
        bits[index] &= ~(1 << pos);
    }

    public static void main(String[] args) {
        BitMap2Test test = new BitMap2Test();
        test.init(100);
        int[] a = {1, 4, 32, 2, 6, 9};
        for (int i=0; i<a.length; i++){
            test.set(a[i]);
        }
        for (int i=0; i<test.capacity; i++){
            if (test.get(i) != 0)
                System.out.println(i + " ");
        }
    }

    @Test
    public void TBitMap(){
        BitMap2Test test = new BitMap2Test();
        test.init(100000);
        int[] a = {2, 2, 6, 6, 2, 3};
        for (int i=0; i<a.length; i++){
            test.setT(a[i]);
        }/*
        for (int i = 0; i < a.length; i++){
            if (test.getT(a[i]) == )
                System.out.println(a[i] + " ");
        }*/
        /*test.setT(6);
        test.setT(6);
        test.setT(32);*/
        System.out.println(test.getT(2));
        System.out.println(test.getT(6));
        System.out.println(test.getT(32));
        System.out.println(test.getT(1));
        System.out.println(test.getT(3));
    }

    int getT(int i){
        boolean i1 = get(i) != 0;
        boolean i2 = get(i+1) != 0;
        if (!i1 && !i2){
            return 0;
        }else if(!i1 && i2){
            return 1;
        }else if(i1 && !i2){
            return 2;
        }else{
            return 3;
        }
    }

    public void setT(int i){
        /**
         * 检测对应位的当前值
         */
        int t = getT(i);
        switch (t){
            case 0: set(i+1);break;
            case 1: set(i);clear(i+1);break;
            case 2: break;
        }
    }




}
