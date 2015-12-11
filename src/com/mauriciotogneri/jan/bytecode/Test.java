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

    public static final custom custom = new custom();
    public static final custom2 custom2 = new custom2();

    public static class factorial implements F1<Long, Long>
    {
        @Override
        public Long call(final Long n)
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

    public static class fibonacci implements F1<Long, long[]>
    {
        @Override
        public long[] call(final Long n)
        {
            return fibo2.call(0L, n, new long[] {});
        }
    }

    public static class fibo2 implements Function3<Long, Long, long[], long[]>
    {
        @Override
        public long[] call(final Long index, final Long limit, final long[] list)
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
    public static class map<A, B> implements Function2<F1<A, B>, Array<A>, Array<B>>
    {
        @Override
        public Array<B> call(final F1<A, B> function, final Array<A> array)
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
    public static class multiplyByTwo implements F1<Num, Num>
    {
        @Override
        public Num call(final Num number)
        {
            return number.mul(new Num(2));
        }
    }

    public static class callTwice<A> implements Function2<F1<A, A>, A, A>
    {
        @Override
        public A call(final F1<A, A> function, final A a)
        {
            return function.call(function.call(a));
        }
    }

    public static class duplicate implements F1<Num, Num>
    {
        @Override
        public Num call(final Num num)
        {
            return num.mul(new Num(2));
        }
    }

    public static class pi implements F0<Num>
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

    // test2
    // multiplicator 3 7

    public static class multiplicator implements F1<Num, F1<Num, Num>>
    {
        @Override
        public F1<Num, Num> call(final Num param)
        {
            return new F1<Num, Num>()
            {
                @Override
                public Num call(final Num num)
                {
                    return param.mul(num);
                }
            };
        }
    }

    // - + * 3 2 7 9
    // - + *3 2 7 9
    // - + 6 7 9
    // - +6 7 9
    // - 13 9
    // -13 9
    // 4

    public static class $mul implements F1<F0<Num>, F1<F0<Num>, F0<Num>>>
    {
        public static final $mul instance = new $mul();

        @Override
        public F1<F0<Num>, F0<Num>> call(final F0<Num> a)
        {
            return new F1<F0<Num>, F0<Num>>()
            {
                @Override
                public F0<Num> call(final F0<Num> b)
                {
                    return new F0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().mul(b.call());
                        }
                    };
                }
            };
        }
    }

    public static class $add implements F1<F0<Num>, F1<F0<Num>, F0<Num>>>
    {
        public static final $add instance = new $add();

        @Override
        public F1<F0<Num>, F0<Num>> call(final F0<Num> a)
        {
            return new F1<F0<Num>, F0<Num>>()
            {
                @Override
                public F0<Num> call(final F0<Num> b)
                {
                    return new F0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().add(b.call());
                        }
                    };
                }
            };
        }
    }

    // custom % a % b % c
    // + * c b a

    public static class custom implements F1<Num, F1<Num, F1<Num, Num>>>
    {
        @Override
        public F1<Num, F1<Num, Num>> call(final Num a)
        {
            return new F1<Num, F1<Num, Num>>()
            {
                @Override
                public F1<Num, Num> call(final Num b)
                {
                    return new F1<Num, Num>()
                    {
                        @Override
                        public Num call(final Num c)
                        {
                            return c.mul(b).add(a);
                        }
                    };
                }
            };
        }
    }

    public static class custom2 implements F1<F0<Num>, F1<F0<Num>, F1<F0<Num>, F0<Num>>>>
    {
        @Override
        public F1<F0<Num>, F1<F0<Num>, F0<Num>>> call(final F0<Num> a)
        {
            return new F1<F0<Num>, F1<F0<Num>, F0<Num>>>()
            {
                @Override
                public F1<F0<Num>, F0<Num>> call(final F0<Num> b)
                {
                    return new F1<F0<Num>, F0<Num>>()
                    {
                        @Override
                        public F0<Num> call(final F0<Num> c)
                        {
                            return new F0<Num>()
                            {
                                @Override
                                public Num call()
                                {
                                    return c.call().mul(b.call()).add(a.call());
                                }
                            };
                        }
                    };
                }
            };
        }
    }

    // mul3 % _ %
    // * 3

    public static class mul3 implements F1<F0<Num>, F0<Num>>
    {
        public static final mul3 instance = new mul3();

        @Override
        public F0<Num> call(final F0<Num> _1)
        {
            return new F0<Num>()
            {
                @Override
                public Num call()
                {
                    return new Num(3).mul(_1.call());
                }
            };
        }
    }

    // mul3AndAdd % _ % _ %
    // + * 3

    public static class mul3AndAdd implements F1<F0<Num>, F1<F0<Num>, F0<Num>>>
    {
        public static final mul3AndAdd instance = new mul3AndAdd();

        @Override
        public F1<F0<Num>, F0<Num>> call(final F0<Num> _1)
        {
            return new F1<F0<Num>, F0<Num>>()
            {
                @Override
                public F0<Num> call(final F0<Num> _2)
                {
                    return new F0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return new Num(3).mul(_1.call()).add(_2.call());
                        }
                    };
                }
            };
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        print(Arrays.toString(fibonacci.call(10L)));

        print(pi.call());

        print(duplicate.call(new Num(-0.123)));

        Array<Num> array = new Array<>(new Num(1), new Num(2.4), new Num(3));
        print(map.call(multiplyByTwo, array));

        print(callTwice.call(new F1<Num, Num>()
        {
            @Override
            public Num call(Num num)
            {
                return num.mul(new Num(2));
            }
        }, new Num(3)));

        F1<Num, Num> multiplicator3 = multiplicator.call(new Num(3));
        print(multiplicator3.call(new Num(7)));
        print(multiplicator3.call(new Num(8)));

        F0<Num> a = new F0<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(5);
            }
        };
        F0<Num> b = new F0<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(7);
            }
        };
        F0<Num> c = new F0<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(2);
            }
        };
        // custom2 5 7 2
        print(custom2.call(a).call(b).call(c));

        F0<Num> d = new F0<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(7);
            }
        };
        print(mul3.instance.call(d));
    }

    private static void print(F0<?> function)
    {
        System.out.println(function.call());
    }

    private static void print(Object object)
    {
        System.out.println(object);
    }

    public interface F0<R>
    {
        R call();
    }

    public interface F1<P1, R>
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