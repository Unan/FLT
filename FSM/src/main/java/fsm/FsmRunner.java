package fsm;

import component.InputType;
import component.TransitionFunction;

/**
 * Получает на вход класс-автомат {@link FSM}, которому подаёт на вход строку и управляет его состояниями
 * основываясь на его фукциях перехода
 * */
public class FsmRunner {

    private FSM fsm;
    private String input;
    private int skip;

    private String currentState;
    private int maxSuccess = 0;
    private boolean success = false;

    public FsmRunner(FSM fsm, String input, int skip) {
        if (skip >= input.length()) {
            throw new IllegalArgumentException("skip should be shorter then input length");
        }
        this.fsm = fsm;
        this.input = input;
        this.skip = skip;
        this.currentState = fsm.getStart();
    }

    public String max() {
        char[] inputArray = input.toCharArray();
        for (int i = skip; i < inputArray.length; i++) {
            currentState = step(inputArray[i]);
            if (currentState.equals(fsm.getFinish())) {
                break;
            } else {
                maxSuccess++;
            }
        }
        success = maxSuccess != 0;
        return result();
    }

    public String step(char c) {
        InputType inputType = fsm.getInputs().stream()
                .filter(it -> it.getInputPattern().matcher(String.valueOf(c)).find())
                .findFirst().orElseThrow();

        TransitionFunction transitionFunction = fsm.getTransitionFunctions().stream()
                .filter(tf -> tf.getOriginalState().equals(currentState))
                .filter(tf -> tf.getInputType().equals(inputType.getInputName()))
                .findFirst().orElseThrow();

        return transitionFunction.getTransitionState();
    }

    private String result() {
        return input + " -> " + maxSuccess + " : " + success;
    }

}
