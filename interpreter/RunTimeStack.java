package interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public void dump() {
        // TODO dump method
        // The dump method should be a part of the RunTimeStack class. This method is called without any arguments.
        // Therefore, there is no way to pass any information about the VM or the bytecode classes into RunTimeStack.
        // As a result, you can’t really do much dumping inside RunTimeStack.dump() except for dumping the state of
        // the RunTimeStack itself. Also, NO BYTECODE CLASS OR SUBCLASS SHOULD BE CALLING DUMP.
        //
        // Void function used to dump the current state of the
        // RuntimeStack. When printing the runtime stack make sure
        // to include divisions between frames. If a frame is
        // empty, this must be shown as well.

        System.err.println("frm: " + Arrays.toString(framePointer.toArray())); // FOR DEBUGGING
        System.err.println("run: " + Arrays.toString(runTimeStack.toArray())); // FOR DEBUGGING

        ArrayList<Integer> frameList = new ArrayList<>(framePointer);
        int index = 0;
        if (framePointer.size() > 1) {
            for (int i = 1; i < framePointer.size(); i++) {
                System.out.print(runTimeStack.subList(index, frameList.get(i)) + " ");
                index = frameList.get(i);
            }
        }
        System.out.println(runTimeStack.subList(index, runTimeStack.size()));
    }

    public int peek() {
        // returns the top of the stack without removing the item.

        if (runTimeStack.size() > 0) {
            return runTimeStack.get(runTimeStack.size() - 1);
        }

        return 0;
    }

    public int pop() {
        // removes an item from the top of the stack and returns
        // it.

        // Prevent popping past the frame boundary
        if (!framePointer.isEmpty()) {
            if (!runTimeStack.isEmpty() && framePointer.peek() < runTimeStack.size()) {
                return runTimeStack.remove(runTimeStack.size() - 1);
            }
        } else if (!runTimeStack.isEmpty()) {
            return runTimeStack.remove(runTimeStack.size() - 1);
        }

        return 0;
    }

    public void newFrameAt(int offset) {
        // creates a new frame in the RuntimeStack class. The
        // parameter offset is used to denote how many slots down
        // from the top of RuntimeStack for starting a new frame.

        framePointer.push(runTimeStack.size() - offset);
    }

    public void popFrame() {
        // we pop the top frame when we return from a function.
        // Before popping, the function’s return value is at the
        // top of the stack, so we’ll save the value, then pop the
        // top frame and then push the return value back onto the
        // stack. It is assumed return values are at the top of
        // the stack.

        Integer top = pop();

        if (!framePointer.isEmpty()) {
            for (int i = framePointer.pop(); i < runTimeStack.size(); i++) {
                pop();
            }
        }

        push(top);
    }

    public int store(int offset) {
        // Used to store values into variables. Store will pop the
        // top value of the stack and replace the value at the
        // given offset in the current frame. The value stored is
        // returned.

        // If storing to the same index as what was just popped, must peek first
        Integer top = peek();
        runTimeStack.set((!framePointer.isEmpty()) ? framePointer.peek() + offset : offset, top);
        pop();

        return top;
    }

    public int load(int offset) {
        // Used to load variables onto the RuntimeStack from a
        // given offset within the current frame. This means we
        // will go to the offset in the current frame, copy the
        // value and push it to the top of the stack. No values
        // should be removed with load.

        return push(runTimeStack.get(framePointer.peek() + offset));
    }

    public Integer push(Integer val) {
        // Used to load literal values onto the RuntimeStack. For
        // example, LIT 5 or LIT 0 will call push with val being 5
        // or val being 0.

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
