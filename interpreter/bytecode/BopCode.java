package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {

    private String op;

    @Override
    public void init(ArrayList<String> args) {
        op = args.get(1);
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Second operand gets popped first
        int op2 = vm.popRunStack();
        int op1 = vm.popRunStack();

        if (op.equals("+")) {
            vm.pushRunStack(op1 + op2);
        }
        else if (op.equals("-")) {
            vm.pushRunStack(op1 - op2);
        }
        else if (op.equals("/")) {
            vm.pushRunStack(op1 / op2);
        }
        else if (op.equals("*")) {
            vm.pushRunStack(op1 * op2);
        }
        else if (op.equals("==")) {
            vm.pushRunStack((op1 == op2) ? 1 : 0);
        }
        else if (op.equals("!=")) {
            vm.pushRunStack((op1 != op2) ? 1 : 0);
        }
        else if (op.equals("<=")) {
            vm.pushRunStack((op1 <= op2) ? 1 : 0);
        }
        else if (op.equals(">")) {
            vm.pushRunStack((op1 > op2) ? 1 : 0);
        }
        else if (op.equals(">=")) {
            vm.pushRunStack((op1 >= op2) ? 1 : 0);
        }
        else if (op.equals("<")) {
            vm.pushRunStack((op1 < op2) ? 1 : 0);
        }
        else if (op.equals("|")) {
            // At least one operand is false
            vm.pushRunStack(((op1 != 0) || (op2 != 0)) ? 1 : 0);
        }
        else if (op.equals("&")) {
            // Both operands are false
            vm.pushRunStack(((op1 != 0) && (op2 != 0)) ? 1 : 0);
        }
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("BOP " + op);
    }
}
