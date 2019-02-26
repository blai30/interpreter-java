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

    public void halt() {

    }

    public int popRunStack() {
        return runStack.pop();
    }

    public void popFrame() {
        runStack.popFrame();
    }

    public int getPC() {
        return pc;
    }

    public void setPC(int value) {
        pc = value;
    }

    public void pushPC(int value) {
        returnAddrs.push(value);
    }

    public int popPC() {
        return (int) returnAddrs.pop();
    }

    public void store(int offset) {
        runStack.store(offset);
    }

    public void setDump(boolean flag) {
        dumpFlag = flag;
    }

    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        dumpFlag = false;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if (dumpFlag) {
                System.out.println(code);
                runStack.dump(); // Used to dump runstack state.
            }
            pc++;
        }
    }

}
