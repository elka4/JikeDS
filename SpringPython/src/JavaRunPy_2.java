/**
 * Created by tzh on 1/29/17.
 */
import org.python.util.PythonInterpreter;
import org.python.core.PyInteger;
import org.python.*;

public class JavaRunPy_2 {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        try {
            interpreter.execfile(
                    "/Users/tzh/IdeaProjects/JikeDS/SpringPython/src/testSpringPython.py");
            PyInteger  res = (PyInteger)interpreter.get("Result");
            System.out.println("command return value = " + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
