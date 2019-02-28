package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: WRITE
 * WRITE; Write the value of the top of the stack to output. Leave the value
 * on the top of the stack
 *
 * Example:
 * WRITE
 */
public class WriteCode extends ByteCode {
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        System.out.println(vm.peekRunStack());
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("WRITE");
    }
}
