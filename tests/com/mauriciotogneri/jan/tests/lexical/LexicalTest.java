package com.mauriciotogneri.jan.tests.lexical;

import com.mauriciotogneri.jan.compiler.lexical.Character;
import com.mauriciotogneri.jan.compiler.lexical.LexicalAnalyzer;
import com.mauriciotogneri.jan.compiler.lexical.Token;
import com.mauriciotogneri.jan.compiler.lexical.Token.Type;
import com.mauriciotogneri.jan.exceptions.LexicalException;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LexicalTest
{
    @Test
    public void testTokens() throws Exception
    {
        List<Pair<String, Token.Type>> testBattery = new ArrayList<>();
        testBattery.add(new Pair<>("+", Type.ARITHMETIC_ADDITION));
        testBattery.add(new Pair<>(" ", Type.SPACE));
        testBattery.add(new Pair<>("-", Type.ARITHMETIC_SUBTRACTION));
        testBattery.add(new Pair<>(" ", Type.SPACE));
        testBattery.add(new Pair<>("*", Type.ARITHMETIC_MULTIPLICATION));
        testBattery.add(new Pair<>(" ", Type.SPACE));
        testBattery.add(new Pair<>("/", Type.ARITHMETIC_DIVISION));

        List<String> stringTokens = new ArrayList<>();

        for (Pair<String, ?> element : testBattery)
        {
            stringTokens.add(element.a);
        }

        File testFile = createTestFile(stringTokens);

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        List<Token> tokens = lexicalAnalyzer.getTokens(testFile);
        tokens.remove(tokens.size() - 1);

        Assert.assertEquals(tokens.size(), stringTokens.size());

        for (int i = 0; i < tokens.size(); i++)
        {
            Token token = tokens.get(i);
            Token.Type expected = testBattery.get(i).b;

            Assert.assertEquals(token.type, expected);
        }
    }

    @Test
    public void testInvalidCharacter() throws Exception
    {
        List<String> invalidCharacters = new ArrayList<>();
        invalidCharacters.add(Character.UNDERSCORE.toString());
        invalidCharacters.add(Character.GRAVE_ACCENT.toString());

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

        for (String character : invalidCharacters)
        {
            List<String> testBattery = new ArrayList<>();
            testBattery.add(character);

            File testFile = createTestFile(testBattery);

            try
            {
                lexicalAnalyzer.getTokens(testFile);

                Assert.fail("Character '" + character + "' should be invalid");
            }
            catch (LexicalException e)
            {
                // expected exception
            }
            catch (Exception e)
            {
                Assert.fail("Unexpected exception: " + e.getMessage());
            }
        }
    }

    private File createTestFile(List<String> stringTokens) throws IOException
    {
        File file = File.createTempFile("test", "jan");

        PrintWriter writer = new PrintWriter(file, "UTF-8");

        for (String stringToken : stringTokens)
        {
            writer.print(stringToken);
        }

        writer.close();

        return file;
    }

    public static class Pair<A, B>
    {
        public final A a;
        public final B b;

        public Pair(A a, B b)
        {
            this.a = a;
            this.b = b;
        }
    }
}