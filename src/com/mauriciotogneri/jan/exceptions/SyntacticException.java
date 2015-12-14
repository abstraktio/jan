package com.mauriciotogneri.jan.exceptions;

import com.mauriciotogneri.jan.compiler.lexical.Token;

public class SyntacticException extends RuntimeException
{
    public SyntacticException(String message, Token token)
    {
        super(message + " '" + token + "' at: [" + token.cursorPosition.line + ", " + token.cursorPosition.column + "]");
    }

    public SyntacticException(String message, int line, int column)
    {
        super(message + " at: [" + line + ", " + column + "]");
    }

    public SyntacticException(Token token)
    {
        super("Invalid token '" + token + "' at: [" + token.cursorPosition.line + ", " + token.cursorPosition.column + "]");
    }
}