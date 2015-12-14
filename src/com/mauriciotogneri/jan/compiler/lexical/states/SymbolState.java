package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SymbolState extends State
{
    public SymbolState(@NotNull List<Token> tokens, @NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        addCharacter(character);
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        if (character.isLetter() || character.isDigit() || (character == Character.UNDERSCORE))
        {
            addCharacter(character);

            return this;
        }
        else if (character.isDelimiter())
        {
            return createToken(character, Type.SYMBOL, cursorPosition);
        }
        else
        {
            throw new LexicalException(character, cursorPosition);
        }
    }
}