package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StringState extends State
{
    public StringState(@NotNull List<Token> tokens, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);
    }

    public StringState(@NotNull List<Token> tokens, @NotNull String lexeme, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        setLexeme(lexeme);
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        if (character == Character.DOUBLE_QUOTES)
        {
            return new StringEndState(getTokens(), getLexeme(), cursorPosition);
        }
        else if (character == Character.BACK_SLASH)
        {
            addCharacter(character);

            return new StringEscapeState(getTokens(), getLexeme(), cursorPosition);
        }
        else if (character.isNewLine())
        {
            throw new LexicalException(character, cursorPosition);
        }
        else
        {
            addCharacter(character);

            return this;
        }
    }
}