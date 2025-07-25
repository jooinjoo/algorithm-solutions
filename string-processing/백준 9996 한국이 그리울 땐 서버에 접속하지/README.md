# 한국이 그리울 땐 서버에 접속하지

> https://www.acmicpc.net/problem/9996

## 문제 설명

- 문제

선영이는 이번 학기에 오스트레일리아로 교환 학생을 가게 되었다.

호주에 도착하고 처음 며칠은 한국 생각을 잊으면서 즐겁게 지냈다. 몇 주가 지나니 한국이 그리워지기 시작했다.

선영이는 한국에 두고온 서버에 접속해서 디렉토리 안에 들어있는 파일 이름을 보면서 그리움을 잊기로 했다. 매일 밤, 파일 이름을 보면서 파일 하나하나에 얽힌 사연을 기억하면서 한국을 생각하고 있었다.

어느 날이었다. 한국에 있는 서버가 망가졌고, 그 결과 특정 패턴과 일치하는 파일 이름을 적절히 출력하지 못하는 버그가 생겼다.

패턴은 알파벳 소문자 여러 개와 별표(*) 하나로 이루어진 문자열이다.

파일 이름이 패턴에 일치하려면, 패턴에 있는 별표를 알파벳 소문자로 이루어진 임의의 문자열로 변환해 파일 이름과 같게 만들 수 있어야 한다. 별표는 빈 문자열로 바꿀 수도 있다. 예를 들어, "abcd", "
ad", "anestonestod"는 모두 패턴 "a*d"와 일치한다. 하지만, "bcd"는 일치하지 않는다.

패턴과 파일 이름이 모두 주어졌을 때, 각각의 파일 이름이 패턴과 일치하는지 아닌지를 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 파일의 개수 N이 주어진다. (1 ≤ N ≤ 100)

둘째 줄에는 패턴이 주어진다. 패턴은 알파벳 소문자와 별표(아스키값 42) 한 개로 이루어져 있다. 문자열의 길이는 100을 넘지 않으며, 별표는 문자열의 시작과 끝에 있지 않다.

다음 N개 줄에는 파일 이름이 주어진다. 파일 이름은 알파벳 소문자로만 이루어져 있고, 길이는 100을 넘지 않는다.

- 출력

총 N개의 줄에 걸쳐서, 입력으로 주어진 i번째 파일 이름이 패턴과 일치하면 "DA", 일치하지 않으면 "NE"를 출력한다.

참고로, "DA"는 크로아티어어로 "YES"를, "NE"는 "NO"를 의미한다.

## 접근 방법

`*`를 기준으로 좌측의 문자열 시작 기준, 우측의 문자열 끝 기준을 모두 만족하는 문자열 찾기.  
주어지는 문자열마다 처음과 끝을 검증하면 된다.  
단, 반례를 염두하여 `*`를 제외한 패턴의 길이보다 주어진 문자열이 짧으면 안 된다.

## 문제 해결 과정

- 첫 번째 시도:
    - `String.split()`을 활용해 패턴을 좌우로 잘라서 검증했지만, 반례를 생각하지 못해 65% 실패.
- 최종 해결 방법:
    - 각 문자열이 주어질 때, 가장 먼저 해야할 일은 현재 문자열이 `*`를 제외한 패턴의 길이와 비교하는 것.
        - 만약 문자열 길이가 더 짧다면 패턴은 이후 검증 로직은 만족하는 것처럼 보이지만, `*`가 없어 실패해야 한다.
            - e.g. 문자열: `ab` / 패턴: `ab*ab`
            - 앞, 뒤 패턴 `ab`는 모두 통과하지만, 최소한 문자열은 `abab`여야 한다.
    - 이후 `startsWith()`과 `endsWith()`을 활용해 검증.
- 25.7.4. 다시 푼 방법:
    - 문자열이 주어지면, 해당 문자열의 처음과 끝 종료 조건을 `startsWith()`과 `endsWith()`을 사용하여 검증.
    - 한편 첫 시도에 틀렸는데, 문제를 제대로 확인하지 않아 `*` 사이에 무조건 문자가 있어야 한다고 해석. 문제를 좀 더 꼼꼼히 읽자.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] pattern = br.readLine().split("\\*");

        while (N-- > 0) {
            String input = br.readLine();
            if (input.length() >= pattern[0].length() + pattern[1].length() &&
                    input.startsWith(pattern[0]) && input.endsWith(pattern[1])) {
                sb.append("DA\n");
            } else {
                sb.append("NE\n");
            }
        }
        System.out.println(sb.toString());
    }
}
```