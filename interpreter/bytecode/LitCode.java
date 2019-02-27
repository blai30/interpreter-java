package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

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
