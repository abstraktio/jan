package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.$F1;
import com.mauriciotogneri.jan.bytecode.objects.$Array;
import com.mauriciotogneri.jan.bytecode.objects.$Num;

public class $List
{
    private $List()
    {
    }

    // @ :: a % b [ $A ] -> $A
    // @ a b

    public static class $get<A> implements $F1<$Num, $F1<$Array<A>, A>>
    {
        public static final $get instance = new $get();

        @Override
        public $F1<$Array<A>, A> call(final $Num a)
        {
            return new $F1<$Array<A>, A>()
            {
                @Override
                public A call(final $Array<A> b)
                {
                    return b.get(a);
                }
            };
        }
    }

    // ~ :: a % b [ $A ] -> [ $A ]
    // ~ a b

    public static class $remove<A> implements $F1<$Num, $F1<$Array<A>, $Array<A>>>
    {
        public static final $remove instance = new $remove();

        @Override
        public $F1<$Array<A>, $Array<A>> call(final $Num a)
        {
            return new $F1<$Array<A>, $Array<A>>()
            {
                @Override
                public $Array<A> call(final $Array<A> b)
                {
                    return b.remove(a);
                }
            };
        }
    }

    // # :: a [ $A ] -> %
    // # a

    public static class $length<A> implements $F1<$Array<A>, $Num>
    {
        public static final $length instance = new $length();

        @Override
        public $Num call(final $Array<A> a)
        {
            return a.length();
        }
    }

    // +> :: a $A b [ $A ] -> [ $A ]
    // +> a b

    public static class $addBefore<A> implements $F1<A, $F1<$Array<A>, $Array<A>>>
    {
        public static final $addBefore instance = new $addBefore();

        @Override
        public $F1<$Array<A>, $Array<A>> call(final A a)
        {
            return new $F1<$Array<A>, $Array<A>>()
            {
                @Override
                public $Array<A> call(final $Array<A> b)
                {
                    return b.addBefore(a);
                }
            };
        }
    }

    // >+ :: a $A b [ $A ] -> [ $A ]
    // >+ a b

    public static class $addAfter<A> implements $F1<A, $F1<$Array<A>, $Array<A>>>
    {
        public static final $addAfter instance = new $addAfter();

        @Override
        public $F1<$Array<A>, $Array<A>> call(final A a)
        {
            return new $F1<$Array<A>, $Array<A>>()
            {
                @Override
                public $Array<A> call(final $Array<A> b)
                {
                    return b.addAfter(a);
                }
            };
        }
    }
}