# 오큰수

> https://www.acmicpc.net/problem/17298

## 문제 설명

- 문제

크기가 N인 수열 A = A1, A2, ..., AN이 있다. 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다. Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를
의미한다. 그러한 수가 없는 경우에 오큰수는 -1이다.

예를 들어, A = [3, 5, 2, 7]인 경우 NGE(1) = 5, NGE(2) = 7, NGE(3) = 7, NGE(4) = -1이다. A = [9, 5, 4, 8]인 경우에는 NGE(1) = -1, NGE(

2) = 8, NGE(3) = 8, NGE(4) = -1이다.

- 입력

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)이 주어진다.

- 출력

총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.

## 접근 방법

수열의 각 원소에 대해 오른쪽에 있는 수 중에서, 자신보다 큰 가장 왼쪽에 있는 수 찾기 문제.  
단순히 이중 for문을 통해 문제를 풀 수 있으나, N은 최대 100만인데 시간 복잡도가 O(N^2)가 되어 실패.  
대신 스택을 통해, 오큰수가 되지 못한 원소의 인덱스를 스택에 삽입하며 시간 복잡도 O(N)으로 줄일 수 있다.

## 문제 해결 과정

- 첫 번째 시도:
    - 단순 이중 루프를 사용해 각 원소마다, 오른쪽으로 루프하며 오큰수 찾기.
    - 직관적이지만 N이 최대 100만이라 실행 시간이 너무 커서 실패.
- 최종 해결 방법:
    - 우선 모든 인덱스의 오큰수를 저장한 배열 `int[] ret = -1`로 초기화.
    - 첫 번째 원소의 인덱스를 스택에 삽입. 스택에는 오직 해당 원소의 인덱스만 들어간다.
    - 다음 원소부터 현재 원소의 값 `cur`과 스택의 top에 있는 인덱스의 원소 값, `arr[stk.peek()]`을 비교.
        - 만약 만약 현재 원소 값이 더 크다면 해당 인덱스에 대해, 현재 원소 값이 오큰수.
        - 이 비교를 스택이 비거나, 더이상 크지 않을 때까지 반복.
        - `stk.pop()`은 O(1)의 연산이므로 시간 복잡도에 큰 영향을 주지 않는다.
    - 현재 원소 값은 위의 과정과는 상관없이 항상 스택에 인덱스를 삽입.
        - 어차피 해당 인덱스의 오큰수 검증은 무조건 다음 인덱스부터 가능하므로.
    - 루프가 끝나면 `ret`의 값 출력.
    - 값끼리 1대1로 짝을 짓는 형식은 항상 스택을 먼저 떠올려보자.
- 25.8.10. 다시 푼 방법:
    - 특정 인덱스는 하나의 오큰수와 짝을 이룬다. 따라서 오큰수가 나올 때까지 스택에 담아두며 루프하다가, 오큰수 등장 시 스택에 담아놓은 것들을 모두 오큰수로 지정.
    - 이 과정에서 스택에는 해당 값이 아닌 인덱스를 넣어놓는 것이 좋다.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int[] ret = new int[N];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(tok[i]);
        }
        Arrays.fill(ret, -1);

        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stk.isEmpty() && input[stk.peek()] < input[i]) {
                ret[stk.pop()] = input[i];
            }
            stk.push(i);
        }

        for (int i : ret) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
```