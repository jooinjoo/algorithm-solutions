# 치킨 배달

> https://www.acmicpc.net/problem/15686

## 문제 설명

- 문제

크기가 N×N인 도시가 있다. 도시는 1×1크기의 칸으로 나누어져 있다. 도시의 각 칸은 빈 칸, 치킨집, 집 중 하나이다. 도시의 칸은 (r, c)와 같은 형태로 나타내고, r행 c열 또는 위에서부터 r번째 칸,
왼쪽에서부터 c번째 칸을 의미한다. r과 c는 1부터 시작한다.

이 도시에 사는 사람들은 치킨을 매우 좋아한다. 따라서, 사람들은 "치킨 거리"라는 말을 주로 사용한다. 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다. 즉, 치킨 거리는 집을 기준으로 정해지며, 각각의 집은
치킨 거리를 가지고 있다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.

임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|로 구한다.

예를 들어, 아래와 같은 지도를 갖는 도시를 살펴보자.

0은 빈 칸, 1은 집, 2는 치킨집이다.

(2, 1)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |2-1| + |1-2| = 2, (5, 5)에 있는 치킨집과의 거리는 |2-5| + |1-5| = 7이다. 따라서, (2, 1)에 있는 집의 치킨
거리는 2이다.

(5, 4)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |5-1| + |4-2| = 6, (5, 5)에 있는 치킨집과의 거리는 |5-5| + |4-5| = 1이다. 따라서, (5, 4)에 있는 집의 치킨
거리는 1이다.

이 도시에 있는 치킨집은 모두 같은 프랜차이즈이다. 프렌차이즈 본사에서는 수익을 증가시키기 위해 일부 치킨집을 폐업시키려고 한다. 오랜 연구 끝에 이 도시에서 가장 수익을 많이 낼 수 있는 치킨집의 개수는 최대
M개라는 사실을 알아내었다.

도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다. 어떻게 고르면, 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성하시오.

## 접근 방법

최대 13개의 치킨집 중 M개의 치킨집을 남겨, 각각의 모든 집이 가지는 치킨 거리의 합이 최소가 되는 값을 구하는 문제.  
N과 M이 각각 최대 50과 13으로 크지 않아, 최댓값 기준으로 모든 치킨집 조합에 따른 도시의 치킨 거리를 구해도 시간 복잡도를 충분히 통과한다.  
직접 계산해보면 13C6 * 2N = 1716 * 100 = 171600 가지 경우의 수.

## 문제 해결 과정

- 최종 해결 방법:
    - 도시의 지도를 입력할 때 `map[i][j]`의 값에 따라 각각 집 리스트 `ArrayList<Pos> hList`와 치킨집 리스트 `ArrayList<Pos> cList`에 입력.
    - 그리고 치킨집 리스트에서 M개의 치킨집 조합을 뽑아 해당 인덱스를 `int[] res`에 삽입.
        - 이 과정에서 `comb()` 메서드를 재귀하며 `res`에 M개의 치킨집 조합을 넣었을 때, `calc()`를 통해 모든 집과의 해당 치킨 거리를 계산.
        - `tmp`는 해당 조합에 대한 치킨집 거리의 최솟값을 의미하고, `tmp = Integer.MAX_VALUE` 초기화.
            - 각각의 치킨집 조합에 따른 `tmp`를 모두 더해 현재 조합의 도시의 치킨 거리를 `ret`에 저장.
            - 직전까지의 도시의 치킨 거리 `dist`와 `ret`를 비교하며 최솟값을 갱신한다.
