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

    public List<String> readSnippet(String input) throws IOException {
        File file = new File(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(input))
                .getFile());

        StringBuilder stringBuilder = new StringBuilder();
        new BufferedReader(new InputStreamReader(new FileInputStream(file)))
                .lines()
                .forEach(stringBuilder::append);

        return Arrays.asList(stringBuilder.toString()
                .split("(?<=;)|(?=;)|(?<=\\s)|(?=\\s)|(?<=!)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))"));
    }

    public List<Token> tokenize(String inputFile) throws IOException {
        return readSnippet(inputFile).stream()
                .map(this::newGetToken)
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

    public Token newGetToken(String string) {
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

        if (resultEntry == null)
            return null;
        return new Token(resultEntry.getEntryType(), string.substring(0, maxSuccess));
    }

}
