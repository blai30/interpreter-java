package interpreter;

import java.util.ArrayList;
import java.util.Stack;

// Comments for each method is taken directly from theinterpreter.pdf
public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

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

    }

    public int peek() {
        // returns the top of the stack without removing the item.

        return runTimeStack.get(runTimeStack.size() - 1);
    }

    public int pop() {
        // removes an item from the top of the stack and returns
        // it.

        if (runTimeStack.size() > 0) {
            return runTimeStack.remove(runTimeStack.size() - 1);
        }
        return 0;
    }

    public void newFrameAt(int offset) {
        // creates a new frame in the RuntimeStack class. The
        // parameter offset is used to denote how many slots down
        // from the top of RuntimeStack for starting a new frame.

        framePointer.add(runTimeStack.size() - offset);
    }

    public void popFrame() {
        // we pop the top frame when we return from a function.
        // Before popping, the function’s return value is at the
        // top of the stack, so we’ll save the value, then pop the
        // top frame and then push the return value back onto the
        // stack. It is assumed return values are at the top of
        // the stack.

        Integer top = (!runTimeStack.isEmpty()) ? peek() : 0;
        Integer bot = (!framePointer.empty()) ? framePointer.pop() : 0;

        for (int i = runTimeStack.size() - 1; i >= bot; i--) {
            pop();
        }

        push(top);
    }

    public int store(int offset) {
        // Used to store values into variables. Store will pop the
        // top value of the stack and replace the value at the
        // given offset in the current frame. The value stored is
        // returned.

        runTimeStack.add(framePointer.peek() + offset, pop());

        return framePointer.peek() + offset;
    }

    public int load(int offset) {
        // Used to load variables onto the RuntimeStack from a
        // given offset within the current frame. This means we
        // will go to the offset in the current frame, copy the
        // value and push it to the top of the stack. No values
        // should be removed with load.

        return push(framePointer.peek() + offset);
    }

    public Integer push(Integer val) {
        // Used to load literal values onto the RuntimeStack. For
        // example, LIT 5 or LIT 0 will call push with val being 5
        // or val being 0.

        runTimeStack.add(val);

        return val;
    }
    
}
