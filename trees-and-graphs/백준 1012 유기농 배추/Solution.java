import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static int t, n, m, k;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] strs = br.readLine().split(" ");
            m = Integer.parseInt(strs[0]);
            n = Integer.parseInt(strs[1]);
            k = Integer.parseInt(strs[2]);
            map = new int[n][m];
            for (int i = 0; i < k; i++) {
                strs = br.readLine().split(" ");
                map[Integer.parseInt(strs[1])][Integer.parseInt(strs[0])] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int r, int c) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(r, c));
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0) continue;
                map[nr][nc] = 0;
                q.offer(new Pos(nr, nc));
            }
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}