package fsm;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new FsmReader().readFsm();

        FSM fsm = new FsmReader().readFsm();

        System.out.println(new FsmRunner(fsm, "12a", 0).max());
        System.out.println(new FsmRunner(fsm, "123" , 2).max());
        System.out.println(new FsmRunner(fsm, "123456" , 0).max());
        System.out.println(new FsmRunner(fsm, "ab24c" , 2).max());
        System.out.println(new FsmRunner(fsm, "qz123", 0).max());
        System.out.println(new FsmRunner(fsm, "pl543", 1).max());

    }
}
