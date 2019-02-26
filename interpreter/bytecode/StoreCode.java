package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {

    private int offset;
    private String variable;

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) {
            variable = args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer storedValue = vm.peekRunStack();
        vm.storeRunStack(offset);
    }
}
