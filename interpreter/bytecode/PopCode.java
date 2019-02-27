package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {

    private int levelsToPop;

    @Override
    public void init(ArrayList<String> args) {
        levelsToPop = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        for (int i = 0; i < levelsToPop; i++) {
            vm.popRunStack();
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("POP " + levelsToPop);
    }
}
