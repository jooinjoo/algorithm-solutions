import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    static Map<String, Integer> map1;
    static Map<Integer, String> map2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        int N = Integer.parseInt(tok[0]);
        int M = Integer.parseInt(tok[1]);
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            map1.put(s, i);
            map2.put(i, s);
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (Character.isAlphabetic(s.charAt(0))) {
                sb.append(map1.get(s)).append("\n");
            } else {
                int num = Integer.parseInt(s);
                sb.append(map2.get(num)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}