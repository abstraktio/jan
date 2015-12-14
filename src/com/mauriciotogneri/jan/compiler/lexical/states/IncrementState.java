package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IncrementState extends State
{
    public IncrementState(@NotNull List<Token> tokens, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        setLexeme(Character.PLUS.toString() + Character.PLUS.toString());
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        if (character.isDelimiter())
        {
            return createToken(character, Type.ARITHMETIC_INCREMENT, cursorPosition);
        }
        else
        {
            throw new LexicalException(character, cursorPosition);
        }
    }
}