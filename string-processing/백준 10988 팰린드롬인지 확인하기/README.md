# 팰린드롬인지 확인하기

> https://www.acmicpc.net/problem/10988

## 문제 설명

- 문제

알파벳 소문자로만 이루어진 단어가 주어진다. 이때, 이 단어가 팰린드롬인지 아닌지 확인하는 프로그램을 작성하시오.

팰린드롬이란 앞으로 읽을 때와 거꾸로 읽을 때 똑같은 단어를 말한다.

level, noon은 팰린드롬이고, baekjoon, online, judge는 팰린드롬이 아니다.

- 입력

첫째 줄에 단어가 주어진다. 단어의 길이는 1보다 크거나 같고, 100보다 작거나 같으며, 알파벳 소문자로만 이루어져 있다.

- 출력

첫째 줄에 팰린드롬이면 1, 아니면 0을 출력한다.

## 접근 방법

입력받은 문자열을 직접 뒤집어 비교한다.

## 문제 해결 과정

- 최종 해결 방법:
    - `StringBuilder.reverse()`를 활용해, 주어진 문자열을 뒤집은 뒤 원본 비교
    - 단순 `String` 객체보다 `StringBuilder`를 활용하면, 메모리 측면이나 활용 측면 모두 좋은 부분이 존재
- 25.6.24. 다시 푼 방법:
    - `StringBuilder.reverse()`를 활용해, `String input`과 값이 동일한 지 비교.
    - 다른 방법으로는, 왼쪽 끝 인덱스와 오른쪽 끝 인덱스를 서로 비교하며 중간에서 만날 때까지 동일한 지 비교하면 될 것.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String reverse = new StringBuilder(input).reverse().toString();

        System.out.println(input.equals(reverse) ? 1 : 0);
    }
}
```