package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: DUMP
 * This bytecode is used to set the state of dumping in the virtual machine.
 * When dump is on, after the execution of each bytecode, the state of
 * the runtime stack is dumped to the console.
 *
 * Example:
 * DUMP ON
 * DUMP OFF
 */
public class DumpCode extends ByteCode {

    private boolean dump;

    @Override
    public void init(ArrayList<String> args) {
        dump = args.get(1).equals("ON");
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setDump(dump);
    }

    @Override
    public void dump(VirtualMachine vm) {

    }
}
