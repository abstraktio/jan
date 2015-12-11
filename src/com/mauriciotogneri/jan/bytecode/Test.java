package com.mauriciotogneri.jan.bytecode;

import com.mauriciotogneri.jan.bytecode.objects.Array;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Test
{
    // factorial % n %
    // 	? = n 0 1
    // * n factorial - n 1
    public static class factorial implements F1<F0<Num>, F0<Num>>
    {
        public static final factorial instance = new factorial();

        @Override
        public F0<Num> call(final F0<Num> n)
        {
            return new F0<Num>()
            {
                @Override
                public Num call()
                {
                    if (n.call().equals(new Num(0)))
                    {
                        return new Num(1);
                    }
                    else
                    {
                        return n.call().mul(factorial.instance.call(new F0<Num>()
                        {
                            @Override
                            public Num call()
                            {
                                return n.call().sub(new Num(1));
                            }
                        }).call());
                    }
                }
            };
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
        public static final map instance = new map();

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

                return map.instance.call(function, xs).concatenateBefore(function.call(x));
            }
        }
    }

    // pi
    // 3.13159

    public static class pi implements F0<Num>
    {
        @Override
        public Num call()
        {
            return new Num(3.14159);
        }
    }

    // custom % a % b % c
    // + * c b a

    public static class custom implements F1<F0<Num>, F1<F0<Num>, F1<F0<Num>, F0<Num>>>>
    {
        public static final custom instance = new custom();

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
        F0<Num> n = new F0<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(5);
            }
        };
        print(factorial.instance.call(n));

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
        print(custom.instance.call(a).call(b).call(c));

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