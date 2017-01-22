package Brother.Enumeration;

/**用类的方法模拟枚举的实现
 * Created by tzh on 1/21/17.
 */
public abstract class Controller {
    private Controller(){}
//匿名内部类
    public static final Controller ON = new Controller(){

        @Override
        public Controller downAction() {
            return OFF;
        }
        @Override
        public String toString() {
            return "ON";
        }
    };
    public static final Controller OFF = new Controller(){

        @Override
        public Controller downAction() {
            return ON;
        }
        @Override
        public String toString() {
            return "OFF";
        }
    };

    public abstract Controller downAction();


}
