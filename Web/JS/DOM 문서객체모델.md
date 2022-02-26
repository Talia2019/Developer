# DOM(문서 객체 모델)

<br>

## 목차

1. 문서 객체 모델
2. 문서 객체 가져오기
3. 문서 객체 제거

<br>

## 1. 문서 객체 모델(DOM)

![image](https://images.velog.io/images/ldaehi0205/post/caa59697-d723-49a9-9e7d-e8e22cd11adb/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-01-25%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%207.09.56.png)

문서 객체 모델(DOM, Document Object Model)은 XML이나 HTML 문서에 접근하기 위한 일종의 인터페이스(API)

문서 내의 모든 요소를 정의하고, 각각의 요소에 접근하는 방법을 제공

HTML계층 구조의 제일 위에는 document노드가 있고 그 아래로 HTML 태그나 요소(element)들을 표현하는 노드와 문자열을 표현하는 노드가 있음

<br>

## 2. 문서 객체 만들기

문서 객체는 text node를 갖는 객체와 갖지 않는 객체로 나뉨

| 함수명                    | 설명                       |
| ------------------------- | -------------------------- |
| createElement(tagName)    | element node 를 생성       |
| createTextNode(text)      | text node 를 생성          |
| appendChild(node)         | 객체에 node를 child로 추가 |
| setAttribute(name, value) | 객체의 속성 지정           |
| getAttribute(name)        | 객체의 속성값              |
| innerHTML                 | 문자열을 HTML 태그로 삽입  |
| innerText                 | 문자열을 text node로 삽입  |

<br>

## 3. 문서 객체 가져오기

| 함수명                            | 설명                                                      |
| --------------------------------- | --------------------------------------------------------- |
| getElementById(id)                | 태그의 id속성이 id와 일치하는 element 객체 얻기           |
| getElementsByClassName(classname) | 태그의 class속성이 classname과 일치하는 element 배열 얻기 |
| getElementsByTagName(tagname)     | 태그이름이 tagname과 일치하는 element 배열 얻기           |
| getElementsByName(name)           | 태그의 name속성이 name과 일치하는 element 배열 얻기       |
| querySelector(selector)           | selector 에 일치하는 첫번째 element 객체 얻기             |
| querySelectorAll(selector)        | selector 에 일치하는 모든 element 배열 얻기               |

querySelector는 id갖고 얻어오는경우 많이쓰고
querySelectorAll은 여러개(id말고 나머지등) 얻어올때 많이씀

<br>

## 4. 문서 객체 제거

| 함수명                 | 설명                    |
| ---------------------- | ----------------------- |
| removeChild(childnode) | 객체의 자식 노드를 제거 |

<br>
