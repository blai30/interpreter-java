package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {

    private String function = "";
    private String baseID = "";

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() > 1) {
            function = args.get(1);
            baseID = function.split("<<", 2)[0];
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
        vm.printArgs();
        System.out.println();
    }
}
