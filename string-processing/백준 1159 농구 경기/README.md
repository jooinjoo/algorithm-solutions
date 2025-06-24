# 농구 경기

> https://www.acmicpc.net/problem/1159

## 문제 설명

- 문제

상근이는 농구의 세계에서 점차 영향력을 넓혀가고 있다. 처음에 그는 농구 경기를 좋아하는 사람이었다. 농구에 대한 열정은 그를 막을 수 없었고, 결국 상근이는 농구장을 청소하는 일을 시작했다. 상근이도 농구장을
청소하면서 감독이 되기 위해 가져야할 능력을 공부해나갔다. 서당개 3년이면 풍월을 읊듯이 상근이는 점점 감독으로 한 걸음 다가가고 있었다. 어느 날 그에게 지방의 한 프로농구팀을 감독할 기회가 생기게 되었다. 그는
엄청난 지도력을 보여주며 프로 리그에서 우승을 했고, 이제 국가대표팀의 감독이 되었다.

내일은 일본과 국가대표 친선 경기가 있는 날이다. 상근이는 내일 경기에 나설 선발 명단을 작성해야 한다.

국가대표팀의 감독이 된 이후에 상근이는 매우 게을러졌다. 그는 선수의 이름을 기억하지 못하고, 각 선수의 능력도 알지 못한다. 따라서, 누가 선발인지 기억하기 쉽게 하기 위해 성의 첫 글자가 같은 선수 5명을
선발하려고 한다. 만약, 성의 첫 글자가 같은 선수가 5명보다 적다면, 상근이는 내일 있을 친선 경기를 기권하려고 한다.

상근이는 내일 경기를 위해 뽑을 수 있는 성의 첫 글자를 모두 구해보려고 한다.

- 입력

첫째 줄에 선수의 수 N (1 ≤ N ≤ 150)이 주어진다. 다음 N개 줄에는 각 선수의 성이 주어진다. (성은 알파벳 소문자로만 이루어져 있고, 최대 30글자이다)

- 출력

상근이가 선수 다섯 명을 선발할 수 없는 경우에는 "PREDAJA" (따옴표 없이)를 출력한다. PREDAJA는 크로아티아어로 항복을 의미한다. 선발할 수 있는 경우에는 가능한 성의 첫 글자를 사전순으로 공백없이
모두 출력한다.

## 접근 방법

각 선수의 첫 글자만 따서, 해당 알파벳을 카운팅.  
전체 선수를 카운팅한 뒤, 5이상 카운팅된 경우 선발.

## 문제 해결 과정

- 최종 해결 방법:
    - 첫 번째 글자만 따서, `int[] arr`에 알파벳 카운팅.
        - 이후 아스키 코드 값을 다시 `char` 타입으로 변환.
    - 알파벳 관련 문제는 대부분 카운팅으로 일단 접근해보면 좋을 법하다.
- 25.6.24. 다시 푼 방법:
    - 선발 가능한 알파벳은 5개 이상 카운팅되어야 한다. 따라서 `int[] cnt`를 통해 앞 글자만 판별하여 카운팅.
    - 모든 알파벳의 카운팅 후, `cnt`의 원소 값이 5 이상인 인덱스에 대해서만 `StringBuilder sb`에 출력.
        - 한편 `isEmpty()` 메서드는 `String` 타입에서만 가능하다. `StringBuilder`타입에서 변환하지 않으면 컴파일 에러.

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
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            char ch = br.readLine().charAt(0);
            cnt[ch - 97]++;
        }

        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] >= 5) sb.append((char) (i + 97));
        }

        if (sb.toString().isEmpty()) System.out.println("PREDAJA");
        else System.out.println(sb.toString());
    }
}
```
