package Brother.Annotation;

import java.lang.annotation.*;

/**自定义一个注解
 * Created by tzh on 1/21/17.
 */
@Retention(value = RetentionPolicy.RUNTIME)
//@Retention(RetentionPolicy.SOURCE)
//元注解
//元数据
//要使用反射来让注解起作用
//@Retention(RetentionPolicy.CLASS)
@Documented   //可以在生成doc文档的时候添加注解

//表示一个Annotation的使用范围，没有就是可以用在任意范围
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
//@Target(ElementType.)
//@Target(ElementType.TYPE)
//@Target(ElementType.TYPE)

@Inherited//表示一个Annotation是否允许被其自子类继承下来


public @interface MyAnnotation {
   // public String name();//定义一个属性
   // public  String info();//定义一个属性
    //属性不赋值就会报错

    public String name() default "XB";//定义一个属性
    public  String info() default "WSDR";//定义一个属性， 并指定默认值

    public  String[] like();//定义一个数组

    public  EnumSex sex();//定义一个枚举变量

}
