package app.lexer;

import com.fasterxml.jackson.core.JsonProcessingException;
import app.fsm.FsmRunner;
import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Lexer {

    private List<Entry> entries;

    public Lexer(String lexerFileName) throws FileNotFoundException, JsonProcessingException {
        this.entries = new EntryReader().readEntries(lexerFileName);
    }

    public String readSnippet(String input) throws FileNotFoundException {
        File file = new File(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(input))
                .getFile());

        return new BufferedReader(new InputStreamReader(new FileInputStream(file)))
                .lines().collect(Collectors.joining("\n"));
    }

    public List<Token> tokenize(String inputFile) throws IOException {
        return split(inputFile).stream()
                .map(this::getToken)
                .collect(Collectors.toList());
    }

    public List<String> split(String input) throws IOException {
        return Arrays.asList(readSnippet(input)
                .split("(?<=;)|(?=;)|(?<=\\s)|(?=\\s)|(?<=!)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))"));
    }

    public Token getToken(String string) {
        List<Pair<Entry, Integer>> result = new ArrayList<>();
        for (Entry entry : entries) {
            int max = new FsmRunner(entry.getFsm(), string).max();
            result.add(new Pair<>(entry, max));
        }

        List<Pair<Entry, Integer>> successResult = result.stream().filter(entryIntegerPair -> entryIntegerPair.getValue() > 0).collect(Collectors.toList());

        int maxPriority = 0;

        List<Pair<Entry, Integer>> priorityResult = new ArrayList<>();

        for (Pair<Entry, Integer> pair : successResult) {
            if (pair.getKey().getPriority() > maxPriority) {
                maxPriority = pair.getKey().getPriority();
            }
        }

        for (Pair<Entry, Integer> pair : successResult) {
            if (pair.getKey().getPriority() == maxPriority) {
                priorityResult.add(pair);
            }
        }

        Entry resultEntry = null;
        int maxSuccess = 0;
        for (Pair<Entry, Integer> pair : priorityResult) {
            if (pair.getValue() > maxSuccess) {
                maxSuccess = pair.getValue();
                resultEntry = pair.getKey();
            }
        }

        return resultEntry == null
                ? new Token("WARNING: Unexpected token:", string.substring(0, maxSuccess))
                : replaceSpace(new Token(resultEntry.getEntryType(), string.substring(0, maxSuccess)));
    }

    public Token replaceSpace(Token token) {
        if (token.getEntryType().equals("Space")) {
            token.setValue(token.getValue()
                    .replace(' ', '_')
                    .replace("\t", "\\t")
                    .replace("\n", "\\n"));
        }
        return token;
    }

}
