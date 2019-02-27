package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {

    private int offset;
    private String variable = "";
    private Integer storedValue;

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            variable = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        storedValue = vm.peekRunStack();
        vm.storeRunStack(offset);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("STORE " + offset + " " + variable + "\t" + variable + " = " + storedValue);
    }
}
