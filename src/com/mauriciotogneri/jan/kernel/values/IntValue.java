package com.mauriciotogneri.jan.kernel.values;

import com.mauriciotogneri.jan.kernel.Value;

public class IntValue extends Value
{
	public IntValue(long value)
	{
		super(Type.INT, value);
	}
}