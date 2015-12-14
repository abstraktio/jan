package com.mauriciotogneri.jan.exceptions;

import com.mauriciotogneri.jan.compiler.lexical.Token;

public class SemanticException extends RuntimeException
{
    public SemanticException(String message, Token token)
    {
        super(message + " '" + token + "' at: [" + token.cursorPosition.line + ", " + token.cursorPosition.column + "]");
    }

    public SemanticException(String message)
    {
        super(message);
    }
}