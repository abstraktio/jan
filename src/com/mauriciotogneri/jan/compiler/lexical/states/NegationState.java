package com.mauriciotogneri.jan.compiler.lexical.states;

import java.util.List;
import com.mauriciotogneri.jan.compiler.lexical.LexicalException;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;

public class NegationState extends State
{
	public NegationState(List<Token> tokens, int line, int column)
	{
		super(tokens, line, column);
		
		addCharacter(EXCLAMATION);
	}
	
	@Override
	public State process(char character, int line, int column)
	{
		if (character == EQUAL)
		{
			return new NotEqualState(getTokens(), line, column);
		}
		else if (isDelimiter(character))
		{
			return createToken(character, Type.LOGIC_NEGATION, line, column);
		}
		else
		{
			throw new LexicalException(character, line, column);
		}
	}
}