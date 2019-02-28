package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: LIT
 * LIT n – load the literal value n
 * LIT 0 i – this form of Lit was generated to load 0 on the stack to
 * initialize the variable i to the value 0 and reserve space on the
 * runtime stack for i.
 *
 * Example:
 * LIT 5
 * LIT 0 i
 */
public class LitCode extends ByteCode {

    private int value;
    private String variable = "";

    @Override
    public void init(ArrayList<String> args) {
        value = Integer.parseInt(args.get(1));
        if (args.size() > 2) {
            variable = args.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushRunStack(value);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("LIT " + value + " " + variable + "\t" + "int " + variable);
    }
}
