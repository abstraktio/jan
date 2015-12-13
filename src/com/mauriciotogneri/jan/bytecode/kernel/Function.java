package com.mauriciotogneri.jan.bytecode.kernel;

public interface Function<A, Z>
{
    Z call(A a);
}