package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.join;

public class Stage implements Serializable {
    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The stage directory. */
    public static final File STAGE_DIR = join(CWD, ".gitlet","stage");









}
