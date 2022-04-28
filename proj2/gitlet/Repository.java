package gitlet;

import java.io.File;
import static gitlet.Utils.*;


/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author CuiYuxin
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /** Init a gitlet repository.
     *  @author CuiYuxin
     */
    public static void initGitlet(){
        if (!GITLET_DIR.exists()){
            CAPERS_FOLDER.mkdir();
            // TODO





        }
        else {
            System.out.print("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
    }









    /* TODO: fill in the rest of this class. */
}
