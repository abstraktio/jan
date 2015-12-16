package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.$F0;
import com.mauriciotogneri.jan.bytecode.kernel.$F1;
import com.mauriciotogneri.jan.bytecode.objects.$Num;

public class $Arithmetic
{
    private $Arithmetic()
    {
    }

    // + ( % a -> % b -> % )
    // + a b

    public static class $add implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Num>>>
    {
        public static final $add instance = new $add();

        @Override
        public $F1<$F0<$Num>, $F0<$Num>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Num>>()
            {
                @Override
                public $F0<$Num> call(final $F0<$Num> b)
                {
                    return new $F0<$Num>()
                    {
                        @Override
                        public $Num call()
                        {
                            return a.call().add(b.call());
                        }
                    };
                }
            };
        }
    }

    // - ( % a -> % b -> % )
    // - a b

    public static class $sub implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Num>>>
    {
        public static final $sub instance = new $sub();

        @Override
        public $F1<$F0<$Num>, $F0<$Num>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Num>>()
            {
                @Override
                public $F0<$Num> call(final $F0<$Num> b)
                {
                    return new $F0<$Num>()
                    {
                        @Override
                        public $Num call()
                        {
                            return a.call().sub(b.call());
                        }
                    };
                }
            };
        }
    }

    // * ( % a -> % b -> % )
    // * a b

    public static class $mul implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Num>>>
    {
        public static final $mul instance = new $mul();

        @Override
        public $F1<$F0<$Num>, $F0<$Num>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Num>>()
            {
                @Override
                public $F0<$Num> call(final $F0<$Num> b)
                {
                    return new $F0<$Num>()
                    {
                        @Override
                        public $Num call()
                        {
                            return a.call().mul(b.call());
                        }
                    };
                }
            };
        }
    }

    // / ( % a -> % b -> % )
    // / a b

    public static class $div implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Num>>>
    {
        public static final $div instance = new $div();

        @Override
        public $F1<$F0<$Num>, $F0<$Num>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Num>>()
            {
                @Override
                public $F0<$Num> call(final $F0<$Num> b)
                {
                    return new $F0<$Num>()
                    {
                        @Override
                        public $Num call()
                        {
                            return a.call().div(b.call());
                        }
                    };
                }
            };
        }
    }

    // /% ( % a -> % b -> % )
    // /% a b

    public static class $divf implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Num>>>
    {
        public static final $divf instance = new $divf();

        @Override
        public $F1<$F0<$Num>, $F0<$Num>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Num>>()
            {
                @Override
                public $F0<$Num> call(final $F0<$Num> b)
                {
                    return new $F0<$Num>()
                    {
                        @Override
                        public $Num call()
                        {
                            return a.call().divf(b.call());
                        }
                    };
                }
            };
        }
    }

    // ++ ( % a -> % )
    // ++ a

    public static class $inc implements $F1<$F0<$Num>, $F0<$Num>>
    {
        public static final $inc instance = new $inc();

        @Override
        public $F0<$Num> call(final $F0<$Num> a)
        {
            return new $F0<$Num>()
            {
                @Override
                public $Num call()
                {
                    return a.call().inc();
                }
            };
        }
    }

    // -- ( % a -> % )
    // -- a

    public static class $dec implements $F1<$F0<$Num>, $F0<$Num>>
    {
        public static final $dec instance = new $dec();

        @Override
        public $F0<$Num> call(final $F0<$Num> a)
        {
            return new $F0<$Num>()
            {
                @Override
                public $Num call()
                {
                    return a.call().dec();
                }
            };
        }
    }

    // ^ ( % a -> % b -> % )
    // ^ a b

    public static class $pow implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Num>>>
    {
        public static final $pow instance = new $pow();

        @Override
        public $F1<$F0<$Num>, $F0<$Num>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Num>>()
            {
                @Override
                public $F0<$Num> call(final $F0<$Num> b)
                {
                    return new $F0<$Num>()
                    {
                        @Override
                        public $Num call()
                        {
                            return a.call().pow(b.call());
                        }
                    };
                }
            };
        }
    }

    // % ( % a -> % b -> % )
    // % a b

    public static class $mod implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Num>>>
    {
        public static final $mod instance = new $mod();

        @Override
        public $F1<$F0<$Num>, $F0<$Num>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Num>>()
            {
                @Override
                public $F0<$Num> call(final $F0<$Num> b)
                {
                    return new $F0<$Num>()
                    {
                        @Override
                        public $Num call()
                        {
                            return a.call().mod(b.call());
                        }
                    };
                }
            };
        }
    }
}