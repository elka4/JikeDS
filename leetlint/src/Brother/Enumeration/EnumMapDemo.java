package Brother.Enumeration;

import org.junit.Test;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by tzh on 1/21/17.
 */
public class EnumMapDemo {


    @Test
    public void test01(){
        //性能更好
        EnumMap<EnumController, String> map =
                new EnumMap<EnumController, String>(EnumController.class);

        map.put(EnumController.ON, "打开");
        map.put(EnumController.OFF, "关闭");
        Set<EnumController> keySet = map.keySet();


        for (EnumController enumController : keySet) {
            System.out.println(enumController + "--" + map.get(enumController));
        }
    }
}
