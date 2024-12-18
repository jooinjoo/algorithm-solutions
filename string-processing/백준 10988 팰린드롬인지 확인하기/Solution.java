import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String rev = sb.append(str).reverse().toString();

        if (str.equals(rev)) System.out.println(1);
        else System.out.println(0);
    }
}