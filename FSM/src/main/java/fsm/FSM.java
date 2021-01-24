package fsm;

import component.InputType;
import component.TransitionFunction;

import java.util.List;

/**
 * Класс - автомат
 * Начальное состояние {@link FSM#start}
 * Множество конечных состояний {@link FSM#finish}
 * Алфавит {@link FSM#inputs}
 * Функции переходов {@link FSM#transitionFunctions}
 */
public class FSM {

    private String start;
    private List<String> finish;
    private List<InputType> inputs;
    private List<TransitionFunction> transitionFunctions;

    public FSM(String start, List<String> finish, List<InputType> inputs, List<TransitionFunction> transitionFunctions) {
        this.start = start;
        this.finish = finish;
        this.inputs = inputs;
        this.transitionFunctions = transitionFunctions;
    }

    @Override
    public String toString() {
        return "FSM{" +
                "start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                ", inputs=" + inputs +
                ", transitionFunctions=" + transitionFunctions +
                '}';
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<String> getFinish() {
        return finish;
    }

    public void setFinish(List<String> finish) {
        this.finish = finish;
    }

    public List<InputType> getInputs() {
        return inputs;
    }

    public void setInputs(List<InputType> inputs) {
        this.inputs = inputs;
    }

    public List<TransitionFunction> getTransitionFunctions() {
        return transitionFunctions;
    }

    public void setTransitionFunctions(List<TransitionFunction> transitionFunctions) {
        this.transitionFunctions = transitionFunctions;
    }
}
