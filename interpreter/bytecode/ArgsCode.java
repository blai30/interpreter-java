package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {

    private int argsCount;

    @Override
    public void init(ArrayList<String> args) {
        argsCount = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(argsCount);
    }
}
