import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n;
    static int[] arr;
    static int[][] sub = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
    static int[][][] vis;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[3];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tok[i]);
        }
        vis = new int[61][61][61];
        q = new LinkedList<>();

        q.offer(new int[]{arr[0], arr[1], arr[2]});
        vis[arr[0]][arr[1]][arr[2]] = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == 0 && cur[1] == 0 && cur[2] == 0) break;

            for (int i = 0; i < 6; i++) {
                int x = Math.max(0, cur[0] - sub[i][0]);
                int y = Math.max(0, cur[1] - sub[i][1]);
                int z = Math.max(0, cur[2] - sub[i][2]);
                if (vis[x][y][z] != 0) continue;

                vis[x][y][z] = vis[cur[0]][cur[1]][cur[2]] + 1;
                q.offer(new int[]{x, y, z});
            }
        }
        System.out.println(vis[0][0][0] - 1);
    }
}