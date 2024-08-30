import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Solution {

    static int n, m, max;
    static ArrayList<Integer>[] adj;
    static int[] dp;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            tok = br.readLine().split(" ");
            int a = Integer.parseInt(tok[0]);
            int b = Integer.parseInt(tok[1]);
            adj[b].add(a);
        }
        max = -1;
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            vis = new boolean[n + 1];
            dp[i] = bfs(i);
            max = Math.max(max, dp[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (dp[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static int bfs(int idx) {
        Queue<Integer> q = new ArrayDeque<>();
        vis[idx] = true;
        q.offer(idx);
        int ret = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : adj[cur]) {
                if (vis[next]) continue;
                vis[next] = true;
                ret++;
                q.offer(next);
            }
        }
        return ret;
    }
}