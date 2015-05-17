package com.mauriciotogneri.jan.compiler.syntactic.states;

import com.mauriciotogneri.jan.compiler.definitions.ProgramDefinition;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.compiler.syntactic.State;
import com.mauriciotogneri.jan.compiler.syntactic.SyntacticException;

public class ImportEndState extends State
{
	private final String path;
	
	public ImportEndState(ProgramDefinition program, String path)
	{
		super(program);
		
		this.path = path;
	}
	
	@Override
	public State process(Token token)
	{
		if (token.type == Type.NEW_LINE)
		{
			addImport(this.path);
			
			return new InitialState(getProgram());
		}
		else
		{
			throw new SyntacticException(token);
		}
	}
}