package app;

import app.lexer.Lexer;

import java.io.IOException;

public class LexerApp {
    public static void main(String[] args) throws IOException {
        new Lexer("lexer.json").tokenize("snippet/input.txt").forEach(System.out::println);
    }
}
