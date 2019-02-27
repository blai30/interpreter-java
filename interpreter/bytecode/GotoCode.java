package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode implements AddressLabel {

    private String label;
    private int address;

    @Override
    public void init(ArrayList<String> args) {
        label = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setPC(address);
    }

    @Override
    public void dump(VirtualMachine vm) {

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
