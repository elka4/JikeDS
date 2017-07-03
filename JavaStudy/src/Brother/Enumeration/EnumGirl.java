package Brother.Enumeration;

/**4个心动女生枚举
 * Created by tzh on 1/21/17.
 */
public enum EnumGirl {
   // Girl1, Girl2, Girl3, Girl4;//如果继续写代码，要有分号
   Girl1("梦梦"), Girl2("全彩"), Girl3("萌萌"), Girl4("空空");//如果继续写代码，要有分号

    private String name;//姓名

    //构造方法必须私有
    private EnumGirl(){
        System.out.println("无参构造方法");
    }

    private EnumGirl(String name){
        this.name = name;
        System.out.println("带参构造方法");
    }




}
