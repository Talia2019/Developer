# 📚 메모리

<br>

<br>

## 1. 메모리

<br>

#### 메모리

> Main Memory, RAM을 의미
>
> 프로그램 실행 시 필요한 주소, 정보들을 저장하고 가져다 사용할 수 있게 만드는 공간 (작업을 위해 사용되는 공간)
>
> 프로세스가 실행되려면 프로그램이 메모리에 올라와야 함

<br>

#### 메모리 관리가 필요한 이유

각각의 프로세스는 독립된 메모리 공간을 갖고, 운영체제 혹은 다른 프로세스의 메모리 공간에 접근할 수 없는 제한이 걸려있음

단지, 운영체제 만이 운영체제 메모리 영역과 사용자 메모리 영역의 접근에 제약을 받지 않기 때문에 운영체제에서 메모리를 관

또한, 멀티프로그래밍 환경으로 변화하면서 한정된 메모리를 효율적으로 사용해야 했고, 운영체제가 이를 어떻게 관리하는지에 대한 관리방법이 중요해짐

<br>

#### MMU

CPU는 레지스터가 지시하는대로 메모리에 접근하여 다음에 수행할 명령어를 가져옴

명령어 수행 시 메모리에 필요한 데이터가 없으면 해당 데이터를 우선 가져와야 함

이 역할을 하는 것이 바로 MMU

<br>

메모리 관리장치(MMU)는 논리 주소를 물리주소로 변환해줌

뿐만 아니라 메모리 보호나 캐시 관리 등 CPU가 메모리에 접근하는 것을 총 관리해주는 하드웨어

<br>

메모리의 공간이 한정적이기 때문에, 사용자에게 더 많은 메모리를 제공하기 위해 '가상 주소'라는 개념이 등장 (가상 주소는 프로그램 상에서 사용자가 보는 주소 공간이라고 보면 됨)

이 가상 주소에서 실제 데이터가 담겨 있는 곳에 접근하기 위해선 빠른 주소 변환이 필요한데, 이를 MMU가 도와주는 것

<br>

또한 메인 메모리의 직접 접근은 비효율적이므로, CPU와 메인 메모리 속도를 맞추기 위해 캐시가 존재

<br>

#### MMU의 메모리 보호

프로세스는 독립적인 메모리 공간을 가져야 되고, 자신의 공간만 접근해야 함

따라서 한 프로세스에게 합법적인 주소 영역을 설정하고, 잘못된 접근이 오면 trap을 발생시키며 보호

- base와 limit 레지스터를 활용한 메모리 보호 기법

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2F5Lgut%2FbtquNvKMRwH%2FJOqzcmz8wiXf0Kv7okfGzK%2Fimg.png">

base 레지스터는 메모리상의 프로세스 시작주소를 물리 주소로 저장
limit 레지스터는 프로세스의 사이즈를 저장

이로써 프로세스의 접근 가능한 합법적인 메모리 영역(x)은

```
base <= x < base+limit
```

이 영역 밖에서 접근을 요구하면 trap을 발생시킴

안전성을 위해 base와 limit 레지스터는 커널 모드에서만 수정 가능하도록 설계 (사용자 모드에서는 직접 변경할 수 없도록)

<br>

#### 메모리 과할당(over allocating)

> 실제 메모리의 사이즈보다 더 큰 사이즈의 메모리를 프로세스에 할당한 상황

<br>

페이지 기법과 같은 메모리 관리 기법은 사용자가 눈치 채지 못하도록 눈속임을(가상메모리) 통해 메모리를 할당

<br>

과할당 상황에 대해서 눈속임이 들킬만한 상황이 존재

1. 프로세스 실행 도중 페이지 폴트 발생
2. 페이지 폴트를 발생시킨 페이지 위치를 디스크에서 찾음
3. 메모리의 빈 프레임에 페이지를 올려야 하는데, 모든 메모리가 사용중이라 빈 프레임이 없음

<br>

이러한 과할당을 해결하기 위해선, 빈 프레임을 확보할 수 있어야 함

1. 메모리에 올라와 있는 한 프로세스를 종료시켜 빈 프레임을 얻음

2. 프로세스 하나를 swap out하고, 이 공간을 빈 프레임으로 활용

<br>

swapping 기법을 통해 공간을 바꿔치기하는 2번 방법과는 달리 1번은 사용자에게 페이징 시스템을 들킬 가능성이 매우 높아서 하면 안됨

(페이징 기법은 사용자 모르게 시스템 능률을 높이기 위해 선택한 일이므로 들키지 않게 처리해야한다)

<br>

따라서, 2번과 같은 해결책을 통해 페이지 교체가 이루어져야 함

<br>

<br>

#### 페이지 교체

> 메모리 과할당이 발생했을 때, 프로세스 하나를 swap out해서 빈 프레임을 확보하는 것

<br>

1. 프로세스 실행 도중 페이지 부재 발생

2. 페이지 폴트를 발생시킨 페이지 위치를 디스크에서 찾음

3. 메모리에 빈 프레임이 있는지 확인

   > 빈 프레임이 있으면 해당 프레임을 사용
   >
   > 빈 프레임이 없으면, victim 프레임을 선정해 디스크에 기록하고 페이지 테이블을 업데이트함

4. 빈 프레임에 페이지 폴트가 발생한 페이지를 올리고, 페이지 테이블 업데이트

<br>

페이지 교체가 이루어지면 아무일이 없던것 처럼 프로세스를 계속 수행시켜주면서 사용자가 알지 못하도록 해야 함

이때, 아무일도 일어나지 않은 것처럼 하려면, 페이지 교체 당시 오버헤드를 최대한 줄여야 함

<br>

#### 오버헤드를 감소시키는 해결법

이처럼 빈 프레임이 없는 상황에서 victim 프레임을 비울 때와 원하는 페이지를 프레임으로 올릴 때 두 번의 디스크 접근이 이루어짐

페이지 교체가 많이 이루어지면, 이처럼 입출력 연산이 많이 발생하게 되면서 오버헤드 문제가 발생

<br>

1. 방법1

변경비트를 모든 페이지마다 둬서, victim 페이지가 정해지면 해당 페이지의 비트를 확인

해당 비트가 set 상태면? → 해당 페이지 내용이 디스크 상의 페이지 내용과 달라졌다는 뜻
(즉, 페이지가 메모리 올라온 이후 한번이라도 수정이 일어났던 것. 따라서 이건 디스크에 기록해야함)

bit가 clear 상태라면? → 디스크 상의 페이지 내용과 메모리 상의 페이지가 정확히 일치하는 상황
(즉, 디스크와 내용이 같아서 기록할 필요가 없음)

비트를 활용해 디스크에 기록하는 횟수를 줄이면서 오버헤드에 대한 수를 최대 절반으로 감소시키는 방법

<br>

2. 방법2

페이지 교체 알고리즘을 상황에 따라 잘 선택해야 함

현재 상황에서 페이지 폴트를 발생할 확률을 최대한 줄여줄 수 있는 교체 알고리즘을 사용

FIFO, OPT, LRU

<br>

<br>
