package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.Function1;
import com.mauriciotogneri.jan.bytecode.kernel.Function2;
import com.mauriciotogneri.jan.bytecode.objects.Bool;

public class Logical
{
    private Logical()
    {
    }

    // & :: a ? b ? -> ?
    // & a b

    public static class $and implements Function2<Bool, Bool, Bool>
    {
        public static final $and instance = new $and();

        @Override
        public Function1<Bool, Bool> call(final Bool a)
        {
            return new Function1<Bool, Bool>()
            {
                @Override
                public Bool call(final Bool b)
                {
                    return a.and(b);
                }
            };
        }
    }

    // | :: a ? b ? -> ?
    // | a b

    public static class $or implements Function2<Bool, Bool, Bool>
    {
        public static final $or instance = new $or();

        @Override
        public Function1<Bool, Bool> call(final Bool a)
        {
            return new Function1<Bool, Bool>()
            {
                @Override
                public Bool call(final Bool b)
                {
                    return a.or(b);
                }
            };
        }
    }

    // ! :: a ? -> ?
    // ! a

    public static class $neg implements Function1<Bool, Bool>
    {
        public static final $neg instance = new $neg();

        @Override
        public Bool call(final Bool a)
        {
            return a.neg();
        }
    }
}