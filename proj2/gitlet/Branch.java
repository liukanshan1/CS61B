package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static gitlet.Utils.join;

public class Branch implements Serializable  {
    /** The name of the branch. */
    private String name;
    /** The commit SHA1 of the head of this branch. */
    private String latestCommit;
    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The branch directory. */
    public static final File BRANCH_DIR = join(CWD, ".gitlet","branches");

    /**
     * Creates a new branch
     * @author: CuiYuxin
     */
    public Branch(String name, String latestCommit) {
        this.name = name;
        this.latestCommit = latestCommit;
    }

    /**
     * Write this Branch.
     * @author: CuiYuxin
     */
    public void write() {
        if (!BRANCH_DIR.exists()) {
            BRANCH_DIR.mkdir();
        }
        String sp = System.getProperty("file.separator");
        File branchFile = new File(".gitlet" + sp + "branches" + sp + name);
        if (!branchFile.exists()) {
            try {
                branchFile.createNewFile();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        Utils.writeObject(branchFile, this);
    }

}
