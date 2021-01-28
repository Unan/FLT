package app.component;

import java.util.regex.Pattern;

/**
 * Паттерны(типы) входных данных(символов) описанных с помощью регулярных выражений
 */
public class InputType {

    private String inputName;
    private Pattern inputPattern;

    public InputType(String inputName, String inputPattern) {
        this.inputName = inputName;
        this.inputPattern = Pattern.compile(inputPattern);
    }

    @Override
    public String toString() {
        return "InputType{" +
                "inputName='" + inputName + '\'' +
                ", inputPattern=" + inputPattern +
                '}';
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public Pattern getInputPattern() {
        return inputPattern;
    }

    public void setInputPattern(Pattern inputPattern) {
        this.inputPattern = inputPattern;
    }
}
