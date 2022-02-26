# 📚 PCB (Process Control Block) & Context Switching

<br>

<br>

## 1. Process Management

> CPU 가 프로세스가 여러개일 때, CPU 스케줄링을 통해 관리하는 것을 말함

이때, CPU는 각 프로세스들이 누군지 알아야 관리가 가능함

프로세스들의 특징을 갖고있는 것이 바로 `Process Metadata`

- Process Metadata
  - Process ID
  - Process State
  - Process Priority
  - CPU Registers
  - Owner
  - CPU Usage
  - Memeory Usage

이 메타데이터는 프로세스가 생성되면 `PCB(Process Control Block)`이라는 곳에 저장됨

<br>

## 2. PCB(Process Control Block)

> 프로세스 메타데이터들을 저장해 놓는 곳, 한 PCB 안에는 한 프로세스의 정보가 담김
>
> 운영체제가 프로세스 스케줄링을 위해 프로세스에 관한 모든 정보를 가지고 있는 데이터베이스

프로세스는 CPU를 점유하여 작업을 처리하다가도 상태가 전이되면, 진행하던 작업 내용들을 모두 정리하고 CPU를 반환해야 하는데, 이때 진행하던 작업들을 모두 저장하지 않으면 다음에 자신의 순서가 왔을 때 어떠한 작업을 해야하는지 알 수 없어짐

따라서 프로세스는 CPU가 처리하던 작업의 내용들을 자신의 PCB에 저장하고, 다음에 다시 CPU를 점유하여 작업을 수행해야 할 때 PCB로부터 해당 정보들을 CPU에 넘겨와서 계속해서 하던 작업을 진행

<br>

![image](https://nesoy.github.io/assets/posts/20181113/1.png)

- 프로세스 식별자 (Process ID)

- 프로세스 상태(Process State) : 생성(create), 준비(ready), 실행 (running), 대기(waiting), 완료(terminated) 상태가 있음

- 프로그램 계수기(Program Counter) : 프로세스가 다음에 실행할 명령어의 주소

- CPU 레지스터 및 일반 레지스터

- CPU 스케줄링 정보 : 우선 순위, 최종 실행시각, CPU 점유시간 등

- 메모리 관리 정보 : 해당 프로세스의 주소 공간 등

- 포인터 : 부모프로세스에 대한 포인터, 자식 프로세스에 대한 포인터, 프로세스가 위치한 메모리 주소에 대한 포인터, 할당된 자원에 대한 포인터 정보 등

<br>

#### 실행 흐름

프로그램 실행 → 프로세스 생성 → 프로세스 주소 공간에 (코드, 데이터, 스택) 생성
→ 이 프로세스의 메타데이터들이 PCB에 저장

<br>

#### 관리 방법

Linked List 방식으로 관리

PCB List Head에 PCB들이 생성될 때 붙음 (주소값 연결리스트)

프로세스가 생성되면 해당 PCB가 생성되고 프로세스 완료시 제거됨

이처럼 수행중인 프로세스를 변경할 때 CPU의 레지스터 정보가 변경되는 것을 `Context Switching` 이라 함

<br>

## 3. Context Switching

> CPU가 이전의 프로세스 상태를 PCB에 보관하고, 또 다른 프로세스의 정보를 PCB에 읽어 레지스터에 적재하는 과정

보통 인터럽트가 발생하거나, 실행 중인 CPU 사용 허가시간을 모두 소모하거나, 입출력을 위해 대기해야 하는 경우에 Context Switching이 발생

`프로세스가 Ready → Running, Running → Ready, Running → Waiting처럼 상태 변경 시 발생`

<br>

#### 왜 Context Switching 이 필요한가?

> 컴퓨터가 하나의 프로세스만 처리할 수 있다면 사용자 입장에서 반응속도가 매우 느려 사용이 불편함
>
> CPU가 빠른 속도로 프로세스를 바꿔가며 실행해 동시에 여러 일을 한번에 처리하는것처럼 보이기(Multitasking) 위해서 Context Switching이 필요

<br>

#### Context Switching의 OverHead란?

overhead는 과부하라는 뜻으로 보통 안좋은 말로 많이 쓰이나 프로세스 작업 중에는 OverHead를 감수해야 하는 상황이 있다.

```
프로세스를 수행하다가 입출력 이벤트가 발생해서 대기 상태로 전환시킴
이때, CPU를 그냥 놀게 놔두는 것보다 다른 프로세스를 수행시키는 것이 효율적
```

즉, CPU에 계속 프로세스를 수행시키도록 하기 위해서 다른 프로세스를 실행시키고 Context Switching 하는 것

CPU가 놀지 않도록 만들고, 사용자에게 빠르게 일처리를 제공해주기 위한 것
