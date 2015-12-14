package com.mauriciotogneri.jan.compiler.lexical;

public class CursorPosition
{
    public final int line;
    public final int column;

    public CursorPosition(int line, int column)
    {
        this.line = line;
        this.column = column;
    }

    public CursorPosition newLine()
    {
        return new CursorPosition(line + 1, 0);
    }

    public CursorPosition tab()
    {
        return new CursorPosition(line, column + 3);
    }

    public CursorPosition space()
    {
        return new CursorPosition(line, column + 1);
    }
}