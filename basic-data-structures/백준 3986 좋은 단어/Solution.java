import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        Stack<Character> stack;

        while (n-- > 0) {
            stack = new Stack<>();
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                char cur = str.charAt(i);
                if (!stack.isEmpty() && stack.peek() == cur) {
                    stack.pop();
                } else {
                    stack.push(cur);
                }
            }
            if (stack.isEmpty()) cnt++;
        }
        System.out.println(cnt);
    }
}