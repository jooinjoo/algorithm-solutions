import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();
        int n = input.length;
        char mid = 0;
        boolean flag = true;

        Arrays.sort(input);
        for (int i = 0; i < n; i++) {
            if (i < n - 1 && input[i] == input[i + 1]) {
                sb.append(input[i]);
                i++;
            } else {
                if (mid == 0) {
                    mid = input[i];
                } else {
                    flag = false;
                    break;
                }
            }
        }

        if (!flag) {
            System.out.println("I'm Sorry Hansoo");
        } else {
            String ans = sb.toString() + (mid == 0 ? "" : mid) + sb.reverse().toString();
            System.out.println(ans);
        }
    }
}
