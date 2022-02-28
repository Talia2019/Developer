# 📚 Service

<br>

<br>

## 1. 서비스란?

일반적으로 화면 없이 동작하는 프로그램으로 백그라운드 프로세스(Background Process)

액티비티 응용프로그램은 화면이 종료되면 동작하지 않으나 서비스는 백그라운드에서 실행 됨(화면과 상관 없이 계속 동작)

<br>

#### 서비스 특징

1. 유저와 상호작용 할 수 없음
2. 액티비티의 생명주기에 종속되지 않음
3. 별도의 스레드에서 동작하지 않고 호스팅 프로세스의 메인스레드에서 작동
4. 액티비티가 비활성화 되면 액티비티보다 우선순위가 높음

<br>

#### 서비스를 실행시키는 방법

- startService

  - 앱 내의 액티비티 같은 컴포넌트가 호출했을 때 실행됨
  - 백그라운드에서 한가지 일을 하며, 결과를 호출했던 컴포넌트에 보내지 않음
  - 예) 다운로드, 파일업로드

- bindService
  - startService와 다르게 자신을 호출한 컴포넌트와 인터랙션 주고받을 수 있음
  - 처리한 결과 주고받을 수 있음
  - 서로 다른 프로세스상에 있어도 처리 가능

<br>

## 2. startService

<br>

#### startSetvice 생명주기

![image](https://t1.daumcdn.net/cfile/tistory/99170B3A5AA620F21B)

- 첫번째와 다섯번째부분인 시작요청과 중지요청은 Activity에서 호출

- 함수부분 (onCreate, onStartCommand, onDestroy)은 서비스가 시작되고 순서에 따라 호출됨

- 중지요청이 되면 onDestroy가 호출되고 서비스가 종료됨

<br>

- onCreate

  - 서비스가 처음 초기화 될 때 수행되는 메소드

- onStartCommand

  - Service를 다른 컴포너트가 수행했을 시 수행되고, 이 메서드를 통해 서비스가 백그라운드에서 동작
  - 리턴해줘야 되는 3가지 값 `START_NOT_STICKY`, `START_STICKY`, `START_REDELIVER_INTENT`

- onDestroy
  - 다른 컴포넌트에서 startService로 호출할경우 onStartCommand가 실행
  - stopService나 stopSelf가 호출되면 서비스가 동작을 멈추고 시스템이 onDestroy
