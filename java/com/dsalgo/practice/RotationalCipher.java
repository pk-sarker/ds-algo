package com.dsalgo.practice;

/**
 * One simple way to encrypt a string is to "rotate" every alphanumeric character by a certain amount.
 * Rotating a character means replacing it with another character that is a certain number of steps away
 * in normal alphabetic or numerical order.
 * For example, if the string "Zebra-493?" is rotated 3 places, the resulting string is "Cheud-726?".
 * Every alphabetic character is replaced with the character 3 letters higher (wrapping around from Z to A),
 * and every numeric character replaced with the character 3 digits higher (wrapping around from 9 to 0).
 * Note that the non-alphanumeric characters remain unchanged.
 * Given a string and a rotation factor, return an encrypted string.
 *
 * input = Zebra-493?
 * rotationFactor = 3
 * output = Cheud-726?
 */
public class RotationalCipher {

    public static String rotationalCipher(String input, int rotationFactor) {
        int letterOffset = rotationFactor % 26;
        int numberOffset = rotationFactor % 10;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<input.length();i++) {
            char c = input.charAt(i);
            int asciiValue = (int) c;
            if (Character.isDigit(c)) {
                int index = c - '0';
                sb.append((char) ((index + numberOffset)%10 + 48));
                continue;
            }
            if (Character.isLetter(c)) {
                if (asciiValue >=65 && asciiValue <= 90) {
                    int index = c - 'A';
                    sb.append((char) ((index + letterOffset)%26 + 65));
                } else {
                    int index = c - 'a';
                    sb.append((char) ((index + letterOffset)%26 + 97));
                }
                continue;
            }
            sb.append(c);
        }

        return sb.toString();
    }
    public static void main(String args[]) {
        System.out.println("\nInput: YZAabyz-698? rotation=3\nOutput: " + RotationalCipher.rotationalCipher("YZAabyz-698?", 3));

        System.out.println("\nInput: YZAabyz-698? rotation=30\nOutput: " + RotationalCipher.rotationalCipher("YZAabyz-698?", 30));
    }
}
