package java8_1;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
