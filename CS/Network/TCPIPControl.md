# 📚 TCP 흐름제어 & 혼잡제어

<br>

<br>

### 1. 흐름제어 (Flow Control)

> 흐름 제어는 송신 측과 수신 측의 TCP 버퍼 크기 차이로 인해 생기는 데이터 처리 속도 차이를 해결하기 위한 기법

수신 측이 송신 측보다 데이터 처리 속도가 빠르면 문제가 없지만, 송신 측의 속도가 빠를 경우 문제

수신 측에서 제한된 저장 용량을 초과한 이후에 도착하는 패킷은 손실될 수 있으며, 만약 손실된다면 불필요한 추가 패킷 전송이 발생

`TCP 버퍼` : 송신 측은 버퍼에 TCP 세그먼트를 보관한 후 순차적으로 전송하고, 수신 측은 도착한 TCP 세그먼트를 애플리케이션이 읽을 때까지 버퍼에 보관

<br>

### 1.1. 흐름제어 기법

<br>

#### Stop and Wait

> 매번 전송한 패킷에 대해 확인 응답(ACK)를 받으면 다음 패킷을 전송하는 방법
>
> 그러나 패킷을 하나씩 보내기 때문에 비효율적

<br>

#### Sliding Window

> 수신 측에서 설정한 윈도우 크기만큼 송신 측에서 확인 응답(ACK) 없이 패킷을 전송할 수 있게 하여 데이터 흐름을 동적으로 조절하는 제어 기법

- 윈도우 크기

최초의 윈도우 크기는 호스트들의 '3 way handshaking'을 통해 수신 측 윈도우 크기로 설정되며, 이후 수신 측의 버퍼에 남아있는 공간에 따라 변함

윈도우 크기는 수신 측에서 송신 측으로 확인 응답(ACK)을 보낼 때 TCP 헤더(window size)에 담아서 보냄

즉, 윈도우는 메모리 버퍼의 일정 영역

- 동작 방식

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcTbios%2FbtrloyXkTF6%2FNCXRvhUJvAzXkz7xOiOLQ0%2Fimg.png)

윈도우에 포함된 패킷을 계속 전송하고, 수신 측으로부터 확인 응답(ACK)이 오면 윈도우를 옆으로 옮겨 다음 패킷들을 전송

> 최초로 수신자는 윈도우 사이즈를 7로 정함
>
> 송신자는 수신자의 확인 응답(ACK)을 받기 전까지 데이터를 송신
>
> 수신자는 확인 응답(ACK)을 송신자에게 보내면, 슬라이딩 윈도우 사이즈을 충족할 수 있게끔 윈도우를 옆으로 옮김
>
> 이후 데이터를 다 받을 때까지 위 과정을 반복

- 재전송

송신 측은 일정 시간 동안 수신 측으로부터 확인 응답(ACK)을 받지 못하면, 패킷을 재전송

만약, 송신 측에서 재전송을 했는데 패킷이 소실된 경우가 아니라 수신 측의 버퍼에 남는 공간 없는 경우면 문제 발생

이를 해결하기 위해 송신 측은 해결 응답(ACK)을 보내면서 남은 버퍼의 크기 (윈도우 크기)도 함께 송신

<br>

### 2. 혼잡 제어 (Congestion Control)

> 송 수신 측 사이의 패킷 수를 제어하는 기능이라 할 수 있으며, 혼잡 제어는 네트워크 내의 패킷 수를 조절하여 네트워크의 오버플로우를 방지하는 기능

데이터의 양이 라우터가 처리할 수 있는 양을 초과하면 초과된 데이터는 라우터가 처리하지 못함

이때 송신 측에서는 라우터가 처리하지 못한 데이터를 손실 데이터로 간주하고 계속 재전송하여 네트워크를 혼잡하게 함

이런 상황은 송신 측의 전송 속도를 적절히 조절하여 예방할 수 있는데, 이것을 혼잡 제어라고 함

<br>

### 2.1. 혼잡 제어 기법

<br>

#### AIMD (Additive Increse/Multicative Decrease)

합 증가/곱 감소 방식

AIMD 방식은 처음에 패킷을 하나씩 보내고 문제 없이 도착하면 윈도우의 크기를 1씩 증가시켜가며 전송

만약, 전송에 실패하면 윈도우 크기를 반으로 줄임

윈도우 크기를 너무 조금씩 늘리기 때문에 네트워크의 모든 대역을 활용하여 제대로 된 속도로 통신하기까지 시간이 오래 걸린다는 단점이 존재

<br>

#### Slow Start (느린 시작)

AIMD 방식은 윈도우 크기를 선형적으로 증가시키기 때문에, 제대로된 속도가 나오기까지 시간이 오래 걸림

반면, Slow Start는 윈도우의 크기를 1, 2, 4, 8, ...과 같이 지수적으로 증가시키다가 혼잡이 감지되면 윈도우 크기를 1로 줄이는 방식

이 방식은 보낸 데이터의 ACK가 도착할 때마다 윈도우 크기를 증가시키기 때문에 처음에는 윈도우 크기가 조금 느리게 증가할지라도, 시간이 가면 갈수록 윈도우 크기가 점점 빠르게 증가한다는 장점을 지님

<br>

#### Fast Retransmit (빠른 재전송)

