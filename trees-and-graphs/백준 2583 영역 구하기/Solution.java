import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Solution {

    static int m, n, k, cnt;
    static int[][] map;
    static ArrayList<Integer> list;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] words = br.readLine().split(" ");
        m = Integer.parseInt(words[0]);
        n = Integer.parseInt(words[1]);
        k = Integer.parseInt(words[2]);
        map = new int[m][n];
        for (int i = 0; i < k; i++) {
            words = br.readLine().split(" ");
            int x1 = Integer.parseInt(words[0]);
            int y1 = Integer.parseInt(words[1]);
            int x2 = Integer.parseInt(words[2]);
            int y2 = Integer.parseInt(words[3]);
            for (int j = y1; j < y2; j++) {
                for (int k = x1; k < x2; k++) {
                    map[j][k] = 1;
                }
            }
        }
        cnt = 0;
        list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        Collections.sort(list);
        sb.append(cnt).append("\n");
        for (int i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        map[r][c] = 1;
        int ret = 1;
        q.offer(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || map[nr][nc] == 1) continue;
                map[nr][nc] = 1;
                ret++;
                q.offer(new int[]{nr, nc});
            }
        }
        list.add(ret);
    }
}