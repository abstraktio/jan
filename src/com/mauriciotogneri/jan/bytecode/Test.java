package com.mauriciotogneri.jan.bytecode;

import com.mauriciotogneri.jan.bytecode.functions.Arithmetic.$add;
import com.mauriciotogneri.jan.bytecode.functions.Arithmetic.$div;
import com.mauriciotogneri.jan.bytecode.functions.Arithmetic.$mul;
import com.mauriciotogneri.jan.bytecode.functions.Arithmetic.$sub;
import com.mauriciotogneri.jan.bytecode.functions.Comparison.$equals;
import com.mauriciotogneri.jan.bytecode.functions.List.$addBefore;
import com.mauriciotogneri.jan.bytecode.functions.List.$length;
import com.mauriciotogneri.jan.bytecode.kernel.Constant;
import com.mauriciotogneri.jan.bytecode.kernel.Function0;
import com.mauriciotogneri.jan.bytecode.kernel.Function1;
import com.mauriciotogneri.jan.bytecode.kernel.Function2;
import com.mauriciotogneri.jan.bytecode.kernel.Function3;
import com.mauriciotogneri.jan.bytecode.objects.Array;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Test
{
    // factorial :: n % -> %
    // ? = n 0 1
    // * n factorial - n 1

    public static class factorial implements Function1<Num, Num>
    {
        public static final factorial instance = new factorial();

        @Override
        public Num call(final Num n)
        {
            if ($equals.$equalsNum.call(n).call(Num.create(0)).isTrue())
            {
                return Num.create(1);
            }

            return $mul.instance.call(n).call(factorial.instance.call($sub.instance.call(n).call(Num.create(1))));
        }
    }

    // map :: function ( $A -> $B ) array [ $A ] -> [ $B ]
    // ? = 0 # array [ ]
    // _ x  @ 0 array
    // _ xs - 0 array
    // + function x map ( function ) xs

    @SuppressWarnings("unchecked")
    public static class map<A, B> implements Function2<Function1<A, B>, Array<A>, Array<B>>
    {
        @Override
        public Function1<Array<A>, Array<B>> call(final Function1<A, B> function)
        {
            return new Function1<Array<A>, Array<B>>()
            {
                @Override
                public Array<B> call(final Array<A> array)
                {
                    if ($equals.$equalsNum.call(Num.create(0)).call($length.instance.call(array)).isTrue())
                    {
                        return new Array<>();
                    }

                    A x = array.get(Num.create(0));
                    Array<A> xs = array.remove(Num.create(0));

                    return new $addBefore<B>().call(function.call(x)).call(new map<A, B>().call(function).call(xs));
                }
            };
        }
    }

    // multiplyBy :: n % -> ( % -> % )
    // * n

    public static class multiplyBy implements Function2<Num, Num, Num>
    {
        @Override
        public Function1<Num, Num> call(final Num n)
        {
            return $mul.instance.call(n);
        }
    }

    // pi -> %
    // 3.13159

    public static class pi implements Function0<Num>
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
                public Function1<Num, Num> call(final Num b)
                {
                    return new Function1<Num, Num>()
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

    // half :: -> ( % -> % )
    // / 2

    public static class half implements Function0<Function1<Num, Num>>
    {
        public static final half instance = new half();

        @Override
        public Function1<Num, Num> call()
        {
            return $div.instance.call(Num.create(2));
        }
    }

    // duplicate :: a % -> %
    // * a 2

    public static class duplicate implements Function1<Num, Num>
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
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
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

    public static class mul3 implements Function1<Num, Num>
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
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
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
        check(duplicate.instance.call(Num.create(23)), Num.create(46));

        check(factorial.instance.call(Num.create(5)), Num.create(120));

        Array<Num> input = new Array<>(Num.create(1), Num.create(2), Num.create(3));
        Array<Num> output = new Array<>(Num.create(2), Num.create(4), Num.create(6));
        check(new map<Num, Num>().call(duplicate.instance).call(input), output);

        Num a = Num.create(5);
        Num b = Num.create(7);
        Num c = Num.create(2);
        check(custom.instance.call(a).call(b).call(c), Num.create(19));

        Num d = Num.create(7);
        check(mul3.instance.call(d), Num.create(21));
    }

    private static <A> void check(Constant<A> c1, Constant<A> c2)
    {
        System.out.println(c1.isEqual(c2).isTrue() + "   " + c1.call());
    }
}