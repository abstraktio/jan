package com.mauriciotogneri.jan.bytecode.functions;

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

    public static class $add implements Function2<Num, Num, Num>
    {
        public static final $add instance = new $add();

        @Override
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.add(b);
                }
            };
        }
    }

    // - :: a % b % -> %
    // - a b

    public static class $sub implements Function2<Num, Num, Num>
    {
        public static final $sub instance = new $sub();

        @Override
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.sub(b);
                }
            };
        }
    }

    // * :: a % b % -> %
    // * a b

    public static class $mul implements Function2<Num, Num, Num>
    {
        public static final $mul instance = new $mul();

        @Override
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.mul(b);
                }
            };
        }
    }

    // / :: a % b % -> %
    // / a b

    public static class $div implements Function2<Num, Num, Num>
    {
        public static final $div instance = new $div();

        @Override
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.div(b);
                }
            };
        }
    }

    // /% :: a % b % -> %
    // /% a b

    public static class $divf implements Function2<Num, Num, Num>
    {
        public static final $divf instance = new $divf();

        @Override
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.divf(b);
                }
            };
        }
    }

    // ++ :: a % -> %
    // ++ a

    public static class $inc implements Function1<Num, Num>
    {
        public static final $inc instance = new $inc();

        @Override
        public Num call(final Num a)
        {
            return a.inc();
        }
    }

    // -- :: a % -> %
    // -- a

    public static class $dec implements Function1<Num, Num>
    {
        public static final $dec instance = new $dec();

        @Override
        public Num call(final Num a)
        {
            return a.dec();
        }
    }

    // ^ :: a % b % -> %
    // ^ a b

    public static class $pow implements Function2<Num, Num, Num>
    {
        public static final $pow instance = new $pow();

        @Override
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.pow(b);
                }
            };
        }
    }

    // % :: a % b % -> %
    // % a b

    public static class $mod implements Function2<Num, Num, Num>
    {
        public static final $mod instance = new $mod();

        @Override
        public Function1<Num, Num> call(final Num a)
        {
            return new Function1<Num, Num>()
            {
                @Override
                public Num call(final Num b)
                {
                    return a.mod(b);
                }
            };
        }
    }
}