# AOP (Aspect Oriented Programming)

<br>

<br>

## 1. AOP(관점 지향 프로그래밍) 개요

<br>

#### 핵심 관심 사항과 공통(부가) 관심 사항

핵심 관심 사항(core concern) 과 공통 관심 사항 (cross-cutting concern)

기존 OOP에서는 공통관심사항을 여러 모듈에서 적용하는데 있어 중복된 코드를 양상 하는 한계가 존재

이를 해결하기 위해 AOP가 등장

Aspect Oriented Programming은 문제를 해결하기 위한 핵심 관심 사항과 전체에 적용되는 공통 관심 사항을 기준으로 프로그래밍함으로써 공통 모듈을 손쉽게 적용할 수 있게 함

> 핵심 관심사항 : 중요한 로직들
>
> 공통관심사항 : 공통적으로 들어가야할 로직

<br>

#### AOP

AOP는 application에서의 관심사의 분리 (기능의 분리) 즉, 핵심적인 기능에서 부가적인 기능을 분리

분리한 부가기능을 어스펙트(Aspect)라는 독특한 모듈 형태로 만들어서 설계하고 개발하는 방법

OOP를 적용하여도 핵심기능에서 부가기능을 쉽게 분리된 모듈로 작성하기 어려운 문제점을 AOP가 해결

AOP는 부가기능을 어스펙트(Aspect)로 정의하여, 핵심기능에서 부가기능을 분리함으로써 핵심 기능을 설계하고 구현할 때 객체지향적인 가치를 지킬 수 있도록 도와주는 개념

<br>

#### AOP 적용 예

1. 간단한 메소드의 성능 검사

- 개발 도중 특히 DB에 다향의 데이터를 넣고 빼는 등의 배치 작업에 대하여 시간을 측정해보고 쿼리를 개선하는 작업은 매우 의미가 있음. 이 경우 매번 해당 메소드의 처음과 끝에 System.currentTimeMills()를 사용하거나, 스프링이 제공하는 StopWatch코드를 사용하기는 매우 번거로움.
- 이런 경우 해당 작업을 하는 코드를 밖에서 설정하고 해당 부분을 사용하는것이 편리

2. 트랜잭션 처리

- 트랜잭션의 경우 비즈니스 로직의 전후에 설정됨
- 하지만 매번 사용하는 트랜잭션 (try{}catch{}부분)의 코드는 번거롭고, 소스를 더욱 복잡하게 보여줌

3. 예외 반환

- 스프링에는 DataAccessException이라는 매우 잘 정의되어있는 예외 계층 구조가 있음
- 예전 하이버네이트 예외들은 몇개 없었고 그나마도 UncatchedException이 아니었음
- 이렇게 구조가 별로 안좋은 예외들이 발생했을때, 그걸 잡아서 잘 정의되어있는 예외 계층 구조로 변환해서 다시 던지는 Aspect 는 제 3의 프레임워크를 사용할 때, 본인의 애플레키에션에서 별도의 예외 계층 구조로 변환하고 싶을때 유용

4. 아키텍처 검증
5. 기타

- 하이버네이트와 JDBC를 같이 사용할경우, DB동기화 문제 해결
- 멀티쓰레드 Safety 관련하여 작업해야 하는 경우, 메소드들에 일괄적으로 락을 설정하는 Aspect
- 데드락등으로 인한 PessimisticLockingFailureException등의 예외를 만났을때 재시도하는 설정?
- 로깅, 인증 권한 등

<br>

## 2. AOP 구조

<br>

- 핵심 관심 사항 : BankingService, AccountService, CustomerService
- 공통 관심 사항 : Security, Transaction, Other

핵심 관심사항에 공통 관심사항을 어떻게 적용시킬 것인가

<br>

## 3. Spring AOP 용어

<br>

1. Target

핵심 기능을 담고 있는 모듈로 target 은 부가기능을 부여할 대상이 됨

2. Advice

어느 시점 (예: mothod의 수행 전/후, 예외 발생 후 등..) 에 어떤 공통 관심 기능(Aspect)을 적용할지 정의한것. Target에 제공할 부가 기능을 담고 있는 모듈

