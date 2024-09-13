# 부등호

> https://www.acmicpc.net/problem/2529

## 문제 설명

- 문제

두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열 A가 있다. 우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다. 예를 들어, 제시된 부등호 순서열
A가 다음과 같다고 하자.

A ⇒ < < < > < < > < >

부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다. 아래는 부등호 순서열 A를 만족시키는 한 예이다.

3 < 4 < 5 < 6 > 1 < 2 < 8 > 7 < 9 > 0

이 상황에서 부등호 기호를 제거한 뒤, 숫자를 모두 붙이면 하나의 수를 만들 수 있는데 이 수를 주어진 부등호 관계를 만족시키는 정수라고 한다. 그런데 주어진 부등호 관계를 만족하는 정수는 하나 이상 존재한다. 예를
들어 3456128790 뿐만 아니라 5689023174도 아래와 같이 부등호 관계 A를 만족시킨다.

5 < 6 < 8 < 9 > 0 < 2 < 3 > 1 < 7 > 4

여러분은 제시된 k개의 부등호 순서를 만족하는 (k+1)자리의 정수 중에서 최댓값과 최솟값을 찾아야 한다. 앞서 설명한 대로 각 부등호의 앞뒤에 들어가는 숫자는 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
}중에서 선택해야 하며 선택된 숫자는 모두 달라야 한다.

- 입력

첫 줄에 부등호 문자의 개수를 나타내는 정수 k가 주어진다. 그 다음 줄에는 k개의 부등호 기호가 하나의 공백을 두고 한 줄에 모두 제시된다. k의 범위는 2 ≤ k ≤ 9 이다.

- 출력

여러분은 제시된 부등호 관계를 만족하는 k+1 자리의 최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력해야 한다. 단 아래 예(1)과 같이 첫 자리가 0인 경우도 정수에 포함되어야 한다. 모든 입력에 답은 항상
존재하며 출력 정수는 하나의 문자열이 되도록 해야 한다.

## 접근 방법

최대 10개의 0 ~ 9 숫자를 각각의 부등호 사이 자리에 넣는 방식이므로 최대 경우의 수는 10! = 약 362만.  
따라서 완전 탐색으로 모든 조합을 만든 뒤 해당 조합의 부등호가 성립하면, 최댓값 또는 최솟값 갱신.  
마지막으로 숫자를 문자열로, 그리고 앞에 0이 있다면 붙여주는 과정도 필요하다.

## 문제 해결 과정

- 최종 해결 방법:
    - `perm()` 함수를 재귀하여 순열의 경우의 수를 만든다.
        - 사용한 수의 경우 방문 처리하며 다시 사용하지 않도록 처리하고, 해당 순열 조합이 완성되면 부등호를 모두 만족하는지 `calc()`로 계산.
        - 모든 부등호가 만족하면 최댓값, 또는 최솟값 갱신.
    - 이후 최댓값과 최솟값을 출력할 때, 앞자리에 0이 있으면 붙여줘야 하기 때문에 `string()` 메서드를 통해 변환한다.
        - 앞에 "0"와 해당 숫자를 뒤에 나열한 뒤, 필요한 자릿수만큼 뒤에서부터 잘라준다.
- 다른 해결 방법:
    - 만들 수 있는 모든 순열 조합을 구하기 보단, `perm()` 함수 내에서 다음 숫자를 체크할 때 부등호 만족 여부 확인.
    - 또한 만족하는 경우는 일단 전부 `ArrayList<String> vals`에 담은 뒤, 정렬하여 첫 값과 마지막 값만 가져오도록 하였다.
        - String 값의 정렬이므로 앞자리부터 아스키 코드 값에 따라 정렬된다.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    static int k;
    static String[] oper;
    static ArrayList<String> vals;
    static boolean[] vis;
    static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        oper = br.readLine().split(" ");
        vals = new ArrayList<>();
        vis = new boolean[10];
        tmp = new int[k + 1];

        perm(0);
        Collections.sort(vals);
        System.out.println(vals.get(vals.size() - 1) + "\n" + vals.get(0));
    }

    static void perm(int idx) {
        if (idx == k + 1) {
            StringBuilder sb = new StringBuilder();
            for (int i : tmp) sb.append(i);
            vals.add(sb.toString());
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (vis[i]) continue;
            if (idx == 0 || calc(idx - 1, i)) {
                vis[i] = true;
                tmp[idx] = i;
                perm(idx + 1);
                vis[i] = false;
            }
        }
    }

    static boolean calc(int idx, int rt) {
        if (oper[idx].equals("<")) return tmp[idx] < rt;
        else return tmp[idx] > rt;
    }
}
```