package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {

    private String function;

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty()) {
            function = args.get(0);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.popFrame();
        vm.setPC(vm.popPC());
    }
}
