package fsm;

import component.InputType;
import component.TransitionFunction;

/**
 * Получает на вход класс-автомат {@link FSM}, которому подаёт на вход строку и управляет его состояниями
 * основываясь на его фукциях перехода
 */
public class FsmRunner {

    private FSM fsm;
    private String input;
    private int skip;

    private String currentState;
    private boolean success = false;
    private int maxSuccess = 0;

    public FsmRunner(FSM fsm, String input) {
        if (skip >= input.length()) {
            throw new IllegalArgumentException("skip should be shorter then input length");
        }
        this.fsm = fsm;
        this.input = input;
        this.skip = 0;
        this.currentState = fsm.getStart();
    }

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
            try {
                currentState = step(inputArray[i]);
                if (fsm.getFinish().contains(currentState)) {
                    maxSuccess = i + 1;
                }
            } catch (Exception e) {
                break;
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
