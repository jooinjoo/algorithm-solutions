import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] arr = br.readLine().toCharArray();

        for (char c : arr) {
            if (Character.isAlphabetic(c)) {
                if (Character.isUpperCase(c)) {
                    c = (char) ('A' + (c - 'A' + 13) % 26);
                } else {
                    c = (char) ('a' + (c - 'a' + 13) % 26);
                }
            }
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}