import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        long a = Long.parseLong(tokens[0]);
        long b = Long.parseLong(tokens[1]);
        long c = Long.parseLong(tokens[2]);

        System.out.println(recur(a, b, c));
    }

    static long recur(long a, long b, long c) {
        if (b == 1) return a % c;

        long tmp = recur(a, b / 2, c);
        if (b % 2 == 0) {
            return tmp * tmp % c;
        } else {
            return (tmp * tmp % c) * a % c;
        }
    }
}