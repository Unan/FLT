package lexer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fsm.FsmReader;
import fsm.FsmRunner;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Lexer {

    private List<Entry> entries;

    public Lexer(String lexerFileName) throws FileNotFoundException, JsonProcessingException {
        this.entries = new EntryReader().readEntries(lexerFileName);
    }

    public List<String> readSnippet(String input) throws IOException {
        File file = new File(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(input))
                .getFile());

        StringBuilder stringBuilder = new StringBuilder();
        new BufferedReader(new InputStreamReader(new FileInputStream(file)))
                .lines()
                .forEach(stringBuilder::append);

        return Arrays.asList(stringBuilder.toString().split("(?<=;)|(?=;)|(?<=\\s)|((?=\\s))"));
    }

    public List<Token> tokenize(String inputFile) throws IOException {
        return readSnippet(inputFile).stream()
                .map(this::getToken)
                .collect(Collectors.toList());
    }


    public Token getToken(String string) {
        for (Entry entry : entries) {
            int max = new FsmRunner(entry.getFsm(), string).max();
            if (max > 0) {
                return new Token(entry.getEntryType(), string.substring(0, max));
            }
        }
        return null;
    }

}
