import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    static int n, cnt, tmp;
    static int[][] map;
    static boolean[][] vis;
    static Set<Integer> set;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String[] words = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(words[j]);
                set.add(map[i][j]);
            }
        }
        cnt = 1;

        for (int cur : set) {
            tmp = 0;
            vis = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (vis[i][j] || map[i][j] <= cur) continue;
                    vis[i][j] = true;
                    dfs(i, j, cur);
                    tmp++;
                }
            }
            cnt = Math.max(cnt, tmp);
        }
        System.out.println(cnt);
    }

    static void dfs(int r, int c, int cur) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n || vis[nr][nc] || map[nr][nc] <= cur) continue;
            vis[nr][nc] = true;
            dfs(nr, nc, cur);
        }
    }
}