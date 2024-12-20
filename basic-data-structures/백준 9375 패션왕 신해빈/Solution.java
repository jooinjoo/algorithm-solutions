import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String[] tok = br.readLine().split(" ");
                map.put(tok[1], map.getOrDefault(tok[1], 0) + 1);
            }
            int tmp = 1;
            for (int v : map.values()) {
                tmp *= (v + 1);
            }
            sb.append(tmp - 1).append("\n");
        }
        System.out.println(sb.toString());
    }
}