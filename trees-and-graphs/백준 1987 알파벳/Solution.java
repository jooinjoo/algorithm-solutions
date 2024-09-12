import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    static int r, c, ans;
    static char[][] map;
    static boolean[][] vis;
    static Set<Character> set;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        r = Integer.parseInt(tok[0]);
        c = Integer.parseInt(tok[1]);
        ans = 0;
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        vis = new boolean[r][c];
        set = new HashSet<>();

        vis[0][0] = true;
        set.add(map[0][0]);
        dfs(0, 0, 1);
        System.out.println(ans);
    }

    static void dfs(int row, int col, int cnt) {
        ans = Math.max(ans, cnt);
        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];
            if (nr < 0 || nc < 0 || nr >= r || nc >= c || vis[nr][nc]) continue;
            if (!set.contains(map[nr][nc])) {
                vis[nr][nc] = true;
                set.add(map[nr][nc]);
                dfs(nr, nc, cnt + 1);
                vis[nr][nc] = false;
                set.remove(map[nr][nc]);
            }
        }
    }
}