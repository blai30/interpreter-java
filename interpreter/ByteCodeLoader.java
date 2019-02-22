
package interpreter;

import interpreter.bytecode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


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
                System.out.println(byteCodeLine);
                String[] byteCodeTokens = byteCodeLine.split(" ");

                System.out.println(byteCodeTokens[0]);
                String className = CodeTable.getClassName(byteCodeTokens[0]);
                Class byteCodeClass = Class.forName("interpreter.bytecode." + className);
                ByteCode byteCode = (ByteCode) byteCodeClass.getDeclaredConstructor().newInstance();
                System.out.println(byteCodeTokens.length);

                byteCode.init(byteCodeTokens);
            }
        } catch (IOException e) {
            System.err.println("Exception in ByteCodeLoader loadCodes() .readLine: " + e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Exception in ByteCodeLoader loadCodes() Class.forName: " + e);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.err.println("Exception in ByteCodeLoader loadCodes() .getDeclaredConstructor: " + e);
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println("Exception in ByteCodeLoader loadCodes() .newInstance(): " + e);
            e.printStackTrace();
        }

        loadedProgram.resolveAddrs();

        return loadedProgram;
    }
}
