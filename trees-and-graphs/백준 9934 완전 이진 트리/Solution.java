import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int k, n, cnt;
    static ArrayList<Integer>[] adj;
    static int[] arr, dp;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        k = Integer.parseInt(br.readLine());
        n = (int) (Math.pow(2, k) - 1);
        cnt = 0;
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            if (i > 0 && i <= Math.pow(2, k - 1) - 1) {
                adj[i].add(i * 2);
                adj[i].add(i * 2 + 1);
            }
        }
        arr = new int[n];
        dp = new int[n + 1];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(tok[i]);
        vis = new boolean[n + 1];

        dfs(1);
        int tmp = 1;
        for (int i = 1; i <= n; i++) {
            sb.append(arr[dp[i]]).append(" ");
            if (i == Math.pow(2, tmp) - 1) {
                sb.append("\n");
                tmp++;
            }
        }
        System.out.println(sb);
    }

    static void dfs(int cur) {
        if (adj[cur].isEmpty()) {
            vis[cur] = true;
            dp[cur] = cnt;
            cnt++;
            return;
        }

        int lt = adj[cur].get(0);
        int rt = adj[cur].get(1);
        dfs(lt);
        vis[cur] = true;
        dp[cur] = cnt;
        cnt++;
        dfs(rt);
    }
}