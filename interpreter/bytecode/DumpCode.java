package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {

    private boolean dump;

    @Override
    public void init(ArrayList<String> args) {
        dump = args.get(0).equals("ON");
    }

    @Override
    public void execute(VirtualMachine vm) {
//        vm.setDump(dump);
    }
}
