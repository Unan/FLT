package fsm;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Integer");
        FSM fsmInteger = new FsmReader().readFsm("integer.json");

        System.out.println(new FsmRunner(fsmInteger, "12a", 0).max());
        System.out.println(new FsmRunner(fsmInteger, "123" , 2).max());
        System.out.println(new FsmRunner(fsmInteger, "123456" , 0).max());
        System.out.println(new FsmRunner(fsmInteger, "ab24c" , 2).max());
        System.out.println(new FsmRunner(fsmInteger, "qz123", 0).max());
        System.out.println(new FsmRunner(fsmInteger, "pl543", 1).max());

        System.out.println();
        System.out.println("Double");
        FSM fsmDouble = new FsmReader().readFsm("double.json");

        System.out.println(new FsmRunner(fsmDouble, "-7e+18").max());
        System.out.println(new FsmRunner(fsmDouble, "-7e-1.8").max());
        System.out.println(new FsmRunner(fsmDouble, "2.2e5").max());
        System.out.println(new FsmRunner(fsmDouble, "14").max());
        System.out.println(new FsmRunner(fsmDouble, "e14").max());
        System.out.println(new FsmRunner(fsmDouble, ".14").max());

    }
}
