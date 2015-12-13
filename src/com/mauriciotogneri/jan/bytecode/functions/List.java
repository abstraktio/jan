package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.Function;
import com.mauriciotogneri.jan.bytecode.kernel.Function2;
import com.mauriciotogneri.jan.bytecode.objects.Array;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class List
{
    private List()
    {
    }

    // @ :: a % b [ A ] -> A
    // @ a b

    public static class $get<A> implements Function2<Num, Array<A>, A>
    {
        public static final $get instance = new $get();

        @Override
        public Function<Array<A>, A> call(final Num a)
        {
            return new Function<Array<A>, A>()
            {
                @Override
                public A call(final Array<A> b)
                {
                    return b.get(a);
                }
            };
        }
    }

    // ~ :: a % b [ A ] -> [ A ]
    // ~ a b

    public static class $remove<A> implements Function2<Num, Array<A>, Array<A>>
    {
        public static final $remove instance = new $remove();

        @Override
        public Function<Array<A>, Array<A>> call(final Num a)
        {
            return new Function<Array<A>, Array<A>>()
            {
                @Override
                public Array<A> call(final Array<A> b)
                {
                    return b.remove(a);
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

    // + :: a A b [ A ] -> [ A ]
    // + a b

    public static class $addBefore<A> implements Function2<A, Array<A>, Array<A>>
    {
        public static final $addBefore instance = new $addBefore();

        @Override
        public Function<Array<A>, Array<A>> call(final A a)
        {
            return new Function<Array<A>, Array<A>>()
            {
                @Override
                public Array<A> call(final Array<A> b)
                {
                    return b.addBefore(a);
                }
            };
        }
    }

    // + :: a A b [ A ] -> [ A ]
    // + a b

    public static class $addAfter<A> implements Function2<A, Array<A>, Array<A>>
    {
        public static final $addAfter instance = new $addAfter();

        @Override
        public Function<Array<A>, Array<A>> call(final A a)
        {
            return new Function<Array<A>, Array<A>>()
            {
                @Override
                public Array<A> call(final Array<A> b)
                {
                    return b.addAfter(a);
                }
            };
        }
    }
}