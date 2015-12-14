package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IntegerState extends State
{
    public IntegerState(@NotNull List<Token> tokens, @NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        addCharacter(character);
    }

    public IntegerState(@NotNull List<Token> tokens, @NotNull String lexeme, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        setLexeme(lexeme);
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        if (character.isDigit())
        {
            addCharacter(character);

            return this;
        }
        else if (character == Character.DOT)
        {
            addCharacter(Character.DOT);

            return new FloatState(getTokens(), getLexeme(), getCursorPosition());
        }
        else if (character.isDelimiter())
        {
            return createToken(character, Type.INTEGER, cursorPosition);
        }
        else
        {
            throw new LexicalException(character, cursorPosition);
        }
    }
}