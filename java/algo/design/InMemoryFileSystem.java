package algo.design;

import java.util.*;

/**
 * Design a data structure that simulates an in-memory file system.
 *
 * Implement the FileSystem class:
 *
 *  - InMemoryFileSystem() Initializes the object of the system.
 *  - List<String> ls(String path)
 *      - If path is a file path, returns a list that only contains this file's name.
 *      - If path is a directory path, returns the list of file and directory names in this directory.
 * The answer should in lexicographic order.
 *  - void mkdir(String path) Makes a new directory according to the given path. The given
 *      directory path does not exist. If the middle directories in the path do not exist,
 *      you should create them as well.
 *  - void addContentToFile(String filePath, String content)
 *      - If filePath does not exist, creates that file containing given content.
 *      - If filePath already exists, appends the given content to original content.
 *  - String readContentFromFile(String filePath) Returns the content in the file at filePath.
 *
 * Example:
 * Input
 * ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 * [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 * Output
 * [null, [], null, null, ["a"], "hello"]
 *
 * Explanation
 * FileSystem fileSystem = new FileSystem();
 * fileSystem.ls("/");                         // return []
 * fileSystem.mkdir("/a/b/c");
 * fileSystem.addContentToFile("/a/b/c/d", "hello");
 * fileSystem.ls("/");                         // return ["a"]
 * fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 *
 * Trie Data structure
 */
public class InMemoryFileSystem {

    public class FileDir {
        public boolean isFile;
        public Map<String, FileDir> fileDir;  // Trie Child
        public StringBuilder content;

        public FileDir() {
            this.isFile = false;
            this.fileDir = new HashMap<>();
            this.content = new StringBuilder();
        }
    }

    private FileDir root;  // Trie root

    public InMemoryFileSystem() {
        this.root = new FileDir();
    }

    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();

        if (path == null || path == "") {
            return res;
        }
        FileDir fd = root;
        if (!path.equals("/")) {
            String[] pathAr = path.split("/"); // "/a/b/c" => ["",a, b, c]
            for(int i = 1; i < pathAr.length;  i++) {
                fd = fd.fileDir.get(pathAr[i]);
            }
            if (fd.isFile) {
                res.add(pathAr[pathAr.length -1]);
                return res;
            }
        }

        // for "/", get all the files and directories at root level
        res = new ArrayList<String>(fd.fileDir.keySet());

        Collections.sort(res);
        return res;
    }

    // Insert to trie.
    public void mkdir(String path) {
        if (path == null || path == "") {
            return;
        }
        FileDir fd = root;
        String[] pathAr = path.split("/");
        for(int i = 1; i < pathAr.length;  i++) {
            if (!fd.fileDir.containsKey(pathAr[i])) {
                fd.fileDir.put(pathAr[i], new FileDir()); // create a child if the child doesn't exist
            }
            fd = fd.fileDir.get(pathAr[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        if (filePath == null || filePath == "" || content == null || content == "") {
            return ;
        }
        String[] pathAr = filePath.split("/");
        FileDir fd = root;
        // go to file location, if not present then create
        for(int i=1; i<pathAr.length -1; i++) {
            if (!fd.fileDir.containsKey(pathAr[i])) {
                fd.fileDir.put(pathAr[i], new FileDir());
            }
            fd = fd.fileDir.get(pathAr[i]);
        }
        //
        if (!fd.fileDir.containsKey(pathAr[pathAr.length-1])) {
            fd.fileDir.put(pathAr[pathAr.length-1], new FileDir());
        }
        fd = fd.fileDir.get(pathAr[pathAr.length-1]);
        fd.isFile = true;
        fd.content = fd.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        if (filePath == null || filePath == "") {
            return "";
        }
        FileDir fd = root;
        String[] pathAr = filePath.split("/");
        for(int i=1; i<pathAr.length -1; i++) {
            fd = fd.fileDir.get(pathAr[i]);
        }
        fd = fd.fileDir.get(pathAr[pathAr.length-1]);
        return fd.content.toString();
    }
}
