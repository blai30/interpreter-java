package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {

    private int value;
    private String variable;

    @Override
    public void init(ArrayList<String> args) {
        value = Integer.parseInt(args.get(0));
        if (args.size() > 1) {
            variable = args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {

    }
}
