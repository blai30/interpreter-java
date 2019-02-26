package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {

    private String op;

    @Override
    public void init(ArrayList<String> args) {
        op = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
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
            vm.pushRunStack(((op1 != 0) || (op2 != 0)) ? 1 : 0);
        }
        else if (op.equals("&")) {
            vm.pushRunStack(((op1 != 0) && (op2 != 0)) ? 1 : 0);
        }
    }
}
