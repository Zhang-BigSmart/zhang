import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/11/1
 * @history
 */
public class DelayQueue2Test {
}

class Student2 implements Runnable, Delayed {

    private String name;
    public long workTime;
    private long submitTime;
    private boolean isForce = false;

    public Student2() {
    }

    public Student2(String name, long workTime) {
        this.name = name;
        this.workTime = workTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(workTime, TimeUnit.NANOSECONDS) + System.nanoTime();// 纳秒级别
    }

    @Override
    public int compareTo(Delayed o) {
        // TODO Auto-generated method stub
        if (o == null || !(o instanceof Student2))
            return 1;
        if (o == this)
            return 0;
        Student2 s = (Student2) o;
        if (this.workTime > s.workTime) {
            return 1;
        } else if (this.workTime == s.workTime) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // TODO Auto-generated method stub
        return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (isForce) {
            System.out.println(name + " 交卷，实际用时 120分钟");
        } else {
            System.out.println(name + " 交卷," + "实际用时 " + workTime + " 分钟");
        }
    }

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean isForce) {
        this.isForce = isForce;
    }

}

class Teacher2 implements Runnable{

    private int counter = 20;
    private DelayQueue<Student2> students = new DelayQueue<>();

    public Teacher2(DelayQueue<Student2> students) {
        this.students = students;
    }

    @Override
    public void run() {
        try {
            System.out.println(" test start");
            while (counter > 0) {
                Student2 student = students.poll();
                if (student.workTime<120) {
                    student.run();
                    if (counter > 0) {
                        counter--;
                    }
                } else {
                    System.out.println(" 考试时间到，全部交卷！");
                    Student2 tmpStudent;
                    for (Iterator<Student2> iterator2 = students.iterator(); iterator2.hasNext();) {
                        tmpStudent = iterator2.next();
                        tmpStudent.setForce(true);
                        tmpStudent.run();
                        if (counter > 0) {
                            counter--;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}