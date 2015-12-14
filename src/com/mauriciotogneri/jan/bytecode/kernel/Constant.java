package com.mauriciotogneri.jan.bytecode.kernel;

import com.mauriciotogneri.jan.bytecode.objects.Bool;

public interface Constant<T> extends Function0<T>
{
    Bool isEqual(Constant<T> t);

    Bool isNotEqual(Constant<T> t);

    String toString();

    boolean equals(Object o);
}