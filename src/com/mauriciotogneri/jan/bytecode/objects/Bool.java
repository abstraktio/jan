package com.mauriciotogneri.jan.bytecode.objects;

public class Bool
{
    private final boolean value;

    public Bool(boolean value)
    {
        this.value = value;
    }

    public Bool negate()
    {
        return new Bool(!value);
    }

    public Bool and(Bool bool)
    {
        return new Bool(this.value && bool.value);
    }

    public Bool or(Bool bool)
    {
        return new Bool(this.value || bool.value);
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

        Bool bool = (Bool) o;

        return value == bool.value;

    }

    @Override
    public int hashCode()
    {
        return (value ? 1 : 0);
    }
}