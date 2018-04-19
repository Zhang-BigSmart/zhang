import java.util.ArrayList;
import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/11/26
 * @history
 */
public class OOMTest {

    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException{
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        fillHeap(1000);
    }

}


