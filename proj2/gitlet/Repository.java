package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    /** The current branch. */
    private String branch;

    /**
     * Constructs a new repository.
     * @author CuiYuxin
     */
    public Repository() {
        if (GITLET_DIR.exists()) {
            String sp = "/";
            File repo = new File(".gitlet" + sp + "REPO");
            if (repo.exists()) {
                Repository repoObj = Utils.readObject(repo, Repository.class);
                head = repoObj.head;
                branch = repoObj.branch;
            }
        }
    }

    /**
     * Init a gitlet repository.
     * @author CuiYuxin
     */
    public void initGitlet() {
        if (!GITLET_DIR.exists()) {
            //create .gitlet directory
            GITLET_DIR.mkdir();
            //create init commit
            Commit cmt = new Commit();
            cmt.initCommit();
            head = cmt.write();
            //create master branch
            Branch br = new Branch("master", Utils.sha1(Utils.serialize(cmt)));
            br.write();
            branch = "master";
            //update repository status
            write();
        } else {
            String e = "A Gitlet version-control system already exists in the current directory.";
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * Add a file to the staging area.
     * @author CuiYuxin
     */
    public void add(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
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
        } else {
            stage = new Stage();
        }
        stage.add(fileName, blobName, head);
        stage.write();
    }

    /**
     * Creating a new commit.
     * @author CuiYuxin
     */
    public void commit(String message) {
        if (message == null || message.equals("")) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }
        Commit oldCmt = Commit.read(head); //read old commit
        Stage stage = new Stage(); //read stage
        if (stage.isEmpty()) {
            System.out.println("No changes added to the commit.");
            System.exit(0);
        }
        Map<String, String> cmtMap = Commit.mergeBlobs(stage, oldCmt);
        Commit cmt = new Commit(message, head, "", cmtMap); //create new commit
        head = cmt.write(); //update head
        stage.clear(); //clear stage
        //read current branch and update branch
        Branch br = Branch.read(branch);
        br.update(head);
        br.write();
        write(); //update repository status
    }

    /**
     * Remove a file from the staging area.
     * @author CuiYuxin
     */
    public void rm(String fileName) {
        //update staging area
        File stageFile = new File(".gitlet/stage");
        Stage stage;
        if (stageFile.exists()) {
            stage = Utils.readObject(stageFile, Stage.class);
        } else {
            stage = new Stage();
        }
        stage.rm(fileName, head);
        stage.write();
    }

    /**
     * Print the log the current branch.
     * @author CuiYuxin
     */
    public void log() {
        String cmtID = head;
        while (!cmtID.equals("")) {
            Commit cmt = Commit.read(cmtID);
            System.out.println(cmt.toString());
            cmtID = cmt.getParent();
        }
    }

    /**
     * Print all commit.
     * @author CuiYuxin
     */
    public void globalLog() {
        List<String> cmtLogs = Commit.getCommitLog();
        for (String cmtLog : cmtLogs) {
            System.out.print(cmtLog);
            System.out.print("\n");
        }
    }

    /**
     * Print the status of the repository.
     * @author CuiYuxin
     */
    public void status() {
        // Branch status
        System.out.println("=== Branches ===");
        for (String branchName : Branch.allBranches()) {
            if (branchName.equals(this.branch)) {
                System.out.println("*" + branchName);
            } else {
                System.out.println(branchName);
            }
        }
        System.out.println();
        // Staged files
        Stage stage = new Stage();
        System.out.println("=== Staged Files ===");
        for (String fileName : stage.getStagedFiles()) {
            System.out.println(fileName);
        }
        System.out.println();
        // Removed files
        System.out.println("=== Removed Files ===");
        for (String fileName : stage.getRemovedFiles()) {
            System.out.println(fileName);
        }
        System.out.println();
        // Unstaged files
        System.out.println("=== Modifications Not Staged For Commit ===");
        for (String fileName : stage.getUnstagedFiles(head)) {
            System.out.println(fileName);
        }
        System.out.println();
        // Untracked files
        System.out.println("=== Untracked Files ===");
        for (String fileName : stage.getUntrackedFiles(head)) {
            System.out.println(fileName);
        }
        System.out.println();
    }

    /**
     * Find the commit which has given message.
     * @author CuiYuxin
     */
    public void find(String message) {
        List<String> cmtID = Commit.find(message);
        if (cmtID.size() == 0) {
            System.out.println("Found no commit with that message.");
            System.exit(0);
        }
        for (String cmt : cmtID) {
            System.out.println(cmt);
        }
    }


    /** Write repository status to disk.
     *  @author CuiYuxin */
    public void write() {
        String sp = "/";
        File repo = new File(".gitlet" + sp + "REPO");
        if (!repo.exists()) {
            try {
                repo.createNewFile();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        Utils.writeObject(repo, this);
    }

    /**
     * Check if current folder is a repository.
     * @author CuiYuxin
     */
    public static boolean isRepo() {
        return GITLET_DIR.exists();
    }

    /**
     * Checkout file in given commit.
     * @author CuiYuxin
     */
    public void checkout(String cmtID, String fileName) {
        if (cmtID.equals("head")) {
            cmtID = head;
        }
        Commit cmt = Commit.read(cmtID);
        if (cmt == null) {
            System.out.println("No commit with that id exists.");
            System.exit(0);
        }
        if (!cmt.getBlobs().containsKey(fileName)) {
            System.out.println("File does not exist in that commit.");
            System.exit(0);
        }
        String fileID = cmt.getBlobs().get(fileName);
        byte[] fileContent = Blob.getBlob(fileID);
        Utils.writeContents(new File(fileName), fileContent);
    }

    /**
     * Checkout branch.
     * @author CuiYuxin
     */
    public void checkout(String branchName) {
        if (branchName.equals(branch)) {
            System.out.println("No need to checkout the current branch.");
            System.exit(0);
        }
        Branch br = Branch.read(branchName);
        if (br == null) {
            System.out.println("No such branch exists.");
            System.exit(0);
        }
        // TODO



//        if (cmtID.equals("head")) {
//            cmtID = head;
//        }
//        Commit cmt = Commit.read(cmtID);
//        if (cmt == null) {
//            System.out.println("No commit with that id exists.");
//            System.exit(0);
//        }
//        if (!cmt.getBlobs().containsKey(fileName)) {
//            System.out.println("File does not exist in that commit.");
//            System.exit(0);
//        }
//        String fileID = cmt.getBlobs().get(fileName);
//        byte[] fileContent = Blob.getBlob(fileID);
//        Utils.writeContents(new File(fileName), fileContent);
    }
}
