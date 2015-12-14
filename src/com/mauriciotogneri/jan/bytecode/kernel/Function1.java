package com.mauriciotogneri.jan.bytecode.kernel;

public interface Function1<A, Z>
{
    Z call(A a);

    String toString();
}