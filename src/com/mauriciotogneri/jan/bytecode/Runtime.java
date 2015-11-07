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

        int[][] ccc = new int[][] {new int[] {1, 2}, new int[] {3, 4}, new int[] {5, 6}};
        int[][] ddd = ArraysRuntime.$concat(ccc, new int[] {7, 8});
git        System.out.println(Arrays.toString(ddd));
    }

    public static class ArraysRuntime
    {
        public static char[] $concat(char[] array, char element)
        {
            char[] newArray = new char[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        public static int[] $concat(int[] array, int element)
        {
            int[] newArray = new int[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        public static long[] $concat(long[] array, long element)
        {
            long[] newArray = new long[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        public static float[] $concat(float[] array, float element)
        {
            float[] newArray = new float[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        public static double[] $concat(double[] array, double element)
        {
            double[] newArray = new double[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        public static boolean[] $concat(boolean[] array, boolean element)
        {
            boolean[] newArray = new boolean[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unchecked")
        public static <T> T[] $concat(T[] array, T element)
        {
            T[] newArray = $cloneArray(array, array.length + 1);
            newArray[array.length] = element;

            return newArray;
        }

        @SuppressWarnings("unchecked")
        public static <T, E> T $clone(T object)
        {
            if (object instanceof char[])
            {
                return (T) ((char[]) object).clone();
            }
            else if (object instanceof int[])
            {
                return (T) ((int[]) object).clone();
            }
            else if (object instanceof long[])
            {
                return (T) ((long[]) object).clone();
            }
            else if (object instanceof float[])
            {
                return (T) ((float[]) object).clone();
            }
            else if (object instanceof double[])
            {
                return (T) ((double[]) object).clone();
            }
            else if (object instanceof boolean[])
            {
                return (T) ((boolean[]) object).clone();
            }
            else if (object.getClass().isArray())
            {
                E[] array = (E[]) object;

                return (T) $cloneArray(array, array.length);
            }
            else
            {
                return object;
            }
        }

        @SuppressWarnings("unchecked")
        public static <T> T $createArray(Class<?> clazz, int size)
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
                return null;
            }
        }

        @SuppressWarnings({"unchecked", "ConstantConditions"})
        private static <T, E> E[] $cloneArray(T array, int size)
        {
            E[] source = (E[]) array;
            E[] newArray = $createArray(array.getClass(), size);

            for (int i = 0; i < source.length; i++)
            {
                newArray[i] = $clone(source[i]);
            }

            return newArray;
        }
    }
}