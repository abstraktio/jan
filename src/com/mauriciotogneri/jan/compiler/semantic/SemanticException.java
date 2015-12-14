package com.mauriciotogneri.jan.compiler.semantic;

import com.mauriciotogneri.jan.compiler.lexical.Token;

public class SemanticException extends RuntimeException
{
    public SemanticException(String message, Token token)
    {
        super(message + " '" + token + "' at: [" + token.line + ", " + token.column + "]");
    }

    public SemanticException(String message)
    {
        super(message);
    }
}