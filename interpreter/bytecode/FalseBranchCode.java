package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

/**
 * ByteCode: FALSEBRANCH
 * FALSEBRANCH <label> pop the top of the stack; if it is false (0) then
 * branch to <label> else execute the next bytecode
 *
 * Example:
 * FALSEBRANCH xyz<<3>>
 */
public class FalseBranchCode extends ByteCode implements AddressLabel {

    private String label;
    private int address;

    @Override
    public void init(ArrayList<String> args) {
        label = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {
        if (vm.popRunStack() == 0) {
            // Offset by 1 to allow printing during dump
            vm.setPC(address - 1);
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("FALSEBRANCH " + label);
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
