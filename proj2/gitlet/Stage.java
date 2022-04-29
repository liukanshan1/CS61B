package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stage implements Serializable {
    /** The stage map. */
    private Map<String,String> blobmap = new java.util.HashMap<>();
    /** Remove filename. Key:filename Value:SHA1 */
    private List<String> removeFile = new ArrayList<>();

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
        else{
            this.blobmap = Utils.readObject(stagefile, Stage.class).blobmap;
            this.removeFile = Utils.readObject(stagefile, Stage.class).removeFile;
        }
    }

    /**
     * Return the stage map.
     * @author CuiYuxin
     */
    public Map<String,String> getBlobmap() {
        return blobmap;
    }

    /**
     * Return the remove file list.
     * @author CuiYuxin
     */
    public List<String> getRemoveFile() {
        return removeFile;
    }

    /**
     * Add a file to stage.
     * @author CuiYuxin
     */
    public void add(String fileName, String blobName, String head) {
        if (!this.blobmap.containsKey(fileName)) {
            this.blobmap.put(fileName, blobName);
        } else {
            Commit headObj = Commit.read(head);
            if (headObj.getBlobs().get(fileName).equals(blobName)) {
                this.blobmap.remove(fileName);
            } else {
                this.blobmap.replace(fileName, blobName);
            }
        }
    }

    /**
     * return if the stage is empty.
     * @author CuiYuxin
     */
    public boolean isEmpty() {
        return this.blobmap.isEmpty()&&this.removeFile.isEmpty();
    }

    /**
     * Remove a file from stage.
     * @author CuiYuxin
     */
    public void rm(String fileName,String head) {
        boolean fail = true;
        if (this.blobmap.containsKey(fileName)) {
            fail = false;
            this.blobmap.remove(fileName);
        }
        Commit headObj = Commit.read(head);
        if (headObj.getBlobs().containsKey(fileName)) {
            fail = false;
            removeFile.add(fileName);
            Utils.restrictedDelete(fileName);
        }
        if (fail) {
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

    /** Clean the stage.
     *  @author CuiYuxin
     */
    public void clear() {
        blobmap = new java.util.HashMap<>();
        removeFile = new ArrayList<>();
        this.write();
    }

}
