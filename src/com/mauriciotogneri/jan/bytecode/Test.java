package com.mauriciotogneri.jan.bytecode;

import com.mauriciotogneri.jan.bytecode.objects.Array;
import com.mauriciotogneri.jan.bytecode.objects.Bool;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Test
{
    // factorial :: n % -> %
    // ? = n 0 1
    // * n factorial - n 1
    public static class factorial implements Function<Constant<Num>, Constant<Num>>
    {
        public static final factorial instance = new factorial();

        @Override
        public Constant<Num> call(final Constant<Num> n)
        {
            if ($equals.instance.call(n).call(Num.asConstant(0)).call().isTrue())
            {
                return Num.asConstant(1);
            }

            return $mul.instance.call(n).call(factorial.instance.call($sub.instance.call(n).call(Num.asConstant(1))));
        }
    }

    // map :: function ( A -> B ) array [ A ] -> [ B ]
    // ? = 0 # array [ ]
    // _ x  @ 0 array
    // _ xs - 0 array
    // + function x map ( function ) xs

    @SuppressWarnings("unchecked")
    public static class map<A, B> implements Function<Function<Constant<A>, Constant<B>>, Function<Constant<Array<A>>, Constant<Array<B>>>>
    {
        @Override
        public Function<Constant<Array<A>>, Constant<Array<B>>> call(final Function<Constant<A>, Constant<B>> function)
        {
            return new Function<Constant<Array<A>>, Constant<Array<B>>>()
            {
                @Override
                public Constant<Array<B>> call(final Constant<Array<A>> array)
                {
                    if ($equals.instance.call(Num.asConstant(0)).call($length.instance.call(array)).call().isTrue())
                    {
                        return new Constant<Array<B>>()
                        {
                            @Override
                            public Array<B> call()
                            {
                                return new Array<>();
                            }
                        };
                    }

                    final Constant<A> x = new Constant<A>()
                    {
                        @Override
                        public A call()
                        {
                            return array.call().get(0);
                        }
                    };
                    final Constant<Array<A>> xs = new Constant<Array<A>>()
                    {
                        @Override
                        public Array<A> call()
                        {
                            return array.call().remove(0);
                        }
                    };

                    final Constant<B> _1 = function.call(x);
                    final Function<Constant<Array<A>>, Constant<Array<B>>> _2 = new map<A, B>().call(function);
                    final Constant<Array<B>> _3 = _2.call(xs);

                    return new Constant<Array<B>>()
                    {
                        @Override
                        public Array<B> call()
                        {
                            return _3.call().concatenateBefore(_1.call());
                        }
                    };
                }
            };
        }
    }

    // = :: a % b % -> ?
    // = a b

    public static class $equals implements Function<Constant<Num>, Function<Constant<Num>, Constant<Bool>>>
    {
        public static final $equals instance = new $equals();

        @Override
        public Function<Constant<Num>, Constant<Bool>> call(final Constant<Num> a)
        {
            return new Function<Constant<Num>, Constant<Bool>>()
            {
                @Override
                public Constant<Bool> call(final Constant<Num> b)
                {
                    return Bool.asConstant(a.call().equals(b.call()));
                }
            };
        }
    }

    // # :: a [ A ] -> %
    // # a

    public static class $length<A> implements Function<Constant<Array<A>>, Constant<Num>>
    {
        public static final $length instance = new $length();

        @Override
        public Constant<Num> call(final Constant<Array<A>> a)
        {
            return new Constant<Num>()
            {
                @Override
                public Num call()
                {
                    return a.call().length();
                }
            };
        }
    }

    // * :: a % b % -> %
    // * a b

    public static class $mul implements Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>
    {
        public static final $mul instance = new $mul();

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

    public static class $mul2 implements Function<Num, Function<Num, Num>>
    {
        public static final $mul2 instance = new $mul2();

        @Override
        public Function<Num, Num> call(final Num a)
        {
            return new Function<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.call().mul(b.call());
                }
            };
        }
    }

    // + :: a % b % -> %
    // + a b

    public static class $add implements Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>
    {
        public static final $add instance = new $add();

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
                            return a.call().add(b.call());
                        }
                    };
                }
            };
        }
    }

    // - :: a % b % -> %
    // - a b

    public static class $sub implements Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>
    {
        public static final $sub instance = new $sub();

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
                            return a.call().sub(b.call());
                        }
                    };
                }
            };
        }
    }

    // multiplyBy :: n % -> ( % -> % )
    // * n

    public static class multiplyBy implements Function<Constant<Num>, Function<Constant<Num>, Constant<Num>>>
    {
        @Override
        public Function<Constant<Num>, Constant<Num>> call(final Constant<Num> n)
        {
            return $mul.instance.call(n);
        }
    }

    // pi -> %
    // 3.13159

    public static class pi implements Constant<Num>
    {
        @Override
        public Num call()
        {
            return Num.create(3.14159);
        }
    }

    // custom :: a % b % c % -> %
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
                            return $add.instance.call(a).call($mul.instance.call(b).call(c));
                        }
                    };
                }
            };
        }
    }

    // duplicate :: a % -> %
    // * a 2

    public static class duplicate implements Function<Constant<Num>, Constant<Num>>
    {
        public static final duplicate instance = new duplicate();

        @Override
        public Constant<Num> call(final Constant<Num> a)
        {
            return $mul.instance.call(a).call(Num.asConstant(2));
        }
    }

    public static class duplicate2 implements Function<Num, Num>
    {
        public static final duplicate2 instance = new duplicate2();

        @Override
        public Num call(final Num a)
        {
            return $mul2.instance.call(a).call(Num.create(2));
        }
    }

    // multi :: a % b % -> %
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
                    return $mul.instance.call(a).call(b);
                }
            };
        }
    }

    // mul3 :: a % -> %
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
                    return Num.create(3).mul(a.call());
                }
            };
        }
    }

    // mul3AndAdd :: a % b % -> %
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
                            return Num.create(3).mul(a.call()).add(b.call());
                        }
                    };
                }
            };
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        print(duplicate2.instance.call(Num.create(23)));

        Constant<Num> n = Num.asConstant(5);
        print(factorial.instance.call(n));

        Constant<Array<Num>> array = new Constant<Array<Num>>()
        {
            @Override
            public Array<Num> call()
            {
                return new Array<>(Num.create(1), Num.create(2), Num.create(3));
            }
        };
        print(new map<Num, Num>().call(duplicate.instance).call(array));

        Constant<Num> a = Num.asConstant(5);
        Constant<Num> b = Num.asConstant(7);
        Constant<Num> c = Num.asConstant(2);
        print(custom.instance.call(a).call(b).call(c));

        Constant<Num> d = Num.asConstant(7);
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

    public interface Function<P, R>
    {
        R call(P p);
    }
}