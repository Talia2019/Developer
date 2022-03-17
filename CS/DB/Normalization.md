# 📚 정규화(Normalization)

<br>

<br>

## Normalization, 정규화

가장 큰 목표는 테이블 간 **중복된 데이터를 허용하지 않는 것**

중복된 데이터를 만들지 않으면, 무결성을 유지할 수 있고, DB 저장 용량 또한 효율적으로 관리할 수 있음

<br>

## 목적

- 데이터의 중복을 없애면서 불필요한 데이터를 최소화시킴
- 무결성을 지키고, 이상 현상을 방지
- 테이블 구성을 논리적이고 직관적으로 할 수 있음
- 데이터베이스 구조를 확장에 용이해짐

<br>

정규화에는 여러가지 단계가 있지만, 대체적으로 1~3단계 정규화까지의 과정을 거침

<br>

#### 제 1정규화(1NF)

> 테이블 컬럼이 원자값(Atomic Value, 하나의 값)을 갖도록 테이블을 분리시키는 것

만족해야 할 조건은 아래와 같음

- 어떤 릴레이션에 속한 모든 도메인이 원자값만으로 되어 있어야함
- 모든 속성에 반복되는 그룹이 나타나지 않음
- 기본키를 사용하여 관련 데이터의 각 집합을 고유하게 식별할 수 있어야 함

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNbQUm%2FbtqT18yag04%2FpTXJX3wB23ouk8az7EgWQ1%2Fimg.png">

현재 테이블은 여러개의 취미를 가지고 있어 원자값이 아님

따라서 1NF에 맞추기 위해서는 아래와 같이 분리할 수 있음

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbMlNZj%2FbtqT17FWVot%2FjUKTAUyOdrH83pRraKw3K0%2Fimg.png">

<br>

#### 제 2정규화(2NF)

> 제1 정규화를 진행한 테이블에 대해 완전 함수 종속을 만족하도록 테이블을 분해하는 것

완전 함수 종속: 기본키의 부분집합이 결정자가 되어서는 안됨

조금 쉽게 말하면, 테이블에서 기본키가 복합키(키1, 키2)로 묶여있을 때, 두 키 중 하나의 키만으로 다른 컬럼을 결정지을 수 있으면 안됨

> 기본키의 부분집합 키가 결정자가 되어선 안된다는 것

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FylbaZ%2FbtqT8Jc4K3s%2F0VFTPoKKFkbxZghKWDwKo1%2Fimg.png">

기본키는 `학생번호, 강좌이름`의 복합키

그러나 `강의실`이라는 컬럼은 기본키의 부분집합인 `강좌이름`에 의해 결정될 수 있음

즉 기본키 `학생번호, 강좌이름`의 부분키인 `강좌이름`이 결정자이기 때문에 완전 함수적 종속을 충족시키지 못하고 있는 테이블

부분 함수 종속을 해결하기 위해 테이블을 아래와 같이 나눠서 2NF를 만족

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbluCnc%2FbtqT7VEOf04%2FMe8DfY7rtycgJPYlYQKEWK%2Fimg.png">

<br>

#### 제 3정규화(3NF)

> 2NF가 진행된 테이블에 대해 이행적 종속을 없애기 위해 테이블을 분리하는 것
>
> 이행적 종속 : A → B, B → C면 A → C가 성립됨
>
> 아래 두가지 조건을 만족시켜야 함

- 릴레이션이 2NF에 만족
- 기본키가 아닌 속성들은 기본키에 의존

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FenwN1N%2FbtqUeiMyErd%2FsP8NKCe70NKsZncGuhO9uK%2Fimg.png">

`학생 번호`는 `강좌 이름`을 결정하고, `강좌이름`은 `수강료`를 결정

즉 `학생번호, 강좌이름` 테이블과 `강좌이름, 수강료`테이블로 분리해야 함

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fci1le3%2FbtqUeXnPnpD%2FyKkURqr8cZl21f5erx42QK%2Fimg.png">

<br>

#### BCNF 정규화

> 제3 정규화를 진행한 테이블에 대해 모든 결정자가 후보키가 되도록 테이블을 분해하는 것

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbBN6xu%2FbtqT6IlqRF4%2FMvBoxYMxtgS1JT7t1AymnK%2Fimg.png)

`특강수강`테이블에서 기본키는 `학생번호, 특강이름`

기본키 `학생번호, 특강이름`은 `교수`를 결정하고, `교수`는 `특강이름`을 결정

`교수`가 특강이름을 결정하는 결정자이지만, `후보키`가 아니라는 점

그렇기에 BCNF 정규화를 만족시키기 위해서 `특강신청`테이블과 `특강교수`테이블로 분해할 수 있음
