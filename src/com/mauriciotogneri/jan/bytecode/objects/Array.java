package com.mauriciotogneri.jan.bytecode.objects;

import java.util.Arrays;

public class Array<T>
{
    private final Object[] data;

    public Array()
    {
        this.data = new Object[0];
    }

    public Array(int size)
    {
        this.data = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public Array(T... data)
    {
        this.data = clone(data);
    }

    @SuppressWarnings("unchecked")
    public T index(int index)
    {
        return (T) data[index];
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

    public int length()
    {
        return data.length;
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

    public Array<T> cloneArray()
    {
        return clone(this);
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
    public String toString()
    {
        return Arrays.toString(data);
    }
}