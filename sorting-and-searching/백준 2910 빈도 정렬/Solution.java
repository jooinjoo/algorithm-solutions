import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n, c;
    static Map<Integer, Integer> map;
    static Map<Integer, Integer> mapIdx;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] words = br.readLine().split(" ");
        n = Integer.parseInt(words[0]);
        c = Integer.parseInt(words[1]);
        map = new HashMap<>();
        mapIdx = new HashMap<>();
        words = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(words[i]);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if (!mapIdx.containsKey(cur)) {
                mapIdx.put(cur, i);
            }
        }

        list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> {
            if (map.get(o2) == map.get(o1)) return mapIdx.get(o1) - mapIdx.get(o2);
            return map.get(o2) - map.get(o1);
        });

        for (int cur : list) {
            for (int j = 0; j < map.get(cur); j++) {
                sb.append(cur).append(" ");
            }
        }
        System.out.println(sb);
    }
}
