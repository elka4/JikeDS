package top100.HighFreq._1Linear;


import java.io.*;
import java.util.*;
import javax.script.*;

public class _38Calculater3 {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("input.txt"));

        ScriptEngine cal = (new ScriptEngineManager()).getEngineByName("JavaScript");

        String equation = in.nextLine();

        while(equation.equals("END") == false){
            //System.out.println(cal.eval("(" + equation + ")"));
            equation = in.nextLine();
        }
        in.close();
    }
}
