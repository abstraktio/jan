package com.mauriciotogneri.jan.compiler.lexical.states;

import java.util.List;
import com.mauriciotogneri.jan.compiler.lexical.LexicalException;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;

public class FloatState extends State
{
	public FloatState(List<Token> tokens, String lexeme, int line, int column)
	{
		super(tokens, line, column);
		
		setLexeme(lexeme);
	}
	
	@Override
	public State process(char character, int line, int column)
	{
		State state = this;
		
		if (isDigit(character))
		{
			addCharacter(character);
		}
		else if (isDelimiter(character))
		{
			state = createToken(character, Type.INTEGER, line, column);
		}
		else
		{
			throw new LexicalException(line, column);
		}
		
		return state;
	}
}