package _19_JieShiZhe_interpreter.cls;

import java.util.HashMap;

public class MultiExpresstion extends SymbolExpresstion {

	public MultiExpresstion(AbstractExpresstion _left,
			AbstractExpresstion _right) {
		super(_left, _right);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Float _19_JieShiZhe_interpreter(HashMap<String, Float> var) {
		// TODO Auto-generated method stub
		return super.left._19_JieShiZhe_interpreter(var) * super.right._19_JieShiZhe_interpreter(var);
	}

}
