package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {
    int levelsToPop;

    @Override
    public void init(ArrayList<String> args) {
        levelsToPop = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        for (int i = 0; i < levelsToPop; i++) {
            int temp = vm.popRunStack();
        }
    }
}
