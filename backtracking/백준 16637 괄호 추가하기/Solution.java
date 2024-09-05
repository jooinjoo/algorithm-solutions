import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    static int n, ans;
    static List<Integer> vals;
    static List<Character> opers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = Integer.MIN_VALUE;
        vals = new ArrayList<>();
        opers = new ArrayList<>();
        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) vals.add(c - '0');
            else opers.add(c);
        }

        dfs(0, vals.get(0));

        System.out.println(ans);
    }

    static void dfs(int idx, int sum) {
        if (idx == vals.size() - 1) {
            ans = Math.max(ans, sum);
            return;
        }

        dfs(idx + 1, calc(sum, vals.get(idx + 1), opers.get(idx)));
        if (idx + 2 <= vals.size() - 1) {
            int rt = calc(vals.get(idx + 1), vals.get(idx + 2), opers.get(idx + 1));
            dfs(idx + 2, calc(sum, rt, opers.get(idx)));
        }
    }

    static int calc(int lt, int rt, char oper) {
        if (oper == '+') return lt + rt;
        else if (oper == '-') return lt - rt;
        else return lt * rt;
    }
}