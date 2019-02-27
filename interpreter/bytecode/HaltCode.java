package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class HaltCode extends ByteCode {
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.halt();
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("HALT");
    }
}
