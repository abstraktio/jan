package com.mauriciotogneri.jan.kernel.nodes.array;

import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.kernel.Context;
import com.mauriciotogneri.jan.kernel.Value;
import com.mauriciotogneri.jan.kernel.nodes.PrimitiveNode;

import java.math.BigDecimal;
import java.util.List;

public class IndexNode extends PrimitiveNode
{
	public IndexNode(Token token)
	{
		super(token, 2);
	}
	
	@Override
	public Value evaluate(Context context)
	{
		Value operand1 = get(0, context);
		Value operand2 = get(1, context);
		
		if (operand1.isNumber() && operand2.isList())
		{
			BigDecimal value1 = operand1.getNumber();
			List<Value> value2 = operand2.getList();
			
			return value2.get(value1.intValue());
		}
		else if (operand1.isNumber() && operand2.isString())
		{
			BigDecimal value1 = operand1.getNumber();
			String value2 = operand2.getString();
			
			return Value.asString(String.valueOf(value2.charAt(value1.intValue())));
		}
		
		// TODO: explain more
		throw new RuntimeException("Cannot perform operation '" + token.lexeme + "' at: [" + token.line + ", " + token.column + "]");
	}
}