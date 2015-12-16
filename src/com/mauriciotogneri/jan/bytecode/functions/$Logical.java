package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.$F0;
import com.mauriciotogneri.jan.bytecode.kernel.$F1;
import com.mauriciotogneri.jan.bytecode.objects.$Bool;

public class $Logical
{
    private $Logical()
    {
    }

    // & ( ? a -> ? b -> ? )
    // & a b

    public static class $and implements $F1<$F0<$Bool>, $F1<$F0<$Bool>, $F0<$Bool>>>
    {
        public static final $and instance = new $and();

        @Override
        public $F1<$F0<$Bool>, $F0<$Bool>> call(final $F0<$Bool> a)
        {
            return new $F1<$F0<$Bool>, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final $F0<$Bool> b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return a.call().and(b.call());
                        }
                    };
                }
            };
        }
    }

    // | ( ? a -> ? b -> ? )
    // | a b

    public static class $or implements $F1<$F0<$Bool>, $F1<$F0<$Bool>, $F0<$Bool>>>
    {
        public static final $or instance = new $or();

        @Override
        public $F1<$F0<$Bool>, $F0<$Bool>> call(final $F0<$Bool> a)
        {
            return new $F1<$F0<$Bool>, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final $F0<$Bool> b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return a.call().or(b.call());
                        }
                    };
                }
            };
        }
    }

    // ! ( ? a -> ? )
    // ! a

    public static class $neg implements $F1<$F0<$Bool>, $F0<$Bool>>
    {
        public static final $neg instance = new $neg();

        @Override
        public $F0<$Bool> call(final $F0<$Bool> a)
        {
            return new $F0<$Bool>()
            {
                @Override
                public $Bool call()
                {
                    return a.call().neg();
                }
            };
        }
    }
}