# BOM(브라우저 객체 모델)

<br>

## 목차

1. window 객체
2. 브라우저 객체 모델
3. window 객체 사용
4. window 객체 프로퍼티
5. window 객체 함수

<br>

## 1. Window 객체

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/566fea5f-5a6e-4199-a2cb-a6b8bae00bb4/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211213%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211213T080923Z&X-Amz-Expires=86400&X-Amz-Signature=d1331f7b173d6f91a0182cc83a6359950c9400b6430770d8fe724fab6c1ab4d0&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

Window 객체는 자바스크립트의 최상위객체

Window 객체에는 브라우저와 관련된 여러 객체와 속성 , 함수가 있음

JavaScript 에서 기본으로 제공하는 프로퍼티와 함수도 포함 .(Number 객체 , setInterval () 함수등)

BOM(Browser Object Model) 으로 불리기도 함

<br>

## 2. 브라우저 객체 모델(BOM)

자바스크립트를 이용하면 브라우저의 정보에 접근하거나 브라우저의 여러 기능들을 제어할 수 있음

이때 사용할 수 있는 객체 모델이 바로 브라우저 객체 모델(BOM, Browser Object Model)

브라우저 객체 모델(BOM)은 문서 객체 모델(DOM)과는 달리 W3C의 표준 객체 모델은 아니나 자바스크립트가 브라우저의 기능적인 요소들을 직접 제어하고 관리할 방법을 제공

자바스크립트에서는 이러한 BOM 모델의 객체들을 전역 객체(global object)로 사용할 수 있음

<br>

## 3. Window 객체 사용

- alert, confirm, prompt

window 객체의 함수를 호출하면 브라우저에서 제공하는 창을 open.

alert() : 브라우저의 알림창

confirm() : 브라우저의 확인 취소 선택창

prompt() : 브라우저의 입력 창

<br>

- navigator

navigator 객체는 브라우저의 정보가 내장된 객체

navigator 의 정보로 서로 다른 브라우저를 구분할 수 있으며 , 브라우저 별로 다르게 처리 가능

HTML5 에서는 위치 정보를 알려주는 역할 가능

<br>

- location, history

location 객체를 이용하여 현재 페이지 주소 ( 와 관련 된 정보들을 알 수 있다

location.href 프로퍼티에 값을 할당하지 않으면 현재 URL 을 조회하고 값을 할당하면 할당 된 URL 로 페이지 이동

location.reload () : 현재 페이지를 새로고침

history 객체는 브라우저의 페이지 이력을 담는 객체

history.back () / history.forward () : 브라우저의 뒤로 가기 앞으로 가기 버튼과 같은 동작

<br>

- 새 창 열기

window 객체의 open() 함수를 사용하면 새 창을 열 수 있음

window.open 페이지 URL’, ‘ 창이름 ’, 특성 ’, 히스토리 대체여부

창이름 (string) : open 할 대상 (\_blank, \_self 등 ) 지정 혹은 창의 이름

특성 (string) : 새로 열릴 창의 너비 , 높이 등의 특성 지정

히스토리 대체여부 (Boolean) : 현재 페이지 히스토리에 덮어 쓸지 여부 ( false)

1. 창 열고 닫기

이벤트를 이용하여 특정 시점에 창을 열 수 있음

페이지 로딩 완료 후 새 창 열기 , 클릭할 때 새 창 열기 등

window 객체의 close() 함수로 현재 창을 닫을 수 있음

특히 브라우저에 내장 된 창이 아닌 JavaScript 로 자체 구현한 팝업에서 필요

2. 부모 창 컨트롤

window 객체의 opener속성을 이용하면 부모 창(새창을 연 창)을 컨트롤 가능

부모 창에 값 전달

부모 창을 새로 고침 하거나 페이지 이동

opener 객체는 부모 창의 window 객체

<br>

## 4. window 객체 프로퍼티

window 객체는 웹 브라우저에서는 구동 되는 JavaScript 의 전역 객체

screen 객체는 화면의 가로 , 세로 크기 정보를 알 수 있다

pageYOffset 등과 scroll() 함수를 이용하면 현재 화면의 크기를 계산하여 페이지 단위로 스크롤 제어가 가능

| 객체        | 설명                                     |
| ----------- | ---------------------------------------- |
| self        | 현재 창 자신. window와 같음              |
| history     | history 객체                             |
| location    | location 객체                            |
| opener      | open()으로 열린 창에서 볼때 자신을 연 창 |
| parent      | 현재 프레임의 상위 프레임                |
| top         | 현재 프레임의 최상위 프레임              |
| frames      | 창안의 모든 프레임에 대한 배열 정보      |
| locationbar | location 바                              |
| menubar     | 창 메뉴바                                |
| statusbar   | 창의 상태바                              |
| toolbar     | 창의 툴바                                |
| personalbar | 창의 퍼스널바                            |
| scrollbars  | 창의 스크롤바                            |
| innerHeight | 창 표시 영역의 높이(픽셀) IE지원안함     |
| innerWidth  | 창 표시영역의 너비(픽셀) IE지원안함      |
| outerHeight | 창 바깥쪽 둘레의 높이(픽셀) IE지원안함   |
| outerWidth  | 창 바깥쪽 둘레의 너비(픽셀) IE지원안함   |
| pageXOffset | 현재 나타나는 페이지의 X위치 IE지원안함  |
| pageYOffset | 현재 나타나는 페이지의 Y위치 IE지원안함  |

<br>

## 5. window 객체 함수

브라우저에서 버튼으로 제공하는 기능인 find, stop, print 와 같은 함수도 있음

move함수로 현재 열려 있는 창의 위치를 이동 가능

| 함수          | 설명                                                          |
| ------------- | ------------------------------------------------------------- |
| alert()       | 경고용 대화상자                                               |
| confirm()     | 확인, 취소 대화상자                                           |
| prompt()      | 입력창 대화상자                                               |
| open()        | 새 창 오픈                                                    |
| scroll()      | 창을 스크롤                                                   |
| find()        | 창안에 지정된 문자열이 있으면 true, 아니면 false (IE지원안함) |
| stop()        | 불러오기 중지 (IE지원안함)                                    |
| print()       | 화면의 내용을 프린터로 출력                                   |
| moveTo()      | 창을 절대적 좌표로 이동                                       |
| moveBy()      | 창을 상대적 좌표로 이동                                       |
| resizeTo()    | 창의 크기 변경                                                |
| setInterval() | 지속적으로 일정한 시간간격으로 함수 호출                      |
| setTimeout()  | 단 한번 일정한 시간간격으로 함수 호출                         |

<br>
