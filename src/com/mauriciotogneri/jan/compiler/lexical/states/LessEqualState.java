package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.LexicalException;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;

import java.util.List;

public class LessEqualState extends State
{
    public LessEqualState(List<Token> tokens, int line, int column)
    {
        super(tokens, line, column);

        setLexeme(Character.LESS.toString() + Character.EQUAL.toString());
    }

    @Override
    public State process(Character character, int line, int column)
    {
        if (character.isDelimiter())
        {
            return createToken(character, Type.LOGIC_LESS_EQUAL, line, column);
        }
        else
        {
            throw new LexicalException(character, line, column);
        }
    }
}