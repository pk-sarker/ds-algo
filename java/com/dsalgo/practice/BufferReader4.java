package com.dsalgo.practice;

public class BufferReader4 {

    char[] internalBuffAr;
    int internalBufPtr;
    public BufferReader4(String fileContent) {
        this.internalBuffAr = fileContent.toCharArray();
        this.internalBufPtr = 0;
    }
    private int read4(char[] buf4) {
        int readCount = 0;
        if (this.internalBufPtr < internalBuffAr.length) {
            while(readCount < 4 && this.internalBufPtr <  internalBuffAr.length) {
                buf4[readCount++] = internalBuffAr[this.internalBufPtr++];
            }
        }
        return readCount;
    }

    private int bufPtr = 0; // buffer pointer in the loaded buffer
    private int bufCount = 0; // number of characters read from the actual file
    char[] loadedBuf = new char[4];
    public int read(char[] buf, int n) {
        int readCountPtr = 0;
        while(readCountPtr < n) {
            // if buffer pointer is 0 then load from actual file
            if (this.bufPtr == 0) {
                this.bufCount = read4(loadedBuf);
            }

            System.out.println("Read Count: " + this.bufCount +  ", buf: " + new String(loadedBuf));
            if (this.bufCount == 0) { // there is no more content to read from the file
                break;
            }
            while(readCountPtr < n && this.bufPtr < this.bufCount) {
                buf[readCountPtr++] = loadedBuf[this.bufPtr++];
            }
            if (this.bufPtr >= this.bufCount) {
                this.bufPtr = 0;
            }
        }
        return readCountPtr;
    }

    public static void main(String args[]) {
        BufferReader4 obj = new BufferReader4("abcdefg");
        char[] buf = new char[4];
        System.out.println("> " + obj.read(buf, 4) + " > buf: " + new String(buf));
        buf = new char[4];
        System.out.println("> " + obj.read(buf, 4)+ " > buf: " + new String(buf));
        buf = new char[4];
        System.out.println("> " + obj.read(buf, 4)+ " > buf: " + new String(buf));
    }


}
