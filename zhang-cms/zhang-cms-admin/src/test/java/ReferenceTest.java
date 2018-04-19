import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * @author Edison
 * @ClassName:
 * @Desc: 强 软 弱 虚-引用
 * @date 2017/11/3
 * @history
 */
public class ReferenceTest {

    public static void main(String[] args) {
        Person Person = new Person(1,2);

        // 软引用-内存不足才回收
        System.out.println("--软引用--");
        SoftReference<Person> softRef = new SoftReference(Person);
        Person r1 = softRef.get();
        System.out.println(r1.getX());

        // 弱引用-垃圾回收时不管内存是否充足，都会被回收
        System.out.println("--弱引用--");
        WeakReference<Person> weakRef = new WeakReference(new Person(3,3));
        System.out.println(weakRef.get().getX());
        //System.gc();
        System.out.println(weakRef.get().getX());

        // 如果软弱引用同时存在强引用，不会被回收

        // 虚引用
        System.out.println("--虚引用--");
        ReferenceQueue<Person> queue = new ReferenceQueue<>();
        PhantomReference<Person> phaRef = new PhantomReference<Person>(new Person(6,7),queue);
        System.out.println(phaRef.get().getX());
    }




}


class Person{

    private int x,y;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
