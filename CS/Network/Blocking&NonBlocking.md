# 📚 Blocking/Non-blocking & Synchronous/Asynchronous

<br>

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fda50Yz%2Fbtq0Dsje4ZV%2FlGe8H8nZgdBdgFvo7IczS0%2Fimg.png">

<br>

## 1. Blocking/Non-blocking

> Blocking/NonBlocking은 호출되는 함수가 바로 리턴하느냐 마느냐가 관심사

블럭/논블럭은 간단히 말해서 `호출된 함수`가 `호출한 함수`에게 제어권을 건네주는 유무의 차이

함수 A, B가 있고, A 안에서 B를 호출했다고 가정하면, 호출한 함수는 A고, 호출된 함수는 B가 됨

현재 B가 호출되면서 B는 자신의 일을 진행해야 함(제어권이 B에게 주어진 상황)

- **Blocking** : 함수 B는 내 할 일을 다 마칠 때까지 제어권을 가지고 있고, A는 B가 다 마칠 때까지 기다려야 함

- **Non-blocking** : 함수 B는 할 일을 마치지 않았어도 A에게 제어권을 바로 넘겨줌. A는 B를 기다리면서도 다른 일을 진행할 수 있음

즉, 호출된 함수에서 일을 시작할 때 바로 제어권을 리턴해주느냐, 할 일을 마치고 리턴해주느냐에 따라 블럭과 논블럭으로 나누어진다고 볼 수 있음

<br>

## 2. Synchronous/Asynchronous

> Synchronous/Asynchronous는 호출되는 함수의 작업 완료 여부를 누가 신경쓰냐가 관심사

동기/비동기는 일을 수행 중인 `동시성`에 주목

아까처럼 함수 A와 B라고 똑같이 생각했을 때, B의 수행 결과나 종료 상태를 A가 신경쓰고 있는 유무의 차이라고 생각하면 됨

- **Synchronous** : 함수 A는 함수 B가 일을 하는 중에 기다리면서, 현재 상태가 어떤지 계속 체크
- **Asynchronous** : 함수 B의 수행 상태를 B 혼자 직접 신경쓰면서 처리 (Callback)

즉, 호출된 함수(B)를 호출한 함수(A)가 신경쓰는지, 호출된 함수(B) 스스로 신경쓰는지를 동기/비동기라고 생각하면 됨

비동기는 호출시 Callback을 전달하여 작업의 완료 여부를 호출한 함수에게 답하게 됨 (Callback이 오기 전까지 호출한 함수는 신경쓰지 않고 다른 일을 할 수 있음)

<br>

> 출처
>
> http://homoefficio.github.io/2017/02/19/Blocking-NonBlocking-Synchronous-Asynchronous/
