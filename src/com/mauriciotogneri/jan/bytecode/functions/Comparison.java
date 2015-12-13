package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.Constant;
import com.mauriciotogneri.jan.bytecode.kernel.Function;
import com.mauriciotogneri.jan.bytecode.kernel.Function2;
import com.mauriciotogneri.jan.bytecode.objects.Bool;
import com.mauriciotogneri.jan.bytecode.objects.Char;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Comparison
{
    private Comparison()
    {
    }

    // = :: a A b A -> ?
    // = a b

    public static class $equals<A> implements Function2<Constant<A>, Constant<A>, Bool>
    {
        public static final $equals<Num> $equalsNum = new $equals<>();
        public static final $equals<Bool> $equalsBool = new $equals<>();
        public static final $equals<Char> $equalsChar = new $equals<>();

        @Override
        public Function<Constant<A>, Bool> call(final Constant<A> a)
        {
            return new Function<Constant<A>, Bool>()
            {
                @Override
                public Bool call(final Constant<A> b)
                {
                    return a.isEqual(b);
                }
            };
        }
    }

    // != :: a A b A -> ?
    // != a b

    public static class $notEquals<A> implements Function2<Constant<A>, Constant<A>, Bool>
    {
        public static final $notEquals<Num> $notEqualsNum = new $notEquals<>();
        public static final $notEquals<Bool> $botEqualsBool = new $notEquals<>();
        public static final $notEquals<Char> $notEqualsChar = new $notEquals<>();

        @Override
        public Function<Constant<A>, Bool> call(final Constant<A> a)
        {
            return new Function<Constant<A>, Bool>()
            {
                @Override
                public Bool call(final Constant<A> b)
                {
                    return a.isNotEqual(b);
                }
            };
        }
    }

    // < :: a % b % -> ?
    // < a b

    public static class $less implements Function2<Num, Num, Bool>
    {
        public static final $less instance = new $less();

        @Override
        public Function<Num, Bool> call(final Num a)
        {
            return new Function<Num, Bool>()
            {
                @Override
                public Bool call(final Num b)
                {
                    return a.less(b);
                }
            };
        }
    }

    // <= :: a % b % -> ?
    // <= a b

    public static class $lessOrEqual implements Function2<Num, Num, Bool>
    {
        public static final $lessOrEqual instance = new $lessOrEqual();

        @Override
        public Function<Num, Bool> call(final Num a)
        {
            return new Function<Num, Bool>()
            {
                @Override
                public Bool call(final Num b)
                {
                    return a.lessOrEqual(b);
                }
            };
        }
    }

    // > :: a % b % -> ?
    // > a b

    public static class $greater implements Function2<Num, Num, Bool>
    {
        public static final $greater instance = new $greater();

        @Override
        public Function<Num, Bool> call(final Num a)
        {
            return new Function<Num, Bool>()
            {
                @Override
                public Bool call(final Num b)
                {
                    return a.greater(b);
                }
            };
        }
    }

    // >= :: a % b % -> ?
    // >= a b

    public static class $greaterOrEqual implements Function2<Num, Num, Bool>
    {
        public static final $greaterOrEqual instance = new $greaterOrEqual();

        @Override
        public Function<Num, Bool> call(final Num a)
        {
            return new Function<Num, Bool>()
            {
                @Override
                public Bool call(final Num b)
                {
                    return a.greaterOrEqual(b);
                }
            };
        }
    }
}