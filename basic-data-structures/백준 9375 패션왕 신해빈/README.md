# 패션왕 신해빈

> https://www.acmicpc.net/problem/9375

## 문제 설명

- 문제

해빈이는 패션에 매우 민감해서 한번 입었던 옷들의 조합을 절대 다시 입지 않는다. 예를 들어 오늘 해빈이가 안경, 코트, 상의, 신발을 입었다면, 다음날은 바지를 추가로 입거나 안경대신 렌즈를 착용하거나 해야한다.
해빈이가 가진 의상들이 주어졌을때 과연 해빈이는 알몸이 아닌 상태로 며칠동안 밖에 돌아다닐 수 있을까?

- 입력

첫째 줄에 테스트 케이스가 주어진다. 테스트 케이스는 최대 100이다.

각 테스트 케이스의 첫째 줄에는 해빈이가 가진 의상의 수 n(0 ≤ n ≤ 30)이 주어진다.
다음 n개에는 해빈이가 가진 의상의 이름과 의상의 종류가 공백으로 구분되어 주어진다. 같은 종류의 의상은 하나만 입을 수 있다.
모든 문자열은 1이상 20이하의 알파벳 소문자로 이루어져있으며 같은 이름을 가진 의상은 존재하지 않는다.

- 출력

각 테스트 케이스에 대해 해빈이가 알몸이 아닌 상태로 의상을 입을 수 있는 경우를 출력하시오.

## 접근 방법

같은 종류의 의상은 하나만 입을 수 있으므로, 모든 종류에 대해 입을 수 있는 모든 조합의 수를 구한다.  
이후 최소 하나는 입어야 하므로, 전부 입지 않은 경우의 수 1을 빼면 정답.  
n은 30이하이므로, 최대 2^30-1 경우의 수까지 가능.

## 문제 해결 과정

- 최종 해결 방법:
    - 각 테스트마다 `Map<String, Integer> map`을 선언해 의상 종류를 키, 의상 수를 밸류로 삽입
    - 모든 의상을 넣은 후, `map.values()`를 통해 모든 의상 수를 루프하며 모든 경우의 수 구하기
        - 최종 값에 -1한 값 출력.
- 25.7.8. 다시 푼 방법:
    - 경우의 수로 접근. 옷을 입는 모든 경우의 수에서 아무것도 입지 않는 경우의 수 1가지만 빼주면 된다.
        - 이 과정에서 각 옷마다 옷 종류를 키로 갖는 Map에 +1 씩 밸류값을 더한다.
        - 이후 `Map.EntrySet()`을 활용해 키/밸류를 모두 가져오는 `Map.Entry`를 활용.
    - 시간복잡도는 O(t * n)으로 최대 3,000 정도로 무난히 통과.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        Map<String, Integer> map;
        int n;
        String[] tok;
        while (t-- > 0) {
            map = new HashMap<>();
            n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                tok = br.readLine().split(" ");
                map.put(tok[1], map.getOrDefault(tok[1], 0) + 1);
            }

            int ans = 1;
            for (Map.Entry<String, Integer> entrySet : map.entrySet()) {
                ans *= entrySet.getValue() + 1;
            }
            sb.append(ans - 1).append("\n");
        }
        System.out.println(sb.toString());
    }
}
```