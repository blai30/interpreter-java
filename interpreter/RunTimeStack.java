package interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

// Comments for each method is taken directly from theinterpreter.pdf
public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    private int argsCount;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * The dump method should be a part of the RunTimeStack class. This method is called without any arguments.
     * Therefore, there is no way to pass any information about the VM or the bytecode classes into RunTimeStack.
     * As a result, you can’t really do much dumping inside RunTimeStack.dump() except for dumping the state of
     * the RunTimeStack itself. Also, NO BYTECODE CLASS OR SUBCLASS SHOULD BE CALLING DUMP.
     *
     * Void function used to dump the current state of the
     * RuntimeStack. When printing the runtime stack make sure
     * to include divisions between frames. If a frame is
     * empty, this must be shown as well.
     */
    public void dump() {
//        System.err.println("fps: " + Arrays.toString(framePointer.toArray())); // FOR DEBUGGING
//        System.err.println("rts: " + Arrays.toString(runTimeStack.toArray())); // FOR DEBUGGING

        int index = 0;
        if (framePointer.size() > 1) {
            for (int i = 1; i < framePointer.size(); i++) {
                System.out.print(runTimeStack.subList(index, framePointer.get(i)) + " ");
                index = framePointer.get(i);
            }
        }
        System.out.println(runTimeStack.subList(index, runTimeStack.size()) + "\n");
    }

    /**
     * returns the top of the stack without removing the item.
     *
     * @return element at the top of the runtime stack
     */
    public int peek() {
        if (!runTimeStack.isEmpty()) {
            return runTimeStack.get(runTimeStack.size() - 1);
        }

        return 0;
    }

    /**
     * removes an item from the top of the stack and returns it.
     * not allowed to pop past frame boundary
     *
     * @return element that was removed from the runtime stack
     */
    public int pop() {
        if (!runTimeStack.isEmpty() && framePointer.peek() < runTimeStack.size()) {
            return runTimeStack.remove(runTimeStack.size() - 1);
        }

        return 0;
    }

    /**
     * creates a new frame in the RuntimeStack class.
     * The parameter offset is used to denote how many slots down
     * from the top of RuntimeStack for starting a new frame.
     *
     * @param offset passed in by ArgsCode
     */
    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }

    /**
     * we pop the top frame when we return from a function.
     * Before popping, the function’s return value is at the
     * top of the stack, so we’ll save the value, then pop the
     * top frame and then push the return value back onto the
     * stack. It is assumed return values are at the top of
     * the stack.
     */
    public void popFrame() {
        Integer top = pop();
        Integer bot = framePointer.pop();

        for (int i = runTimeStack.size() - 1; i >= bot; i--) {
            pop();
        }

        push(top);
    }

    /**
     * Used to store values into variables. Store will pop the
     * top value of the stack and replace the value at the
     * given offset in the current frame. The value stored is
     * returned.
     *
     * @param offset index of the current frame where the value will go
     * @return the value that was just stored
     */
    public int store(int offset) {
        Integer top = pop();
        runTimeStack.set(framePointer.peek() + offset, top);

        return top;
    }

    /**
     * Used to load variables onto the RuntimeStack from a
     * given offset within the current frame. This means we
     * will go to the offset in the current frame, copy the
     * value and push it to the top of the stack. No values
     * should be removed with load.
     *
     * @param offset index of the current frame where the value is copied from
     * @return the value that was just loaded
     */
    public int load(int offset) {
        return push(runTimeStack.get(framePointer.peek() + offset));
    }

    /**
     * Used to load literal values onto the RuntimeStack. For
     * example, LIT 5 or LIT 0 will call push with val being 5
     * or val being 0.
     *
     * @param val value to be added to the top of runtime stack
     * @return the value that was just pushed
     */
    public Integer push(Integer val) {
        runTimeStack.add(val);

        return val;
    }


    // Only used for dumping CallCode
    public void setArgsCount(int count) {
        argsCount = count;
    }

    // CallCode and ReturnCode will call this method when dumping
    public void printArgs() {
        if (!runTimeStack.isEmpty()) {
            ArrayList<Integer> args = new ArrayList<>();
            for (int i = 0; i < argsCount; i++) {
                args.add(runTimeStack.get(runTimeStack.size() - 1));
            }
            System.out.print(args.toString().replace("[", "").replace("]", ""));
        }
    }

}
