package interpreter;

import java.util.Arrays;
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

    // Terminate the program from running
    public void halt() {
        isRunning = false;
    }

    public int peek() {
        return runStack.peek();
    }

    public void pushRunStack(Integer value) {
        runStack.push(value);
    }

    public int popRunStack() {
        return runStack.pop();
    }

    public void loadRunStack(Integer offset) {
        runStack.load(offset);
    }

    public void newFrameAt(int offset) {
        runStack.newFrameAt(offset);
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
        if (!returnAddrs.empty()) {
            return (int) returnAddrs.pop();
        }
        return 0;
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
            System.out.println(code); // FOR DEBUGGING
            System.out.println(Arrays.toString(runStack.printArray()));
            code.execute(this);
            if (dumpFlag) {
                System.out.println(code);
                runStack.dump(); // Used to dump runstack state.
            }
            pc++;
        }
    }

}
