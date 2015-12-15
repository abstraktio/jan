package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.Function0;
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

    public static class $and implements Function2<Function0<Bool>, Function0<Bool>, Function0<Bool>>
    {
        public static final $and instance = new $and();

        @Override
        public Function1<Function0<Bool>, Function0<Bool>> call(final Function0<Bool> a)
        {
            return new Function1<Function0<Bool>, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final Function0<Bool> b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return a.call().and(b.call());
                        }
                    };
                }
            };
        }
    }

    // | :: a ? b ? -> ?
    // | a b

    public static class $or implements Function2<Function0<Bool>, Function0<Bool>, Function0<Bool>>
    {
        public static final $or instance = new $or();

        @Override
        public Function1<Function0<Bool>, Function0<Bool>> call(final Function0<Bool> a)
        {
            return new Function1<Function0<Bool>, Function0<Bool>>()
            {
                @Override
                public Function0<Bool> call(final Function0<Bool> b)
                {
                    return new Function0<Bool>()
                    {
                        @Override
                        public Bool call()
                        {
                            return a.call().or(b.call());
                        }
                    };
                }
            };
        }
    }

    // ! :: a ? -> ?
    // ! a

    public static class $neg implements Function1<Function0<Bool>, Function0<Bool>>
    {
        public static final $neg instance = new $neg();

        @Override
        public Function0<Bool> call(final Function0<Bool> a)
        {
            return new Function0<Bool>()
            {
                @Override
                public Bool call()
                {
                    return a.call().neg();
                }
            };
        }
    }
}