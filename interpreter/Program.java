package interpreter;

import java.util.ArrayList;
import interpreter.bytecode.ByteCode;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    // Courtesy of Jonathan Julian for giving me the idea of creating a separate constructor
    // I initially had an addByteCode(ByteCode newByteCode) method but decided to go with the constructor
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
     * @param program Program object that holds a list of ByteCodes
     */
    public void resolveAddrs() {
        for (int i = 0; i < program.size(); i++) {
            System.out.println(getCode(i));
        }
    }
}
