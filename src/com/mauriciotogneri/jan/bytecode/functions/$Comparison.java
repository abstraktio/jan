package com.mauriciotogneri.jan.bytecode.functions;

import com.mauriciotogneri.jan.bytecode.kernel.$Eq;
import com.mauriciotogneri.jan.bytecode.kernel.$F0;
import com.mauriciotogneri.jan.bytecode.kernel.$F1;
import com.mauriciotogneri.jan.bytecode.objects.$Bool;
import com.mauriciotogneri.jan.bytecode.objects.$Char;
import com.mauriciotogneri.jan.bytecode.objects.$Num;

public class $Comparison
{
    private $Comparison()
    {
    }

    public static boolean equals(Object a, Object b)
    {
        if ((a instanceof $F0) && (b instanceof $F0))
        {
            Object aValue = (($F0) a).call();
            Object bValue = (($F0) b).call();

            return (aValue instanceof $Eq) && (bValue instanceof $Eq) && aValue.equals(bValue);
        }

        return false;
    }

    // = ( $A a -> $A b -> ? )
    // = a b

    public static class $equals<A extends $F0> implements $F1<A, $F1<A, $F0<$Bool>>>
    {
        public static final $equals<$F0<$Num>> $equalsNum = new $equals<>();
        public static final $equals<$F0<$Bool>> $equalsBool = new $equals<>();
        public static final $equals<$F0<$Char>> $equalsChar = new $equals<>();

        @Override
        public $F1<A, $F0<$Bool>> call(final A a)
        {
            return new $F1<A, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final A b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return $Bool.create($Comparison.equals(a, b));
                        }
                    };
                }
            };
        }
    }

    // != ( $A a -> $A b -> ? )
    // != a b

    public static class $notEquals<A extends $F0> implements $F1<A, $F1<A, $F0<$Bool>>>
    {
        public static final $notEquals<$F0<$Num>> $notEqualsNum = new $notEquals<>();
        public static final $notEquals<$F0<$Bool>> $notEqualsBool = new $notEquals<>();
        public static final $notEquals<$F0<$Char>> $notEqualsChar = new $notEquals<>();

        @Override
        public $F1<A, $F0<$Bool>> call(final A a)
        {
            return new $F1<A, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final A b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return $Bool.create(!$Comparison.equals(a, b));
                        }
                    };
                }
            };
        }
    }

    // < ( % a -> % b -> ? )
    // < a b

    public static class $less implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Bool>>>
    {
        public static final $less instance = new $less();

        @Override
        public $F1<$F0<$Num>, $F0<$Bool>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final $F0<$Num> b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return a.call().less(b.call());
                        }
                    };
                }
            };
        }
    }

    // <= ( % a -> % b -> ? )
    // <= a b

    public static class $lessOrEqual implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Bool>>>
    {
        public static final $lessOrEqual instance = new $lessOrEqual();

        @Override
        public $F1<$F0<$Num>, $F0<$Bool>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final $F0<$Num> b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return a.call().lessOrEqual(b.call());
                        }
                    };
                }
            };
        }
    }

    // > ( % a -> % b -> ? )
    // > a b

    public static class $greater implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Bool>>>
    {
        public static final $greater instance = new $greater();

        @Override
        public $F1<$F0<$Num>, $F0<$Bool>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final $F0<$Num> b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return a.call().greater(b.call());
                        }
                    };
                }
            };
        }
    }

    // >= ( % a -> % b -> ? )
    // >= a b

    public static class $greaterOrEqual implements $F1<$F0<$Num>, $F1<$F0<$Num>, $F0<$Bool>>>
    {
        public static final $greaterOrEqual instance = new $greaterOrEqual();

        @Override
        public $F1<$F0<$Num>, $F0<$Bool>> call(final $F0<$Num> a)
        {
            return new $F1<$F0<$Num>, $F0<$Bool>>()
            {
                @Override
                public $F0<$Bool> call(final $F0<$Num> b)
                {
                    return new $F0<$Bool>()
                    {
                        @Override
                        public $Bool call()
                        {
                            return a.call().greaterOrEqual(b.call());
                        }
                    };
                }
            };
        }
    }
}