3. Join Point

Aspect가 적용될 수 있는 지점 (method, field) (스프링에서는 field안됨! 메소드만..)

즉 target 객체가 구현한 인터페이스의 모든 method는 JoinPoint가 됨

4. Pointcut

공통 관심 사항이 적용될 JoinPoint

Advice를 적용할 target의 method를 선별하는 정규 표현식

Pointcut 표현식은 execution으로 시작하고 method의 Signature를 비교하는 방법을 주로 이용

5. Aspect

여러 객체에서 공통으로 적용되는 공통 관심 사항 (transaction, logging, security.. )

AOP의 기본 모듈

Aspect = Advice + Pointcut

Aspect 는 Sigleton형태의 객체로 존재

6. Advisor

Advisor = Advice + pointcut

Advisor는 Spring AOP에서만 사용되는 특별한 용어

7. Weaving

어떤 Advice를 어떤 Pointcut(핵심사항)에 적용시킬 것인지에 대한 설정(Advisor)

즉 Pointcut에 의해서 결정된 타겟의 JoinPoint에 부가기능 (Advice)을 삽입하는 과정을 뜻함

Weaving은 AOP핵심기능 (Target)의 코드에 영향을 주지 않으면서 필요한 부가기능 (Advice)을 추가할 수 있도록 해주는 핵심적인 처리 과정

<br>

## 4. Pointcut 표현식

<br>

#### execution 명시자

Advice를 적용할 메서드 지정

기본형식 : "\*"는 모든 값을 의미. ".."는 0개 이상 의미

> excution( [제한자] [리턴타입] [클래스이름] [이름] ([파라미터]) )

- execution(_ some.package._.\*())

  - some.package 패키지 내
  - 파라미터가 없는 모든 메서드 호출

- execution(_ some.package.._.\*(..))

  - some.package 패키지와 하위 패키지에 있는
  - 파라미터가 0개 이상인 모든 메서드 호출

- execution(String some.package.SomeService.someMethod(..))

  - 리턴 타입이 String,
  - some.package.SomeService 인터페이스 내
  - 파라미터가 0개 이상인 someMethod 메서드 호출

- execution(_ some_(\*))

  - 메서드 이름이 some으로 시작되고,
  - 파라미터가 1개인 메서드 호출

- execution(_ some_(_, _))

  - 메서드 이름이 some으로 시작되고,
  - 파라미터가 2개인 메서드 호출

- execution(_ some_(String, ..))

  - 메서드 이름이 some으로 시작되고,
  - 첫번째 파라미터 타입이 String,
  - 파라미터가 1개 이상인 메서드 호출

#### within 명시자

특정타입 (Interface, Class) 에 속하는 메서드를 Pointcut으로 지정

- within(some.package.SomeService)

  - SomeService 인터페이스 내 모든 메서드 호출

- within(some.package.\*)

  - some.package 패키지 내 모든 메서드 호출

- within(some.package..\*)

  - some.package 패키지 및 하위 패키지 내 모든 메서드 호출

#### bean 명시자

스프링 빈 이름을 이용하여 Pointcut 지정

빈이름의 패턴을 설정

- bean(someServiceBean)

  - 이름이 someServiceBean인 빈의 메서드 호출

- bean(\*SomeService)

  - 이름이 SomeService로 끝나는 빈의 메서드 호출

#### 표현식에는 '&&' 및 '||' 연산자를 이용

각각의 표현식을 연결 (and, or)

<br>

## 5. AOP 비교

