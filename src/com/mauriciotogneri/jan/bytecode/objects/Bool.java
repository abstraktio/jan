package com.mauriciotogneri.jan.bytecode.objects;

import com.mauriciotogneri.jan.bytecode.kernel.Eq;
import com.mauriciotogneri.jan.bytecode.kernel.Function0;

public final class Bool implements Function0<Bool>, Eq
{
    private final boolean value;

    public static final Bool TRUE = new Bool(true);
    public static final Bool FALSE = new Bool(false);

    private Bool(boolean value)
    {
        this.value = value;
    }

    public Bool and(Bool bool)
    {
        return Bool.create(this.value && bool.value);
    }

    public Bool or(Bool bool)
    {
        return Bool.create(this.value || bool.value);
    }

    public Bool neg()
    {
        return Bool.create(!value);
    }

    public boolean isTrue()
    {
        return value;
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

        Bool other = (Bool) o;

        return (value == other.value);

    }

    public static Bool create(boolean value)
    {
        return (value) ? TRUE : FALSE;
    }

    @Override
    public Bool call()
    {
        return this;
    }
}