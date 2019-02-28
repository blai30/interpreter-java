package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: STORE
 * STORE N <id> - pop the top of the stack; store the value into the offset
 * n from the start of the frame; <id> is used as a comment and for dumping,
 * it’s the variable name where the data is stored.
 *
 * Example:
 * STORE 3 i
 * STORE 2
 */
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
