package com.mauriciotogneri.jan.bytecode.objects;

import com.mauriciotogneri.jan.bytecode.kernel.Constant;

public class Bool implements Constant<Bool>
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
    public Bool isEqual(Constant<Bool> object)
    {
        return Bool.create(object.call().equals(this));
    }

    @Override
    public Bool isNotEqual(Constant<Bool> object)
    {
        return Bool.create(!object.call().equals(this));
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

        return (value == bool.value);

    }

    @Override
    public int hashCode()
    {
        return (value ? 1 : 0);
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