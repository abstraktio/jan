package com.mauriciotogneri.jan.bytecode;

import com.mauriciotogneri.jan.bytecode.objects.Array;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Test
{
    // factorial n % %
    // 	? = n 0 1
    // * n factorial - n 1
    public static class factorial implements Function<Constant<Num>, Constant<Num>>
    {
        public static final factorial instance = new factorial();

        @Override
        public Constant<Num> call(final Constant<Num> n)
        {
            return new Constant<Num>()
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
                        return n.call().mul(factorial.instance.call(new Constant<Num>()
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

    // duplicate a % %
    // * a 2

    public static class duplicate implements Function<Constant<Num>, Constant<Num>>
    {
        public static final duplicate instance = new duplicate();

        @Override
        public Constant<Num> call(final Constant<Num> a)
        {
            return new Constant<Num>()
            {
                @Override
                public Num call()
                {
                    return a.call().mul(new Num(2));
                }
            };
        }
    }

    // map function ( A B ) array [ A ] [ B ]
    // ? = 0 # array [ ]
    // _ x  @ 0 array
    // _ xs - 0 array
    // + function x map function xs

    @SuppressWarnings("unchecked")
    public static class map<A, B> implements Function<Function<A, B>, Function<Array<A>, Array<B>>>
    {
        @Override
        public Function<Array<A>, Array<B>> call(final Function<A, B> function)
        {
            return new Function<Array<A>, Array<B>>()
            {
                @Override
                public Array<B> call(final Array<A> array)
                {
                    if (array.length() == 0)
                    {
                        return new Array<>();
                    }

                    A x = array.get(0);
                    Array<A> xs = array.remove(0);

                    B _1 = function.call(x);
                    Function<Array<A>, Array<B>> _2 = new map<A, B>().call(function);
                    Array<B> _3 = _2.call(xs);

                    return _3.concatenateAfter(_1);

                    //return new map<A, B>().call(function).call(xs).concatenateBefore(function.call(x));
                }
            };
        }
    }

    // pi
    // 3.13159

    public static class pi implements Constant<Num>
    {
        @Override
        public Num call()
        {
            return new Num(3.14159);
        }
    }

    // custom a % b % c % %
    // + * c b a

    public static class custom implements Function<Constant<Num>, Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>>
    {
        public static final custom instance = new custom();

        @Override
        public Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>> call(final Constant<Num> a)
        {
            return new Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>()
            {
                @Override
                public Function<Constant<Num>, Constant<Num>> call(final Constant<Num> b)
                {
                    return new Function<Constant<Num>, Constant<Num>>()
                    {
                        @Override
                        public Constant<Num> call(final Constant<Num> c)
                        {
                            return new Constant<Num>()
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

    // multi a % b % %
    // * a b

    public static class multi implements Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>
    {
        public static final multi instance = new multi();

        @Override
        public Function<Constant<Num>, Constant<Num>> call(final Constant<Num> a)
        {
            return new Function<Constant<Num>, Constant<Num>>()
            {
                @Override
                public Constant<Num> call(final Constant<Num> b)
                {
                    return new Constant<Num>()
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

    // mul3 a % %
    // * 3 a

    public static class mul3 implements Function<Constant<Num>, Constant<Num>>
    {
        public static final mul3 instance = new mul3();

        @Override
        public Constant<Num> call(final Constant<Num> a)
        {
            return new Constant<Num>()
            {
                @Override
                public Num call()
                {
                    return new Num(3).mul(a.call());
                }
            };
        }
    }

    // mul3AndAdd a % b % %
    // + * 3 a b

    public static class mul3AndAdd implements Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>
    {
        public static final mul3AndAdd instance = new mul3AndAdd();

        @Override
        public Function<Constant<Num>, Constant<Num>> call(final Constant<Num> a)
        {
            return new Function<Constant<Num>, Constant<Num>>()
            {
                @Override
                public Constant<Num> call(final Constant<Num> b)
                {
                    return new Constant<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return new Num(3).mul(a.call()).add(b.call());
                        }
                    };
                }
            };
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        Constant<Num> a1 = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(1);
            }
        };
        Constant<Num> a2 = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(2);
            }
        };
        Constant<Num> a3 = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(3);
            }
        };

        Array<Constant<Num>> array = new Array<>(a1, a2, a3);
        print(new map<Constant<Num>, Constant<Num>>().call(duplicate.instance).call(array));

        Constant<Num> n = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(5);
            }
        };
        print(factorial.instance.call(n));

        Constant<Num> a = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(5);
            }
        };
        Constant<Num> b = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(7);
            }
        };
        Constant<Num> c = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(2);
            }
        };
        // custom2 5 7 2
        print(custom.instance.call(a).call(b).call(c));

        Constant<Num> d = new Constant<Num>()
        {
            @Override
            public Num call()
            {
                return new Num(7);
            }
        };
        print(mul3.instance.call(d));
    }

    private static void print(Constant<?> function)
    {
        System.out.println(function.call());
    }

    private static void print(Object object)
    {
        System.out.println(object);
    }

    public interface Constant<R>
    {
        R call();
    }

    public interface Function<P1, R>
    {
        R call(P1 p1);
    }
}