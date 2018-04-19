import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/11/26
 * @history
 */
public class ThreadTest {

    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true);
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void createLcokThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object o = new Object();
        createLcokThread(o);
    }



    public void isContinuous(int[] numbers){
        if (numbers.length != 5) return;
        int min = 14;
        int max = -1;
        int flag = 0;
        for (int i=0; i < numbers.length; i++){
            int number = numbers[i];
            if (number < 0 || number > 13) return;
            if (number ==0) continue;
            if(((flag >> number) & 1) == 1) return;
            flag |= (1 << number);
            if (number > max) max = number;
            if (number < min) min = number;
            if (max - min >=5) return;
        }
    }

    @Test
    public void finalTest(){
        System.out.println(finallTest());
    }


    public int finallTest(){
        int x ;
        try{
            x = 1;
            return x;
        }catch (Exception e){
            x = 2;
            return x;
        }finally{
            x = 3;
        }
    }

}
