package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: LABEL
 * LABEL <label>; target for branches;(FALSEBRANCH, GOTO and CALL)
 *
 * Example:
 * LABEL xyz<<3>>
 * LABEL Read
 */
public class LabelCode extends ByteCode {

    private String label;

    @Override
    public void init(ArrayList<String> args) {
        label = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {

    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("LABEL " + label);
    }

    public String getLabel() {
        return label;
    }
}
