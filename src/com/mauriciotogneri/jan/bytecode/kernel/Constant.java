package com.mauriciotogneri.jan.bytecode.kernel;

import com.mauriciotogneri.jan.bytecode.objects.Bool;

public interface Constant<T>
{
    T call();

    Bool isEqual(Constant<T> t);

    Bool isNotEqual(Constant<T> t);
}