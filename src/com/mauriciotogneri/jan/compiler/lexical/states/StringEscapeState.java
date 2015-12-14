package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StringEscapeState extends State
{
    public StringEscapeState(@NotNull List<Token> tokens, @NotNull String lexeme, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        setLexeme(lexeme);
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        if (character.isNewLine())
        {
            throw new LexicalException(character, cursorPosition);
        }
        else
        {
            addCharacter(character);

            return new StringState(getTokens(), getLexeme(), cursorPosition);
        }
    }
}