package com.mauriciotogneri.jan.bytecode.objects;

import com.mauriciotogneri.jan.bytecode.kernel.Constant;
import com.mauriciotogneri.jan.bytecode.kernel.Function0;

import java.util.Arrays;

public class Array<T> implements Constant<Array<T>>
{
    private final Object[] data;

    private Array(int size)
    {
        this.data = new Object[size];
    }

    @SuppressWarnings("unchecked")
    private Array(T... data)
    {
        this.data = clone(data);
    }

    @SuppressWarnings("unchecked")
    public T get(Num index)
    {
        return (T) data[(int) index.get()];
    }

    public Num length()
    {
        return Num.create(data.length);
    }

    public Array<T> addAfter(T element)
    {
        Array<T> result = new Array<>(data.length + 1);

        for (int i = 0; i < data.length; i++)
        {
            result.data[i] = clone(data[i]);
        }

        result.data[data.length] = element;

        return result;
    }

    public Array<T> addBefore(T element)
    {
        Array<T> result = new Array<>(data.length + 1);
        result.data[0] = element;

        for (int i = 0; i < data.length; i++)
        {
            result.data[i + 1] = clone(data[i]);
        }

        return result;
    }

    public Array<T> remove(Num index)
    {
        Array<T> result = new Array<>(data.length - 1);

        int position = 0;
        int indexValue = (int) index.get();

        for (int i = 0; i < data.length; i++)
        {
            if (i != indexValue)
            {
                result.data[position++] = clone(data[i]);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private <P, L> P clone(final P object)
    {
        if (object instanceof Array)
        {
            Array<L> list = (Array<L>) object;
            Array result = new Array(list.length());

            for (int i = 0; i < list.data.length; i++)
            {
                result.data[i] = clone(list.data[i]);
            }

            return (P) result;
        }
        else
        {
            return object;
        }
    }

    @Override
    public Bool isEqual(Constant<Array<T>> object)
    {
        return Bool.create(object.call().equals(this));
    }

    @Override
    public Bool isNotEqual(Constant<Array<T>> object)
    {
        return Bool.create(!object.call().equals(this));
    }

    @Override
    @SuppressWarnings("unchecked")
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

        Array<T> array = (Array<T>) o;

        if (array.data.length == this.data.length)
        {
            for (int i = 0; i < data.length; i++)
            {
                T e1 = (T) data[i];
                T e2 = (T) array.data[i];

                if (!e1.equals(e2))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (Object object : data)
        {
            if (builder.length() != 1)
            {
                builder.append(", ");
            }

            if (object instanceof Function0)
            {
                Function0 constant = (Function0) object;
                builder.append(constant.call());
            }
            else
            {
                builder.append(object);
            }
        }

        builder.append("]");

        return builder.toString();
    }

    public static <T> Array<T> create(T... data)
    {
        return new Array<>(data);
    }

    @Override
    public Array<T> call()
    {
        return this;
    }
}