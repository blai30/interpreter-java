package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: RETURN
 * RETURN <funcname>; Return from the current function; <funcname> is
 * used as a comment to indicate the current function.
 * RETURN is generated for intrinsic functions.
 *
 * Example:
 * RETURN f<<2>>
 * RETURN
 * Note: returns with labels functions EXECUTE THE same as returns without labels.
 */
public class ReturnCode extends ByteCode {

    private String function = "";
    private String baseID = "";

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() > 1) {
            function = args.get(1);
            baseID = args.get(1).split("<<", 2)[0];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.popFrame();
        vm.returnPC();
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.print("RETURN " + function + "\t" + "exit " + baseID + ":");
        vm.setArgsCount(1); // Return should print only one value when dumping
        vm.printArgs();
        System.out.println();
    }
}
