# EL & JSTL

<br>

JSP에는 데이터가 있는지 여부확인(if문), 여러개인지(for문), 데이터만 있는게 이상적 (덧셈, 뺄셈같은 로직은 없도록)

그래서 출력만 하는게 EL. 변수 생성 if문이나 for문같은건 JSTL로 처리

<%= %>과 같은 출력문을 EL로, <% %>같은 처리문을 JSTL로 처리

<br>

<br>

## 1. EL (Expression Language)

EL은 표현을 위한 언어로 JSP스크립트의 표현식을 대신하여 속성 값을 쉽게 출력하도록 고안된 언어

즉 표현식(<%= %>)를 대체할 수 있음

EL표현식에서 도트 연산자 왼쪽은 반드시 java.util.Map객체 또는 Java Bean객체여야 함

EL표현식에서 도트연산자 오른쪽은 반드시 맵의 키이거나 Bean프로퍼티여야 함

<br>

- EL에서 제공하는 기능

JSP의 네가지 기본 객체가 제공하는 영역의 속성 사용

자바 클래스 메소드 호출 기능

표현 언어만의 기본 객체 제공

수치, 관계, 논리 연산 제공

<br>

- EL 문법 : 기본

```js
//Map을 사용하는 경우
${ Map . Map의 키 }

//Java Bean을 사용하는 경우
${ Java Bean . Bean 프로퍼티 }

```

프로퍼티라는것은 외부에서 접근 가능한 값

뭔가를 얻어올때는 get, 지정할때는 set

만약 name이라는 변수를 getName2()로 설정했다면 프로퍼티는 get과()를 지우고 소문자로 바꾼 name2가 됨 (get이나 set을 뺀 나머지를 프로퍼티라함)

프로퍼티는 항상 get과 set함수를 지녀야함

<br>

- EL 문법 : [ ] 연산자

Dot표기법 외에 [ ] 연산자를 사용하여 객체의 값에 접근할 수 있음

[ ] 연산자 안의 값이 문자열인 경우, 맵의 키가 될 수도 있고 Bean 프로퍼티나 리스트 및 배열의 인덱스가 될 수 있음

배열과 리스트인 경우, 문자로 된 인덱스 값은 숫자로 변경하여 처리

```js
${ userinfo["name"] }

${ userinfo.name }

${ users[0] }

${ users["1"] } // users[1] 과 결과 같음
```

일반적으로 속성이름에 하이픈(-)등이 있으면 [ ] 연산자 사용

<br>

- EL 내장객체

| category        | identifier       | type      | description                                                         |
| --------------- | ---------------- | --------- | ------------------------------------------------------------------- |
| JSP             | pageContext      | Java Bean | 현재 페이지의 프로세싱과 상용하는 PageContext instance              |
| 범위(scope)     | pageScope        | Map       | page scope에 저장된 객체 추출                                       |
|                 | requestScope     | Map       | request scope에 저장된 객체 추출                                    |
|                 | sessionScope     | Map       | session scope에 저장된 객체 추출                                    |
|                 | applicationScope | Map       | application scope에 저장된 객체 추출                                |
| 요청 매개변수   | param            | Map       | ServletRequest.getParameter(String)을 통해 요청 정보 추출           |
|                 | paramValues      | Map       | ServletRequest.getParameterValues(String)을 통해 요청 정보 추출     |
| 요청 헤더       | header           | Map       | HttpServletRequest.getHeader(String)을 통해 헤더 정보 추출          |
|                 | headerValues     | Map       | HttpServletRequest.getHeaders(String)을 통해 헤더 정보 추출         |
| 쿠키            | cookie           | Map       | HttpServletRequest.getCookies()을 통해 쿠키 정보 추출               |
| 초기화 매개변수 | initParam        | Map       | ServletContext.getInitParameter(String)을 통해 초기화 파라미터 추출 |

하지만 request.getParameter("username") = param.username

<br>

- EL 사용

pageContext를 제외한 모든 EL내장 객체는 Map (key와 value 쌍)

```js
${ expr }
```

<br>

- EL에서 객체 접근

```jsx
request.setAttribute("userinfo", "정지영");
1. ${requestScopte.userinfo}
2. ${pageContest.request.userinfo}, ${userinfo}
//property이름만 사용할경우 자동으로 pageScope>requestScope>sessionScope>applicationScope순으로 객체 찾음
//아무데도 없을경우 공백 반환

url?name=정지영&fruit=사과&fruit=바나나
1. ${param.name}
2. ${paramValues.fruit[0]}.${paramValues.fruit[1]}
```

<br>

```jsx
${cookie.id.value}
1. Cookie가 null이라면 null return
2. null이 아니라면 id를 검사 후 null이라면 null return
3. null이 아니라면 value값 검사
```

