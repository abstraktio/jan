package com.mauriciotogneri.jan.compiler.lexical.states;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;
import com.mauriciotogneri.jan.compiler.lexical.State;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InitialState extends State
{
    public InitialState(@NotNull List<Token> tokens, @NotNull CursorPosition cursorPosition)
    {
        super(tokens, cursorPosition);
    }

    @Override
    @NotNull
    public State process(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        State state = this;

        if (character.isLetter())
        {
            state = new SymbolState(getTokens(), character, cursorPosition);
        }
        else if (character.isDigit())
        {
            state = new IntegerState(getTokens(), character, cursorPosition);
        }
        else
        {
            switch (character)
            {
                case SPACE:
                case TAB:
                case NEW_LINE:
                case CARRIAGE_RETURN:
                    addCharacter(character);
                    addToken(character.getDelimiterType(cursorPosition));
                    break;

                case PLUS:
                    state = new AddState(getTokens(), cursorPosition);
                    break;

                case MINUS:
                    state = new SubtractState(getTokens(), cursorPosition);
                    break;

                case DOUBLE_QUOTES:
                    state = new StringState(getTokens(), cursorPosition);
                    break;

                case EXCLAMATION:
                    state = new NegationState(getTokens(), cursorPosition);
                    break;

                case GREATER:
                    state = new GreaterState(getTokens(), cursorPosition);
                    break;

                case LESS:
                    state = new LessState(getTokens(), cursorPosition);
                    break;

                case QUESTION:
                    state = new IfState(getTokens(), cursorPosition);
                    break;

                case SEMICOLON:
                    state = new CommentState(getTokens(), cursorPosition);
                    break;

                case STAR:
                case SLASH:
                case SHARP:
                case CARET:
                case PERCENT:
                case EQUAL:
                case AMPERSAND:
                case VERTICAL_BAR:
                case DOLLAR:
                case BACK_SLASH:
                case OPEN_BRACKETS:
                case CLOSE_BRACKETS:
                case AT:
                case DOT:
                case COLON:
                case TILDE:

                    // case COMMA:
                    // case APOSTROPHE:
                    // case UNDERSCORE:
                    // case GRAVE_ACCENT:
                    // case OPEN_PARENTHESES:
                    // case CLOSE_PARENTHESES:
                    // case OPEN_BRACES:
                    // case CLOSE_BRACES:

                    state = new OperatorState(getTokens(), character, cursorPosition);
                    break;

                default:
                    throw new LexicalException(character, cursorPosition);
            }
        }

        return state;
    }
}