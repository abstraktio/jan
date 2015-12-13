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
            if ($equals.instance.call(n).call(Num.create(0)).isTrue())
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
    public static class map<A, B> implements Function2<Function<A, B>, Array<A>, Array<B>>
    {
        @Override
        public Function<Array<A>, Array<B>> call(final Function<A, B> function)
        {
            return new Function<Array<A>, Array<B>>()
            {
                @Override
                public Array<B> call(final Array<A> array)
                {
                    if ($equals.instance.call(Num.create(0)).call($length.instance.call(array)).isTrue())
                    {
                        return new Array<>();
                    }

                    A x = array.get(0);
                    Array<A> xs = array.remove(0);

                    return new $concatenateBefore<B>().call(function.call(x)).call(new map<A, B>().call(function).call(xs));
                }
            };
        }
    }

    // = :: a A b A -> ?
    // = a b

    public static class $equals implements Function2<Constant, Constant, Bool>
    {
        public static final $equals instance = new $equals();

        @Override
        public Function<Constant, Bool> call(final Constant a)
        {
            return new Function<Constant, Bool>()
            {
                @Override
                public Bool call(final Constant b)
                {
                    return Bool.create(a.equals(b));
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
            return a.length();
        }
    }

    // * :: a % b % -> %
    // * a b

    public static class $mul implements Function2<Num, Num, Num>
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
                    return a.mul(b);
                }
            };
        }
    }

    // + :: a % b % -> %
    // + a b

    public static class $add implements Function2<Num, Num, Num>
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
                    return a.add(b);
                }
            };
        }
    }

    // - :: a % b % -> %
    // - a b

    public static class $sub implements Function2<Num, Num, Num>
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

    // + :: a A b [ A ] -> [ A ]
    // + a b

    public static class $concatenateBefore<A> implements Function2<A, Array<A>, Array<A>>
    {
        public static final $concatenateBefore instance = new $concatenateBefore();

        @Override
        public Function<Array<A>, Array<A>> call(final A a)
        {
            return new Function<Array<A>, Array<A>>()
            {
                @Override
                public Array<A> call(final Array<A> b)
                {
                    return b.concatenateBefore(a);
                }
            };
        }
    }

    // multiplyBy :: n % -> ( % -> % )
    // * n

    public static class multiplyBy implements Function2<Num, Num, Num>
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

    public static class custom implements Function3<Num, Num, Num, Num>
    {
        public static final custom instance = new custom();

        @Override
        public Function2<Num, Num, Num> call(final Num a)
        {
            return new Function2<Num, Num, Num>()
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

    public static class multi implements Function2<Num, Num, Num>
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
            return Num.create(3).mul(a);
        }
    }

    // mul3AndAdd :: a % b % -> %
    // + * 3 a b

    public static class mul3AndAdd implements Function2<Num, Num, Num>
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
                    return Num.create(3).mul(a).add(b);
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

        Array<Num> array = new Array<>(Num.create(1), Num.create(2), Num.create(3));
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

    public interface Function<A, Z>
    {
        Z call(A a);
    }

    public interface Function2<A, B, Z> extends Function<A, Function<B, Z>>
    {
    }

    public interface Function3<A, B, C, Z> extends Function2<A, B, Function<C, Z>>
    {
    }
}