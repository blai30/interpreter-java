package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ByteCode: READ
 * READ; Read an integer; prompt the user for input and push the value to the
 * stack. Make sure the input is validated.
 *
 * Example:
 * READ
 */
public class ReadCode extends ByteCode {
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        Scanner userInput = new Scanner(System.in);
        Integer readInput = null;

        // Continuously prompt user for input until a valid Integer is entered
        do {
            try {
                System.out.print("Enter an Integer: ");
                readInput = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input.");
            }
        } while (readInput == null);

        vm.pushRunStack(readInput);
    }

    @Override
    public void dump(VirtualMachine vm) {
        System.out.println("READ");
    }
}
