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
    private String head;

    /**
     * Constructs a new repository.
     * @author CuiYuxin
     */
    public Repository() {
        if (GITLET_DIR.exists()) {
            String sp = System.getProperty("file.separator");
            File repo = new File(".gitlet"+sp+"REPO");
            if (repo.exists()) {
                Repository repoObj = Utils.readObject(repo, Repository.class);
                head = repoObj.head;
            }
        }
    }

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
            head = cmt.write();
            //create master branch
            Branch br = new Branch("master",Utils.sha1(Utils.serialize(cmt)));
            br.write();
            //update repository status
            write();
        }
        else {
            System.out.print("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
    }

    /**
     * Add a file to the staging area.
     * @author CuiYuxin
     */
    public void add(String fileName){
        File file = new File(fileName);
        if (!file.exists()){
            System.out.println("File does not exist.");
            System.exit(0);
        }
        //copy file to blob directory
        Blob blob = new Blob(file);
        String blobName = blob.write();
        //update staging area
        File stageFile = new File(".gitlet/stage");
        Stage stage;
        if (stageFile.exists()) {
            stage = Utils.readObject(stageFile, Stage.class);
        }
        else{
            stage = new Stage();
        }
        stage.add(fileName,blobName,head);
        stage.write();
    }

    /**
     * Remove a file from the staging area.
     * @author CuiYuxin
     */
    public void rm(String fileName){
        //update staging area
        File stageFile = new File(".gitlet/stage");
        Stage stage;
        if (stageFile.exists()) {
            stage = Utils.readObject(stageFile, Stage.class);
        }
        else{
            stage = new Stage();
        }
        stage.rm(fileName,head);
        stage.write();
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
