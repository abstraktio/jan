package com.mauriciotogneri.jan.exceptions;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.CursorPosition;

import org.jetbrains.annotations.NotNull;

public class LexicalException extends RuntimeException
{
    public LexicalException(@NotNull Character character, @NotNull CursorPosition cursorPosition)
    {
        super("Invalid character '" + character + "' at: [" + cursorPosition.line + ", " + cursorPosition.column + "]");
    }

    public LexicalException(char character, @NotNull CursorPosition cursorPosition)
    {
        super("Illegal character '" + character + "' (" + ((int) character) + ") at: [" + cursorPosition.line + ", " + cursorPosition.column + "]");
    }
}