package ds.Stack;

import java.util.Stack;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style
 * file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the
 * directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For
 * this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 *  - The path starts with a single slash '/'.
 *  - Any two directories are separated by a single slash '/'.
 *  - The path does not end with a trailing '/'.
 *  - The path only contains the directories on the path from the root directory to the target file or directory
 *    (i.e., no period '.' or double period '..')
 *
 * Return the simplified canonical path.
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 *
 */
public class SimplifyPath {

    public String simplifyPath(String path) {

        // Initialize a stack
        Stack<String> stack = new Stack<String>();
        String[] components = path.split("/");

        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String dir : components) {

            // A no-op for a "." or an empty string
            if (dir.equals(".") || dir.isEmpty()) {
                continue;
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.add(dir);
            }
        }

        // Stich together all the directory names together
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }

        return result.length() > 0 ? result.toString() : "/" ;
    }

    public static void main(String args[]) {
        SimplifyPath obj = new SimplifyPath();
        System.out.println("> /../ -> "  + obj.simplifyPath("/../"));
        System.out.println("> /... -> "  + obj.simplifyPath("/..."));
        System.out.println("> /.../a -> "  + obj.simplifyPath("/.../a"));

//        System.out.println("> /home/ -> "  + obj.simplifyPath("/home/"));
//        System.out.println("> /home//foo/ -> "  + obj.simplifyPath("/home//foo/"));
//        System.out.println("> /a/./b/../../c/ -> "  + obj.simplifyPath("/a/./b/../../c/"));
//        System.out.println("> /a/./b/../../c/../a/c/ -> "  + obj.simplifyPath("/a/./b/../../c/../a/c/"));
//        System.out.println("> /a/./bc/../../c/cd -> "  + obj.simplifyPath("/a/./bc/../../c/cd"));

    }
}
