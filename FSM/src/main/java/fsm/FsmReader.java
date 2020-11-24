package fsm;

import com.fasterxml.jackson.databind.ObjectMapper;
import component.InputType;
import component.TransitionFunction;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Считывает автомат в формате json и возвращает экземпляр класса {@link FSM}
 */
public class FsmReader {

    public FSM readFsm() throws IOException {

        FsmJson fsmJson = readFsmJson();

        List<InputType> inputTypes = fsmJson.getInputs().entrySet().stream()
                .map(entry -> new InputType(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        List<TransitionFunction> transitionFunctions = fsmJson.getMatrix().entrySet().stream()
                .flatMap(entry -> entry.getValue().entrySet().stream()
                        .map(entry1 -> new TransitionFunction(entry.getKey(), entry1.getKey(), entry1.getValue())))
                .collect(Collectors.toList());

        return new FSM(
                fsmJson.getStart(),
                fsmJson.getFinish(),
                inputTypes,
                transitionFunctions
        );
    }

    private FsmJson readFsmJson() throws IOException {
        File file = new File(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource("integer.json"))
                .getFile());

        StringBuilder stringBuilder = new StringBuilder();
        new BufferedReader(new InputStreamReader(new FileInputStream(file)))
                .lines()
                .forEach(stringBuilder::append);

        return new ObjectMapper().readValue(stringBuilder.toString(), FsmJson.class);
    }

    private static class FsmJson {

        private String start;
        private String finish;
        private Map<String, String> inputs;
        private Map<String, Map<String, String>> matrix;

        @Override
        public String toString() {
            return "FsmJson{" +
                    "start='" + start + '\'' +
                    ", finish='" + finish + '\'' +
                    ", inputs=" + inputs +
                    ", matrix=" + matrix +
                    '}';
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getFinish() {
            return finish;
        }

        public void setFinish(String finish) {
            this.finish = finish;
        }

        public Map<String, String> getInputs() {
            return inputs;
        }

        public void setInputs(Map<String, String> inputs) {
            this.inputs = inputs;
        }

        public Map<String, Map<String, String>> getMatrix() {
            return matrix;
        }

        public void setMatrix(Map<String, Map<String, String>> matrix) {
            this.matrix = matrix;
        }
    }
}
