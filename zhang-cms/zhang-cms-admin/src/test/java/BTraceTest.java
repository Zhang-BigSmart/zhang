import java.util.Random;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/11/28
 * @history
 */
public class BTraceTest {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        Counter counter = new Counter();
        while(true){
            counter.add(random.nextInt(10));
            Thread.sleep(1000);
        }

    }


}

class Counter{

    private static int totalCount = 0;

    public int add(int num) throws InterruptedException {
        totalCount += num;
        Thread.sleep(1000);
        return totalCount;
    }
}
