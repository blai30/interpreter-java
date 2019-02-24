
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

        ArrayList<ByteCode> loadedByteCodes = new ArrayList<>();
        String fileLine;

        try {
            while ((fileLine = byteSource.readLine()) != null) {
                // Tokenize line into an ArrayList of strings, the first token will be the byte code class key
                ArrayList<String> tokens = new ArrayList<>(Arrays.asList(fileLine.split(" ")));

                // Remove the byte code class key from the ArrayList
                // Convert the byte code class key into a byte code class name
                // Create new byte code object from class name
                Class byteCodeClass = Class.forName("interpreter.bytecode." + CodeTable.getClassName(tokens.remove(0)));
                ByteCode newByteCode = (ByteCode) byteCodeClass.getDeclaredConstructor().newInstance();

                // Pass ArrayList of arguments to the byte code's init method even if it's empty
                newByteCode.init(tokens);

                loadedByteCodes.add(newByteCode);
            }
        } catch (IOException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() .readLine: " + e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() Class.forName: " + e);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() .getDeclaredConstructor: " + e);
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() .newInstance(): " + e);
            e.printStackTrace();
        }

        // Using the new Program constructor courtesy of Jonathan Julian
        Program loadedProgram = new Program(loadedByteCodes);
        loadedProgram.resolveAddrs();

        return loadedProgram;
    }
}
