# 📚 Clean Coding & Refactoring

<br>

<br>

## 1. 클린코드

클린코드란, 가독성이 높은 코드를 말함

가독성을 높인다는 것은 다른 사람이 코드를 봐도, 자유롭게 수정이 가능하고 버그를 찾고 변경된 내용이 어떻게 상호작용하는지 이해하는 시간을 최소화 시키는 것

가독성을 높이려면 다음과 같이 구현해야 함

- 네이밍이 잘 되어야 함
- 오류가 없어야 함
- 중복이 없어야 함
- 의존성을 최대한 줄여야 함
- 클래스 혹은 메소드가 한가지 일만 처리해야 함

얼마나 **코드가 잘 읽히는 지, 코드가 지저분하지 않고 정리된 코드인지**를 나타내는 것이 바로 '클린 코드'

```java
public int AAA(int a, int b){
    return a+b;
}
public int BBB(int a, int b){
    return a-b;
}
```

클린코드로 개선

```java
public int sum(int a, int b){
    return a+b;
}

public int sub(int a, int b){
    return a-b;
}
```

첫째는 **함수 네이밍**. 다른 사람들이 봐도 무슨 역할을 하는 함수인 지 알 수 있는 이름을 사용해야 함

둘째는 **함수와 함수 사이의 간격**. 여러 함수가 존재할 때 간격을 나누지 않으면 시작과 끝을 구분하는 것이 매우 힘듦

<br>

#### 클린코드 구현방법

<br>

##### 변수 이름 작성하기

1. 의미 있게 구분하라

구분짓지 못하는 변수명은 의미가 없음 (ProductInfo와 ProductData는 구분이안됨)

변수 타입을 뒤에 작성하는 것도 의미가 없으면 좋지 않음 (NameString과 Name의 차이가 없음)

2. 한 개념에 한 단어만 사용하라

Controller, Manager, driver와 같이 의미가 비슷한 단어들은 혼란을 유발

어휘를 일관성있게 작성할것

3. 의미있는 맥락을 추가하라

예를 들어 사용자의 주소를 나타내는 변수로 state라는 이름을 지었다면, state만 봐서는 이게 주소인지, 상태인지 의미를 유추하기 힘듦

이 때, state와 함께 선언되는 다른 변수들의 이름이 주소와 연관된 단어라면 state의 옳은 뜻을 쉽게 유추할 수 있음

<br>

##### 함수 작성하기

1. 작게 만들어라

어떤 것도 최대한 작고 간결하게 만들려고 할 것

2. 객체의 상태를 변경한 후 출력하는 함수를 만들지 마라.

객체의 변경은 객체 내부에서만 하는 것

다른 객체에게 변경이나 생성을 맡기려 하면 안 됨

3. 함수는 하나의 일만 해야 한다.

부수 효과를 일으키지말고 명령과 조회를 분리

이 모든게 결국 함수가 하나의 일만 한다면 해결 될 일

상태를 변경만 하면 변경만 하고 출력 할 것이라면 출력만! 두 가지 모두는 오해를 일으키기도, 테스트 하기도 어려움

4. 반복하지 마라

5. 파라미터는 없거나 하나, 적은게 낫다

클래스를 생성하여 파라미터를 최대한 줄이려고 노력할 것

<br>

#### 주석 작성하기

1. 주석은 작성 하지 않는 것이 가장 좋음

최대한 코드만 보고 이해할 수 있도록 코드를 구현할 것

2. Todo 주석을 작성하자

일을 마치고 오늘 어디 까지 했고, 내일 무엇을 하면 좋을지 기록

<br>

#### 객체 만들기

1. 자료를 추상화 하자

추상 인터페이스를 이용하여 클래스를 생성하면 관련이 적은 클래스들도 하나로 묶어 사용 가능

2. 객체의 구조를 숨겨라

단지 private하게 변수를 선언 한다고, 구조를 숨기는 것이 아님

