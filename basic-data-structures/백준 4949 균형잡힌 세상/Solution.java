import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;
        Stack<Character> stk = new Stack<>();
        while (!(s = br.readLine()).equals(".")) {
            char[] chars = s.toCharArray();
            boolean flag = true;

            for (char c : chars) {
                if (c == '(' || c == '[') stk.push(c);
                else if (c == ')' || c == ']') {
                    if (stk.isEmpty()) {
                        flag = false;
                        break;
                    }
                    char top = stk.peek();
                    if ((top == '(' && c == ']') || (top == '[' && c == ')')) {
                        flag = false;
                        break;
                    }
                    stk.pop();
                }
            }
            if (!stk.isEmpty()) flag = false;

            sb.append(flag ? "yes\n" : "no\n");
            stk.clear();
        }
        System.out.println(sb);
    }
}