import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/9/12
 * @history
 */
public class NIOTest {

    @Test
    public void nio1() throws IOException {
        RandomAccessFile afile = new RandomAccessFile("C:\\Users\\Edison\\Desktop\\nioTest.txt","rw");
        FileChannel inChannel = afile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int byteRead = inChannel.read(buf);
        while(byteRead != -1){
            System.out.println("Read" + byteRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.println(buf.get());
            }
            buf.clear();
            byteRead = inChannel.read(buf);
        }
        afile.close();
    }
}
