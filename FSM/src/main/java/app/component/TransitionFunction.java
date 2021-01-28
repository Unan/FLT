package app.component;

/**
 * Функция перехода. Содержит информацию об исходном состоянии {@link TransitionFunction#originalState},
 * типе входного символа {@link TransitionFunction#inputType}
 * и состостояние в которое нужно перейти {@link TransitionFunction#transitionState}
 */
public class TransitionFunction {

    private String originalState;
    private String inputType;
    private String transitionState;

    public TransitionFunction(String originalState, String inputType, String transitionState) {
        this.originalState = originalState;
        this.inputType = inputType;
        this.transitionState = transitionState;
    }

    @Override
    public String toString() {
        return "TransitionFunction{" +
                "originalState='" + originalState + '\'' +
                ", inputType='" + inputType + '\'' +
                ", transitionState='" + transitionState + '\'' +
                '}';
    }

    public String getOriginalState() {
        return originalState;
    }

    public void setOriginalState(String originalState) {
        this.originalState = originalState;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getTransitionState() {
        return transitionState;
    }

    public void setTransitionState(String transitionState) {
        this.transitionState = transitionState;
    }
}
