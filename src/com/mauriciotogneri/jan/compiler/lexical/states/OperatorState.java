package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OperatorState extends State
{
    public OperatorState(@NotNull List<Token> tokens, @NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);

        addCharacter(character);
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        if (character.isDelimiter())
        {
            Character operatorCharacter = Character.fromChar(getLexeme().charAt(0), cursorPosition);

            return createToken(character, operatorCharacter.getOperatorType(cursorPosition), cursorPosition);
        }
        else
        {
            throw new LexicalException(character, cursorPosition);
        }
    }
}