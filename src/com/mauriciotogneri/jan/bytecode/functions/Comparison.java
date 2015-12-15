package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.Eq;
import com.mauriciotogneri.jan.bytecode.kernel.Function0;
import com.mauriciotogneri.jan.bytecode.kernel.Function1;
import com.mauriciotogneri.jan.bytecode.kernel.Function2;
import com.mauriciotogneri.jan.bytecode.objects.Bool;
import com.mauriciotogneri.jan.bytecode.objects.Char;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Comparison
{
    private Comparison()
    {
    }

    public static boolean equals(Object a, Object b)
    {
        if ((a instanceof Function0) && (b instanceof Function0))
        {
            Object aValue = ((Function0) a).call();
            Object bValue = ((Function0) b).call();

            return (aValue instanceof Eq) && (bValue instanceof Eq) && aValue.equals(bValue);
        }

        return false;
    }

    // = :: a $A b $A -> ?
    // = a b

    public static class $equals<A extends Function0> implements Function2<A, A, Function0<Bool>>
    {
        public static final $equals<Function0<Num>> $equalsNum = new $equals<>();
        public static final $equals<Function0<Bool>> $equalsBool = new $equals<>();
        public static final $equals<Function0<Char>> $equalsChar = new $equals<>();

        @Override
        public Function1<A, Function0<Bool>> call(final A a)
        {
            return new Function1<A, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final A b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return Bool.create(Comparison.equals(a, b));
                        }
                    };
                }
            };
        }
    }

    // != :: a $A b $A -> ?
    // != a b

    public static class $notEquals<A extends Function0> implements Function2<A, A, Function0<Bool>>
    {
        public static final $notEquals<Function0<Num>> $notEqualsNum = new $notEquals<>();
        public static final $notEquals<Function0<Bool>> $notEqualsBool = new $notEquals<>();
        public static final $notEquals<Function0<Char>> $notEqualsChar = new $notEquals<>();

        @Override
        public Function1<A, Function0<Bool>> call(final A a)
        {
            return new Function1<A, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final A b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return Bool.create(!Comparison.equals(a, b));
                        }
                    };
                }
            };
        }
    }

    // < :: a % b % -> ?
    // < a b

    public static class $less implements Function2<Function0<Num>, Function0<Num>, Function0<Bool>>
    {
        public static final $less instance = new $less();

        @Override
        public Function1<Function0<Num>, Function0<Bool>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final Function0<Num> b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return a.call().less(b.call());
                        }
                    };
                }
            };
        }
    }

    // <= :: a % b % -> ?
    // <= a b

    public static class $lessOrEqual implements Function2<Function0<Num>, Function0<Num>, Function0<Bool>>
    {
        public static final $lessOrEqual instance = new $lessOrEqual();

        @Override
        public Function1<Function0<Num>, Function0<Bool>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final Function0<Num> b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return a.call().lessOrEqual(b.call());
                        }
                    };
                }
            };
        }
    }

    // > :: a % b % -> ?
    // > a b

    public static class $greater implements Function2<Function0<Num>, Function0<Num>, Function0<Bool>>
    {
        public static final $greater instance = new $greater();

        @Override
        public Function1<Function0<Num>, Function0<Bool>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final Function0<Num> b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return a.call().greater(b.call());
                        }
                    };
                }
            };
        }
    }

    // >= :: a % b % -> ?
    // >= a b

    public static class $greaterOrEqual implements Function2<Function0<Num>, Function0<Num>, Function0<Bool>>
    {
        public static final $greaterOrEqual instance = new $greaterOrEqual();

        @Override
        public Function1<Function0<Num>, Function0<Bool>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final Function0<Num> b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return a.call().greaterOrEqual(b.call());
                        }
                    };
                }
            };
        }
    }
}