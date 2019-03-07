package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;

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

    public int peekRunStack() {
        return runStack.peek();
    }

    public void pushRunStack(Integer value) {
        runStack.push(value);
    }

    public int popRunStack() {
        return runStack.pop();
    }

    public void storeRunStack(int offset) {
        runStack.store(offset);
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

    public void pushPC(int value) {
        returnAddrs.push(value);
    }

    public void returnPC() {
        if (!returnAddrs.isEmpty()) {
            setPC((int) returnAddrs.pop());
        }
    }

    public void savePC() {
        pushPC(pc);
    }

    public void setPC(int value) {
        pc = value;
    }

    public void setDump(boolean flag) {
        dumpFlag = flag;
    }

    public void setArgsCount(int count) {
        runStack.setArgsCount(count);
    }

    public void printArgs() {
        runStack.printArgs();
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

            // DumpCode does not get dumped and does not dump runStack state
            if (dumpFlag && !(code instanceof DumpCode)) {
                code.dump(this); // Dumping byte code happens after execute
                runStack.dump(); // Used to dump runstack state.
            }

            pc++;
        }

    }

}
