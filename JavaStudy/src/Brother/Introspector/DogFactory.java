package Brother.Introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**工厂类
 * Created by tzh on 1/21/17.
 */
public class DogFactory {
    private static Properties config = new Properties();
    static {
        //读取属性文件
        InputStream inStream = Thread.currentThread().getContextClassLoader().
                getResourceAsStream(" Brother/Introspector/bean.properties");//包名 Brother/Introspector/
        //加载属性文件
        try {
            config.load(inStream);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dog getDog(String name){
/*        if ("dog".equals(name)) {
            return new Dog();
        }*/
        //从Properties对象中根据key获取value
        String className = config.getProperty(name);
        try {
            //根据类全名获取类信息对象（Class对象)
            Class dogClass = Class.forName(className);
            //实例化对象
            Dog dog = (Dog)dogClass.newInstance();

            //开始使用内省了
            //获取bean信息
            BeanInfo beanInfo = Introspector.getBeanInfo(dogClass);
            //通过bean信息获取所有属性描述器，数组
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            //循环遍历描述器
            for (int i = 0; i < pds.length; i++) {
                if ("name".equals(pds[i].getName())){
                    String nameValue = config.getProperty("dog.name");
                    //通过属性描述器获取该属性的写操作方法（set方法）
                    Method method =pds[i].getWriteMethod();
                    //在dog对象上调用方法
                    method.invoke(dog, nameValue);
                } else if ("age".equals(pds[i].getName())) {
                    String ageValue = config.getProperty("dog.age");
                    Method method =pds[i].getWriteMethod();
                    method.invoke(dog, Integer.parseInt(ageValue));
                }
            }
            return dog;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
