package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

import interpreter.bytecode.AddressLabel;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    // Courtesy of Jonathan Julian for giving me the idea of creating a separate constructor
    // I initially had an addByteCode(ByteCode newByteCode) method but decided to go with the constructor for efficiency
    public Program(ArrayList<ByteCode> loadedByteCodes) {
        program = loadedByteCodes;
    }

    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public int getSize() {
        return this.program.size();
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
//     * @param program Program object that holds a list of ByteCodes
     */
    public void resolveAddrs() {
        HashMap<String, Integer> addresses = new HashMap<>();

        // Store addresses of labels in HashMap
        for (int i = 0; i < program.size(); i++) {
            if (program.get(i) instanceof LabelCode) {
                addresses.put(((LabelCode) program.get(i)).getLabel(), i);
            }
        }

        for (ByteCode bc : program) {
            if (bc instanceof AddressLabel) {
                // Matches the label in bc with the label in the hashmap and sets the same address
                // bc.setAddress(addresses.get(bc.getLabel()));
                ((AddressLabel) bc).setAddress(addresses.get(((AddressLabel) bc).getLabel()));
            }
        }
    }
}
