import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.*;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/10/21
 * @history
 */
public class IOTest {

    /**字节流提供readLine()方法
     * readLine()每次读取一行,返回为null时到达文件末尾
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readLine(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s = in.readLine()) != null)
            sb.append(s);
        in.close();
        return sb.toString();
    }

    /**
     * read()以int形式返回
     * @param filename
     * @return
     */
    public static void read(String filename){
        byte[] buffer=new byte[512];   //一次取出的字节数大小,缓冲区大小
        int numberRead=0;
        FileInputStream input=null;
        FileOutputStream out =null;
        try {
            input=new FileInputStream("D:/David/Java/java 高级进阶/files/tiger.jpg");
            out=new FileOutputStream("D:/David/Java/java 高级进阶/files/tiger2.jpg"); //如果文件不存在会自动创建

            while ((numberRead=input.read(buffer))!=-1) {  //numberRead的目的在于防止最后一次读取的字节小于buffer长度，
                out.write(buffer, 0, numberRead);       //否则会自动被填充0
            }
        } catch (final IOException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        }finally{
            try {
                input.close();
                out.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }

        }
    }

    /**
     * nio
     * 对于只读访问，必须显示地使用静态allocate()方法分配
     */
    private static final int BSIZE = 1024;
    public void channel(String filename) throws IOException {
        FileChannel fc = new FileOutputStream(filename).getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();
        fc = new RandomAccessFile(filename,"rw").getChannel();
        fc.position(fc.size()); // Move to end
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();
        fc = new FileInputStream(filename).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        while (buff.hasRemaining())
            System.out.println((char)buff.get());
    }

    /**
     * filp()读准备，clear()写准备
     * @param inname
     * @param outname
     * @throws IOException
     */
    public void channelCopy(String inname, String outname) throws IOException {
        FileChannel in = new FileInputStream(inname).getChannel(),
        out = new FileOutputStream(outname).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        while (in.read(buff) != -1){
            buff.flip();   // Prepare for writing
            out.write(buff);
            buff.clear();  // Prepare for reading
        }
    }

    /**
     * 通道之间直接传输
     * @param inname
     * @param outname
     * @throws IOException
     */
    public void transferTo(String inname, String outname) throws IOException {
        FileChannel in = new FileInputStream(inname).getChannel(),
                out = new FileOutputStream(outname).getChannel();
        in.transferTo(0,in.position(),out);
        // or
        // out.transferFrom(in,0,in.size());
    }

    /**
     * GZIP压缩，对单个数据流比较合适
     * @param filename
     * @throws IOException
     */
    public void gizp(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        BufferedOutputStream out = new BufferedOutputStream(
                new GZIPOutputStream(new FileOutputStream("test.gz")));
        System.out.println("Wiriting file");
        int c;
        while ((c = in.read()) != -1)
            out.write(c);
        in.close();
        out.close();
        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(
                        new GZIPInputStream(
                            new FileInputStream("test.gz"))));
        String s;
        while ((s = in2.readLine()) != null)
            System.out.println(s);
    }

    public void ZipCompress(String filename) throws IOException {
        FileOutputStream f = new FileOutputStream("test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        System.out.println("Writing file" + filename);
        BufferedReader in = new BufferedReader(new FileReader(filename));
        zos.putNextEntry(new ZipEntry(filename));
        int c;
        while ((c = in.read()) != -1)
            out.write(c);
        in.close();
        out.close();

        //checksum valid only after the file has been closed
        System.out.println("checksum:" + csum.getChecksum().getValue());
        // now extract the files
        System.out.println("Reading file");
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csum1 = new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csum1);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) !=null)
            System.out.println("reading file"+ ze);
            int x;
            while ((x = bis.read()) != -1)
                System.out.write(x);

    }

}
