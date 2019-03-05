package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: LOAD
 * LOAD n <id> ; push the value in the slot which is offset n from the
 * start of the frame onto the top of the stack; <id> is used as a comment
 * and for dumping, it’s the variable name where the data is loaded.
 *
 * Example:
 * LOAD 3
 * LOAD 2 i
 */
public class LoadCode extends ByteCode {

    private int offset;
    private String variable = "";

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            variable = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.loadRunStack(offset);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.print("LOAD " + offset);
        if (!variable.equals("")) {
            System.out.print(" " + variable + "\t" + "<load " + variable + ">");
        }
        System.out.println();
    }
}
