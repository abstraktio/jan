package com.mauriciotogneri.jan.compiler.lexical;

import com.mauriciotogneri.jan.exceptions.CompilationException;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class LexicalAnalyzer
{
    @NotNull
    public List<Token> getTokens(@NotNull String sourcePath)
    {
        char[] characters = getCharacters(sourcePath);

        StateMachine stateMachine = new StateMachine();

        return stateMachine.getTokens(characters);
    }

    @NotNull
    private char[] getCharacters(@NotNull String sourcePath)
    {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath)))
        {
            File file = new File(sourcePath);

            char[] result = new char[(int) file.length()];
            bufferedReader.read(result);

            return result;
        }
        catch (Exception e)
        {
            throw new CompilationException("Error reading file: " + sourcePath);
        }
    }
}