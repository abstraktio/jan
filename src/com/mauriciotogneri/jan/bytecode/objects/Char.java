package com.mauriciotogneri.jan.bytecode.objects;

public class Char
{
    private final char value;

    public Char(char value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        else if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Char aChar = (Char) o;

        return value == aChar.value;

    }

    @Override
    public int hashCode()
    {
        return (int) value;
    }
}