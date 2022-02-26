# JavaScript 기본

<br>

## 목차

1. JavaScript 개요
2. JavaScript 기본문법

<br>

## 1. JavaScript 개요

<br>

### 1-1. JavaScript

JavaScript는 프로토타입 기반의 스크립트 프로그래밍 언어로 객체지향 개념을 지원

웹브라우저가 JS를 HTML과 함께 다운로드 하여 실행

웹브라우저가 HTML문서를 읽어들이는 시점에 JS Engine이 실핻됨

대부분의 JS Engine은 ECMA Script 표준을 지원함

<br>

### 1-2. ECMA Script

1996 년 3 월 Netscape 는 ‘Netscape Navigator 을 출시하면서 JavaScript 를 지원

Microsoft 사는 웹에 호환되는 Jscript 를 개발해서 1996 년 8 월 Internet Explore 3.0 에 포함하여 출시

Netscape 는 JavaScript 표준화를 위해 기술 규격을 ECMA 에 제출

1997 년 6 월 ECMA 는 ES1(ECMA-262 1st edition) 초안을 일반 회의에서 채택

현재 ES6(ECMA-262 6th edition)가 널리 사용되고 있음

<br>

### 1-3. JavaScript의 특징

JavaScript 는 HTML, CSS 와 함께 웹을 구성하는 요소 중 하나로 웹 브라우저에서 동작하는 유일한 프로그래밍언어

JavaScript 는 개발자가 별도의 컴파일 작업을 수행하지 않는 인터프리터 언어

각 브라우저별 JavaScript 엔진 (Chrome 의 V8 엔진등) 은 인터프리터와 컴파일러의 장점을 결합하여 비교적 처리 속도가 느린 인터프리터의 단점을 해결

명령형 (imperative), 함수형 (functional), 프로토타입 기반 (prototype based) 객체지향 프로그래밍을 지원하는 멀티 패러다임 프로그래밍 언어

과거의 JS는 클라이언트 컴퓨터에서만 작동하는 언어였으나, NodeJS를 통해 서버에서도 작동됨. 그에 따라 사이트 제작, 모바일 웹/앱, 스마트TV제작 등 다양한 UI개발에 사용되고 있음.

<br>

## 2. JavaScript 기본문법

<br>

### 2-1. JavaScript 선언문

```
<script src="js/hello.js" type="text/javascript">
  자바스크립트 코드;
</script>
```

HTML 에서 JavaScript 를 사용하려면 script 태그를 사용

script 태그는 src 와 type 속성을 사용하여 JavaScript 를 선언 (HTML5 부터는 type 속성 생략가능)

src 속성은 외부의 JavaScript 파일을 HTML 문서에 포함할 때 사용하며 , 생략할 수 있음

type 속성은 미디어 타입을 지정할 때 사용 . JavaScript 코드는 ‘text/ 로 지정

<br>

script 태그는 HTML 파일 내부의 head나 body 어느곳에서나 선언 가능하나, body 의 끝부분에 둘 것을 권장함

- head 안에 위치한 JavaScript 는 브라우저의 각종 입 출력 발생 이전에 초기화되므로 브라우저가 먼저 점검
- body 안에 위치하면 브라우저가 HTML 부터 해석하여 화면에 그리기 때문에 사용자가 빠르다고 느낄 수 있음

<br>

### 2-2. 주석

주석은 JavaScript 코드에 대한 부연 설명이므로 실행 코드에 포함되지 않음

JavaScript 주석은 한 줄 주석 (Line) 과 블록 주석 (Block) 이 있음

한 줄 주석은 //code 로 표기하고 , 블록 주석은 /\* code \*/ 로 표기

가능하면 블록 주석보다 라인 주석을 사용

<br>

### 2-3. 변수

타입 명시 없이 var, let, const 키워드를 사용하여 선언 (let, const : ES6)

let, const 특징

- 변수 중복 선언 불가

  let은 재할당 가능하나 const는 선언과 초기화를 반드시 동시에 해야하며 재할당이 불가능

- 블록 레벨 스코프

- 변수 호이스팅

  let은 선언과 초기화단계가 분리되어 진행 (초기화 전에 변수에 접근하면 참조 에러)

  const는 선언과 초기화단계가 동시에 진행 (변수 선언&초기화 전에 접근시 에러)

