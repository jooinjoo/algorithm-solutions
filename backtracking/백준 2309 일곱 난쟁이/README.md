# 일곱 난쟁이

> https://www.acmicpc.net/problem/2309

## 문제 설명

- 문제

왕비를 피해 일곱 난쟁이들과 함께 평화롭게 생활하고 있던 백설공주에게 위기가 찾아왔다. 일과를 마치고 돌아온 난쟁이가 일곱 명이 아닌 아홉 명이었던 것이다.

아홉 명의 난쟁이는 모두 자신이 "백설 공주와 일곱 난쟁이"의 주인공이라고 주장했다. 뛰어난 수학적 직관력을 가지고 있던 백설공주는, 다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해 냈다.

아홉 난쟁이의 키가 주어졌을 때, 백설공주를 도와 일곱 난쟁이를 찾는 프로그램을 작성하시오.

- 입력

아홉 개의 줄에 걸쳐 난쟁이들의 키가 주어진다. 주어지는 키는 100을 넘지 않는 자연수이며, 아홉 난쟁이의 키는 모두 다르며, 가능한 정답이 여러 가지인 경우에는 아무거나 출력한다.

- 출력

일곱 난쟁이의 키를 오름차순으로 출력한다. 일곱 난쟁이를 찾을 수 없는 경우는 없다.

## 접근 방법

7명의 난쟁이를 조합하여, 합이 100이 되는 경우의 수를 아무거나 출력하면 된다.
9C7 = 9C2 = 36가지의 경우의 수를 전부 탐색하며 두 난쟁이를 제외한 합이 100이 될 때 출력.

## 문제 해결 과정

- 최종 해결 방법:
    - 조합의 개수가 3개 이하이므로, 재귀가 아닌 단순 2중 for문으로 두 난쟁이 조합
        - 가능한 조합 중 아무거나 출력해도 되지만, 사전 순으로 가장 빠른 조합 등 제약이 있다면 이중 for문에서 break를 걸어줘야 한다.
    - 총합 `sum`에서 조합만큼 뺐을 때, 100이 되면 `arr[]`에 기록. 이후 출력.
- 다른 해결 방법 2:
    - 문제의 의도에는 맞지 않지만, 다양한 풀이 연습을 위해 조합이 아닌 순열을 활용한 백트래킹 풀이.
    - `n`은 전체 원소의 개수, `r`은 선택할 원소의 개수, `depth`는 현재 탐색의 깊이(몇 번째 원소를 선택하고 있는지)
        - `r`이 `depth`와 같아지면 정답인지 검사.
        - 정답이 아니라면, `a[i]`와 `a[depth]`를 바꾸어 `depth` 위치에 `i`번째 원소를 배치.

## 다른 코드 1

```java
public class Solution {

    static int sum = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        arr = new int[9];
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        Arrays.sort(arr);

        int[] tmp = comb();
        for (int i = 0; i < 9; i++) {
            if (i == tmp[0] || i == tmp[1]) continue;
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[] comb() {
        int[] ret = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    return new int[]{i, j};
                }
            }
        }
        return ret;
    }
}
```