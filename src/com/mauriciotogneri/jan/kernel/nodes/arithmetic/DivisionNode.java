package com.mauriciotogneri.jan.kernel.nodes.arithmetic;

import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.kernel.Value;
import com.mauriciotogneri.jan.kernel.nodes.operations.BinaryNumericNode;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionNode extends BinaryNumericNode
{
    public DivisionNode(Token token)
    {
        super(token);
    }

    @Override
    public Value evaluate(BigDecimal operand1, BigDecimal operand2)
    {
        BigDecimal result = operand1.divide(operand2, 10, RoundingMode.HALF_EVEN);

        try
        {
            return Value.asNumber(BigDecimal.valueOf(result.intValueExact()));
        }
        catch (Exception e)
        {
            return Value.asNumber(result);
        }
    }
}