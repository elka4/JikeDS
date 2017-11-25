package _JAVA;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
//http://www.baeldung.com/java-write-to-file
public class IO {
    //http://www.baeldung.com/java-write-to-file
    //char: The char data type is a single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).

    //2. Write with BufferedWriter


    @Test
    public void test08() throws IOException {


//        String str = "test08";
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/tianhuizhu/Downloads/uber/code/JikeDS/J9Ch/src/_JAVA/test08.txt", true));
//        writer.append(' ');
//        writer.append(str);



        int count = 0;
        for (char i = '\u0000'; i < '\uffff' ; i++) {
//            System.out.print((char) i + ", ");
            writer.append((char) i + ", ");
            count++;
        }
        writer.append("count: " + count);
        System.out.println("count: " + count);
        writer.close();
    }//count: 65535

//-----------------------------------------------------------------------------
    //3. Write with PrintWriter


    @Test
    public void test09() throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/tianhuizhu/Downloads/uber/code/JikeDS/J9Ch/src/_JAVA/test09.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int count = 0;
        for (char i = '\u0000'; i < '\uffff' ; i++) {
//            System.out.print((char) i + ", ");
            printWriter.print((char) i + ", ");
            count++;
        }
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", count);
        printWriter.close();
    }
//-----------------------------------------------------------------------------
    //4. Write with FileOutputStream


    @Test
    public void test10() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("/Users/tianhuizhu/Downloads/uber/code/JikeDS/J9Ch/src/_JAVA/test10.txt");

        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (char i = '\u0000'; i < '\uffff' ; i++) {
            //            System.out.print((char) i + ", ");
            sb.append((char) i + ", ");
//            printWriter.print((char) i + ", ");
            count++;
        }
        byte[] strToBytes = sb.toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();

    }

    //-----------------------------------------------------------------------------
    //5. Write with DataOutputStream
    //Next – let’s take a look at how we can use a DataOutputStream to write a String to file:
    @Test
    public void whenWritingToSpecificPositionInFile_thenCorrect()
            throws IOException {

        String fileName = "";
        String fileName2 = "";
        int data1 = 2014;
        int data2 = 1500;

        writeToPosition(fileName, data1, 4);
        assertEquals(data1, readFromPosition(fileName, 4));

        writeToPosition(fileName2, data2, 4);
        assertEquals(data2, readFromPosition(fileName, 4));
    }

    private int readFromPosition(String filename, long position)
            throws IOException {
        int result = 0;
        RandomAccessFile reader = new RandomAccessFile(filename, "r");
        reader.seek(position);
        result = reader.readInt();
        reader.close();
        return result;
    }

    private void writeToPosition(String filename, int data, long position)
            throws IOException {
        RandomAccessFile writer = new RandomAccessFile(filename, "rw");
        writer.seek(position);
        writer.writeInt(data);
        writer.close();
    }
//-----------------------------------------------------------------------------
    //6. Write with RandomAccessFile

    /*
    Let’s now illustrate how to write and edit inside an existing file – rather than just writing to a completely new file or appending to an existing one. Simply put – we need random access.

RandomAccessFile enable us to write at a specific position in the file given the offset – from the beginning of the file – in bytes. The following code writes an integer value with offset given from the beginning of the file:
     */
    @Test
    public void test12(String filename, int data, long position) throws IOException {
        RandomAccessFile writer = new RandomAccessFile(filename, "rw");
        writer.seek(position);
        writer.writeInt(data);
        writer.close();
    }

//-----------------------------------------------------------------------------
    //7. Write with FileChannel

    @Test
    public void givenWritingToFile_whenUsingFileChannel_thenCorrect()
            throws IOException {
        String fileName = "";
        RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
        FileChannel channel = stream.getChannel();
        String value = "Hello";
        byte[] strBytes = value.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();

        // verify
        RandomAccessFile reader = new RandomAccessFile(fileName, "r");
        assertEquals(value, reader.readLine());
        reader.close();
    }

//-----------------------------------------------------------------------------
String fileName = "";
    //8. Write to file using Java 7
@Test
public void givenUsingJava7_whenWritingToFile_thenCorrect()
        throws IOException {
    String str = "Hello";

    Path path = Paths.get(fileName);
    byte[] strToBytes = str.getBytes();

    Files.write(path, strToBytes);

    String read = Files.readAllLines(path).get(0);
    assertEquals(str, read);
}

//-----------------------------------------------------------------------------
    //9. Write to temporary file
@Test
public void whenWriteToTmpFile_thenCorrect() throws IOException {
    String toWrite = "Hello";
    File tmpFile = File.createTempFile("test", ".tmp");
    FileWriter writer = new FileWriter(tmpFile);
    writer.write(toWrite);
    writer.close();

    BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
    assertEquals(toWrite, reader.readLine());
    reader.close();
}

//-----------------------------------------------------------------------------
    //10. Lock File Before Writing

    @Test
    public void whenTryToLockFile_thenItShouldBeLocked()
            throws IOException {
        RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
        FileChannel channel = stream.getChannel();

        FileLock lock = null;
        try {
            lock = channel.tryLock();
        } catch (final OverlappingFileLockException e) {
            stream.close();
            channel.close();
        }
        stream.writeChars("test lock");
        lock.release();

        stream.close();
        channel.close();
    }
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
}
