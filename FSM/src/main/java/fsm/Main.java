package fsm;

public class Main {
    public static void main(String[] args) {
        System.out.println(FSM.isIntegers("1234", 0));
        System.out.println(FSM.isIntegers("123ааа", 0));
        System.out.println(FSM.isIntegers("аа12а", 1));
        System.out.println(FSM.isIntegers("1aaaa", 1));
    }
}
