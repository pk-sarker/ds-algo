package com.ds.practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class SortLogs {
    // With Comparator
    public static String[] reorderLogFiles(String[] logs) {
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] s1Split = s1.split(" ", 2);
                String[] s2Split = s2.split(" ", 2);
                boolean s1isDigit = Character.isDigit(s1Split[1].charAt(0));
                boolean s2isDigit = Character.isDigit(s2Split[1].charAt(0));
                if (!s1isDigit && !s2isDigit) {
                    int comp = s1Split[1].compareTo(s2Split[1]);
                    if (comp != 0) {
                        return comp;
                    }
                    return s1Split[0].compareTo(s2Split[0]);
                }
                if (!s1isDigit && s2isDigit) {
                    return -1;
                } else if (s1isDigit && !s2isDigit) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        Arrays.sort(logs, comp);
        return logs;
    }

    // Without Comparator
    public static String[] reorderLogFilesIterative(String[] logs) {
        String[] orderedLogs = new String[logs.length];
        ArrayDeque<String> digits = new ArrayDeque<>();
        int li = 0;
        for(int i=0; i < logs.length;  i++) {
            String[] log = logs[i].split(" ");
            if (Character.isDigit(log[1].charAt(0))) {
                digits.add(logs[i]);
            } else {
                orderedLogs[li] = logs[i];
                li++;
            }
        }

        // custom string comparator
        for(int i=0; i<li; i++) {
            for(int j=i+1; j<li; j++) {
                int pos1 = orderedLogs[i].indexOf(" ");
                int pos2 = orderedLogs[j].indexOf(" ");
                String s1 = orderedLogs[i].substring(pos1+1);
                String s2 = orderedLogs[j].substring(pos2+1);
                if (s1.compareTo(s2)>= 0 ){
                    String temp = orderedLogs[i];
                    orderedLogs[i] = orderedLogs[j];
                    orderedLogs[j] = temp;
                }
            }
        }

        while(!digits.isEmpty()) {
            orderedLogs[li] = digits.remove();
            li++;
        }

        return orderedLogs;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [\"dig1 8 1 5 1\",\"let1 art can\",\"dig2 3 6\",\"let2 own kit dig\",\"let3 art zero\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFilesIterative(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"})));
        System.out.println("\nInput: [\"dig1 8 1 5 1\",\"let1 art can\",\"dig2 3 6\",\"let2 own kit dig\",\"let3 art zero\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"})));

        System.out.println("\nInput: [\"t kvr\", \"r 3 1\", \"i 403\", \"7 so\", \"t 54\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFilesIterative(new String[]{"t kvr", "r 3 1", "i 403", "7 so", "t 54"})));
        System.out.println("\nInput: [\"t kvr\", \"r 3 1\", \"i 403\", \"7 so\", \"t 54\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFiles(new String[]{"t kvr", "r 3 1", "i 403", "7 so", "t 54"})));

        System.out.println("\nInput: [\"a1 9 2 3 1\",\"g1 act car\",\"zo4 4 7\",\"ab1 off key dog\",\"a8 act zoo\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFilesIterative(new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"})));
        System.out.println("\nInput: [\"a1 9 2 3 1\",\"g1 act car\",\"zo4 4 7\",\"ab1 off key dog\",\"a8 act zoo\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFiles(new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"})));

        System.out.println("\nInput: [\"a1 9 2 3 1\",\"g1 act car\",\"zo4 4 7\",\"ab1 off key dog\",\"a8 act zoo\",\"a2 act car\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFilesIterative(new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"})));
        System.out.println("\nInput: [\"a1 9 2 3 1\",\"g1 act car\",\"zo4 4 7\",\"ab1 off key dog\",\"a8 act zoo\",\"a2 act car\"] \nOutput: " +
                Arrays.toString(SortLogs.reorderLogFiles(new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"})));
    }
}
