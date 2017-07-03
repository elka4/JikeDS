package Brother.Reflection.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**动态代理类
 * Created by tzh on 1/21/17.
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;//被代理对象
    public DynamicProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object obj = null;
        before();
        obj = method.invoke(target, args);//正在调用业务方法
        after();
        return null;
    }

    //相亲之前要做的事情

    private void before(){
        System.out.println("为代理人匹配如意郎君");
    }
    //相亲之后要做的事情
    private  void after() {
        System.out.println("本次相亲结束");
    }

}
