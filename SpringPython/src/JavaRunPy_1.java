

/**
 * Created by tzh on 1/29/17.
 */
public class JavaRunPy_1 {
    public static void main(String[] args) {
        try {
            Process p = Runtime.getRuntime().
                    exec("/Users/tzh/IdeaProjects/JikeDS/SpringPython/src/testSpringPython.py");
/*等待调用返回*/p.exitValue();
            p.waitFor();
/*打印调用执行结果*/
            System.out.println(p.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public void test02(){
        PythonInterpreter interpreter = new PythonInterpreter();
        try {
            interpreter.execfile(
                    "E:\\project\\python\\example.py");
            PyInteger  res = (PyInteger)interpreter.get("Result");
            System.out.println("command return value = " + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
