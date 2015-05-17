package com.mauriciotogneri.jan.compiler.semantic.nodes.conditional;

import com.mauriciotogneri.jan.compiler.definitions.ProgramDefinition;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.semantic.nodes.PrimitiveNode;
import com.mauriciotogneri.jan.kernel.Value;
import com.mauriciotogneri.jan.kernel.symbols.Context;

public class IfNode extends PrimitiveNode
{
	public IfNode(Token token)
	{
		super(token, 2);
	}
	
	@Override
	public Value evaluate(ProgramDefinition program, Context context)
	{
		Value operand1 = get(0, program, context);
		Value operand2 = get(1, program, context);
		
		if (operand1.isBoolean())
		{
			Boolean value = operand1.getBoolean();
			
			return (value) ? operand2 : null;
		}
		
		// TODO: explain more
		throw new RuntimeException("Cannot perform operation '" + this.token.lexeme + "' at: [" + this.token.line + ", " + this.token.column + "]");
	}
}