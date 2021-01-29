package app;

import app.lexer.Lexer;

import java.io.IOException;

public class LexerApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Code snippet: ");
        System.out.println(new Lexer("lexer/lexer.json").readSnippet("codeSnippet/input.txt"));

        System.out.println("\nTokenize:");
        new Lexer("lexer/lexer.json")
                .tokenize("codeSnippet/input.txt")
                .forEach(System.out::println);
    }
}
