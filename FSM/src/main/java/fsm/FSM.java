package fsm;

import util.Pair;

public class FSM {

    public static Pair<Boolean, Integer> isIntegers(String input, Integer offset) {
        return isIntegers(input.toCharArray(), offset);
    }

    public static Pair<Boolean, Integer> isIntegers(char[] input, Integer offset) {
        int i = offset;
        while (i < input.length && Character.isDigit(input[i])) {
            i++;
        }
        return i - offset > 0
                ? new Pair<>(true, i - offset)
                : new Pair<>(false, 0);
    }
}
