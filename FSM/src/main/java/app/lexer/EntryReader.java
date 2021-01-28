package app.lexer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.fsm.FsmReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntryReader {

    public List<Entry> readEntries(String fileName) throws FileNotFoundException, JsonProcessingException {
        File file = new File(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(fileName))
                .getFile());

        StringBuilder stringBuilder = new StringBuilder();
        new BufferedReader(new InputStreamReader(new FileInputStream(file)))
                .lines()
                .forEach(stringBuilder::append);

        List<EntryJson> entryJsons =  new ObjectMapper().readValue(stringBuilder.toString(),
                new TypeReference<ArrayList<EntryJson>>() {
                });

        return entryJsons.stream()
                .map(entryJson -> {
                    try {
                        return new Entry(entryJson.getType(), new FsmReader().readFsm(entryJson.getPath()), entryJson.getPriority());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }


    private static class EntryJson {

        private String type;
        private String path;
        private int priority;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}
