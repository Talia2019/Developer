# Ajax

<br>

## 목차

1. [Ajax](#1.-ajax)
2. [JavaScript Ajax](#2.-javascript-ajax)
3. [jQuery Ajax](#3.-jquery-ajax)
4. [데이터 전송 형식](#4.-데이터-전송-형식)

<br>

<br>

## 1. Ajax

<br>

### Ajax란

Ajax(Asynchronous Javascript And XML)는 언어나 프레임이 아닌 `구현방식`을 의미

Ajax는 비동기방식의 Javascript와 XML

> 동기방식 : 서버에 신호를 보냈을 때 응답이 돌아와야 다음동작을 수행  
> 비동기방식 : 서버에 신호를 보냈을 때 응답 상태와 관계없이 다음 동작을 수행

Ajax를 사용하는 이유는 화면전환 없이 클라이언트와 서버간에 데이터 정보를 교환하기 위해서 (화면전환 없이 요청한 데이터를 전송받을 수 있음)

화면갱신이 없으므로 사용자 입장에서는 편리하나, 동적으로 DOM을 구성해야 하므로 구현이 복잡

##### 일반 요청에 대한 응답

> data입력 후 event 발생(주로 submit)  
> 서버에서 data를 이용해 logic 처리  
> logic 처리에 대한 결과에 따라 응답 page(새로운 html 페이지)를 생성하고 클라이언트에 전송 (화면전환 발생)

##### Ajax 요청에 대한 응답

> data입력 후 event 발생  
> 서버에서 요청을 처리한 후 text, XML, JSON등의 데이터로 응답  
> 클라이언트는 응답 데이터로 화면전환 없이 현재 페이지에서 동적으로 화면 재구성

<br>

### 서버와 클라이언트의 상호작용

웹화면을 구성하는 방식은 서버 중심의 상호작용 방식과 클라이언트 중심의 상호작용 방식으로 구분

- 서버중심의 개발방식  
  화면구성이 서버에서 이뤄짐(프리젠테이션 영역의 JSP나 PHP, ASP등)

- 클라이언트 중심의 개발방식  
  주로 클라이언트(웹 브라우저)에서 화면을 구성 (주로 JavaScript)

Ajax는 클라이언트 중심의 개발 방식이며 비동기 요청보다는 동적 화면구성이 관건

비동기니까 서버와 통신하는 중에도 밑의 코드 실행 가능

<br>

<br>

## 2. JavaScript Ajax

<br>

XMLHttpRequest 객체를 통해 자바스크립트가 Ajax방식으로 통신

XMLHttpRequest가 Ajax 통신시 전송방식, 경로, 서버로 송신할 data등의 전송정보를 담음

실제 서버와의 통신은 브라우저의 Ajax 엔진에서 수행

복잡

<br>

### HttpRequest 속성값

<br>

- readyState

| 값  | 의미          | 설명                                    |
| --- | ------------- | --------------------------------------- |
| 0   | Uninitialized | 객체만 생성(open 메소드 호출 전)        |
| 1   | Loading       | open 메소드 호출                        |
| 2   | Loaded        | send 메소드 호출, status의 헤더 도착 전 |
| 3   | Interactive   | 데이터 일부 받음                        |
| 4   | Completed     | 데이터 전부 받음                        |

open : 연결

send : 연결 후 데이터를 보낸 것

<br>

- status

| 값  | 텍스트                | 설명           |
| --- | --------------------- | -------------- |
| 200 | OK                    | 요청 성공      |
| 403 | Forbidden             | 접근 거부      |
| 404 | Not Found             | 페이지 없음    |
| 500 | Internal Server Error | 서버 오류 발생 |

```js
function getXMLHttpRequest() {
  if (window.ActiveXObject) {
    try {
      return new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e1) {
      try {
        return new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        return null;
      }
    }
  } else if (window.XMLHttpRequest) {
    return new XMLHttpRequest();
  } else {
    return null;
  }
}

var httpRequest = null;

function sendRequest(url, params, callback, method) {
  httpRequest = getXMLHttpRequest();

  var httpMethod = method ? method : "GET";
  if (httpMethod != "GET" && httpMethod != "POST") {
    httpMethod = "GET";
  }
  var httpParams = params == null || params == "" ? null : params;
  var httpUrl = url;
  if (httpMethod == "GET" && httpParams != null) {
    httpUrl = httpUrl + "?" + httpParams;
  }

  httpRequest.open(httpMethod, httpUrl, true);
  httpRequest.setRequestHeader(
    "Content-Type",
    "application/x-www-form-urlencoded"
  );
  httpRequest.onreadystatechange = callback;
  httpRequest.send(httpMethod == "POST" ? httpParams : null);
}
//위 함수들을 선언해놓고 사용

function getTime() {
  sendRequest("test.jsp", null, viewTime, "GET");
}

function viewTime() {
  if (httpRequest.readyState == 4) {
    if (httpRequest.status == 200) {
      var time = httpRequest.responseText;
      var div = document.getElementById("curtime");
      div.setAttribute("class", "viewtime");
      div.innerHTML = time;
    }
  }
}
```

<br>

<br>

## 3. jQuery Ajax

<br>

### Ajax 관련 메소드

| 종류                         | 설명                                                                                                |
| ---------------------------- | --------------------------------------------------------------------------------------------------- |
| load()                       | 외부 콘텐츠를 가져올 때 사용                                                                        |
| $.ajax()                     | 데이터 전송, 요청하는 통합적인 메서드 <br> 밑의 post, get, getJSON메서드의 기능을 하나로 합쳐놓은것 |
| $.post()                     | 데이터를 서버에 HTTP POST 방식으로 전송한 후 서버측의 응답을 받음                                   |
| $.get()                      | 데이터를 서버에 HTTP GET 방식으로 전송한 후 서버측의 응답을 받음                                    |
| $.getJSON()                  | 데이터를 서버에 HTTP GET 방식으로 전송한 후 서버측의 응답을 JSON형식으로 받음                       |
| $.getScript()                | Ajax를 이용해 외부 자바스크립트 불러옴                                                              |
| $.ajaxStop(function(){})     | 비동기 방식으로 서버에 응답 요청이 완료되었을때 함수 실행됨                                         |
| $.ajaxSuccess(function(){})  | Ajax 요청이 성공적으로 완료되면 함수 실행됨                                                         |
| $.ajaxComplete(function(){}) | Ajax 통신이 완료되면 함수 실행됨                                                                    |

<br>

- $(selector).load()

서버로부터 내용을 조회하여 선택자를 통해 탐색한 DOM객체에 동적으로 삽입

첫번째 인자는 필수값으로 HTML을 조회할 서버 URL을 지정

두번째 인자는 요청시 서버에 전달할 데이터를 지정

세번째 인자는 서버와 통신을 완료한 후에 수행할 콜백 함수를 지정

```js
$(selector).load(url);
$(selector).load(url, data, function (result, textStatus, jqXHR) {});
```

<br>

- $.ajax()

jQuery에서 Ajax기능을 제공하는 가장 기본적인 함수

다른 함수들에 비해 옵션을 다양하게 지정할 수 있으며 실무에서 가장 많이 사용

함수의 옵션은 다양하지만 대부분 자동적으로 지정하므로 생략 가능

<br>

- \$.get(), \$.post()

\$.ajax() 의 옵션 속성 중 type옵션이 미리 지정된 함수

지정된 HTTP Method로 Ajax통신을 하며 get()은 GET방식을, post()는 POST방식을 사용

```js
$.xxx(url, function (result, textStatus, jqXHR) {});
$.xxx(url, data, function (result, textStatus, jqXHR) {});
```

<br>

<br>

## 4. 데이터 전송 형식

<br>

Server와 Client는 주고 받을 data의 형식을 맞춰야 함

대표적인 data의 형식은 CSV, XML, JSON등이 있음

- CSV (Comma Separated Values)

각 항목을 쉼표로 구분해 데이터를 표현하는 방법

다른 두 형식에 비해 굉장히 짧아 많은양의 데이터 전송시 유리

단 각각의 데이터가 어떤 내용인지 파악하기 어렵다(서버와 클라이언트가 완벽히 데이터 구조를 공유할 경우 가능)

- XML(eXtensible Markup Language)

xml은 tag로 data를 표현함

tag를 보면 각 data가 무엇을 의미하는지 파악 가능

tag에 사용자 정의 속성을 넣을 수 있으므로 복잡한 data전달 가능

- JSON (JavaScript Object Notation)

CSV와 XML의 단점을 극복한 형식

Javascript에서 사용하는 객체의 형식으로 data를 표현

Ajax사용시 거의 표준으로 사용되는 data표현 방식

<br>
