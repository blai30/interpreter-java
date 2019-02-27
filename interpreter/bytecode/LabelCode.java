package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

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

    }

    public String getLabel() {
        return label;
    }
}
