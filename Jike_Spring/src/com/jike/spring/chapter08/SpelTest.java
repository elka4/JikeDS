package com.jike.spring.chapter08;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = 
				parser.parseExpression("('Hello' + 'World').concat(#end)");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("end", "!");
		System.out.println(expression.getValue(context));

	}

}
