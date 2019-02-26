package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {

    private String op;

    @Override
    public void init(ArrayList<String> args) {
        op = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {

    }
}
