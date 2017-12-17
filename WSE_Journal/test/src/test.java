import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.Date;

public class test {

    @Test
    public void test01(){
        Date nowdate = new Date();

        long now = nowdate.getTime();

        System.out.println("nowdate: " + nowdate);
        System.out.println("now: " + now);

        System.out.println(new Date(now));

    }

    @Test
    public void test02() throws Exception{
        String currentPath = new File(System.getProperty("user.dir")).getPath();
        System.out.println("currentPath: " + currentPath);
        //currentPath: /Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/test
    }

//https://stackoverflow.com/questions/23564070/delete-line-with-bufferedreader

    @Test
    public void deleteline_1() throws Exception{
        List<String> file = new ArrayList();

        BufferedReader in = new BufferedReader(new FileReader("./src/text.txt"));
        String data = null;
        try {
            while ((data = in.readLine()) != null) {
                String[] args = data.split("\\s");

                if (args[0].equals("yes")) {
                    file.add(data);
                } else {
                    //skip this line
                }
            }
            in.close();

            //Add to file
            FileWriter fw = new FileWriter("./src/text.txt");
            BufferedWriter out = new BufferedWriter(fw);
            for (String line : file) {
                out.write(line + "\n");
            }
            out.flush();
            out.close();

        } catch (Exception e){

        }
    }


    @Test
    public void deletelines2() throws Exception{
        File file = new File("./src/text.txt");
//        List<String> lines = FileUtils.readLines(file);
        List<String> lines = FileUtils.readLines(file, "UTF-8");

        for (int i = 0; i < lines.size(); i++)
            if (! lines.get(i).split("\\s")[0].equals("yes"))
                lines.remove(i--);

        org.apache.commons.io.FileUtils.writeLines(file, lines);
    }


    @Test
    public void deletelines3() throws Exception{
        File file = new File("./src/cache2.txt");
//        List<String> lines = FileUtils.readLines(file);
        List<String> lines = FileUtils.readLines(file, "UTF-8");

        for (int i = 0; i < lines.size(); i++){
            String[] parts = lines.get(i).split("\\s");
            long currentTime = new Date().getTime();
            long modifiedTime = Long.parseLong(parts[0]);
            long longestPeriod = getLongestTimeRange();
            if (currentTime - modifiedTime > longestPeriod){
                lines.remove(i--);
            }
        }
        org.apache.commons.io.FileUtils.writeLines(file, lines);
        org.apache.commons.io.FileUtils.writeLines(file, lines);
    }


    private long getLongestTimeRange(){

        return 2 * 7 * 24 * 60 * 60 * 1000;
    }


    @Test
    public void test(){
        String name = "1     9 d " +
                " " +
                "\n";
        String[] strArr = name.split("\\s+");

        for (String s:strArr) {
            System.out.print(s + ",");
        }
    }

}