![img](https://itwiki.kr/images/thumb/1/11/AOP_%EA%B0%9C%EC%9A%94%EB%8F%84.png/500px-AOP_%EA%B0%9C%EC%9A%94%EB%8F%84.png)

AOP의 경우 Aspect로 보안, 인증, 로그인 등의 부가기능을 빼놓음 (스템 전반에 산재되어 사용가능한 공통기능)

<br>

## 6. Spring AOP 특징

<br>

- Spring은 프록시(Proxy) 기반 AOP를 지원

Spring은 Target 객체에 대한 Proxy를 만들어 제공

Target을 감싸는 Proxy는 실행시간(Runtime)에 생성

Proxy는 Advice를 Target객체에 적용하면서 생성되는 객체

- 프록시(Proxy)가 호출을 가로챔 (Intercept)

프록시는 Target객체에 대한 호출을 가로챈 다음 Advice의 부가기능 로직을 수행하고 난 후에 Target의 핵심 기능 로직을 호출 (전처리 Advice)

또는 Target의 핵심 기능 로직 method를 호출한 후에 부가기능 (Advice)을 수행하는 경우도 있음 (후처리 Advice)

전처리/후처리는 포인트컷에따라 달라짐

- Spring AOP는 method JoinPoint만 지원

Spring은 동적 Proxy를 기반으로 AOP를 구현하므로 method JoinPoint만 지원

즉, 핵심기능(Target)의 method가 호출되는 런타임 시점에만 부가기능(Advice)를 적용할 수 있음

반면 AspectJ같은 고급 AOP framework를 사용하면 객체의 생성, 필드값의 조회와 조작, static method 호출 및 초기화 등의 다양한 작업에 부가기능을 적용할 수 있음

<br>

## 7. POJO 기반 AOP 구현 방법

1. XML Schema 확장 기법을 통해 설정 파일을 작성
2. POJO 기반 Advice Class 작성

#### XML Schema를 이용한 AOP 설정 - 설정파일

- aop namespace와 XML Schema 추가

- aop namespace 를 이용한 설정

##### AOP 설정태그

| Tag             | 설명                                     |
| --------------- | ---------------------------------------- |
| \<aop:config>   | aop설정의 root태그 (weaving들의 묶음)    |
| \<aop:aspect>   | Aspect 설정 (하나의 weaving에 대한 설정) |
| \<aop:pointcut> | Pointcut 설정                            |

##### Advice 설정태그

| Tag                    | 설명                                                              |
| ---------------------- | ----------------------------------------------------------------- |
| \<aop:before>          | method 실행 전 실행될 Advice                                      |
| \<aop:after-returning> | method 정상 실행 후 실행될 Advice                                 |
| \<aop:after-throwing>  | method에서 예외발생시 실행될 Advice (catch block)                 |
| \<aop:after>           | method 정상 또는 예외 발생 상관없이 실행될 Advice (finally block) |
| \<aop:around>          | 모든 시점(실행 전, 후)에서 적용시킬 수 있는 Advice                |

- 설정파일 \<aop:aspect>

한개의 Aspect(공통관심기능)을 설정

ref 속성을 통해 공통기능을 가지고 있는 bean을 연결

id는 이 태그의 식별자로 설정

자식태그로 \<aop:pointcut> advice관련 태그가 올 수 있음

<br>

#### POJO 기반 Advice Class 작성

설정 파일의 advice 관련 태그에 맞게 작성

\<bean> 으로 등록하며 \<aop:aspect> 의 ref속성으로 참조

공통 기능 메소드 : advice 관련 태그들의 method 속성의 값이 method의 이름이 됨

> pointcut : 직접 pointcut을 설정. 호출 할 method의 패전 지정
>
> pointcut-ref : \<aop:pointcut> 태그의 id명을 넣어 pointcut 지정
>
> method : Aspect bean에서 호출할 method 명 지정

- POJO 기반의 Class로 작성

class 명이나 method명에 대한 제한은 없고 method 구문은 호출되는 시점에 따라 달라 질 수 있음

method 의 이름은 advice 태그 (\<aop:before/>)에서 method 속성의 값이 method 명이 됨

- Advice 종류

  1. Before Advice

     대상 객체의 메소드가 실행되기 전에 실행됨

     return type : 리턴값을 갖더라도 실제 Advice의 적용과정에서 사용되지 않기 때문에 void를 씀

     argument : 없거나 대상 객체 및 호출되는 메소드에 대한 정보 또는 파라미터에 대한 정보가 필요하다면 JoitPoint 객체를 전달

     실행순서

     > 1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP프록시의 메소드를 호출
     > 2. AOP프록시는 \<aop:before>에서 지정한 메소드를 호출
     > 3. AOP프록시는 Aspect 기능 실행 후 실제 빈 객체의 메소드를 호출

  2. After Returning Advice

     대상 객체의 method 실행이 정상적으로 끝난 뒤 실행됨

     return type : void

     argument : 없거나 org.aspectj.lang.JoinPoint 객체를 받는다. JoinPoint는 항상 철 argument로 사용. 대상 method에서 반환되는 특정 객체 타입의 값을 argument로 받을수 있음

     After Returning Advice 실행 순서

     > 1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP프록시의 메소드를 호출
     > 2. AOP프록시는 실제 빈 객체의 메소드를 호출 (정상 실행)
     > 3. AOP프록시는 \<aop:after-returning>에서 지정한 메소드를 호출

  3. After Throwing Advice

     대상 객체의 method실행 중 예외가 발생한 경우 실행됨

     return type : void

     argument : 없거나 JoinPoint객체를 받음. JoinPoint는 항상 첫 argument로 사용. 대상 method에서 전달되는 예외객체를 argument로 받을 수 있음

     After Throwing Advice 실행순서

     > 1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP프록시의 메소드를 호출
     > 2. AOP프록시는 실제 빈 객체의 메소드를 호출 (exception 발생)
     > 3. AOP프록시는 \<aop:after-throwing>에서 지정한 메소드를 호출

  4. After Advice

     대상 객체의 method 가 정상적으로 실행되었는지 아니면 exception을 발생시켰는지 여부와 상관없이 메소드 실행 종료 후 공통 기능 적용

     return type : void

     argument : 없거나 JoinPoint객체를 받음. JoinPoint는 항상 첫 argument로 사용.

     After Advice 실행순서

     > 1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP프록시의 메소드를 호출
     > 2. AOP프록시는 실제 빈 객체의 메소드를 호출 (정상 실행, exception 발생 : java의 finally와 같음)
     > 3. AOP프록시는 \<aop:after>에서 지정한 메소드를 호출

  5. Around Advice

     위의 네가지 Advice를 다 구현할 수 있는 Advice]

     return type : Object

     argument : org.aspectj.lang.ProceedingJoinPoint를 반드시 첫 argument로 지정

     Around Advice 실행 순서

     > 1. 빈 객체를 사용하는 코드에서 스프링이 생성한 AOP프록시의 메소드를 호출
     > 2. AOP프록시는 \<aop:around>에서 지정한 메소드를 호출
     > 3. AOP프록시는 실제 빈 객체의 메소드를 호출
     > 4. AOP프록시는 \<aop:around>에서 지정한 메소드를 호출

