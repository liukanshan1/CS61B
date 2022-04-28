package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static gitlet.Utils.*;


/**
 * Represents a gitlet repository.
 * @author CuiYuxin
 */
public class Repository implements Serializable {
    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The current commit. */
    private static String head;

    /**
     * Init a gitlet repository.
     * @author CuiYuxin
     */
    public void initGitlet(){
        if (!GITLET_DIR.exists()){
            //create .gitlet directory
            GITLET_DIR.mkdir();
            //create init commit
            Commit cmt = new Commit();
            cmt.initCommit();
            cmt.write();
            //create master branch
            Branch br = new Branch("master",Utils.sha1(cmt));
            br.write();
            //update repository status
            head = Utils.sha1(cmt);
            write();
        }
        else {
            System.out.print("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
    }

    /** Write repository status to disk.
     *  @author:CuiYuxin */
    public void write() {
        String sp = System.getProperty("file.separator");
        File repo = new File(".gitlet"+sp+"REPO");
        if (!repo.exists()) {
            try{
                repo.createNewFile();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        Utils.writeObject(repo, this);
    }

}
