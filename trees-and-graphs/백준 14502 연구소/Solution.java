import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Solution {

    static int n, m, ans;
    static int[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        map = new int[n][m];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
                if (map[i][j] == 0) list.add(new int[]{i, j});
            }
        }
        ans = 0;

        for (int i = 0; i < list.size() - 2; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    int[][] cMap = copy();
                    cMap[list.get(i)[0]][list.get(i)[1]] = 1;
                    cMap[list.get(j)[0]][list.get(j)[1]] = 1;
                    cMap[list.get(k)[0]][list.get(k)[1]] = 1;
                    virus(cMap);
                }
            }
        }
        System.out.println(ans);
    }

    static int[][] copy() {
        int[][] ret = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[i][j] = map[i][j];
            }
        }
        return ret;
    }

    static void virus(int[][] cMap) {
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cMap[i][j] == 2) q.offer(new int[]{i, j});
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr < 0 || nc < 0 || nr >= n || nc >= m || cMap[nr][nc] != 0) continue;
                cMap[nr][nc] = 2;
                q.offer(new int[]{nr, nc});
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cMap[i][j] == 0) cnt++;
            }
        }
        ans = Math.max(ans, cnt);
    }
}