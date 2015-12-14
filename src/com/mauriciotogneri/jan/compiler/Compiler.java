package com.mauriciotogneri.jan.compiler;

import com.mauriciotogneri.jan.compiler.lexical.LexicalAnalyzer;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.semantic.SemanticAnalyzer;
import com.mauriciotogneri.jan.compiler.syntactic.SyntacticAnalyzer;
import com.mauriciotogneri.jan.kernel.Program;

import java.util.List;

public class Compiler
{
    public Program compile(String sourcePath)
    {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        List<Token> tokens = lexicalAnalyzer.getTokens(sourcePath);

        SyntacticAnalyzer syntacticAnalyzer = new SyntacticAnalyzer();
        Program program = syntacticAnalyzer.getProgram(tokens);

        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        semanticAnalyzer.analyze(program);

        return program;
    }
}