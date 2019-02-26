package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode {
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        Scanner userInput = new Scanner(System.in);
        Integer readInput;
        do {
            System.out.print("Enter an Integer: ");
            readInput = userInput.nextInt();
        } while (readInput < 0);
        vm.pushRunStack(readInput);
    }
}
