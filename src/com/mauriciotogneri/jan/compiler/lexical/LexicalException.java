package com.mauriciotogneri.jan.compiler.lexical;

public class LexicalException extends RuntimeException
{
    public LexicalException(Character character, int line, int column)
    {
        super("Invalid character '" + character.getCharacter() + "' at: [" + line + ", " + column + "]");
    }

    public LexicalException(char character, int line, int column)
    {
        super("Illegal character '" + character + "' (" + ((int) character) + ") at: [" + line + ", " + column + "]");
    }
}