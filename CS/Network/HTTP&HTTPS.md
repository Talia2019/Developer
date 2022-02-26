# 📚 HTTP & HTTPS

<br>

<br>

## 1. HTTP (HyperText Transfer Protocol)

> 클라이언트와 서버가 자원을 주고 받을 때 쓰는 프로토콜(통신 규약)

즉, 인터넷에서 하이퍼텍스트를 교환하기 위한 통신규약으로 80번 포트를 사용

HTTP서버가 80번 포트에서 요청을 기다리며 클라이언트는 80번 포트로 요청을 보냄

<br>

#### HTTP 구조

HTTP는 애플리케이션 레벨의 프로토콜로 TCP/IP 위에서 작동

HTTP는 상태를 가지고 있지 않는 Stateless 프로토콜이며 Method, Path, Version, Headers, Body 등으로 구성됨

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbkdJ4Q%2FbtqK6AXLEtC%2FjBZzMuJBWzdLYmqILo5Ri1%2Fimg.png)

<br>

하지만 HTTP는 암호화가 되지 않은 텍스트 교환 프로토콜이기에, 누군가 네트워크에서 신호를 가로채면 내용이 노출되는 보안 이슈가 존재

이런 보안 문제를 해결해주는 프로토콜이 **'HTTPS'**

<br>

## 2. HTTPS (HyperText Transfer Protocol Secure)

> HTTP에 데이터 암호화가 추가된 프로토콜

HyperText Transfer Protocol over Secure Socket Layer, HTTP over TLS, HTTP over SSL, HTTP Secure 등으로 불림

인터넷 상에서 정보를 암호화하는 SSL 프로토콜을 사용해 클라이언트와 서버가 자원을 주고 받을 때 쓰는 통신 규약

HTTPS는 HTTP와 다르게 443번 포트를 사용하며, 네트워크 상에서 중간에 제3자가 정보를 볼 수 없도록 암호화를 지원

<br>

#### 대칭키 암호화와 비대칭키 암호화

HTTPS는 대칭키 암호화 방식과 비대칭키 암호화 방식을 모두 사용

- 대칭키 암호화

클라이언트와 서버가 동일한 키를 사용해 암호화/복호화를 진행

키가 노출되면 매우 위험하지만 연산 속도가 빠름

- 비대칭키 암호화

1개의 쌍으로 구성된 공개키와 개인키를 암호화/복호화 하는데 사용

키가 노출되어도 비교적 안전하지만 연산 속도가 느림

<br>

#### HTTPS 동작 과정

HTTPS는 대칭키 암호화와 비대칭키 암호화를 모두 사용하여 빠른 연산 속도와 안정성을 모두 얻음

HTTPS 연결 과정(Hand-Shaking)에서는 먼저 서버와 클라이언트 간에 세션키를 교환. 여기서 세션키는 주고 받는 데이터를 암호화하기 위해 사용되는 대칭키이며, 데이터 간의 교환에는 빠른 연산 속도가 필요하므로 세션키는 대칭키로 만들어짐.

문제는 이 세션키를 클라이언트와 서버가 어떻게 교환할 것이냐 인데, 이 과정에서 비대칭키가 사용됨

즉, 처음 연결을 성립하여 안전하게 세션키를 공유하는 과정에서 비대칭키가 사용되는 것이고, 이후에 데이터를 교환하는 과정에서 빠른 연산 속도를 위해 대칭키가 사용됨

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FM729p%2FbtrqQeyzk3c%2FSMsJrx4EwkmJTYqFiSKIsk%2Fimg.png)

<br>

#### HTTPS 연결과정

1. 클라이언트(브라우저)가 서버로 최초 연결 시도

2. 서버는 공개키(엄밀히는 인증서)를 브라우저에게 넘겨줌

3. 브라우저는 인증서의 유효성을 검사하고 세션키를 발급함

4. 브라우저는 세션키를 보관하며 추가로 서버의 공개키로 세션키를 암호화하여 서버로 전송함

5. 서버는 개인키로 암호화된 세션키를 복호화하여 세션키를 얻음

6. 클라이언트와 서버는 동일한 세션키를 공유하므로 데이터를 전달할 때 세션키로 암호화/복호화를 진행함

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcCodLU%2FbtrqRZnoOFq%2Fe6kFHjADoVby70466Jkq51%2Fimg.png)

<br>

#### HTTPS 발급과정

서버는 클라이언트와 세션키를 공유하기 위한 공개키를 생성해야 하는데, 일반적으로는 인증된 기관(Certificate Authority) 에 공개키를 전송하여 인증서를 발급 받음

1. A기업은 HTTP 기반의 애플리케이션에 HTTPS를 적용하기 위해 공개키/개인키를 발급

2. CA 기업에게 돈을 지불하고, 공개키를 저장하는 인증서의 발급을 요청

3. CA 기업은 CA기업의 이름, 서버의 공개키, 서버의 정보 등을 기반으로 인증서를 생성하고, CA 기업의 개인키로 암호화하여 A기업에게 이를 제공

4. A기업은 클라이언트에게 암호화된 인증서를 제공

5. 브라우저는 CA기업의 공개키를 미리 다운받아 갖고 있어, 암호화된 인증서를 복호화

6. 암호화된 인증서를 복호화하여 얻은 A기업의 공개키로 세션키를 공유함

**_CA란?_** : Certificate Authority로, 공개키를 저장해주는 신뢰성이 검증된 민간기업

<br>

HTTPS도 무조건 안전한 것은 아님 (신뢰받는 CA 기업이 아닌 자체 인증서 발급한 경우 등)

이때는 HTTPS지만 브라우저에서 `주의 요함`, `안전하지 않은 사이트`와 같은 알림으로 주의 받음

<br>

## 3. HTTP와 HTTPS

HTTP는 암호화가 추가되지 않았기 때문에 보안에 취약한 반면, HTTPS는 안전하게 데이터를 주고받을 수 있음

하지만 HTTPS를 이용하면 암호화/복호화의 과정이 필요하기 때문에 HTTP보다 속도가 느림 (물론 오늘날에는 거의 차이를 못느낄 정도)

또한 HTTPS는 인증서를 발급하고 유지하기 위한 추가 비용이 발생

개인 정보와 같은 민감한 데이터를 주고 받아야 한다면 HTTPS를 이용해야 하지만, 노출이 되어도 괜찮은 단순한 정보 조회 등 만을 처리하고 있다면 HTTP를 이용하면 됨

<br>

<br>

> 출처 https://mangkyu.tistory.com/98
