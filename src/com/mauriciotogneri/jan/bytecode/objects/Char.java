package com.mauriciotogneri.jan.bytecode.objects;

import com.mauriciotogneri.jan.bytecode.Test.Constant;

public class Char
{
    private final char value;

    private Char(char value)
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

    public static Char create(char value)
    {
        return new Char(value);
    }

    public static Constant<Char> asConstant(final char value)
    {
        return new Constant<Char>()
        {
            @Override
            public Char call()
            {
                return new Char(value);
            }
        };
    }
}