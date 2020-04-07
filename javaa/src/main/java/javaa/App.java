package javaa;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

class SortThread extends Thread {
    private PrintWriter out = null;
    private BufferedReader in = null;

    public SortThread(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }//from  www .  j av a 2s.c o  m

    public void run() {
        int MAXWORDS = 50;

        if (out != null && in != null) {
            try {
                String[] listOfWords = new String[MAXWORDS];
                int numwords = 0;

                while ((listOfWords[numwords] = in.readLine()) != null)
                    numwords++;
                quicksort(listOfWords, 0, numwords - 1);
                for (int i = 0; i < numwords; i++)
                    out.println(listOfWords[i]);
                out.close();
            } catch (IOException e) {
                System.err.println("SortThread run: " + e);
            }
        }
    }

    private static void quicksort(String[] a, int lo0, int hi0) {
        int lo = lo0;
        int hi = hi0;

        if (lo >= hi)
            return;

        String mid = a[(lo + hi) / 2];
        while (lo < hi) {
            while (lo < hi && a[lo].compareTo(mid) < 0)
                lo++;
            while (lo < hi && a[hi].compareTo(mid) > 0)
                hi--;
            if (lo < hi) {
                String T = a[lo];
                a[lo] = a[hi];
                a[hi] = T;
                lo++;
                hi--;
            }
        }
        if (hi < lo) {
            int T = hi;
            hi = lo;
            lo = T;
        }
        quicksort(a, lo0, lo);
        quicksort(a, lo == lo0 ? lo + 1 : lo, hi0);
    }
}


public class App 
{
 public static void main(String[] args) throws IOException {

        FileReader words = new FileReader("words.txt");

        // do the reversing and sorting
        Reader rhymedWords = reverse(sort(reverse(words)));

        // write new list to standard out
        BufferedReader in = new BufferedReader(rhymedWords);
        String input;

        while ((input = in.readLine()) != null)
            System.out.println(input);
        in.close();
    }

    public static Reader reverse(Reader source) throws IOException {

        BufferedReader in = new BufferedReader(source);

        PipedWriter pipeOut = new PipedWriter();
        PipedReader pipeIn = new PipedReader(pipeOut);
        PrintWriter out = new PrintWriter(pipeOut);

        new ReverseThread(out, in).start();

        return pipeIn;
    }

    public static Reader sort(Reader source) throws IOException {

        BufferedReader in = new BufferedReader(source);

        PipedWriter pipeOut = new PipedWriter();
        PipedReader pipeIn = new PipedReader(pipeOut);
        PrintWriter out = new PrintWriter(pipeOut);

        new SortThread(out, in).start();

        return pipeIn;
    }
}


class ReverseThread extends Thread {
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ReverseThread(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    public void run() {
        if (out != null && in != null) {
            try {
                String input;
                while ((input = in.readLine()) != null) {
                    out.println(reverseIt(input));
                    out.flush();
                }
                out.close();
            } catch (IOException e) {
                System.err.println("ReverseThread run: " + e);
            }
        }
    }

    private String reverseIt(String source) {
        int i, len = source.length();
        StringBuffer dest = new StringBuffer(len);

        for (i = (len - 1); i >= 0; i--)
            dest.append(source.charAt(i));
        return dest.toString();
    }
}

