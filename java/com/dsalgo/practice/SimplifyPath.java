package com.dsalgo.practice;

import java.util.Stack;

/**
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
