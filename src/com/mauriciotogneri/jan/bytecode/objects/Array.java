package com.mauriciotogneri.jan.bytecode.objects;

import com.mauriciotogneri.jan.bytecode.kernel.Constant;

public class Array<T> implements Constant<Array<T>>
{
    private final Object[] data;

    protected Array()
    {
        this.data = new Object[0];
    }

    protected Array(int size)
    {
        this.data = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public Array(T... data)
    {
        this.data = clone(data);
    }

    @SuppressWarnings("unchecked")
    public T get(int index)
    {
        return (T) data[index];
    }

    public Num length()
    {
        return Num.create(data.length);
    }

    public Array<T> concatenateAfter(T element)
    {
        Array<T> result = new Array<>(data.length + 1);

        for (int i = 0; i < data.length; i++)
        {
            result.data[i] = clone(data[i]);
        }

        result.data[data.length] = element;

        return result;
    }

    public Array<T> concatenateBefore(T element)
    {
        Array<T> result = new Array<>(data.length + 1);
        result.data[0] = element;

        for (int i = 0; i < data.length; i++)
        {
            result.data[i + 1] = clone(data[i]);
        }

        return result;
    }

    public Array<T> remove(int index)
    {
        Array<T> result = new Array<>(data.length - 1);

        int position = 0;

        for (int i = 0; i < data.length; i++)
        {
            if (i != index)
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
        return Bool.create(isEquivalent(object));
    }

    @Override
    public Bool isNotEqual(Constant<Array<T>> object)
    {
        return Bool.create(!isEquivalent(object));
    }

    private boolean isEquivalent(Constant<Array<T>> object)
    {
        return false; // TODO
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

            if (object instanceof Constant)
            {
                Constant c = (Constant) object;
                builder.append(c.call());
            }
            else
            {
                builder.append(object);
            }
        }

        builder.append("]");

        return builder.toString();
    }

    @Override
    public Array<T> call()
    {
        return this;
    }
}