package com.mauriciotogneri.jan.bytecode.kernel;

import com.mauriciotogneri.jan.bytecode.objects.Bool;

public abstract class Constant<T> implements Function0<T>
{
    public final Bool isEqual(Constant<T> object)
    {
        return Bool.create(object.call().equals(call()));
    }

    public final Bool isNotEqual(Constant<T> object)
    {
        return Bool.create(!object.call().equals(call()));
    }

    public abstract String toString();

    public abstract boolean equals(Object o);
}