package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import static gitlet.Utils.join;

/** Represents a gitlet commit object.
 *  @author CuiYuXin
 */
public class Commit implements Serializable {

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The commit directory. */
    public static final File COMMIT_DIR = join(CWD, ".gitlet","commits");
    /** The message of this Commit. */
    private String message;
    /** The SHA1 of parent. */
    private String parent;
    /** The SHA1 of parent2. */
    private String parent2;
    /** The TimeStamp of this Commit. */
    private Date timeStamp;
    /** The Blobs of this Commit. */
    private List<String> blobs = new ArrayList<>();

    /** Constructor
     *  @author:CuiYuxin */
    public Commit(String message, String parent, String parent2, Date timeStamp, List<String> blobs) {
        this.message = message;
        this.parent = parent;
        this.parent2 = parent2;
        this.timeStamp = timeStamp;
        this.blobs = blobs;
    }

    /** Constructor
     *  @author:CuiYuxin */
    public Commit() {}

    /** Init first commit
     *  @author:CuiYuxin */
    public void initCommit() {
        this.message = "initial commit";
        this.parent = "";
        this.parent2 ="";
        this.timeStamp.setTime(0);
    }

    /** Return the message
     *  @author:CuiYuxin */
    public String getMessage() {
        return message;
    }

    /** Return the parent1
     *  @author:CuiYuxin */
    public String getParent() {
        return parent;
    }

    /** Return the parent2
     *  @author:CuiYuxin */
    public String getParent2() {
        return parent2;
    }

    /** Return the timestamp
     *  @author:CuiYuxin */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /** Return the Blobs
     *  @author:CuiYuxin */
    public List<String> getBlobs() {
        return blobs;
    }

    /** Write this Commit
     *  @author:CuiYuxin */
    public void write() {
        if (!COMMIT_DIR.exists()) {
            COMMIT_DIR.mkdir();
        }
        String sha1 = Utils.sha1(this);
        String sp = System.getProperty("file.separator");
        File commitFile = new File(".gitlet"+sp+"commits"+sp+sha1);
        if (!commitFile.exists()) {
            try{
                commitFile.createNewFile();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        Utils.writeObject(commitFile, this);
    }

}
