package com.mauriciotogneri.jan.compiler.lexical;

import com.mauriciotogneri.jan.compiler.lexical.states.InitialState;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StateMachine
{
    private CursorPosition cursorPosition = new CursorPosition(1, 0);

    @NotNull
    public List<Token> getTokens(@NotNull char[] characters)
    {
        List<Token> tokens = new ArrayList<>();
        State state = new InitialState(tokens, cursorPosition);

        for (char chr : characters)
        {
            Character character = Character.fromChar(chr, cursorPosition);

            if (character == Character.NEW_LINE)
            {
                cursorPosition = cursorPosition.newLine();
            }

            if (character == Character.TAB)
            {
                cursorPosition = cursorPosition.tab();
            }

            if (!character.isNewLine())
            {
                cursorPosition = cursorPosition.space();
            }

            state = state.process(character, cursorPosition);
        }

        state.process(Character.NEW_LINE, cursorPosition);

        return tokens;
    }
}