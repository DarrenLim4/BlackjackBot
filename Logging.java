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
    static final String[] errorHeaders = {"Class", "Function", "Message"};
    static final String[] playerHeaders = {"Message"};
    static final String[] dealerHeaders = {"Message"};
    static final String[] gameHeaders = {"hand", "action"};
    static final String[] infoHeaders = {"Message"};
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
                        writer.append(String.join(",", playerHeaders) + "\n");
                        break;
                    case "dealer":
                        writer.append(String.join(",", dealerHeaders) + "\n");
                        break;
                    case "game":
                        writer.append(String.join(",", gameHeaders) + "\n");
                        break;
                    case "error":
                        writer.append(String.join(",", errorHeaders) + "\n");
                        break;
                    case "info":
                        writer.append("message\n");
                        break;
                    default:
                        log ("error", className, functionName, "Invalid log group " + group);
                }
            }
            if(group.equals("error")) {
                writer.append(className + ", " + functionName + ", " + String.join(",", args) + "\n");
            }
            else{
                writer.append( String.join(",", args) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to log file");
            System.exit(0);
        }
    }

    /**
     * This function logs a message to a csv file.
     * 
     * @param group The log group to log to.
     * @param args The message to log.
     */
    public static void logToGroup(String group, String ...args) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName() + ".java";
        String functionName = Thread.currentThread().getStackTrace()[2].getMethodName() + "()";
        String lineNumber = "line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "";

        switch(group) {
            case "player":
                checkArgsAndLog(group, playerHeaders.length, className, functionName, lineNumber, args);
                break;
            case "dealer":
                checkArgsAndLog(group, dealerHeaders.length, className, functionName, lineNumber, args);
                break;
            case "game":
                checkArgsAndLog(group, gameHeaders.length, className, functionName, lineNumber, args);
                break;
            case "error":
                checkArgsAndLog(group, errorHeaders.length-2, className, functionName, lineNumber, args);
                break;
            case "info":
                checkArgsAndLog(group, infoHeaders.length, className, functionName, lineNumber, args);
                break;
            default:
                System.out.println("Error:  " + group + " is not a valid log group!");
                System.out.flush();
                System.exit(0);
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
    private static void checkArgsAndLog(String group, int numArgs, String className, String functionName, String lineNumber, String ...args) {
        if(args.length != numArgs) {
            System.err.println("Error:  " + group + " log group requires " + numArgs + " arguments!");
            System.err.flush();
            System.exit(0);
        } else {
            log(group, className, functionName + " " + lineNumber, args);
        }      
    }
}
