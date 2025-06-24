# 알파벳 개수

> https://www.acmicpc.net/problem/10808

## 문제 설명

- 문제

알파벳 소문자로만 이루어진 단어 S가 주어진다. 각 알파벳이 단어에 몇 개가 포함되어 있는지 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 단어 S가 주어진다. 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.

- 출력

단어에 포함되어 있는 a의 개수, b의 개수, …, z의 개수를 공백으로 구분해서 출력한다.

## 접근 방법

주어진 문자에 사용된 알파벳을 카운팅하므로, 맵 또는 배열을 활용.
다만 알파벳이기 때문에 0~26으로 인덱스를 나눌 수 있어 배열 이용. (문자열이나 범위가 엄청 큰 경우는 맵 이용)

## 문제 해결 과정

- 최종 해결 방법:
    - 입력받은 소문자 문자열을 아스키 코드로 변환하여 알파벳 `a`, 즉 65만큼 빼주면 모든 알파벳은 0~26 범위에 존재하게 된다.
    - 해당 인덱스 값을 루프하며 카운팅.
- 25.6.24. 다시 푼 방법:
    - 알파벳 26개를 카운팅하는 배열 `cnt[]`에 `input`을 루프하며 각각 카운팅.
    - 아스키 코드에서 2가지만 외우자. A는 65, a는 97.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[] cnt = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();

        for (char c : input) cnt[c - 97]++;
        for (int i : cnt) sb.append(i).append(" ");
        System.out.println(sb.toString());
    }
}
```