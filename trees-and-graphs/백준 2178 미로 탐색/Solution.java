import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        n = Integer.parseInt(strs[0]);
        m = Integer.parseInt(strs[1]);
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.y == n - 1 && cur.x == m - 1) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m || map[ny][nx] != 1) continue;
                map[ny][nx] = map[cur.y][cur.x] + 1;
                q.offer(new Pos(ny, nx));
            }
        }
        System.out.println(map[n - 1][m - 1]);
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}