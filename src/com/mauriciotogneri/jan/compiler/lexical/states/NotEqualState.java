package com.mauriciotogneri.jan.compiler.lexical.states;

import java.util.List;
import com.mauriciotogneri.jan.compiler.lexical.LexicalException;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;

public class NotEqualState extends State
{
	public NotEqualState(List<Token> tokens, int line, int column)
	{
		super(tokens, line, column);
		
		setLexeme(String.valueOf(EXCLAMATION) + String.valueOf(EQUAL));
	}
	
	@Override
	public State process(char character, int line, int column)
	{
		if (isDelimiter(character))
		{
			return createToken(character, Type.LOGIC_NOT_EQUAL, line, column);
		}
		else
		{
			throw new LexicalException(character, line, column);
		}
	}
}