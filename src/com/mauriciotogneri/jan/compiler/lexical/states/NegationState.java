package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NegationState extends State
{
    public NegationState(@NotNull List<Token> tokens, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        addCharacter(Character.EXCLAMATION);
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        if (character == Character.EQUAL)
        {
            return new NotEqualState(getTokens(), cursorPosition);
        }
        else if (character.isDelimiter())
        {
            return createToken(character, Type.LOGIC_NEGATION, cursorPosition);
        }
        else
        {
            throw new LexicalException(character, cursorPosition);
        }
    }
}