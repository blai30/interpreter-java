package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {

    private int argsCount;

    @Override
    public void init(ArrayList<String> args) {
        argsCount = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(argsCount);
        vm.setArgsCount(argsCount); // Only used for dumping CallCode
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("ARGS " + argsCount);
    }
}
