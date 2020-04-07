import java.util.HashSet;

public class Nkr {
    public static int consecutive(int[] a) {
        // Put all the values into a HashSet
        HashSet<Integer> values = new HashSet();
        for (int i : a) {
            values.add(i);/*from   ww w. ja v  a  2s .  c o m*/
        }

        // For each value, check if its the first in a sequence of consecutive 
        // numbers and iterate through to find the length of the consecutive 
        // sequence
        int maxLength = 0;
        for (int i : values) {
            // If it is not the leftmost value in the sequence, don't bother
            if (values.contains(i - 1))
                continue;
            int length = 0;

            // Iterate through sequence
            while (values.contains(i++))
                length++;
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    // Sample test cases
    public static void main(String[] args) {
        assert consecutive(new int[] { 1, 2, 3, 4, 5 }) == 5 : "Single sequence, sorted order";
        assert consecutive(new int[] { 5, 4, 3, 2, 1 }) == 5 : "Single sequence, unsorted order";
        System.out.println("Passed all test cases");
    }
}