패킷을 받는 수신자 입장에서는 세그먼트로 분할된 내용들이 순서대로 도착하지 않는 경우가 생길 수 있음

이런 상황이 발생했을 때 수신 측에서는 순서대로 잘 도착한 마지막 패킷의 다음 순번을 ACK 패킷에 실어서 보냄

그리고 이런 중복 ACK를 3개 받으면 재전송이 이루어짐

송신 측은 자신이 설정한 타임 아웃 시간이 지나지 않았어도 바로 해당 패킷을 재전송할 수 있기 때문에 보다 빠른 재전송률을 유지

<br>

#### Fast Recovery (빠른 회복)

혼잡한 상태가 되면 윈도우 크기를 1로 줄이지 않고 반으로 줄이고 선형 증가시키는 방법

이 방법을 적용하면 혼잡 상황을 한 번 겪고나서부터는 AIMD 방식으로 동작

<br>

### 2.2. 혼잡 제어 정책

TCP에는 Tahoe, Reno, New Reno, Cubic, Ealstic-TCP까지 다양한 혼잡 제어 정책이 존재

이러한 혼잡 제어 정책들은 공통적으로 혼잡이 발생하면 윈도우 크기를 줄이거나, 혹은 증가시키지 않으며 혼잡을 회피한다 라는 전제를 깔고 있음

Tahoe와 Reno는 기본적으로 처음에는 Slow Start 방식을 사용하다가 네트워크가 혼잡하다고 느껴졌을 때는 AIMD 방식으로 전환하는 방법을 사용하는 정책

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbfh6Md%2FbtrlpcsRChT%2FOSIX5niLtEngkcYrIhEKi0%2Fimg.png)

Y축은 혼잡 윈도우, X축은 시간

`Timeout` : 여러 가지 요인으로 인해 송신 측이 보낸 데이터 자체가 유실되었거나, 수신 측이 응답으로 보낸 ACK가 유실되는 경우

`3 ACK Duplicate` : 패킷을 받는 수신자 입장에서는 세그먼트로 분할된 내용들이 순서대로 도착하지 않는 경우가 생길 수 있음. 이런 상황이 발생했을 때 수신 측에서는 순서대로 잘 도착한 마지막 패킷의 다음 순번을 ACK 패킷에 실어서 보냄. 그리고 이런 중복 ACK 3개를 받으면 문제가 있다고 판단하여 해당 패킷을 송신 측이 재전송. 해당 기법을 빠른 재전송 이라고 부르며, 송신 측은 자신이 설정한 타임 아웃 시간이 지나지 않았어도 바로 해당 패킷을 재전송할 수 있기 때문에 보다 빠른 전송률을 유지할 수 있게 됨.

`Slow Start 임계점 (ssthresh)` : Threshold(임계점)은 Slow Start Threshold(ssthresh) 를 뜻하는 것으로, 여기까지만 Slow Start를 사용하겠다는 의미. Slow Start를 사용하며 윈도우 크기를 지수적으로 증가시키다보면 어느 순간부터는 윈도우 크기가 기하급수적으로 늘어나서 제어하기가 힘듦. 또한, 네트워크의 혼잡이 예상되는 상황에서 빠르게 값을 증가시키기 보다는 조금씩 증가시키는 편이 훨씬 안전

<br>

#### TCP Tahoe

TCP Tahoe는 처음에는 Slow Start를 사용하여 자신의 윈도우 크기를 지수적으로 빠르게 증가시키다가 ssthresh를 만난 이후부터는 AIMD을 사용하여 선형적으로 윈도우 크기를 증가시킴

그러다가 ACK Duplicated나 Timeout이 발생하면 네트워크에 혼잡이 발생했다고 판단하고, ssthresh와 자신의 윈도우 크기를 수정

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbsET8G%2FbtrlozIGFES%2FFvGQ1l69O6dV9oYSUqdsEk%2Fimg.png)

이 정책은 한 번 혼잡 상황이 발생한 지점을 기억하고 그 지점이 가까워지지 않도록 합리적으로 조절

하지만, 초반의 Slow Start 구간에 윈도우 크기를 늘릴 때 오래 걸린다는 단점이 있고, 혼잡 상황이 발생했을 때 다시 윈도우 크기를 1에서부터 시작해야 한다는 단점을 지님

<br>

#### TCP Reno

TCP Reno는 TCP Tahoe 이후에 나온 정책으로, Tahoe와 마찬가지로 Slow Start로 시작하여 임계점을 넘어서면 AIMD을 사용

다만, Tahoe와는 다르게 3 ACK Duplicated와 Timeout 혼잡 상황을 구분

Reno는 3개의 중복 ACK가 발생했을 때, 윈도우 크기를 1로 줄이는 것이 아니라 AIMD처럼 반으로만 줄이고 sshthresh를 줄어든 윈도우 값으로 정하고, 이 방식을 빠른 회복이라고 부름

그러나 Timeout에 의해서 데이터가 손실되면 Tahoe와 마찬가지로 윈도우 크기를 바로 1로 줄여버리고 Slow Start를 진행 (이때 ssthresh를 변경하지는 않음)

즉, Reno는 ACK 중복은 Timeout에 비해 그리 큰 혼잡이 아니라고 가정하고 혼잡 윈도우 크기를 1로 줄이지도 않는다는 점에서 혼잡 상황의 우선 순위를 둔 정책
