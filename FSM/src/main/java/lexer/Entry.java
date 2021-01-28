package lexer;

import fsm.FSM;

public class Entry {

    private String entryType;
    private FSM fsm;
    private int priority;

    public Entry(String entryType, FSM fsm, int priority) {
        this.entryType = entryType;
        this.fsm = fsm;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryType='" + entryType + '\'' +
                ", fsm=" + fsm +
                ", priority=" + priority +
                '}';
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public FSM getFsm() {
        return fsm;
    }

    public void setFsm(FSM fsm) {
        this.fsm = fsm;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}