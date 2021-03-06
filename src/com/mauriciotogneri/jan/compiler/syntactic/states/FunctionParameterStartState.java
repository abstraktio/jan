package com.mauriciotogneri.jan.compiler.syntactic.states;

import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.compiler.syntactic.State;
import com.mauriciotogneri.jan.compiler.syntactic.SyntacticException;
import com.mauriciotogneri.jan.kernel.Function;
import com.mauriciotogneri.jan.kernel.Program;

public class FunctionParameterStartState extends State
{
	private final Function function;
	
	public FunctionParameterStartState(Program program, Function function)
	{
		super(program);
		
		this.function = function;
	}
	
	@Override
	public State process(Token token)
	{
		if (token.type.isSeparator())
		{
			return this;
		}
		else if (token.type == Type.NEW_LINE)
		{
			return new FunctionDefinedState(getProgram(), function);
		}
		else if (token.type == Type.SYMBOL)
		{
			function.addParameter(token.lexeme);
			
			return new FunctionParameterEndState(getProgram(), function);
		}
		else
		{
			throw new SyntacticException(token);
		}
	}
}