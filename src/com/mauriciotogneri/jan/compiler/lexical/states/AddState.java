package com.mauriciotogneri.jan.compiler.lexical.states;

import java.util.List;
import com.mauriciotogneri.jan.compiler.lexical.LexicalException;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;

public class AddState extends State
{
	public AddState(List<Token> tokens, int line, int column)
	{
		super(tokens, line, column);
		
		addCharacter(PLUS);
	}
	
	@Override
	public State process(char character, int line, int column)
	{
		State state = this;
		
		if (isDigit(character))
		{
			// addToken(tokens, Type.ARITHMETIC_ADD);
			// state = new IntegerState(this.automata, line, column, character);
		}
		else if (isLetter(character))
		{
			// addToken(tokens, Type.ARITHMETIC_ADD);
			// state = new IdentifierState(this.automata, line, column, character);
		}
		else
		{
			throw new LexicalException(line, column);
		}
		
		return state;
	}
}