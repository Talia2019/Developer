# 📚 TDD (Test Driven Development)

<br>

<br>

## TDD (Test Driven Development, 테스트 주도 개발)

Agile 방법론중 하나

**테스트가 개발을 이끌어 나간다.**

<br>
일반적으로 개발을 할 때, 설계(디자인)를 한 이후 코드 개발과 테스트 과정을 거침

![img](https://mblogthumb-phinf.pstatic.net/MjAxNzA2MjhfMTU0/MDAxNDk4NjA2NTAyNjU2.zKGh5ZuYgToTz6p1lWgMC_Xb30i7uU86Yh00N2XrpMwg.8b3X9cCS6_ijzWyXEiQFombsWM1J8FlU9LhQ2j0nanog.PNG.suresofttech/image.png?type=w800)

<br>
하지만 TDD는 기존 방법과는 다르게, 테스트케이스를 먼저 작성한 이후에 실제 코드를 개발하는 리팩토링 절차를 밟음

![img](https://mblogthumb-phinf.pstatic.net/MjAxNzA2MjhfMjE3/MDAxNDk4NjA2NTExNDgw.fp8XF9y__Kz75n86xknIPDthTHj9a8Q08ocIJIqMR6Ag.24jJa_8_T0Qj04P62FZbchqt8oTNXGFSLUItzMP95s8g.PNG.suresofttech/image.png?type=w800)
<br>

```
1. 요구사항을 검증하는 테스트 케이스를 작성
2. 테스트 케이스를 통과하기 위한 최소한의 코드 작성
3. 작성한 코드를 표준에 맞게 리팩토링
```

> 소프트웨어 또한 반복적인 테스트와 수정을 통해 고품질의 소프트웨어를 탄생시킬 수 있음

<br>

#### 장점

- 작업과 동시에 테스트를 진행하면서 실시간으로 오류 파악이 가능 ( 시스템 결함 방지 )

- 짧은 개발 주기를 통해 고객의 요구사항 빠르게 수용 가능

  - 피드백이 가능하고 진행 상황 파악이 쉬움

- 자동화 도구를 이용한 TDD 테스트케이스를 단위 테스트로 사용이 가능 (자바는 JUnit, C와 C++은 CppUnit 등)

- 개발자가 기대하는 앱의 동작에 관한 문서를 테스트가 제공해줌

  - 또한 이 테스트 케이스는 코드와 함께 업데이트 되므로 문서 작성과 거리가 먼 개발자에게 매우 좋음

- 코드의 확장성 및 유지보수가 용이 (코드의 모듈화가 자연스럽게 이뤄짐)

#### 단점

- 기존 개발 프로세스에 테스트케이스 설계가 추가되므로 생산 비용 증가

- 테스트의 방향성, 프로젝트 성격에 따른 테스트 프레임워크 선택 등 추가로 고려할 부분의 증가

<br>

## 2. TDD가 적합한 상황

어떤 부분에 대한 코딩을 여러번 해봤고 결과가 어떻게 나올지 뻔하거나, TDD를 했을 때 얻는 것이 적다면 TDD를 하지 않아도 됨

<br>

- 처음해보는 프로그램 주제
- 나에 대한 불확실성이 높은 경우
- 고객의 요구조건이 바뀔 수 있는 프로젝트
- 외부적인 불확실성이 높은 경우
- 개발하는 중에 코드를 많이 바꿔야 된다고 생각하는 경우
- 내가 개발하고 나서 이 코드를 누가 유지보수할지 모르는 경우

> 즉, 불확실성이 높을 때 TDD를 하면 됨

<br>

## 3. TDD의 효과

> 피드백과 협력을 증진시킴

#### 피드백

- TDD를 하면 피드백이 증가
  - 테스트를 통과하는 것으로 잘되고 있는가를 자주 확인 가능

#### 협력

- 테스트가 저장되기에 남들에게 테스트코드를 공유하기 쉬워짐
  - 남이 짠 코드를 빠르고 쉽게 이해할 수 있음
  - 코드 수정에 용기가 생김(문제가 발생해도 자동화된 테스트가 알려줄것)
  - 테스트 코드에는 개발자의 개발과정(고민/의사결정)이 나와있기 때문

<br>

## 4. TDD 종류

자동화 테스트는 세가지 범주로 나뉨

1. A Unit test (단위 테스트)
2. A Widget(or Component) test (컴포넌트 테스트)
3. An Integration test (통합 테스트)

<br>

#### A Unit test [단위 테스트]

단일 함수 또는 클래스를 테스트 하는 것으로 코드의 논리 단위의 정확성을 확인하는 것을 목표로 함

예를 들어 다음과 같은 것들은 단위 테스트에 포함되지 않음

디스크에 쓰고 읽는 것, 화면에 렌더링 되는것, 테스트가 실행되는 프로세스 외부에서 사용자의 입력을 외부에서 받는 것

#### A Widget (or component) test [컴포넌트 테스트]

하나의 컴포넌트 또는 위젯을 테스트 하는 것으로 UI를 테스트 하는 것에 속함

해당 테스트는 위젯의 UI가 예상대로 보이고 상호작용을 확인하는 것을 목표로 함

#### An Integration test [통합 테스트]

완전한 응용 프로그램을 통합적으로 테스트 하는 것

테스트 중인 모든 위젯과 서비스가 예상대로 함께 동작하는지 확인하는 것을 목표로 함

통합 테스트를 사용하여 앱의 성능 또한 확인 가능하며 일반적으로 서비스되는 플랫폼에 따라 앱의 경우 모바일 기기에서 웹의 경우 브라우저에서 테스트가 수행됨

<br>

## 5. TDD 예제

#### 점수 계산 프로그램을 통한 TDD 예제

중간고사, 기말고사, 과제 점수를 통한 성적을 내는 간단한 프로그램

점수 총합 90점 이상은 A, 80점 이상은 B, 70점 이상은 C, 60점 이상은 D, 나머지는 F

<br>

TDD 테스트케이스를 먼저 작성

35 + 25 + 25 = 85점이므로 등급이 B가 나와야 함

따라서 assertEquals의 인자값을 "B"로 주고, 테스트 결과가 일치하는지 확인하는 과정을 진행

```java
public class GradeTest {

    @Test
    public void scoreResult() {

        Score score = new Score(35, 25, 25); // Score 클래스 생성
        SimpleScoreStrategy scores = new SimpleScoreStrategy();

        String resultGrade = scores.computeGrade(score); // 점수 계산

        assertEquals("B", resultGrade); // 확인
    }

}
```

현재는 **Score 클래스와 computeGrade() 메소드가 구현되지 않은 상태** (테스트 코드로만 존재)

테스트 코드에 맞춰서 코드 개발을 진행

우선 점수를 저장할 Score 클래스를 생성

```java
public class Score {

    private int middleScore = 0;
    private int finalScore = 0;
    private int homeworkScore = 0;

    public Score(int middleScore, int finalScore, int homeworkScore) {
        this.middleScore = middleScore;
        this.finalScore = finalScore;
        this.homeworkScore = homeworkScore;
    }

    public int getMiddleScore(){
        return middleScore;
    }

    public int getFinalScore(){
        return finalScore;
    }

    public int getHomeworkScore(){
        return homeworkScore;
    }

}
```

이제 점수 계산을 통해 성적을 뿌려줄 computeGrade() 메소드를 가진 클래스 생성

우선 인터페이스를 구현

```java
public interface ScoreStrategy {

    public String computeGrade(Score score);

}
```

인터페이스를 가져와 오버라이딩한 클래스를 구현

```java
public class SimpleScoreStrategy implements ScoreStrategy {

    public String computeGrade(Score score) {

        int totalScore = score.getMiddleScore() + score.getFinalScore() + score.getHomeworkScore(); // 점수 총합

        String gradeResult = null; // 학점 저장할 String 변수

        if(totalScore >= 90) {
            gradeResult = "A";
        } else if(totalScore >= 80) {
            gradeResult = "B";
        } else if(totalScore >= 70) {
            gradeResult = "C";
        } else if(totalScore >= 60) {
            gradeResult = "D";
        } else {
            gradeResult = "F";
        }

        return gradeResult;
    }

}
```

이제 테스트 코드로 돌아가서, 실제로 통과할 정보를 입력해본 뒤 결과를 확인

이때 예외 처리, 중복 제거, 추가 기능을 통한 리팩토링 작업을 통해 완성도 높은 프로젝트를 구현할 수 있도록 노력

**_굳이 필요하나요?_**

실제 실무 프로젝트에서는 다양한 출력 결과물이 필요하고, 원하는 테스트 결과가 나오는 지 확인하는 과정은 필수적인 부분

TDD를 활용하면, 처음 시작하는 단계에서 테스트케이스를 설계하기 위한 초기 비용이 증가

하지만 개발 과정에 있어서 '초기 비용'보다 '유지보수 비용'이 더 클 수 있다는 것을 명심할 것

또한 안전성이 필요한 소프트웨어 프로젝트에서는 개발 초기 단계부터 확실하게 다져놓고 가는 것이 중요

유지보수 비용이 더 크거나 비행기, 기차에 필요한 소프트웨어 등 안전성이 중요한 프로젝트의 경우 현재 실무에서도 TDD를 활용한 개발을 통해 이루어지고 있음