| 키워드 | 구분 | 선언위치    | 재선언 |
| ------ | ---- | ----------- | ------ |
| var    | 변수 | 전역 스코프 | 가능   |
| let    | 변수 | 전역 스코프 | 불가능 |
| const  | 상수 | 전역 스코프 | 불가능 |

JavaScript 는 동적타입 (Dynamic / Weak Type) 언어. 변수의 타입 지정없이 값이 할당되는 과정에서 자동으로 변수의 타입이 결정됨 >> 같은 변수에 여러 타입의 값을 할당 가능

<br>

#### 데이터의 종류

- 원시 타입(primitive type)

  - 문자형(string)
  - 숫자형(number)
  - 논리형(boolean)
  - null

    변수에 저장된 값이 null인 경우

  - undefined

    변수에 값이 등록되기 전의 기본값

- 객체 타입(object type)

  원시타입을 제외한 모든 값

- typeof

  저장된 자료형을 반환

  null의 경우 object반환

<br>

#### 변수 선언시 주의 사항

변수 이름은 함수 이름과 혼동되지 않도록 유일한 이름을 사용 (변수[형용사, 명사], 함수[동사] 사용)

JavaScript 는 ECMAScript 표준에 따라 낙타 표기법 (Camel case) 를 사용

낙타 표기법이란 기본적으로 소문자로 작성하되 2 개 이상의 단어일 경우 단어의 첫 글자는 대문자로 표기

키워드 , 공백 문자 포함 , 숫자로 시작 X.

특수 문자는 \_와 $ 허용

<br>

### 2-4. 연산자

- 산술 연산자

  +, -, \*, /, %

- 문자 결합 연산자

  \+

  더하기에 피연산자로 문자형 데이터가 한개라도 포함되어 있으면 다른 피연산자의 데이터는 자동으로 문자형 데이터로 형변환 되고 문자 결합이이뤄져 하나의 문자형 데이터를 반환

- 대입 연산자

  =, +=, -=, \*=, /=, %=

- 증감 연산자

  --, ++

- 비교 연산자

  \>, \<, >=, <=, ==, !=, ===, !==

  === 는 자료형까지 같아야 true 반환

- 논리 연산자

  ||, &&, !

- 삼항 조건 연산자

  조건식 ? js코드 1: js코드 2;

<br>

### 2-5. 제어문

<br>

#### 2-5-1. 조건문

- if

- switch

<br>

#### 2-5-2. 반복문

- while

- dowhile

- for

<br>

### 2-6. 객체

객체는 이름과 값으로 구성된 프로퍼티의 집합

문자열 , 숫자 , boolean, null, undefined 를 제외한 모든 값은 객체

JavaScript 의 객체는 키 (key) 와 값 (value) 으로 구성된 프로퍼티 들의 집합

전역 객체를 제외한 JavaScript 객체는 프로퍼티를 동적으로 추가하거나 삭제 가능

JavaScript 의 함수는 일급 객체이므로 값으로 사용할 수 있으므로 프로퍼티의 값으로 함수를 사용 가능

JavaScript 객체는 프로토타입이라는 특별한 프로퍼티를 포함

<br>

#### 2-6-1. 생성

1. 객체 리터럴

   가장 일반적인 방법

   { } 를 사용하여 객체를 생성. { } 내에 1개 이상의 프로퍼티를 추가하여 객체를 생성

2. Object 생성자 함수

   new 연산자와 Object생성자 함수를 호출하여 빈 객체를 생성

   빈 객체 생성 후 프로퍼티 또는 메소드를 추가하여 객체를 완성

3. 생성자 함수

   동일한 프로퍼티를 갖는 객체 생성 시 위 두 방법은 동일한 코드를 반복적으로 작성

   생성자 함수를 이용하면 템플릿 클래스 처럼 사용하여 프로퍼티가 동일한 객체 여러 개를 간단히 생성 가능

   ```jsx
   //생성자 함수
   function Student(name, area) {
     this.name = name;
     this.area = area;
     this.info = function () {
       console.log(this.name + "은" + this.area);
     };
   }

   //객체생성
   var student = new Student("1", "2");
   ```

#### 2-6-2. 속성

<br>

- 속성 값 조회

객체는 dot(.) 을 사용하거나 대괄호 를 사용해서 속성 값에 접근 . 대괄호 내에 들어가는 프로퍼티 이름은 반드시 문자열이어야 함

객체에 없는 속성에 접근하면 undefined 를 반환

