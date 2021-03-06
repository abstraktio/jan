package com.mauriciotogneri.jan.compiler.syntactic.states;

import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.compiler.syntactic.State;
import com.mauriciotogneri.jan.compiler.syntactic.SyntacticException;
import com.mauriciotogneri.jan.kernel.Expression;
import com.mauriciotogneri.jan.kernel.Function;
import com.mauriciotogneri.jan.kernel.Program;

public class AnonymousExpressionEndState extends State
{
    private final Function function;
    private final Expression expression;

    public AnonymousExpressionEndState(Program program, Function function, Expression expression)
    {
        super(program);

        this.function = function;
        this.expression = expression;
    }

    @Override
    public State process(Token token)
    {
        if (token.type.isSeparator())
        {
            return new AnonymousExpressionStartState(getProgram(), function, expression);
        }
        else if (token.type == Type.NEW_LINE)
        {
            function.addExpression(expression);
            addFunction(function);

            return new InitialState(getProgram());
        }
        else
        {
            throw new SyntacticException(token);
        }
    }
}