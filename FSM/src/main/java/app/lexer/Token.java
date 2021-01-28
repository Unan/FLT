package app.lexer;

public class Token {

    private String entryType;
    private String value;

    public Token(String entryType, String value) {
        this.entryType = entryType;
        this.value = value;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("<%s, '%s'>", entryType, value);
    }
}