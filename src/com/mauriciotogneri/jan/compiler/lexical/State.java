package com.mauriciotogneri.jan.compiler.lexical;

import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.compiler.lexical.states.InitialState;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class State
{
    @NotNull
    private final CursorPosition cursorPosition;
    @NotNull
    private final StringBuilder lexeme = new StringBuilder();
    @NotNull
    private final List<Token> tokens;

    protected State(@NotNull List<Token> tokens, @NotNull CursorPosition cursorPosition)
    {
        this.tokens = tokens;
        this.cursorPosition = cursorPosition;
    }

    @NotNull
    protected CursorPosition getCursorPosition()
    {
        return cursorPosition;
    }

    protected void addCharacter(@NotNull Character character)
    {
        lexeme.append(character.toString());
    }

    protected void setLexeme(@NotNull String newLexeme)
    {
        lexeme.setLength(0);
        lexeme.append(newLexeme);
    }

    @NotNull
    protected String getLexeme()
    {
        return lexeme.toString();
    }

    @NotNull
    protected List<Token> getTokens()
    {
        return tokens;
    }

    private void addToken(@NotNull Type type, @NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        tokens.add(new Token(type, character.toString(), cursorPosition));
    }

    protected void addToken(@NotNull Type type)
    {
        tokens.add(new Token(type, getLexeme(), cursorPosition));
    }

    @NotNull
    protected State createToken(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        Token.Type delimiterType = character.getDelimiterType(cursorPosition);
        addToken(delimiterType, character, cursorPosition);

        return new InitialState(tokens, cursorPosition);
    }

    @NotNull
    protected State createToken(@NotNull Character character, @NotNull Type type, @NotNull CursorPosition cursorPosition)
    {
        addToken(type);

        Token.Type delimiterType = character.getDelimiterType(cursorPosition);
        addToken(delimiterType, character, cursorPosition);

        return new InitialState(tokens, cursorPosition);
    }

    public abstract State process(Character character, CursorPosition cursorPosition);
}