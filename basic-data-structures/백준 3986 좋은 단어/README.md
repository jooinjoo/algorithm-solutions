# 좋은 단어

> https://www.acmicpc.net/problem/3986

## 문제 설명

- 문제

이번 계절학기에 심리학 개론을 수강 중인 평석이는 오늘 자정까지 보고서를 제출해야 한다. 보고서 작성이 너무 지루했던 평석이는 노트북에 엎드려서 꾸벅꾸벅 졸다가 제출 마감 1시간 전에 깨고 말았다. 안타깝게도 자는
동안 키보드가 잘못 눌려서 보고서의 모든 글자가 A와 B로 바뀌어 버렸다! 그래서 평석이는 보고서 작성을 때려치우고 보고서에서 '좋은 단어'나 세보기로 마음 먹었다.

평석이는 단어 위로 아치형 곡선을 그어 같은 글자끼리(A는 A끼리, B는 B끼리) 쌍을 짓기로 하였다. 만약 선끼리 교차하지 않으면서 각 글자를 정확히 한 개의 다른 위치에 있는 같은 글자와 짝 지을수 있다면, 그
단어는 '좋은 단어'이다. 평석이가 '좋은 단어' 개수를 세는 것을 도와주자.

- 입력

첫째 줄에 단어의 수 N이 주어진다. (1 ≤ N ≤ 100)

다음 N개 줄에는 A와 B로만 이루어진 단어가 한 줄에 하나씩 주어진다. 단어의 길이는 2와 100,000사이이며, 모든 단어 길이의 합은 1,000,000을 넘지 않는다.

- 출력

첫째 줄에 좋은 단어의 수를 출력한다.

## 접근 방법

입력받은 단어의 알파벳을 스택에 삽입하거나 top을 제거.  
스택이 비어있으면 카운팅.

## 문제 해결 과정

- 최종 해결 방법:
    - 단어의 인덱스마다 알파벳을 스택에 삽입하며, top이 현재 알파벳과 같은지, 다른지 비교.
        - 만약 같다면 스택에 넣지않고 top을 `pop()`, 다르다면 그대로 `push()`.
    - 모든 알파벳을 전부 순회했을 때, 스택이 비어있으면 좋은 단어.
    - "쌍", "짝짓기" 등의 키워드가 나온다면 일단 스택을 염두하자.
- 25.7.10. 다시 푼 방법:
    - 이전 해결 방법과 동일하게, 스택을 활용하여 같다면 `pop()`, 다르면 `push()`한 뒤 최종 스택 조회

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Character> stk = new Stack<>();
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            stk.clear();
            char[] input = br.readLine().toCharArray();
            for (char c : input) {
                if (!stk.isEmpty() && stk.peek() == c) {
                    stk.pop();
                } else {
                    stk.push(c);
                }
            }

            if (stk.isEmpty()) cnt++;
        }
        System.out.println(cnt);
    }
}
```
