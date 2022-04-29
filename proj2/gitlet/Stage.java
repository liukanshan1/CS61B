package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stage implements Serializable {
    /** The stage map. */
    Map<String,String> blobmap = new java.util.HashMap<>();
    /** Remove filename. */
    List<String> removeFile = new ArrayList<>();

    /**
     * Constructor for Stage.
     * @author CuiYuxin
     */
    public Stage() {
        File stagefile = new File(".gitlet/stage");
        if (!stagefile.exists()) {
            try{
                stagefile.createNewFile();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

    /**
     * Add a file to stage.
     * @author CuiYuxin
     */
    public void add(String fileName, String blobName, String head) {
        if (!this.blobmap.containsKey(fileName)) {
            this.blobmap.put(fileName, blobName);
        } else {
            String sp = System.getProperty("file.separator");
            File headCommit = new File(".gitlet" + sp + "commits" + sp + head);
            Commit headObj = Utils.readObject(headCommit, Commit.class);
            if (headObj.getBlobs().get(fileName).equals(blobName)) {
                this.blobmap.remove(fileName);
            } else {
                this.blobmap.replace(fileName, blobName);
            }
        }
    }

    /**
     * Remove a file from stage.
     * @author CuiYuxin
     */
    public void rm(String fileName,String head) {
        boolean fail = false;
        if (this.blobmap.containsKey(fileName)) {
            fail = true;
            this.blobmap.remove(fileName);
        }
        String sp = System.getProperty("file.separator");
        File headCommit = new File(".gitlet" + sp + "commits" + sp + head);
        Commit headObj = Utils.readObject(headCommit, Commit.class);
        if (headObj.getBlobs().containsKey(fileName)) {
            fail = true;
            removeFile.add(fileName);
            Utils.restrictedDelete(fileName);
        }
        if (!fail) {
            System.out.println("No reason to remove the file.");
            System.exit(0);
        }
    }

    /** Write the stage status to disk.
     *  @author CuiYuxin
     */
    public void write() {
        File stageFile = new File(".gitlet/stage");
        Utils.writeObject(stageFile, this);
    }

}
