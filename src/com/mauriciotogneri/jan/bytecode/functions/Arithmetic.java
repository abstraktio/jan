package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.Function0;
import com.mauriciotogneri.jan.bytecode.kernel.Function1;
import com.mauriciotogneri.jan.bytecode.kernel.Function2;
import com.mauriciotogneri.jan.bytecode.objects.Num;

public class Arithmetic
{
    private Arithmetic()
    {
    }

    // + :: a % b % -> %
    // + a b

    public static class $add implements Function2<Function0<Num>, Function0<Num>, Function0<Num>>
    {
        public static final $add instance = new $add();

        @Override
        public Function1<Function0<Num>, Function0<Num>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Num>>()
            {
                @Override
                public Function0<Num> call(final Function0<Num> b)
                {
                    return new Function0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().add(b.call());
                        }
                    };
                }
            };
        }
    }

    // - :: a % b % -> %
    // - a b

    public static class $sub implements Function2<Function0<Num>, Function0<Num>, Function0<Num>>
    {
        public static final $sub instance = new $sub();

        @Override
        public Function1<Function0<Num>, Function0<Num>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Num>>()
            {
                @Override
                public Function0<Num> call(final Function0<Num> b)
                {
                    return new Function0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().sub(b.call());
                        }
                    };
                }
            };
        }
    }

    // * :: a % b % -> %
    // * a b

    public static class $mul implements Function2<Function0<Num>, Function0<Num>, Function0<Num>>
    {
        public static final $mul instance = new $mul();

        @Override
        public Function1<Function0<Num>, Function0<Num>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Num>>()
            {
                @Override
                public Function0<Num> call(final Function0<Num> b)
                {
                    return new Function0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().mul(b.call());
                        }
                    };
                }
            };
        }
    }

    // / :: a % b % -> %
    // / a b

    public static class $div implements Function2<Function0<Num>, Function0<Num>, Function0<Num>>
    {
        public static final $div instance = new $div();

        @Override
        public Function1<Function0<Num>, Function0<Num>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Num>>()
            {
                @Override
                public Function0<Num> call(final Function0<Num> b)
                {
                    return new Function0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().div(b.call());
                        }
                    };
                }
            };
        }
    }

    // /% :: a % b % -> %
    // /% a b

    public static class $divf implements Function2<Function0<Num>, Function0<Num>, Function0<Num>>
    {
        public static final $divf instance = new $divf();

        @Override
        public Function1<Function0<Num>, Function0<Num>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Num>>()
            {
                @Override
                public Function0<Num> call(final Function0<Num> b)
                {
                    return new Function0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().divf(b.call());
                        }
                    };
                }
            };
        }
    }

    // ++ :: a % -> %
    // ++ a

    public static class $inc implements Function1<Function0<Num>, Function0<Num>>
    {
        public static final $inc instance = new $inc();

        @Override
        public Function0<Num> call(final Function0<Num> a)
        {
            return new Function0<Num>()
            {
                @Override
                public Num call()
                {
                    return a.call().inc();
                }
            };
        }
    }

    // -- :: a % -> %
    // -- a

    public static class $dec implements Function1<Function0<Num>, Function0<Num>>
    {
        public static final $dec instance = new $dec();

        @Override
        public Function0<Num> call(final Function0<Num> a)
        {
            return new Function0<Num>()
            {
                @Override
                public Num call()
                {
                    return a.call().dec();
                }
            };
        }
    }

    // ^ :: a % b % -> %
    // ^ a b

    public static class $pow implements Function2<Function0<Num>, Function0<Num>, Function0<Num>>
    {
        public static final $pow instance = new $pow();

        @Override
        public Function1<Function0<Num>, Function0<Num>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Num>>()
            {
                @Override
                public Function0<Num> call(final Function0<Num> b)
                {
                    return new Function0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().pow(b.call());
                        }
                    };
                }
            };
        }
    }

    // % :: a % b % -> %
    // % a b

    public static class $mod implements Function2<Function0<Num>, Function0<Num>, Function0<Num>>
    {
        public static final $mod instance = new $mod();

        @Override
        public Function1<Function0<Num>, Function0<Num>> call(final Function0<Num> a)
        {
            return new Function1<Function0<Num>, Function0<Num>>()
            {
                @Override
                public Function0<Num> call(final Function0<Num> b)
                {
                    return new Function0<Num>()
                    {
                        @Override
                        public Num call()
                        {
                            return a.call().mod(b.call());
                        }
                    };
                }
            };
        }
    }
}