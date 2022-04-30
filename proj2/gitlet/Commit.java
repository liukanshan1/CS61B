package gitlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.Serializable;

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
    private String message = "";
    /** The SHA1 of parent. */
    private String parent = "";
    /** The SHA1 of parent2. */
    private String parent2 = "";
    /** The TimeStamp of this Commit. */
    private Date timeStamp = new Date();
    /** The commit map. Key:filename Value:SHA1 */
    Map<String,String> commmitMap = new java.util.HashMap<>();

    /** Constructor
     *  @author CuiYuxin */
    public Commit(String message, String parent, String parent2, Map<String,String> commmitMap) {
        this.message = message;
        this.parent = parent;
        this.parent2 = parent2;
        this.timeStamp.getTime();
        this.commmitMap = commmitMap;
    }

    /** Constructor
     *  @author CuiYuxin */
    public Commit() {}

    /** Init first commit
     *  @author CuiYuxin */
    public void initCommit() {
        this.message = "initial commit";
        this.parent = "";
        this.parent2 ="";
        this.timeStamp.setTime(0);
    }

//    /** Return the message
//     *  @author CuiYuxin */
//    public String getMessage() {
//        return message;
//    }

    /** Return the parent1
     *  @author CuiYuxin */
    public String getParent() {
        return parent;
    }

//    /** Return the parent2
//     *  @author CuiYuxin */
//    public String getParent2() {
//        return parent2;
//    }
//
//    /** Return the timestamp
//     *  @author CuiYuxin */
//    public Date getTimeStamp() {
//        return timeStamp;
//    }

    /** Return the Map<String,String>
     *  @author CuiYuxin */
    public Map<String,String> getBlobs() {
        return commmitMap;
    }

    /** Write this Commit and return the filename(SHA1)
     *  @author CuiYuxin */
    public String write() {
        if (!COMMIT_DIR.exists()) {
            COMMIT_DIR.mkdir();
        }
        String sha1 = Utils.sha1(Utils.serialize(this));
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
        return sha1;
    }

    /** Read Commit and return
     *  @author CuiYuxin */
    public static Commit read(String sha1) {
        String sp = System.getProperty("file.separator");
        File commitFile = new File(".gitlet"+sp+"commits"+sp+sha1);
        return Utils.readObject(commitFile, Commit.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===\n");
        sb.append("commit ").append(Utils.sha1(Utils.serialize(this))).append("\n");
        sb.append("Date: ");
        SimpleDateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss yyyy Z");
        sb.append(format.format(timeStamp)).append("\n");
        sb.append(message).append("\n");
        return sb.toString();
    }

    /** Create new Blobs
     * @author CuiYuxin
     */
    public static Map<String,String> mergeBlobs(Stage stage, Commit oldCmt) {
        Map<String, String> map = oldCmt.getBlobs();
        for (String rm : stage.getRemoveFile()) {
            map.remove(rm);
        }
        // Map遍历
        for (Map.Entry<String, String> entry : stage.getBlobmap().entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
