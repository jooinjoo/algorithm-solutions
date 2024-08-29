import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int n, root, del;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(tok[i]);
            if (p == -1) {
                root = i;
                continue;
            }
            adj[p].add(i);
        }
        del = Integer.parseInt(br.readLine());

        if (del == root) {
            System.out.println(0);
        } else {
            System.out.println(dfs(root));
        }
    }

    static int dfs(int idx) {
        int cnt = 0;
        int ret = 0;
        for (int next : adj[idx]) {
            if (next == del) continue;
            ret += dfs(next);
            cnt++;
        }
        if (cnt == 0) return 1;
        return ret;
    }
}