객체는 내부에서 값을 얻고 변화하고 계산하고, 외부로 나오는 메소드는 그 결과값만 리턴하는 것

이외의 다른 메소드들은 피해야 함

<br>

#### 오류 처리

1. 가정문보단 try-catch 문을 사용하자

2. try-catch문도 가능하면 사용하지 않는 경우를 고민하자

```java
try{
  MealExpenses expenses = expenseReportDAO.getMeals(employee.getId());
  total += expenses.getTotal();
} catch(MealExpensesNotFound e){
  total += getMealPerDiem();
} // 직원의 id로 타입에 따라 결제방식이 다름

public class PerDiemMealExpenses implements MealExpenses{
  public int getTotal{ // 기본값으로 일일 기본 식비를 반환 }
}

//인터페이스를 이용하여, 조건에 따라 반환하는 값이 다른 MealExpenses를 생성하여 문제를 해결
```

3. Null을 반환하지 마라

빈 배열이나 0과 같은 값을 반환하면 null로 인한 NullPointerException의 발생을 막을 수 있음

<br>

#### 단위 테스트하기

작게, 그리고 가능한 모든 함수를 테스트 하기

#### 클래스 생성하기

1. 클래스 역시 작아야 한다

하나의 책임만 하는 클래스를 생성 해보려고 할 것 (단어 하나로 클래스의 모든 것을 표현 할 수 있을 크기가 좋음)

2. 단일 책임 원칙에 따른다

클래스는 변경할 이유가 단 하나여야 함

예를 들어 버전을 관리하고, 때론 관리된 버전을 출력하는 클래스가 있다면, 이것이 수정 되야 한다면 책임이 두가지기 때문에 두가지 이유로 수정됨

3. 인스턴스 변수의 수가 작아야한다

<br>

<br>

## 2. 리팩토링

> legacy code (테스트가 불가능하거나 어려운 코드)를 클린 코드로 만들기
>
> 냄새나는 코드를 점진적으로 반복 수행되는 과정을 통해 코드를 조금씩 개선해나가는 것

프로그램의 외부 동작은 그대로 둔 채, 내부의 코드를 정리하면서 개선하는 것을 말함

이미 공사가 끝난 집이지만, 더 튼튼하고 멋진 집을 만들기 위해 내부 구조를 개선하는 리모델링 작업

프로젝트가 끝나면, 지저분한 코드를 볼 때 가독성이 떨어지는 부분이 존재

이 부분을 개선시키기 위해 필요한 것이 바로 '리팩토링 기법'

리팩토링 작업은 코드의 가독성을 높이고, 향후 이루어질 유지보수에 큰 도움이 됨

<br>

#### 코드 인스펙션(code inspection)

> 작성한 개발 소스 코드를 분석하여 개발 표준에 위배되었거나 잘못 작성된 부분을 수정하는 작업
> <br>

#### 절차 과정

1. Planning : 계획 수립
2. Overview : 교육과 역할 정의
3. Preparation : 인스펙션을 위한 인터뷰, 산출물, 도구 준비
4. Meeting : 검토 회의로 각자 역할을 맡아 임무 수행
5. Rework : 발견한 결함을 수정하고 재검토 필요한지 여부 결정
6. Follow-up : 보고된 결함 및 이슈가 수정되었는지 확인하고 시정조치 이행

<br>

#### 리팩토링이 필요한 코드는?

- 메소드 정리 : 그룹으로 묶을 수 있는 코드, 수식을 메소드로 변경함
- 객체 간의 기능 이동 : 메소드 기능에 따른 위치 변경, 클래스 기능을 명확히 구분
- 데이터 구성 : 캡슐화 기법을 적용해 데이터 접근 관리
- 조건문 단순화 : 조건 논리를 단순하고 명확하게 작성
- 메소드 호출 단순화 : 메소드 이름이나 목적이 맞지 않을 때 변경
- 클래스 및 메소드 일반화 : 동일 기능 메소드가 여러개 있으면 수퍼클래스로 이동
- 중복 코드
- 긴 메소드
- 거대한 클래스
- Switch 문
- 절차지향으로 구현한 코드

