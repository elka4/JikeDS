package java8_2;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
