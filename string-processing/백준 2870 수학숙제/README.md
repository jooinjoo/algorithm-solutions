# 수학숙제

> https://www.acmicpc.net/problem/2870

## 문제 설명

- 문제

상근이는 수학시간에 딴 짓을 하다가 선생님께 걸렸다. 선생님은 상근이에게 이번 주말동안 반성하라며 엄청난 숙제를 내주었다.

선생님이 상근이에게 준 종이에는 숫자와 알파벳 소문자로 되어있는 글자가 N줄있다. 상근이는 여기서 숫자를 모두 찾은 뒤, 이 숫자를 비내림차순으로 정리해야한다. 숫자의 앞에 0이 있는 경우에는 정리하면서 생략할 수
있다.

글자를 살펴보다가 숫자가 나오는 경우에는, 가능한 가장 큰 숫자를 찾아야 한다. 즉, 모든 숫자의 앞과 뒤에 문자가 있거나, 줄의 시작 또는 끝이어야 한다.

예를 들어, 01a2b3456cde478에서 숫자를 찾으면 1, 2, 3456, 478이다.

선생님이 준 종이의 내용이 주어졌을 때, 상근이의 숙제를 대신하는 프로그램을 작성하시오.

- 입력

첫째 줄에 종이의 줄의 개수 N이 주어진다. (1 ≤ N ≤ 100)

다음 N개의 줄에는 각 줄의 내용이 주어진다. 각 줄은 최대 100글자이고, 항상 알파벳 소문자와 숫자로만 이루어져 있다.

- 출력

종이에서 찾은 숫자의 개수를 M이라고 하면, 출력은 M줄로 이루어져야 한다. 각 줄에는 종이에서 찾은 숫자를 하나씩 출력해야 한다. 이때, 비내림차순으로 출력해야 한다. 비내림차순은 내림차순의 반대인 경우인데, 다음
수가 앞의 수보다 크거나 같은 경우를 말한다.

## 접근 방법

종이의 각 줄에서 연속된 숫자로만 이어진 문자열을 `List<String> list`에 삽입.  
이 과정에서 앞에서부터 연속된 "0"이 존재한다면, 제거한 뒤 삽입.  
모두 삽입한 뒤, `list`를 1순위 문자열 길이, 2순위 아스키 값에 따라 오름차순 정렬.  
시간 복잡도는 O(N * M + logM), 최악의 경우 M = 50 * N이므로, O(N^2 + logN).

## 문제 해결 과정

- 첫 번째 시도:
    - 찾은 숫자를 `list`에 Integer 타입으로 받아, NumberFormatException으로 실패.
        - 각 줄의 문자열 `s`의 원소를 각각 탐색하여, 숫자가 나오면 더하고 문자가 나오면 `tmp`를 리스트에 삽입.
        - 이후 리스트를 오름차순으로 정렬하고 그대로 출력하였으나, Integer 범위를 한참 초과하는 케이스를 고려하지 못함.
            - 한 줄의 최대 길이는 100, 즉 10의 99승까지 가능한데 Long 타입도 최대 2의 64승에 불과함.
- 최종 해결 방법:
    - 찾은 숫자를 String 타입으로 받아, 정렬하는 것이 핵심.
    - 문자열 `s`의 원소 `c`가 숫자인지 `Character.isDigit()` 메서드로 판단.
        - 숫자이면 `tmp`에 누적, 문자이면 리스트에 추가하는 `add()` 호출.
            - `add()`를 통해 앞에서부터 "0"이 연속된 경우에만 "0"을 지워준 뒤, `tmp` 초기화.
        - `c` 루프가 끝나면 마지막에 숫자로 끝난 찾은 숫자가 있는지 확인.
    - 문자열 길이를 우선으로 정렬한 뒤, 길이가 같다면 아스키 코드 값을 기준으로 정렬하는 `compareTo()` 사용.
- 25.8.5. 다시 푼 방법:
    - String 타입의 리스트를 선언하여, 숫자 형태의 String 값을 `vals`에 넣는다.
        - 문자열이 최댓 길이로 들어오면 숫자 타입 범위를 넘어서기 때문.
    - 각 케이스마다 한 글자씩 루프하며, 해당 값이 숫자라면 `tmp`에 더하고, 그렇지 않으면 직전까지 `tmp` 값을 `vals`에 추가.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    static int N;
    static List<String> vals;
    static String tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        vals = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            tmp = "";

            for (char cur : input.toCharArray()) {
                if (Character.isDigit(cur)) {
                    tmp += cur;
                } else if (!tmp.isEmpty()) add(tmp);
            }
            if (!tmp.isEmpty()) add(tmp);
        }

        vals.sort((o1, o2) -> {
            if (o1.length() == o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });

        for (String v : vals) {
            sb.append(v).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void add(String input) {
        while (input.length() > 1 && input.charAt(0) == '0') {
            input = input.substring(1);
        }

        vals.add(input);
        tmp = "";
    }
}
```