<br>

> 리팩토링의 목적은, 소프트웨어를 더 이해하기 쉽고 수정하기 쉽게 만드는 것

리팩토링은 성능을 최적화시키는 것이 아님

코드를 신속하게 개발할 수 있게 만들어주고, 코드 품질을 좋게 만들어줌

이해하기 쉽고, 수정하기 쉬우면? → 개발 속도가 증가!

<br>

#### 리팩토링이 필요한 상황

> 소프트웨어에 새로운 기능을 추가해야 할 때

명심해야할 것은, 우선 코드가 제대로 돌아가야 한다는 것

리팩토링은 우선적으로 해야 할 일이 아님을 명심

<br>

객체지향 특징을 살리려면, switch-case 문을 적게 사용해야 함

(switch문은 오버라이드로 다 바꿔버리기)

<br>

##### 리팩토링 예제

<br>

1번

```java
// 수정 전
public int getFoodPrice(int arg1, int arg2) {
    return arg1 * arg2;
}
```

함수명 직관적 수정, 변수명을 의미에 맞게 수정

```java
// 수정 후
public int getTotalFoodPrice(int price, int quantity) {
    return price * quantity;
}
```

<br>

2번

```java
// 수정 전
public int getTotalPrice(int price, int quantity, double discount) {
    return (int) ((price * quantity) * (price * quantity) * (discount /100));
}
```

`price * quantity`가 중복. 따로 변수로 추출

할인율을 계산하는 부분을 메소드로 따로 추출

할인율 함수 같은 경우는 항상 일정하므로 외부에서 건드리지 못하도록 private 선언

```java
// 수정 후
public int getTotalFoodPrice(int price, int quantity, double discount) {
	int totalPriceQuantity = price * quantity;
    return (int) (totalPriceQuantity - getDiscountPrice(discount, totalPriceQuantity))
}

private double getDiscountPrice(double discount, int totalPriceQuantity) {
    return totalPriceQuantity * (discount / 100);
}
```

<br>

이 코드를 한번 더 리팩토링 해보면?

<br>

3번

```java
// 수정 전
public int getTotalFoodPrice(int price, int quantity, double discount) {

    int totalPriceQuantity = price * quantity;
    return (int) (totalPriceQuantity - getDiscountPrice(discount, totalPriceQuantity))
}

private double getDiscountPrice(double discount, int totalPriceQuantity) {
    return totalPriceQuantity * (discount / 100);
}
```

<br>

totalPriceQuantity를 getter 메소드로 추출

지불한다는 의미를 주기 위해 메소드 명을 수정

<br>

```java
// 수정 후
public int getFoodPriceToPay(int price, int quantity, double discount) {

    int totalPriceQuantity = getTotalPriceQuantity(price, quantity);
    return (int) (totalPriceQuantity - getDiscountPrice(discount, totalPriceQuantity));
}

private double getDiscountPrice(double discount, int totalPriceQuantity) {
    return totalPriceQuantity * (discount / 100);
}

private int getTotalPriceQuantity(int price, int quantity) {
    return price * quantity;
}
```

<br>

<br>

## 3. 클린코드와 리팩토링의 차이?

리팩토링이 더 넓은 의미를 가짐

클린 코드는 단순히 가독성을 높이기 위한 작업으로 이루어져 있다면, 리팩토링은 클린 코드를 포함한 유지보수를 위한 코드 개선이 이뤄짐

클린코드와 같은 부분은 설계부터 잘 이루어져 있는 것이 중요하고, 리팩토링은 결과물이 나온 이후 수정이나 추가 작업이 진행될 때 개선해나가는 것이 올바른 방향

z
