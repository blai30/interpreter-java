package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning;

    private boolean dumpFlag;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public int popRunStack() {
        return runStack.pop();
    }

    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            //runStack.dump(); // Used to dump runstack state.
            pc++;
        }
    }

}
