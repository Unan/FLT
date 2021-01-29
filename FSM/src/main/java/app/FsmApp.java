package app;

import app.fsm.FSM;
import app.fsm.FsmReader;
import app.fsm.FsmRunner;

import java.io.IOException;

public class FsmApp {
    public static void main(String[] args) throws IOException {
        FSM fsmInteger = new FsmReader().readFsm("fsm/integer.json");
        FSM fsmDouble = new FsmReader().readFsm("fsm/double.json");
        FSM fsmSpace = new FsmReader().readFsm("fsm/space.json");
        FSM fsmSemicolon = new FsmReader().readFsm("fsm/semicolon.json");
        FSM fsmOperation = new FsmReader().readFsm("fsm/operation.json");
        FSM fsmKeywordInteger = new FsmReader().readFsm("fsm/kw-integer.json");
        FSM fsmKeywordDouble = new FsmReader().readFsm("fsm/kw-double.json");
        FSM fsmId = new FsmReader().readFsm("fsm/id.json");


        System.out.println("Integer");
        System.out.println(new FsmRunner(fsmInteger, "12a", 0).maxWithResult());
        System.out.println(new FsmRunner(fsmInteger, "123" , 2).maxWithResult());
        System.out.println(new FsmRunner(fsmInteger, "123456" , 0).maxWithResult());
        System.out.println(new FsmRunner(fsmInteger, "ab24c" , 2).maxWithResult());
        System.out.println(new FsmRunner(fsmInteger, "qz123", 0).maxWithResult());
        System.out.println(new FsmRunner(fsmInteger, "pl543", 1).maxWithResult());

        System.out.println("\nDouble");
        System.out.println(new FsmRunner(fsmDouble, "-7e+18").maxWithResult());
        System.out.println(new FsmRunner(fsmDouble, "-7e-1.8").maxWithResult());
        System.out.println(new FsmRunner(fsmDouble, "2.2e5").maxWithResult());
        System.out.println(new FsmRunner(fsmDouble, "14").maxWithResult());
        System.out.println(new FsmRunner(fsmDouble, "e14").maxWithResult());
        System.out.println(new FsmRunner(fsmDouble, ".14").maxWithResult());

        System.out.println("\nSpace");
        System.out.println(new FsmRunner(fsmSpace, " ").maxWithResult());
        System.out.println(new FsmRunner(fsmSpace, "  a  ").maxWithResult());
        System.out.println(new FsmRunner(fsmSpace, "\n").maxWithResult());

        System.out.println("\nSemicolon");
        System.out.println(new FsmRunner(fsmSemicolon, ";").maxWithResult());

        System.out.println("\nOperation");
        System.out.println(new FsmRunner(fsmOperation, "+").maxWithResult());
        System.out.println(new FsmRunner(fsmOperation, ">").maxWithResult());
        System.out.println(new FsmRunner(fsmOperation, "*").maxWithResult());
        System.out.println(new FsmRunner(fsmOperation, "=").maxWithResult());

        System.out.println("\nKeyword Integer");
        System.out.println(new FsmRunner(fsmKeywordInteger, "in").maxWithResult());
        System.out.println(new FsmRunner(fsmKeywordInteger, "int").maxWithResult());
        System.out.println(new FsmRunner(fsmKeywordInteger, "intgr").maxWithResult());
        System.out.println(new FsmRunner(fsmKeywordInteger, "integer").maxWithResult());

        System.out.println("\nKeyword Double");
        System.out.println(new FsmRunner(fsmKeywordDouble, "double").maxWithResult());
        System.out.println(new FsmRunner(fsmKeywordDouble, "doub").maxWithResult());

        System.out.println("\nId");
        System.out.println(new FsmRunner(fsmId, "a").maxWithResult());

    }
}
