import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n, k, ans;
    static int[] vis;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        k = Integer.parseInt(tok[1]);
        ans = 0;
        vis = new int[100001];
        q = new LinkedList<>();

        if (n >= k) {
            sb.append(n - k).append("\n");
            for (int i = n; i >= k; i--) sb.append(i).append(" ");
            System.out.println(sb);
            return;
        }

        vis[n] = 1;
        q.offer(new Pos(n, String.valueOf(n)));
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.idx == k) {
                sb.append(vis[cur.idx] - 1).append("\n");
                sb.append(cur.route);
                break;
            }

            int[] dr = {1, -1, cur.idx};
            for (int i = 0; i < 3; i++) {
                int nr = cur.idx + dr[i];
                if (nr < 0 || nr > 100000) continue;
                if (vis[nr] == 0) {
                    vis[nr] = vis[cur.idx] + 1;
                    q.offer(new Pos(nr, cur.route + " " + nr));
                }
            }
        }
        System.out.println(sb);
    }

    static class Pos {
        int idx;
        String route;

        public Pos(int idx, String route) {
            this.idx = idx;
            this.route = route;
        }
    }
}