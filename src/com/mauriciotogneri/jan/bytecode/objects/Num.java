package com.mauriciotogneri.jan.bytecode.objects;

import com.mauriciotogneri.jan.bytecode.kernel.Constant;

public class Num implements Constant<Num>
{
    private final boolean isDecimal;
    private final double value;

    protected Num(long value)
    {
        this.isDecimal = false;
        this.value = value;
    }

    protected Num(double value)
    {
        this.isDecimal = true;
        this.value = value;
    }

    private Num get(double value, boolean decimal)
    {
        return (decimal) ? new Num(value) : new Num((long) value);
    }

    public double get()
    {
        return value;
    }

    public Num add(Num num)
    {
        return get(this.value + num.value, this.isDecimal || num.isDecimal);
    }

    public Num sub(Num num)
    {
        return get(this.value - num.value, this.isDecimal || num.isDecimal);
    }

    public Num mul(Num num)
    {
        return get(this.value * num.value, this.isDecimal || num.isDecimal);
    }

    public Num div(Num num)
    {
        return get(this.value / num.value, this.isDecimal || num.isDecimal);
    }

    public Num divFloat(Num num)
    {
        return get(this.value / num.value, true);
    }

    public Num inc()
    {
        return get(this.value + 1, this.isDecimal);
    }

    public Num dec()
    {
        return get(this.value - 1, this.isDecimal);
    }

    public Num pow(Num num)
    {
        return get(Math.pow(this.value, num.value), this.isDecimal || num.isDecimal);
    }

    public Num mod(Num num)
    {
        return get(this.value % num.value, false);
    }

    public Bool less(Num num)
    {
        return new Bool(this.value < num.value);
    }

    public Bool lessOrEqual(Num num)
    {
        return new Bool(this.value <= num.value);
    }

    public Bool greater(Num num)
    {
        return new Bool(this.value > num.value);
    }

    public Bool greaterOrEqual(Num num)
    {
        return new Bool(this.value >= num.value);
    }

    @Override
    public String toString()
    {
        return (this.isDecimal) ? String.valueOf(value) : String.valueOf((long) value);
    }

    @Override
    public Bool isEqual(Constant<Num> object)
    {
        return Bool.create(isEquivalent(object));
    }

    @Override
    public Bool isNotEqual(Constant<Num> object)
    {
        return Bool.create(!isEquivalent(object));
    }

    private boolean isEquivalent(Constant<Num> object)
    {
        return (Double.compare(object.call().value, this.value) == 0);
    }

    //    @Override
    //    public boolean equals(Object o)
    //    {
    //        if (this == o)
    //        {
    //            return true;
    //        }
    //        else if (o == null || getClass() != o.getClass())
    //        {
    //            return false;
    //        }
    //
    //        Num num = (Num) o;
    //
    //        return Double.compare(num.value, value) == 0;
    //    }
    //
    //    @Override
    //    public int hashCode()
    //    {
    //        int result = (isDecimal ? 1 : 0);
    //        long temp = Double.doubleToLongBits(value);
    //        result = 31 * result + (int) (temp ^ (temp >>> 32));
    //
    //        return result;
    //    }

    public static Num create(long value)
    {
        return new Num(value);
    }

    public static Num create(double value)
    {
        return new Num(value);
    }

    @Override
    public Num call()
    {
        return this;
    }
}