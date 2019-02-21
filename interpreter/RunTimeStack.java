package interpreter;

import java.util.ArrayList;
import java.util.Stack;

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

    }
    
}
