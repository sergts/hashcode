package io;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[2048];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader() {
        this.stream = System.in;
    }

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public InputReader(SpaceCharFilter filter) {
        this.stream = System.in;
        this.filter = filter;
    }

    public InputReader(InputStream stream, SpaceCharFilter filter) {
        this.stream = stream;
        this.filter = filter;
    }

    public int read() {
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public char nextChar() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return (char) c;
    }

    public int nextDigit() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return c - '0';
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return negative ? -res : res;
    }

    public int[] nextInts(int N) {
        int[] nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = nextInt();
        return nums;
    }

    public long[] nextLongs(int N) {
        long[] nums = new long[N];
        for (int i = 0; i < N; i++)
            nums[i] = nextLong();
        return nums;
    }

    public int nextUnsignedInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int res = 0;
        do {
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res;
    }

    public final long nextLong() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = read();
        }
        long res = 0;
        do {
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return negative ? -res : res;
    }

    public final long nextUnsignedLong() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        long res = 0;
        do {
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res;
    }

    public double nextDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        long sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!(c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1 || c == '.'));

        if (c != '.') {
            return res * sgn;
        }
        c = read();

        long aft = 0;
        int len = 1;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            aft *= 10;
            len *= 10;
            aft += c - '0';
            c = read();
        } while (!isSpaceChar(c));

        return res * sgn + aft / (1.0 * len);
    }

    public String nextLine() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndChar(c));
        return res.toString();
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public boolean isEndChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public char[] nextChars() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));

        char[] chars = new char[res.length()];
        res.getChars(0, chars.length, chars, 0);
        return chars;
    }

    public int[][] nextIntMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = nextInt();
        return matrix;
    }

    public long[][] nextLongMatrix(int rows, int cols) {
        long[][] matrix = new long[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = nextLong();
        return matrix;
    }

    public char[][] nextCharMap(int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = nextChar();
        return matrix;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}