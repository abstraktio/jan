package com.mauriciotogneri.jan.bytecode.objects;

import com.mauriciotogneri.jan.bytecode.kernel.Constant;

public final class Char extends Constant<Char>
{
    protected final char value;

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

        return (value == aChar.value);

    }

    public static Char create(char value)
    {
        return new Char(value);
    }

    @Override
    public Char call()
    {
        return this;
    }
}