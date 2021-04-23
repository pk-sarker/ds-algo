package com.dsalgo.practice;

/**
 * Input: num = 7
 * Output: "Seven"
 *
 *  Input: num = 39
 *  Output: "Thirty Nine"
 *
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 *
 * Input: num = 12,345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Input: num = 1,234,567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class IntegerToEnglishWords {

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int billion = num / 1000000000;

        int million = (num - billion * 1000000000) / 1000000;

        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;

        int remaining = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String result = "";
        if (billion != 0) {
            result += translateThree(billion) + " Billion ";
        }

        if (million != 0) {
            result += translateThree(million) + " Million ";
        }

        if (thousand != 0) {
            result += translateThree(thousand) + " Thousand ";
        }


        if (remaining > 0) {
            result += translateThree(remaining);
        }

        return result; //translateThree(num);
    }

    public String translateOne(int num) {
        switch (num) {
            case 1: return "One";
            case 2: return  "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
        }
        return "";
    }

    public String translateTwo(int num) {
        if (num <10) {
            return translateOne(num);
        } else if (num < 20) {
            switch (num) {
                case 11: return "Eleven";
                case 12: return  "Twelve";
                case 13: return "Thirteen";
                case 14: return "Fourteen";
                case 15: return "Fifteen";
                case 16: return "Sixteen";
                case 17: return "Seventeen";
                case 18: return "Eighteen";
                case 19: return "Nineteen";
            }
        } else {
            int ten = num/10;
            int rem = num - ten*10;
            if (rem>0) {
                return translateTens(ten) +" "+ translateTwo(rem);
            } else {
                return translateTens(num);
            }
        }

        return "";
    }

    public String translateThree(int num) {
        int hundred = num / 100;
        int remaining = num - hundred*100;
        if (hundred!= 0 && remaining != 0) {
            return translateOne(hundred) + " Hundred " + translateTwo(remaining);
        } else if (hundred == 0 && remaining != 0){
            return translateTwo(remaining);
        }
        return "";
    }

    public String translateTens(int num) {
        switch (num) {
            case 1: return "Ten";
            case 2: return  "Twenty";
            case 3: return "Thirty";
            case 4: return "Fourty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
        }
        return "";
    }

    public static void main(String args[]) {
        IntegerToEnglishWords obj = new IntegerToEnglishWords();
        System.out.println(obj.numberToWords(1));
        System.out.println(obj.numberToWords(9));
        System.out.println(obj.numberToWords(17));
        System.out.println(obj.numberToWords(89));
        System.out.println(obj.numberToWords(123));
        System.out.println(obj.numberToWords(999));
        System.out.println(obj.numberToWords(3999));
        System.out.println(obj.numberToWords(103999));
    }
}
