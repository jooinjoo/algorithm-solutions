# 괄호

> https://www.acmicpc.net/problem/9012

## 문제 설명

- 문제

괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS,
VPS)이라고 부른다. 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 그리고 두 VPS x
와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두
VPS 가 아닌 문자열이다.

여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다.

- 입력

입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에
주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.

- 출력

출력은 표준 출력을 사용한다. 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다.

## 접근 방법

스택 자료구조에 "("가 입력될 때마다 `push()`, ")"가 입력될 때마다 `pop()`.   
이 과정에서 스택이 비었는데 ")"가 입력되거나, 모든 연산 이후에 스택이 비지 않으면 VPS가 아님.

## 문제 해결 과정

- 최종 해결 방법:
    - 각 입력에 대해 한 글자씩 루프하며, 스택에 글자를 삽입하거나 빼내거나 반복.
        - `c == '('`인 경우 항상 스택에 `push()`.
        - `c == ')'`인 경우 기존 스택에 삽입된 값을 `pop()`.
            - 이 과정에서 스택이 이미 비어있거나, 모든 루프 이후에도 스택에 값이 남아있다면 VPS 실패. "NO" 출력.
        - 위의 VPS 조건을 모두 만족한다면 "YES" 출력.
    - 입력의 루프가 끝나면, 다음 입력을 위해 `stk.clear()`로 스택 초기화.
- 25.8.6. 다시 푼 방법:
    - 스택을 활용하여 `(`와 `)`가 만나면 항상 `pop()`하도록 만들었을 때, VPS는 항상 스택이 비어있어야 한다.
    - 따라서 스택의 탑 값과 현재 값 `cur`이 짝을 이룰 때만 `pop()`하도록 만들고, 나머지는 전부 `push()`.
        - 이런 로직을 따랐을 때 항상 마지막에 스택이 비어있는지 아닌지만 검사하면 VPS 감별 가능.
    - 좀 더 성능 향상을 위한다면, `)`가 혼자 쌓였을 때 바로 실패하는 종료 조건을 넣을 수도 있겠다.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        Stack<Character> stk;
        for (int i = 0; i < T; i++) {
            stk = new Stack<>();
            char[] chars = br.readLine().toCharArray();

            for (char cur : chars) {
                if (stk.isEmpty()) {
                    stk.push(cur);
                } else {
                    if (stk.peek() == '(' && cur == ')') stk.pop();
                    else stk.push(cur);
                }
            }

            if (stk.isEmpty()) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb.toString());
    }
}
```
