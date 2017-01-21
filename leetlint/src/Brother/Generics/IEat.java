package Brother.Generics;

/**使用泛型定义接口
 * Created by tzh on 1/21/17.
 */
public interface IEat<T> {
    public void eat(T food);

}

class IEatImpl<T> implements IEat<T>{

    @Override
    public void eat(T food) {

    }


}