객체 속성 값을 조회 할 때 || 연산자를 사용하는 방법도 가능

```js
student.age;
student["age"];
```

- 속성 값 변경, 추가, 제거

속성 값을 변경할 때는 dot(.) 이나 대괄호 를 사용

```js
member[‘age’] = 25;
member.age = 25;
```

객체에 값을 할당하는 속성이 없을 경우 , 그 속성은 추가됨

```js
member.hobby = "코딩";
```

delete 연산자를 이용하여 속성 제거

```js
delete member.city;
```

<br>

#### 2-6-3. 참조

객체는 복사되지 않고 참조됨

JavaScript 에서 원시 (Primitive) 데이터 타입이 아닌 모든 값은 참조 타입

참조 타입은 Object, Array, Date, Error 를 포함

타입 확인 방법으로는 typeof 연산자가 있음

null 은 원시 타입이지만 typeof 연산자에서 object 를 반환

<br>

#### 2-6-4. 분류

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/b5805104-f3c4-499b-8cf0-9e974b2303b0/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211213%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211213T074416Z&X-Amz-Expires=86400&X-Amz-Signature=35714de47c84cdbc6e9fa6ea305da1ebaaae36fe7e6db1918958b00c331c9d15&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

<br>

### 2-7. 함수

- 선언, 호출

JavaScript 에서 함수는 일급 (first class) 객체

함수를 변수 , 객체 , 배열 등에 저장할 수 있고 다른 함수에 전달하는 전달 인자 콜백함수 또는 리턴 값으로 사용 가능

함수는 프로그램 실행 중에 동적으로 생성 가능

함수 정의 방법은 함수 선언문 , 함수 표현식 , Function 생성자 (constructor) 함수 세가지 방식 제공

(일급객체 : 다른 객체들에 일반적으로 적용 가능한 연산을 모두 지원하는 객체를 가리킨다. 보통 함수에 매개변수로 넘기기, 수정하기, 변수에 대입하기와 같은 연산을 지원할 때 일급 객체라고 한다)

```jsx
//함수선언문
function funcName(par1, par2, .., parn){}

//함수표현식
var funcName = function(par1, par2, .., parn){}

//Function 생성자 함수
var funcName = new Function("par1", "par2", .., "parn", "함수내용");

//함수 호출
funcName(par1, par2, .., parn);
```

<br>

- 함수 호이스팅

함수 선언문 의 경우 함수 선언의 위치와 상관없이 코드 내 어느 곳에서든지 호출이 가능

JavaScript 는 모든 선언 (var, function) 을 호이스팅함

함수 선언문으로 정의된 함수는 JavaScript 엔진이 스크립트가 로딩되는 시점에 이를 변수객체 저장한다 . 함수 선언 , 초기화 , 할당이 한번에 이루어진다

함수 표현식 의 경우 함수 호이스팅이 아니라 변수 호이스팅이 발생

💡 함수 선언문으로 함수를 정의하면 사용하기는 쉽지만 대규모 application을 개발하는 경우 인터프리터가 너무 많은 코드를 변수 객체에 저장하므로 application의 응답속도를 저하시킬 수 있음

<br>

- 매개변수

함수의 정의 부분에 외부로부터 전달받을 변수를 매개변수 (parameter) 라 함

함수를 호출할 때 전달하는 값을 전달인자 (argument) 라고 함

JavaScript 에서 함수 정의 시 매개변수에 대한 타입은 명시하지 않는다

함수를 호출할 때 정의된 매개변수와 전달인자의 개수가 일치하지 않더라도 호출 가능

<br>

- 콜백 함수

콜백 함수 (Callback function)는 함수를 명시적으로 호출하는 방식이 아니라 특정 이벤트가 발생했을 때 시스템에 의해 호출되는 함수를 말함

일반적으로 콜백 함수는 매개변수를 통해 전달되고 전달받은 함수의 내부에서 어느 특정시점에 실행됨

콜백 함수는 주로 비동기식 처리 모델에서 사용됨 . >> 처리가 종료되면 호출될 함수 콜백함수 를 미리 매개변수에전달하고 처리가 종료되면 콜백함수를 호출

```jsx
//이벤트 핸들러에서의 처리 예
btn.addEventListener("click", function () {
  console.log("hi");
});

//setTimeout()의 예
setTimeout(function () {
  console.log("3초후 실행");
}, 3000);
```

<br>
