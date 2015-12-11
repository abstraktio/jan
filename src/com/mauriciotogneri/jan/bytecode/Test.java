package com.mauriciotogneri.jan.bytecode;

import com.mauriciotogneri.jan.bytecode.objects.Array;
import com.mauriciotogneri.jan.bytecode.objects.Num;

import java.util.Arrays;

public class Test
{
    public static final pi pi = new pi();
    public static final factorial factorial = new factorial();
    public static final fibonacci fibonacci = new fibonacci();
    public static final fibo2 fibo2 = new fibo2();
    public static final map map = new map();
    public static final multiplyByTwo multiplyByTwo = new multiplyByTwo();
    public static final duplicate duplicate = new duplicate();
    public static final callTwice callTwice = new callTwice();
    public static final multiplicator multiplicator = new multiplicator();

    public static class factorial implements Function1<Long, Long>
    {
        @Override
        public Long call(Long n)
        {
            if (n <= 0)
            {
                return 1L;
            }
            else
            {
                return n * factorial.call(n - 1);
            }
        }
    }

    public static class fibonacci implements Function1<Long, long[]>
    {
        @Override
        public long[] call(Long n)
        {
            return fibo2.call(0L, n, new long[] {});
        }
    }

    public static class fibo2 implements Function3<Long, Long, long[], long[]>
    {
        @Override
        public long[] call(Long index, Long limit, long[] list)
        {
            if (index >= limit)
            {
                return list;
            }
            else if (index == 0)
            {
                return fibo2.call(1L, limit, new long[] {1L});
            }
            else if (index == 1)
            {
                return fibo2.call(2L, limit, new long[] {1L, 1L});
            }
            else
            {
                Long value = list[(int) (index - 1)] + list[(int) (index - 2)];
                long[] array = Runtime.ArraysRuntime.$concat(list, value);

                return fibo2.call(index + 1, limit, array);
            }
        }
    }

    // map ( A -> B ) function [ A ] array -> [ B ]
    // ? = 0 # array [ ]
    // _ x  @ 0 array
    // _ xs - 0 array
    // + function x map function xs

    @SuppressWarnings("unchecked")
    public static class map<A, B> implements Function2<Function1<A, B>, Array<A>, Array<B>>
    {
        @Override
        public Array<B> call(Function1<A, B> function, Array<A> array)
        {
            if (array.length() == 0)
            {
                return new Array<>();
            }
            else
            {
                A x = array.get(0);
                Array<A> xs = array.remove(0);

                return map.call(function, xs).concatenateBefore(function.call(x));
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static class multiplyByTwo implements Function1<Num, Num>
    {
        @Override
        public Num call(Num number)
        {
            return number.mul(new Num(2));
        }
    }

    public static class callTwice<A> implements Function2<Function1<A, A>, A, A>
    {
        @Override
        public A call(final Function1<A, A> function, final A a)
        {
            return function.call(function.call(a));
        }
    }

    public static class duplicate implements Function1<Num, Num>
    {
        @Override
        public Num call(final Num num)
        {
            return num.mul(new Num(2));
        }
    }

    public static class pi implements Function0<Num>
    {
        @Override
        public Num call()
        {
            return new Num(3.14159);
        }
    }

    // multiplicator % param -> ( % -> % )
    // { % num -> * param num }

    // test
    // _ m3 multiplicator 3
    // m3 7

    public static class multiplicator implements Function1<Num, Function1<Num, Num>>
    {
        @Override
        public Function1<Num, Num> call(final Num param)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(Num num)
                {
                    return param.mul(num);
                }
            };
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(fibonacci.call(10L)));

        System.out.println(pi.call());

        System.out.println(duplicate.call(new Num(-0.123)));

        Array<Num> array = new Array<>(new Num(1), new Num(2.4), new Num(3));
        System.out.println(map.call(multiplyByTwo, array));

        System.out.println(callTwice.call(new Function1<Num, Num>()
        {
            @Override
            public Num call(Num num)
            {
                return num.mul(new Num(2));
            }
        }, new Num(3)));

        Function1<Num, Num> multiplicator3 = multiplicator.call(new Num(3));
        System.out.println(multiplicator3.call(new Num(7)));
        System.out.println(multiplicator3.call(new Num(8)));
    }

    public interface Function0<R>
    {
        R call();
    }

    public interface Function1<P1, R>
    {
        R call(P1 p1);
    }

    public interface Function2<P1, P2, R>
    {
        R call(P1 p1, P2 p2);
    }

    public interface Function3<P1, P2, P3, R>
    {
        R call(P1 p1, P2 p2, P3 p3);
    }
}