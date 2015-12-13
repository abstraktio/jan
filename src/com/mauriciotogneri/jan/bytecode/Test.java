package com.mauriciotogneri.jan.bytecode;

import com.mauriciotogneri.jan.bytecode.objects.Array;
import com.mauriciotogneri.jan.bytecode.objects.Bool;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Test
{
    // factorial :: n % -> %
    // ? = n 0 1
    // * n factorial - n 1
    public static class factorial implements Function<Num, Num>
    {
        public static final factorial instance = new factorial();

        @Override
        public Num call(final Num n)
        {
            if ($equals.instance.call(n).call(Num.create(0)).call().isTrue())
            {
                return Num.create(1);
            }

            return $mul.instance.call(n).call(factorial.instance.call($sub.instance.call(n).call(Num.create(1))));
        }
    }

    // map :: function ( A -> B ) array [ A ] -> [ B ]
    // ? = 0 # array [ ]
    // _ x  @ 0 array
    // _ xs - 0 array
    // + function x map ( function ) xs

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
                    if ($equals.instance.call(Num.create(0)).call($length.instance.call(array)).call().isTrue())
                    {
                        return new Array<>();
                    }

                    final A x = array.call().get(0);
                    final Array<A> xs = array.call().remove(0);

                    final B _1 = function.call(x);
                    final Function<Array<A>, Array<B>> _2 = new map<A, B>().call(function);
                    final Array<B> _3 = _2.call(xs);

                    return _3.call().concatenateBefore(_1);
                }
            };
        }
    }

    // = :: a % b % -> ?
    // = a b

    public static class $equals implements Function<Num, Function<Num, Bool>>
    {
        public static final $equals instance = new $equals();

        @Override
        public Function<Num, Bool> call(final Num a)
        {
            return new Function<Num, Bool>()
            {
                @Override
                public Bool call(final Num b)
                {
                    return Bool.create(a.call().equals(b.call()));
                }
            };
        }
    }

    // # :: a [ A ] -> %
    // # a

    public static class $length<A> implements Function<Array<A>, Num>
    {
        public static final $length instance = new $length();

        @Override
        public Num call(final Array<A> a)
        {
            return a.call().length();
        }
    }

    // * :: a % b % -> %
    // * a b

    public static class $mul implements Function<Num, Function<Num, Num>>
    {
        public static final $mul instance = new $mul();

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

    public static class $add implements Function<Num, Function<Num, Num>>
    {
        public static final $add instance = new $add();

        @Override
        public Function<Num, Num> call(final Num a)
        {
            return new Function<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.call().add(b.call());
                }
            };
        }
    }

    // - :: a % b % -> %
    // - a b

    public static class $sub implements Function<Num, Function<Num, Num>>
    {
        public static final $sub instance = new $sub();

        @Override
        public Function<Num, Num> call(final Num a)
        {
            return new Function<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.sub(b);
                }
            };
        }
    }

    // multiplyBy :: n % -> ( % -> % )
    // * n

    public static class multiplyBy implements Function<Num, Function<Num, Num>>
    {
        @Override
        public Function<Num, Num> call(final Num n)
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

    public static class custom implements Function<Num, Function<Num, Function<Num, Num>>>
    {
        public static final custom instance = new custom();

        @Override
        public Function<Num, Function<Num, Num>> call(final Num a)
        {
            return new Function<Num, Function<Num, Num>>()
            {
                @Override
                public Function<Num, Num> call(final Num b)
                {
                    return new Function<Num, Num>()
                    {
                        @Override
                        public Num call(final Num c)
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

    public static class duplicate implements Function<Num, Num>
    {
        public static final duplicate instance = new duplicate();

        @Override
        public Num call(final Num a)
        {
            return $mul.instance.call(a).call(Num.create(2));
        }
    }

    // multi :: a % b % -> %
    // * a b

    public static class multi implements Function<Num, Function<Num, Num>>
    {
        public static final multi instance = new multi();

        @Override
        public Function<Num, Num> call(final Num a)
        {
            return new Function<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return $mul.instance.call(a).call(b);
                }
            };
        }
    }

    // mul3 :: a % -> %
    // * 3 a

    public static class mul3 implements Function<Num, Num>
    {
        public static final mul3 instance = new mul3();

        @Override
        public Num call(final Num a)
        {
            return Num.create(3).mul(a.call());
        }
    }

    // mul3AndAdd :: a % b % -> %
    // + * 3 a b

    public static class mul3AndAdd implements Function<Num, Function<Num, Num>>
    {
        public static final mul3AndAdd instance = new mul3AndAdd();

        @Override
        public Function<Num, Num> call(final Num a)
        {
            return new Function<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return Num.create(3).mul(a.call()).add(b.call());
                }
            };
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        print(duplicate.instance.call(Num.create(23)));

        Num n = Num.create(5);
        print(factorial.instance.call(n));

        Array<Num> array = new Array<Num>()
        {
            @Override
            public Array<Num> call()
            {
                return new Array<>(Num.create(1), Num.create(2), Num.create(3));
            }
        };
        print(new map<Num, Num>().call(duplicate.instance).call(array));

        Num a = Num.create(5);
        Num b = Num.create(7);
        Num c = Num.create(2);
        print(custom.instance.call(a).call(b).call(c));

        Num d = Num.create(7);
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