package com.mauriciotogneri.jan.compiler.lexical;

import org.jetbrains.annotations.NotNull;

public class Token
{
    @NotNull
    public final Type type;
    @NotNull
    public final String lexeme;
    @NotNull
    public final CursorPosition cursorPosition;

    public enum Type
    {
        // general
        SYMBOL, //
        IMPORT, //
        ANONYMOUS_FUNCTION, //

        // delimiters
        SPACE, //
        TAB, //
        NEW_LINE, //

        // literals
        INTEGER, //
        FLOAT, //
        STRING, //
        BOOLEAN, //

        // arithmetic
        ARITHMETIC_ADDITION, //
        ARITHMETIC_SUBTRACTION, //
        ARITHMETIC_MULTIPLICATION, //
        ARITHMETIC_DIVISION, //
        ARITHMETIC_POWER, //
        ARITHMETIC_MODULE, //
        ARITHMETIC_INCREMENT, //
        ARITHMETIC_DECREMENT, //

        // logic
        LOGIC_EQUAL, //
        LOGIC_NOT_EQUAL, //
        LOGIC_GREATER, //
        LOGIC_GREATER_EQUAL, //
        LOGIC_LESS, //
        LOGIC_LESS_EQUAL, //
        LOGIC_AND, //
        LOGIC_OR, //
        LOGIC_NEGATION, //

        // conditional
        CONDITIONAL_IF, //

        // arrays (lists and strings)
        ARRAY_INDEX, //
        ARRAY_REMOVE, //
        ARRAY_LENGTH, //

        // lists
        LIST_OPEN, //
        LIST_CLOSE;

        public boolean isSeparator()
        {
            return (this == SPACE) || (this == TAB);
        }

        public boolean isLiteral()
        {
            return (this == INTEGER) || //
                    (this == FLOAT) || //
                    (this == STRING) || //
                    (this == BOOLEAN);
        }

        private boolean isArithmeticToken()
        {
            return (this == ARITHMETIC_ADDITION) || //
                    (this == ARITHMETIC_SUBTRACTION) || //
                    (this == ARITHMETIC_MULTIPLICATION) || //
                    (this == ARITHMETIC_DIVISION) || //
                    (this == ARITHMETIC_POWER) || //
                    (this == ARITHMETIC_MODULE) || //
                    (this == ARITHMETIC_INCREMENT) || //
                    (this == ARITHMETIC_DECREMENT);
        }

        private boolean isLogicToken()
        {
            return (this == LOGIC_EQUAL) || //
                    (this == LOGIC_NOT_EQUAL) || //
                    (this == LOGIC_GREATER) || //
                    (this == LOGIC_GREATER_EQUAL) || //
                    (this == LOGIC_LESS) || //
                    (this == LOGIC_LESS_EQUAL) || //
                    (this == LOGIC_AND) || //
                    (this == LOGIC_OR) || //
                    (this == LOGIC_NEGATION);
        }

        private boolean isConditionalToken()
        {
            return (this == CONDITIONAL_IF);
        }

        private boolean isArrayToken()
        {
            return (this == ARRAY_INDEX) || //
                    (this == ARRAY_REMOVE) || //
                    (this == ARRAY_LENGTH);
        }

        private boolean isListToken()
        {
            return (this == LIST_OPEN) || //
                    (this == LIST_CLOSE);
        }

        public boolean isPrimitive()
        {
            return isConditionalToken() || //
                    isArithmeticToken() || //
                    isLogicToken() || //
                    isArrayToken() || //
                    isListToken();
        }

        public boolean isExpressionToken()
        {
            return (this == SYMBOL) || //
                    isConditionalToken() || //
                    isLiteral() || //
                    isArithmeticToken() || //
                    isLogicToken() || //
                    isArrayToken() || //
                    isListToken();
        }
    }

    public Token(@NotNull Type type, @NotNull String lexeme, @NotNull CursorPosition cursorPosition)
    {
        this.lexeme = lexeme;
        this.type = type;
        this.cursorPosition = cursorPosition;
    }

    @Override
    @NotNull
    public String toString()
    {
        return lexeme;
    }
}