<br>

- EL Operator(연산자)

대부분 java와 동일

| 종류        | 설명                                         |
| ----------- | -------------------------------------------- |
| 산술        | +, -, \*, /, %                               |
| 관계형      | ==(eq), !=(ne), <(lt), >(gt), <=(le), >=(ge) |
| 3항 연산    | 조건 ? 값1 : 값2                             |
| 논리        | &&(and),                                     |
|             | (or), !(not)                                 |
| 타당성 검사 | empty                                        |

empty연산자에서 true를 return 하는 경우 >> ${emtpy var}

1. 값(var)이 null이면 true
2. 값이 빈문자열("")이면 true
3. 길이가 0인 배열([])이면 true
4. 빈 Map 객체는 true
5. 빈 Collection객체이면 true

<br>

- EL 에서 객체 method 호출

```js
<%
List<MemberDto> list = dao.getMembers();
reqeust.setAttribute("users", list);
%>

회원수 : ${ requestScope.users.size() },  ${ users.size() }
그냥 일반 메소드 쓰는것처럼
```

<br>

<br>

# 2. JSTL (Jsp Standard Tag Library)

jsp 는 html 에 가까울수록(tag로만 이뤄질수록) 좋다

→ <% %> 같은거 쓰지말고 태그로 바꿔보자 : JSTL

<br>

- JSTL

자바 서버 페이지 표준 태그 라이브러리(JSTL)은 JavaEE기반의 웹 애플리케이션 개발 플랫폼을 위한 컴포넌트 모음

JSTL은 JSP페이지 내에서 자바 코드를 바로 사용하지 않고 로직을 내장하는 효율적인 방법을 제공

표준화된 태그 셋을 사용하여 자바 코드가 들락거리는 것보다 더 코드의 유지보수와 응용 소프트웨어 코드와 사용자 인터페이스 간의 관심사를 분리

<br>

- 특징

custom tag : 개발자가 직접 태그를 작성할 수 있는 기능을 제공

custom tag중에서 많이 사용되는 것들을 모아서 JSTL이라는 규약을 만듦

논리적인 판단, 반복문의 처리, 데이터베이스 등의 처리 가능

JSP 2.1~JSP2.2와 호환되는 JSTL버전은 1.2

JSTL은 JSP페이지에서 스크립트릿을 사용하지 않고 액션을 통해 간단히 처리할 수 있는 방법을 제공

JSTL에는 다양한 액션이 있으며, EL과 함께 사용하여 코드를 간결하게 작성할 수 있음

jstl-1.2.jar download해야 사용 가능

<br>

- JSTL Tag

```js
// directive 선언 형식
<%@ taglib prefix="prefix" uri="uri" %>
```

| library  | prefix | function                             | URI                                    |
| -------- | ------ | ------------------------------------ | -------------------------------------- |
| core     | c      | 변수지원, 흐름제어, URL처리          | http://java.sun.com/jsp/jstl/core      |
| XML      | x      | XML 코어, 흐름제어, XML 변환         | http://java.sun.com/jsp/jstl/xml       |
| 국제화   | fmt    | 지역, 메시지 형식, 숫자 및 날짜 형식 | http://java.sun.com/jsp/jstl/fmt       |
| database | sql    | SQL                                  | http://java.sun.com/jsp/jstl/sql       |
| 함수     |        | Collection, String 처리              | http://java.sun.com/jsp/jstl/functions |

<br>

- JSTL - core tag

```js
<%@ taglib prefix="c" uri="http://java.sun.cim/jsp/jstl/core" %>
```

| function  | tag                     | description                                                |
| --------- | ----------------------- | ---------------------------------------------------------- |
| 변수지원  | set                     | jsp page에서 사용할 변수 설정                              |
|           | remove                  | 설정한 변수 제거                                           |
| 흐름제어  | if                      | 조건에 따른 코드 실행                                      |
|           | choose, when, otherwise | 다중 조건 처리                                             |
|           | forEach                 | array나 collection의 각 항목 처리할 때 사용                |
|           | forTokens               | 구분자로 분리된 각 토큰을 처리할 때 사용 (StringTokenizer) |
| URL 처리  | import                  | URL을 사용하여 다른 자원의 결과를 삽입                     |
|           | redirect                | 지정한 경로로 redirect                                     |
|           | url                     | URL 작성                                                   |
| 기타 태그 | catch                   | Exception 처리에 사용                                      |
|           | out                     | JspWriter에 내용을 처리한 후 출력                          |

prefix : 접두어

단, if else가 없으므로 if if 혹은 choose, when otherwise로 해결

자바 코드를 넣을땐 무조건 ${}안에 넣는다 생각해도 됨
