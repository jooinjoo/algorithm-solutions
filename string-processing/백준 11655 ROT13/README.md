# ROT13

> https://www.acmicpc.net/problem/11655

## 문제 설명

- 문제

ROT13은 카이사르 암호의 일종으로 영어 알파벳을 13글자씩 밀어서 만든다.

예를 들어, "Baekjoon Online Judge"를 ROT13으로 암호화하면 "Onrxwbba Bayvar Whqtr"가 된다. ROT13으로 암호화한 내용을 원래 내용으로 바꾸려면 암호화한 문자열을 다시
ROT13하면 된다. 앞에서 암호화한 문자열 "Onrxwbba Bayvar Whqtr"에 다시 ROT13을 적용하면 "Baekjoon Online Judge"가 된다.

ROT13은 알파벳 대문자와 소문자에만 적용할 수 있다. 알파벳이 아닌 글자는 원래 글자 그대로 남아 있어야 한다. 예를 들어, "One is 1"을 ROT13으로 암호화하면 "Bar vf 1"이 된다.

문자열이 주어졌을 때, "ROT13"으로 암호화한 다음 출력하는 프로그램을 작성하시오.

- 입력

첫째 줄에 알파벳 대문자, 소문자, 공백, 숫자로만 이루어진 문자열 S가 주어진다. S의 길이는 100을 넘지 않는다.

- 출력

첫째 줄에 S를 ROT13으로 암호화한 내용을 출력한다.

## 접근 방법

ROT13 변환은 두 가지 기준에 따라 적용된다. 1) 글자가 대문자 또는 소문자인지. 2) 글자가 알파벳이고 13만큼 밀었을 때 맨 앞으로 초기화되는지.  
2)의 기준은 글자가 M 또는 m(ascii: 77/109)인 경우까지 +13을 하면 되고, 그 이후의 글자인 경우 -13을 하면 된다.

## 문제 해결 과정

- 최종 해결 방법:
    - 입력값을 `char[] arr`으로 바꾼 뒤, 각 글자를 전부 루프하여 변환.
    - `c`가 알파벳이 아닌 경우 바로 출력
        - 알파벳인 경우, 대문자 또는 소문자인 경우에 따라 변환.
        - `c`가 알파벳의 몇 번째 위치인지 구한 뒤, +13. 이후 전체 알파벳 26글자로 나눠, 알파벳 인덱스 0~25 순환
- 25.7.4. 다시 푼 방법:
    - 입력값을 `char[] input` 배열로 바꾼 후, 한 글자씩 루프하며 판별.
    - 해당 글자가 알파벳인 경우와 아닌 경우로 나눠 알파벳인 경우 중간값인 `'M'`을 기준으로 13만큼 뒤로 밀거나 앞으로 당긴다.
        - 알파벳이 26개이므로 +13으로 알파벳 범위를 넘어가면, 반대로 -13만큼 빼주면 된다.

### 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();

        for (char c : input) {
            if (Character.isAlphabetic(c)) {
                if (c < 'a') {
                    sb.append(c <= 'M' ? (char) (c + 13) : (char) (c - 13));
                } else {
                    sb.append(c <= 'm' ? (char) (c + 13) : (char) (c - 13));
                }
            } else sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
```