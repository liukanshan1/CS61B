package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author CuiYuxin
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.print("Please enter a command.");
            System.exit(0);
        }
        String firstArg = args[0];
        if(!firstArg.equals("init")&&!Repository.isRepo()) {
            System.out.print("Not in an initialized Gitlet directory.");
            System.exit(0);
        }
        Repository repo = new Repository();
        switch(firstArg) {
            case "init":
                validateNumArgs(args, 1);
                repo.initGitlet();
                break;
            case "add":
                validateNumArgs(args, 2);
                repo.add(args[1]);
                break;
            case "commit":
                validateNumArgs(args, 2);
                repo.commit(args[1]);
                break;
            case "rm":
                validateNumArgs(args, 2);
                repo.rm(args[1]);
                break;
            case "log":
                validateNumArgs(args, 1);
                repo.log();
                break;
            case "global-log":
                validateNumArgs(args, 1);

                break;
            case "find":
                validateNumArgs(args, 2);

                break;
            case "status":
                validateNumArgs(args, 1);

                break;
            case "checkout":
                validateNumArgs(args, 2);

                break;
            case "branch":
                validateNumArgs(args, 2);

                break;
            case "rm-branch":
                validateNumArgs(args, 2);

                break;
            case "reset":
                validateNumArgs(args, 2);

                break;
            case "merage":
                validateNumArgs(args, 2);

                break;
            default:
                System.out.print("No command with that name exists.");
                System.exit(0);
        }
    }

    /**
     * Checks the number of arguments versus the expected number,
     * throws an error if they do not match.
     * @author CuiYuxin
     * @param args Argument array from command line
     * @param n Number of expected arguments
     */
    public static void validateNumArgs(String[] args, int n) {
        if (args[0].equals("checkout")){
            if (args.length < n) {
                System.out.print("Incorrect operands.");     // TODO: fix this
                System.exit(0);
            }
            return;
        }
        if (args.length != n) {
            System.out.print("Incorrect operands.");
            System.exit(0);
        }
    }
}
