# 영화감독 숌

> https://www.acmicpc.net/problem/1436

## 문제 설명

- 문제

666은 종말을 나타내는 수라고 한다. 따라서, 많은 블록버스터 영화에서는 666이 들어간 제목을 많이 사용한다. 영화감독 숌은 세상의 종말 이라는 시리즈 영화의 감독이다. 조지 루카스는 스타워즈를 만들 때,
스타워즈 1, 스타워즈 2, 스타워즈 3, 스타워즈 4, 스타워즈 5, 스타워즈 6과 같이 이름을 지었고, 피터 잭슨은 반지의 제왕을 만들 때, 반지의 제왕 1, 반지의 제왕 2, 반지의 제왕 3과 같이 영화 제목을
지었다. 하지만 숌은 자신이 조지 루카스와 피터 잭슨을 뛰어넘는다는 것을 보여주기 위해서 영화 제목을 좀 다르게 만들기로 했다.

종말의 수란 어떤 수에 6이 적어도 3개 이상 연속으로 들어가는 수를 말한다. 제일 작은 종말의 수는 666이고, 그 다음으로 큰 수는 1666, 2666, 3666, .... 이다. 따라서, 숌은 첫 번째 영화의
제목은 "세상의 종말 666", 두 번째 영화의 제목은 "세상의 종말 1666"와 같이 이름을 지을 것이다. 일반화해서 생각하면, N번째 영화의 제목은 세상의 종말 (N번째로 작은 종말의 수) 와 같다.

숌이 만든 N번째 영화의 제목에 들어간 수를 출력하는 프로그램을 작성하시오. 숌은 이 시리즈를 항상 차례대로 만들고, 다른 영화는 만들지 않는다.

- 입력

첫째 줄에 N이 주어진다. N은 10,000보다 작거나 같은 자연수이다.

- 출력

첫째 줄에 N번째 영화의 제목에 들어간 수를 출력한다.

## 접근 방법

브루트 포스 방법을 통해, 특정 수가 "666"을 포함하고 있는지 검사.  
시간복잡도 최대 O(N), N은 1000만 이하.

## 문제 해결 과정

- 첫 번째 시도:
    - "666"을 가진 수를 100의 자리 수, 1000의 자리수 ... 마다 몇 개씩 가지고 있는지 규칙 파악.
        - 같은 자리 수 내에서 "666"을 배치하고 난 뒤, 남은 자리 경우의 수를 계산하려 했으나 겹치는 수가 많아 규칙 도출 실패.
- 최종 해결 방법:
    - "666"부터 시작해 수를 1씩 증가시키며, 해당 수가 "666"을 포함하고 있는지 판단.
    - N은 최대 10000 이하. 따라서 10000번째 "666"을 포함하는 수는 최대한 보수적으로 잡아도 6660000.
        - 예를 들어, 6660, 6661, ... 66600 까지 10개. 이후 66601, 66602, ... 66699까지 99개. 이런 식으로 증가.
    - 최대 반복 값이 1000만 이하로 작으므로, 브루트 포스 방법을 통해 `cur` 값이 `"666"`을 포함하는지 확인.
        - `cur`를 String 타입으로 변환해 `contains("666")` 메서드로 카운팅.
