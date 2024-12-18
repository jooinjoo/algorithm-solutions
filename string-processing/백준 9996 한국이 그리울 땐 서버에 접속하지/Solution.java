import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] tok = br.readLine().split("\\*");
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        for (String s : arr) {
            if (s.length() < tok[0].length() + tok[1].length()) {
                sb.append("NE").append("\n");
            } else {
                if (s.startsWith(tok[0]) && s.endsWith(tok[1])) {
                    sb.append("DA").append("\n");
                } else {
                    sb.append("NE").append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }
}