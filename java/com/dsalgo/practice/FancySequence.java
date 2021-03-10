package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Write an API that generates fancy sequences using the append, addAll, and multAll operations.
 *
 * Implement the Fancy class:
 *
 * - Fancy() Initializes the object with an empty sequence.
 * - void append(val) Appends an integer val to the end of the sequence.
 * - void addAll(inc) Increments all existing values in the sequence by an integer inc.
 * - void multAll(m) Multiplies all existing values in the sequence by an integer m.
 * - int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 10^9 + 7.
 * If the index is greater or equal than the length of the sequence, return -1.
 *
 * Example:
 * Input
 * ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
 * [[], [2], [3], [7], [2], [0], [3],                                       [10], [2], [0], [1], [2]]
 * Output
 * [null, null, null, null, null, 10, null, null, null, 26, 34, 20]
 *
 * SOLUTION
 * Final calculation will be : A[i] * mult + increment
 *
 * on ddAll(inc)
 *      => increment += inc
 * on multAll(m)
 *      original number before current  operation: org  = A[i] * mult + increment
 *      Now multiply with m: org * m  = A[i] * (mult * m) + (increment * m)
 *      That means we can just multiply previous mult  and incriment with m and continue
 * on append(newval)
 *      => Now we cant just ignore this operation because the new value cannot be subject to the orginal formula.
 *      => One way to solve it would be to just compute the values at this point and reset increment and mult but
 *      this would lead to a TLE. So we need a way to negate the final formula
 *      =>  so first we do newval - =increment and then newval/=mult. But as these are in modulo arthematic,
 *      the division changes to newval *= moduloInverse(mult)
 *
 * How to calculate Modulo Inverse?
 *  We know that 10^9+7 is prime and hence we can use Fermat's theorem to calculate inverse
 *  a^(m-1) ≡ 1 (mod m)
 *  If we multiply both sides with a^(-1), we get
 *  a^(-1) ≡ a^(m-2) (mod m)
 *  so we just need to calculate modPower(a, m-2)
 *
 *
 */
public class FancySequence {
    List<Long> sequence;
    long addValue = 0;
    long multiplier = 1;
    int mod = 1000000007;
    public FancySequence() {
        this.sequence = new ArrayList<>();
    }

    public long modPow(long x, long y) {
        System.out.println("\n\t modPow (x,y)=("+x+", "+y+")");
        long res = 1, p = x;
        while(y>0) {

                System.out.println("\ty: "+y+" (y & 1) "+ (y%2));
            if (y % 2 == 1) {
                System.out.println("\n\t update res: res="+res+" p="+p+")");
                res = (res * p) % this.mod;
            } else {
                y = y / 2;
            }
            p = (p*p) % this.mod;
            System.out.println("\n\t new p: "+p+"  y="+y + "  res: " + res);
        }
        return res;
    }
    public void append(int val) {
        System.out.println("====  ==== ==== ==== append("+val+") ==== ==== ==== ====");
        long num = ((this.mod + val - this.addValue) % this.mod * this.modPow(this.multiplier, this.mod-2)) % this.mod;

        System.out.println("<<<<<<<<<<<< append value = "+num+"");
        this.sequence.add(num);
    }

    public void addAll(int inc) {
        this.addValue = (this.addValue + inc% this.mod)% this.mod;
    }

    public void multAll(int m) {
        this.addValue = (this.addValue  * m % this.mod) % this.mod;
        this.multiplier = (this.multiplier  * m % this.mod) % this.mod;
    }

    public int getIndex(int idx) {
        if (idx >= this.sequence.size()) {
            return -1;
        }
        Long num = this.sequence.get(idx);
        int num1 = (int) ((num * this.multiplier)%this.mod + this.addValue)%this.mod;
        return num1;
    }
    public static void main(String args[]) {
        FancySequence ob = new FancySequence();
//        ob.append(2);
//        ob.addAll(3);
//        ob.append(7);
//        ob.multAll(2);
//        System.out.println("getIndex(0): 10 == " + ob.getIndex(0));
//        ob.addAll(3);
//        ob.append(10);
//        ob.multAll(2);
//        System.out.println("getIndex(0): 26 == " + ob.getIndex(0));
//        System.out.println("getIndex(1): 34 == " + ob.getIndex(1));
//        System.out.println("getIndex(2): 20 == " + ob.getIndex(2));


        ob.addAll(3);
        System.out.println("getIndex(0): 10 == " + ob.getIndex(0));
    }
}
