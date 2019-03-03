package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: CALL
 * CALL <funcname> - transfer control to the indicated function. Make
 * sure to save where the function should return to.
 *
 * Example:
 * CALL f
 * CALL f<<3>>
 * Note: CALL f and Call f<<3>> are executed in the same way.
 */
public class CallCode extends ByteCode implements AddressLabel {

    private String label;
    private int address;
    private String baseID;

    @Override
    public void init(ArrayList<String> args) {
        label = args.get(1);
        baseID = args.get(1).split("<<", 2)[0];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.savePC();
        // Offset by 1 to allow printing during dump
        vm.setPC(address - 1);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.print("CALL " + label + "\t" + baseID + "(");
        vm.printArgs();
        System.out.println(")");
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setAddress(int newAddress) {
        address = newAddress;
    }
}
