import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is a logging framework for the blackjack game.
 * 
 * @since Fall 2023
 * @author Chris Sorros
 */
public class Logging {
    File logFile;

    /**
     * This function logs a message to a csv file.
     * 
     * @param group The log group to log to.
     * @param className The name of the class that called the log function.
     * @param functionName The name of the function that called the log function.
     * @param args The message to log.
     */
    private static void log(String group, String className, String functionName, String ...args) {
        String[] message = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            message[i] = args[i];
        }

        String logFileName = group + ".csv";
        File logFile = new File(logFileName);
        try {
            FileWriter writer = new FileWriter(logFile, true);
            //if the file is empty add headers first
            if (logFile.length() == 0) {
                switch(group){
                    case "player":
                        writer.append("Class, function, message\n");
                        break;
                    case "dealer":
                        writer.append("Class, function, message\n");
                        break;
                    case "game":
                        writer.append("Class, function, message\n");
                        break;
                    case "error":
                        writer.append("Class, function, message\n");
                        break;
                    case "info":
                        writer.append("Class, function, message\n");
                        break;
                    default:
                        log ("error", className, functionName, "Error:  " + group + " is not a valid log group!");
                }
            }
            writer.append(className + ", " + functionName + ", " + String.join(",", message) + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to log file");
            System.exit(500);
        }
    }

    /**
     * This function logs a message to a csv file.
     * 
     * @param group The log group to log to.
     * @param args The message to log.
     */
    public static void logToGroup(String group, String ...args) {
        //set the standard number of arguments that can be overridden in the switch statement
        int minArgs = 1;
        int maxArgs = 2;

        String className = Thread.currentThread().getStackTrace()[2].getClassName() + ".java";
        String functionName = Thread.currentThread().getStackTrace()[2].getMethodName() + "()";
        String lineNumber = "line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "";

        switch(group) {
            case "player":
                checkArgsAndLog(group, minArgs, maxArgs, className, functionName, lineNumber, args);
                break;
            case "dealer":
                checkArgsAndLog(group, minArgs, maxArgs, className, functionName, lineNumber, args);
                break;
            case "game":
                minArgs = 3;
                maxArgs = 3;
                checkArgsAndLog(group, minArgs, maxArgs, className, functionName, lineNumber, args);
                break;
            case "error":
                minArgs = 1;
                maxArgs = 1;
                checkArgsAndLog(group, minArgs, maxArgs, className, functionName, lineNumber, args);
                break;
            case "info":
                checkArgsAndLog(group, minArgs, maxArgs, className, functionName, lineNumber, args);
                break;
            default:
                log("error",  className, functionName + " " + lineNumber, "Invalid group");
        }
    }

    /**
     * This function verifies the number of arguments and logs the message.
     * 
     * @param group The log group to log to.
     * @param minArgs The minimum number of arguments.
     * @param maxArgs The maximum number of arguments.
     * @param className The name of the class that called the log function.
     * @param functionName The name of the function that called the log function.
     * @param lineNumber The line number of the function that called the log function.
     * @param args The message to log.
     */
    private static void checkArgsAndLog(String group, int minArgs, int maxArgs, String className, String functionName, String lineNumber, String ...args) {
        if(args.length > minArgs && args.length < maxArgs) {
            log ("error", className, functionName + " " + lineNumber, "Invalid number of arguments for log group " + group);
        } else {
            log(group, className, functionName + " " + lineNumber, args);
        }      
    }
}