<br>

## 8. JoinPoint Class 구성 요소

대상 객체에 대한 정보를 갖고 있는 객체로 Spring Container로부터 받음

org.aspectj.lang 패키지에 있음

반드시 Aspect method의 첫 argument로 와야함

#### 주요 method

| Method                   | 설명                                                                    |
| ------------------------ | ----------------------------------------------------------------------- |
| Object getTarget()       | 대상 객체를 리턴                                                        |
| Object getArgs()         | 파라미터로 넘겨진 값들을 배열로 리턴<br>넘겨온 값이 없으면 빈 배열 리턴 |
| Signature getSignature() | 호출되는 method의 정보를 가진 객체리턴                                  |
| String getName()         | method이름 리턴                                                         |
| String toLongString()    | method전체 syntax 리턴                                                  |
| String toShortString()   | method를 축약해서 리턴                                                  |

<br>

## 9. @aspect Annotation을 이용한 AOP구현

root-context안에 넣어둠

@Aspect Annotation을 이용하여 Aspect Class에 직접 Advice및 Pointcut등을 설정

설정파일에 \<aop:aspect-autoproxy/> 추가

Aspect Class를 \<bean> 으로 등록

Annotation
@Aspect : Aspect Class 선언

    @Before("pointcut")

    @AfterReturning(pointcut="", returning="")

    @AfterThrowing(pointcut="", throwing="")

    @After("pointcut")

    @Around("pointcut")

Around를 제외한 나머지 method들은 첫 argument로 JoinPoint를 가질 수 있음

Around method는 argument로 ProceedingJoinPoint를 가질 수 있음
