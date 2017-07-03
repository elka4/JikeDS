package Brother.Enumeration;

/**
 * Created by tzh on 1/21/17.
 */
public enum ColorExample {
    RED(10), BLUE(10);
    private ColorExample(){}
    private int color;
    private ColorExample(int color){
        this.color = color;
    }
}
