
package interpreter;

import interpreter.bytecode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        Program loadedProgram = new Program();
        String byteCodeLine;

        try {
            while ((byteCodeLine = byteSource.readLine()) != null) {
                // Tokenize line into byte code and arguments if there are any
                // [0] = byte code class key, [1] = non-tokenized string of arguments if there are any
                String[] tokenizedLine = byteCodeLine.split(" ", 2);

                // Store byte code class key into string
                String classKey = tokenizedLine[0];
                System.out.println(classKey);
                System.out.println(tokenizedLine[0]);

                // Create new byte code object from class key
                String className = CodeTable.getClassName(classKey);
                Class byteCodeClass = Class.forName("interpreter.bytecode." + className);
                ByteCode newByteCode = (ByteCode) byteCodeClass.getDeclaredConstructor().newInstance();

                // Tokenize string of arguments if there are any
                // Pass ArrayList of arguments to the byte code's init method
                if (tokenizedLine.length > 1) {
                    System.out.println(tokenizedLine[1]);
                    ArrayList<String> arguments = new ArrayList<>(Arrays.asList(tokenizedLine[1].split(" ")));
                    newByteCode.init(arguments);
                }

                // Add new byte code object to the program's ArrayList of byte code objects
                loadedProgram.addByteCode(newByteCode);
            }
        } catch (IOException e) {
            System.err.println("Exception in ByteCodeLoader.loadCodes() .readLine: " + e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Exception in ByteCodeLoader.loadCodes() Class.forName: " + e);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.err.println("Exception in ByteCodeLoader.loadCodes() .getDeclaredConstructor: " + e);
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println("Exception in ByteCodeLoader.loadCodes() .newInstance(): " + e);
            e.printStackTrace();
        }

        loadedProgram.resolveAddrs();

        return loadedProgram;
    }
}
