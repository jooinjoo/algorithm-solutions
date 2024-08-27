import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < t; i++) {
            char[] chars = br.readLine().toCharArray();
            boolean flag = true;

            for (char c : chars) {
                if (c == '(') stk.push(c);
                else {
                    if (stk.isEmpty()) {
                        flag = false;
                        break;
                    } else stk.pop();
                }
            }
            if (!stk.isEmpty()) flag = false;

            sb.append(flag ? "YES" : "NO").append("\n");
            stk.clear();
        }
        System.out.println(sb);
    }
}