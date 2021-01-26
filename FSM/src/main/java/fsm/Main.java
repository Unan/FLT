package fsm;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FSM fsmInteger = new FsmReader().readFsm("integer.json");
        FSM fsmDouble = new FsmReader().readFsm("double.json");
        FSM fsmSpace = new FsmReader().readFsm("space.json");
        FSM fsmSemicolon = new FsmReader().readFsm("semicolon.json");
        FSM fsmOperation = new FsmReader().readFsm("operation.json");


        System.out.println("Integer");
        System.out.println(new FsmRunner(fsmInteger, "12a", 0).max());
        System.out.println(new FsmRunner(fsmInteger, "123" , 2).max());
        System.out.println(new FsmRunner(fsmInteger, "123456" , 0).max());
        System.out.println(new FsmRunner(fsmInteger, "ab24c" , 2).max());
        System.out.println(new FsmRunner(fsmInteger, "qz123", 0).max());
        System.out.println(new FsmRunner(fsmInteger, "pl543", 1).max());

        System.out.println("\nDouble");
        System.out.println(new FsmRunner(fsmDouble, "-7e+18").max());
        System.out.println(new FsmRunner(fsmDouble, "-7e-1.8").max());
        System.out.println(new FsmRunner(fsmDouble, "2.2e5").max());
        System.out.println(new FsmRunner(fsmDouble, "14").max());
        System.out.println(new FsmRunner(fsmDouble, "e14").max());
        System.out.println(new FsmRunner(fsmDouble, ".14").max());

        System.out.println("\nSpace");
        System.out.println(new FsmRunner(fsmSpace, " ").max());
        System.out.println(new FsmRunner(fsmSpace, "  a  ").max());
        System.out.println(new FsmRunner(fsmSpace, "\n").max());


        System.out.println("\nSemicolon");
        System.out.println(new FsmRunner(fsmSemicolon, ";").max());

        System.out.println("\nOperation");
        System.out.println(new FsmRunner(fsmOperation, "+").max());
        System.out.println(new FsmRunner(fsmOperation, ">").max());
        System.out.println(new FsmRunner(fsmOperation, "*").max());
        System.out.println(new FsmRunner(fsmOperation, "=").max());


    }
}
