# 교수가 된 현우

> https://www.acmicpc.net/problem/3474

## 문제 설명

- 문제

알고리즘의 킹갓제너럴엠퍼러마제스티충무공알고리즘마스터 현우가 교수로 취임하였다!

그러나 학생들에게 크나큰 기대를 품고 첫 수업에 들어갔던 현우는 아무도 외판원 순회 문제(Traveling Salesman Problem, TSP)를 풀지 못하는 것을 보고 낙심하였다.

그 와중에 학생 남규는 TSP를 완전탐색으로 풀려고 하였고, 현우는 그걸 보고 경악을 금치 못한다. 왜냐면 TSP를 완전탐색으로 풀려면 O(N!)의 시간이 소모되는데, 이는 경악을 금치 못할 시간이기 때문이다.

그러나 남규는 O(N!)이 왜 큰지도 잘 모른다. 그래서 현우는 더더욱 경악을 금치 못하고, N!이 얼마나 큰지 대략적으로나마 알려주기 위해, 자연수 N이 주어지면 N!의 오른쪽 끝에 있는 0의 개수를 알려주기로
하였다.

그러나 현우는 경악을 금치 못하여 지금 코딩을 할 수 없는 상황이다. 여러분이 현우를 대신하여 프로그램을 작성하시오.

- 입력

첫째 줄에 테스트 케이스의 개수 T가 주어지고, 이어서 T개의 줄에 정수 N이 주어진다(1 <= N <= 1000000000).

- 출력

각 줄마다 N!의 오른쪽 끝에 있는 0의 개수를 출력한다.

## 접근 방법

각 줄의 N!에 대해, 10을 만들 수 있는 2와 5가 사용된 최소한의 개수를 구한다.

## 문제 해결 과정

- 최종 해결 방법:
    - 5의 제곱수 `tmp`가 `n` 이하인 동안, `n`을 `tmp`로 나눈 몫을 누적 카운팅.
    - 오른쪽 끝에 있는 0의 개수는 10의 개수와 동일. 10 = 2 * 5이므로, 2와 5가 한 짝을 이룰 때마다 0의 개수 하나 증가.
        - 예를 들어, 2400 = 24 * 10 * 10. / 2는 5번, 5는 2번 사용돼 0의 개수 2.
        - 이를 통해 2와 5의 사용 횟수 최소값이지만, 2는 항상 5보다 많이 존재하므로 5가 사용된 횟수만 찾으면 됨.
    - N!은 1 ~ N까지 모두 곱한 값.
        - ex) 10! = 1 * ... * 5 * ... * 10 인데, 5는 오직 5의 배수에만 존재하므로 5, 10에서 두 번 사용되었고, 이는 10 / 5 = 2와 동일.
        - 한편 25의 경우, 한번에 5가 두 번 사용됨. 이러한 경우, 5에서 5가 한 번, 25에서 5가 한번 사용되었다고 볼 수 있다.
            - 그러므로 `tmp = 5`부터 시작해 계속해서 5를 곱해 제수를 키운 다음, 만약 `tmp`가 `n`보다 작은 범위라면 나눈 몫을 계속 더해주면 된다.
            - ex) 26!의 0의 개수 = (26 / 5) + (26 / 25) = 6. 
