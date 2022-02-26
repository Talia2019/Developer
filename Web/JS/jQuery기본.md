# jQuery 기본

<br>

## 목차

1. jQuery
2. jQuery 특징
3. jQuery 설치
4. jQuery 기본구문

<br>

---

<br>

## 1. jQuery

John Resig이 2006년 발표한 크로스 플랫폼을 지원하는 경량 js library

복잡하고 방대한 UI 코드는 관리를 어렵게 함 &rarr; 웹페이지의 구조(HTML)와 행위(JS)그리고 표현(CSS)을 분리하면 어느정도 해결 가능

단, 최근 뷰나 리엑트같은 SPA가 각광받으며 잘 안쓰이기도 함

HTML문서의 탐색이나 조작, 이벤트핸들링, 애니메이션, Ajax등을 멀티브라우저를 지원하는 API를 통해 간편하게 사용

<br>

---

## 2. jQuery 특징

크로스 플랫폼을 지원하는 jQuery는 어떠한 브라우저에서도 동일하게 동작

브라우저 호환성을 고려하여 대체코드를 작성할 필요가 없음

네이티브 DOM API(DOM Query, Traversing, Manipulation등) 보다 직관적이고 편리한 API제공하여 개발속도를 향상

Event 처리, Ajax, Animation 효과를 쉽게 사용

다양한 effect함수를 제공하여 동적인 페이지를 쉽게 구현

<br>

---

## 3. jQuery 설치

다운로드 방식 : 네트워크 상태와 관계없이 개발 가능

CDN 방식 : 간편

```js
<script src="[https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js](https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js)"></script>
```

<br>

---

## 4. jQuery 기본구문

기본 구문은 Selector를 사용하여 DOM객체를 탐색하고, 반환된 래퍼세트를 통해 함수를 수행

Selector 표현식과 Action메소드를 조합한 형태로 구문을 작성

```js
$(selector).action();
```

$(selector) 는 탐색한 DOM 객체들을 담은 래퍼세트(WrapperSet)를 반환

래퍼세트 객체를 통해 기능을 처리하는 액션 함수 호출

```js
$(document).ready(function () {
  //TODO..
});

<!-- 또는 -->

$(function () {
  //TODO..
});
```

<br>

---
