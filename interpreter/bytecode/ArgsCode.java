package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: ARGS
 * ARGS n ; Used prior to calling a function.
 * n = # of args
 * this instruction is immediately followed by the CALL instruction; the
 * function has n args so ARGS n instructs the interpreter to set up a
 * new frame n down from the top of the runtime stack.
 * This will include the arguments in the new frame for the function.
 *
 * Example:
 * ARGS 4
 * ARGS 0
 * ARGS 2
 */
public class ArgsCode extends ByteCode {

    private int argsCount;

    @Override
    public void init(ArrayList<String> args) {
        argsCount = Integer.parseInt(args.get(1));
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrameAt(argsCount);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("ARGS " + argsCount);
        vm.setArgsCount(argsCount); // Only used for dumping CallCode
    }
}
