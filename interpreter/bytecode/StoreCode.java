package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {

    private int offset;
    private String variable;

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            variable = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer storedValue = vm.peekRunStack();
        vm.storeRunStack(offset);
    }

    @Override
    public void dump(VirtualMachine vm) {

    }
}
