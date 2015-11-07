package com.mauriciotogneri.jan.bytecode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Runtime
{
    public static void main(String[] args) throws Exception
    {
        int[] aaa = new int[] {1, 2, 3};
        int[] bbb = ArraysRuntime.$concat(aaa, 4);
        System.out.println(Arrays.toString(bbb));

        // ----------------------------

        int[] a = new int[] {1, 2};
        int[] b = new int[] {3, 4};
        int[] c = new int[] {5, 6};
        int[] d = new int[] {7, 8};
        int[] e = new int[] {11, 22};
        int[] f = new int[] {33, 44};

        int[][] g = new int[][] {a, b};
        int[][] h = new int[][] {c, d};
        int[][] i = new int[][] {e, f};

        int[][][] ccc = new int[][][] {g, h};
        int[][][] ddd = ArraysRuntime.$concat(ccc, i);
        System.out.println(Arrays.toString(ddd));
    }

    public static class ArraysRuntime
    {
        @SuppressWarnings("unused")
        public static char[] $concat(final char[] array, final char element)
        {
            final char[] newArray = new char[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unused")
        public static int[] $concat(final int[] array, final int element)
        {
            final int[] newArray = new int[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unused")
        public static long[] $concat(final long[] array, final long element)
        {
            final long[] newArray = new long[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unused")
        public static float[] $concat(final float[] array, final float element)
        {
            final float[] newArray = new float[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unused")
        public static double[] $concat(final double[] array, final double element)
        {
            final double[] newArray = new double[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unused")
        public static boolean[] $concat(final boolean[] array, final boolean element)
        {
            final boolean[] newArray = new boolean[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings({"unchecked", "unused"})
        public static <T> T[] $concat(final T[] array, final T element)
        {
            final T[] newArray = $cloneArray(array, array.length + 1);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unchecked")
        public static <T> T $cloneArray(final T array)
        {
            if (array instanceof char[])
            {
                return (T) ((char[]) array).clone();
            }
            else if (array instanceof int[])
            {
                return (T) ((int[]) array).clone();
            }
            else if (array instanceof long[])
            {
                return (T) ((long[]) array).clone();
            }
            else if (array instanceof float[])
            {
                return (T) ((float[]) array).clone();
            }
            else if (array instanceof double[])
            {
                return (T) ((double[]) array).clone();
            }
            else if (array instanceof boolean[])
            {
                return (T) ((boolean[]) array).clone();
            }
            else if (array.getClass().isArray())
            {
                return (T) $cloneArray(array, Array.getLength(array));
            }
            else
            {
                throw new RuntimeException("Error cloning array");
            }
        }

        @SuppressWarnings("unchecked")
        private static <T, E> E[] $cloneArray(final T array, final int size)
        {
            final E[] source = (E[]) array;
            final int sourceLength = source.length;
            final E[] newArray = $createArray(array.getClass(), size);

            for (int i = 0; i < sourceLength; i++)
            {
                newArray[i] = $cloneArray(source[i]);
            }

            return newArray;
        }

        @SuppressWarnings("unchecked")
        public static <T> T $createArray(final Class<?> clazz, final int size)
        {
            if (clazz.equals(char[].class))
            {
                return (T) new char[size];
            }
            else if (clazz.equals(int[].class))
            {
                return (T) new int[size];
            }
            else if (clazz.equals(long[].class))
            {
                return (T) new long[size];
            }
            else if (clazz.equals(float[].class))
            {
                return (T) new float[size];
            }
            else if (clazz.equals(double[].class))
            {
                return (T) new double[size];
            }
            else if (clazz.equals(boolean[].class))
            {
                return (T) new boolean[size];
            }
            else if (clazz.isArray())
            {
                return (T) Array.newInstance(clazz.getComponentType(), size);
            }
            else
            {
                throw new RuntimeException("Error creating array");
            }
        }
    }
}