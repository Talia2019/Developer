# Spring

<br>

<br>

## 1. SpringFramework 등장배경

<br>

- EJB를 사용하면 애플리케이션 작성을 쉽게 할 수 있음

  Low Level의 트랜잭션이나 상태관리, 멀티 쓰레딩, 리소스 풀링과 같은 복잡한 Low Level의 API따위를 이해하지 못하더라도 아무 문제없이 어플리케이션을 개발 할 수 있음)

  하지만 EJB를 현실에서 반영 하는것은 어려움

  코드 수정 후 반영하는 과정 자체가 거창해 기능은 좋지만 복잡한 스펙으로 인한 개발의 효율성이 떨어지고, 어플리케이션을 테스트하기 위해서는 반드시 EJB서버가 필요

- 웹사이트가 점점 커지면서 엔터프라이즈급 서비스가 필요하게됨

  세션빈에서 Transaction관리가 용이. 로그인, 분산처리, 보안등)

  자바진영에서는 EJB가 엔터프라이즈급 서비스로 각광을 받게 됨

  EJB스펙에 정의된 인터페이스에 따라 코드를 작성하므로 기존에 작성된 POJO를 변경해야하고, 컨테이너에 배포를 해야 테스트가 가능해 개발속도가 저하됨

  또한 배우기 어렵고, 설정해야 할 부분이 많음

  EJB는 RMI를 기반으로 하는 서버이므로 무거운 Container

- Rod Johnson이 `EJB를 사용하지않고 엔터프라이즈 어플리케이션`을 개발하는 방법을 소개함

  `스프링`의 모태

  AOP나 DI같은 새로운 프로그래밍 방법론으로 가능

  POJO로 전언적인 프로그래밍 모델이 가능해짐

- 점차 POJO + 경량 프레임워크를 사용하기 시작

  POJO (Plain Old Java Object) (포조=순수자바코드. EJB나오기 이전형태)

  특정 프레임워크나 기술에 의존적이지 않은 자바객체

  특정 기술에 종속적이지 않기 때문에 생산성, 이식성 향상

  Plain : component interface를 상속받지 않는 특징 (특정 framework에 종속되지 않는)

  Old : ELB 이전의 java calss를 의미

- 경량 프레임워크

  EJB가 제공하는 서비스를 지원해 줄 수 있는 프레임워크 등장 - Hibernate, JDO, iBatis(MyBatis), Spring

- POJO + Framework

  EJB같은 거창한 컨테이너가 필요 없음

  오픈소스 프레임워크라 사용이 무료

  각종 기업용 어플리케이션 개발에 필요한 상당히 많은 라이브러리가 지원

  스프링 프레임워크는 모든 플랫폼에서 사용이 가능

  스프링은 웹 분야 뿐만이 아니라 어플리케이션 등 모든 분야에 적용이 가능한 다양한 라이브러리를 가지고 있음

<br>

## 2. Spring Framework

엔터프라이즈 급 애플리케이션을 만들기 위한 모든 기능을 종합적으로 제공하는 경량화 된 솔루션

JEE(Java Enterprise Edition)가 제공하는 다수의 기능을 지원하고 있기 때문에, JEE를 대체하는 Framework로 자리잡음

SpringFramework는 JEE가 제공하는 다양한 기능을 제공하는 것 뿐만아니라, DI(Dependency Injection)나 AOP(Aspect Oriented Programming)와 같은 기능도 지원

Spring Framework는 자바로 Enterprise Application을 만들 때 포괄적으로 사용하는 Programming 및 Configuration model을 제공해주는 Framework로 Application 수준의 인프라 스트럭쳐를 제공

즉, 개발자가 복잡하고 실수하기 쉬운 Low Level에 신경쓰지 않고 Business Logic 개발에 전념할 수 있도록 해줌

(Enterprise System이란 서버에서 동작하며 기업의 업무를 처리해주는 System)

<br>

## 3. Spring Framework 의 구조

- Spring 삼각형

Enterprise Application 개발 시 복잡함을 해결하는 Spring의 핵심

1. POJO
2. PSA
3. IoC/DI
4. AOP

![image](https://blog.kakaocdn.net/dn/HHfL7/btrgOtlgqm1/5VTWV01aL33izOCpTJxubk/img.png)

<br>

#### POJO (Plain Old Java Object)

> 특정 환경이나 기술에 종속적이지 않은 객체지향 원리에 충실한 자바객체
>
> 테스트하기 용이하며, 객체지향 설계를 자유롭게 적용할 수 있다

#### PSA (Portable Service Abstaction)

> 환경과 세부기술의 변경과 관계없이 일관된 방식으로 기술에 접근할 수 있게 해주는 설계 원식
>
> 트랜잭션 추상화, OXM추상화, 데이터 엑세스의 Exception변환기능..등 기술적인 복잡함은 추상화를 통해 Low Level의 기술구현부분과 기술을 사용하는 인터페이스로 분리
>
> 예를들어 데이터베이스에 관계없이 동일하게 적용 할 수 있는 트랜잭션 처리방식

#### IoC (Inversion of Control) / DI (Dependency Injection)

> DI는 유연하게 확장가능한 객체를 만들어두고 객체 간의 의존관계는 외부에서 다이나믹하게 설정

#### AOP

> 관심사의 분리를 통해서 소프트웨어의 모듈성을 향상
>
> 공통모듈을 여러 코드에 쉽게 적용 가능

<br>

## 4. SpringFramework의 특징

- 경량 컨테이너

  스프링은 자바객체를 담고 있는 컨테이너

  스프링 컨테이너는 이들 자바 객체의 생성과 소멸같은 라이프사이클을 관리

  언제든지 스프링 컨테이너로부터 필요한 객체를 가져와 사용 가능

- DI(Dependency Injection - 의존성 지원) 패턴 지원

  스프링은 설정파일이나, 어노테이션을 통해서 객체 간의 의존관계를 설정 가능

  따라서, 객체는 의존하고 있는 객체를 직접 생성하거나 검색할 필요가 없음

- AOP(Aspect Oriented Programming - 관점 지향 프로그래밍) 지원

  AOP는 문제를 바라보는 관점을 기준으로 프로그래밍하는 기법

  이는 문제를 해결하기 위한 핵심관심 사항과 전체에 적용되는 공통관심 사항을 기준으로 프로그래밍 함으로서 공통모듈을 여러 코드에 쉽게 적용할 수 있도록 함

  스프링은 자체적으로 프록시 기반의 AOP를 지원하므로 트랜잭션이나 로깅, 보안과 같이 어려모듈에서 공통으로 필요로 하지만 실제 모듈의 핵심이 아닌 기능들을 분리하여 각 모듈에 적용이 가능

- POJO (Plain Old Java Object) 지원

  특정 인터페이스를 구현하거나 또는 클래스를 상속하지 않는 일반 자바 객체 지원

  스프링 컨테이너에 저장되는 자바객체는 특정한 인터페이스를 구현하거나, 클래스 상속 없이도 사용이 가능

  일반적인 자바 객체를 칭하기 위한 별칭 개념

- IoC (Inversion of Control - 제어의 반전)

  IoC는 스프링이 갖고 있는 핵심적인 기능

  자바의 객체 생성 및 의존관계에 있어 모든 제어권은 개발자에게 있었으나 Servlet과 EJB가 나타나면서 기존의 제어권이 Servlet Container및 EJB Container에게 넘어갔음

  단, 모든 객체의 제어권이 넘어간 것은 아니고 Servlet, EJB에 대한 제어권을 제외한 나머지 객체 제어권은 개발자들이 담당

  스프링에서도 객체에 대한 생성과 생명주기를 관리할 수 있는 기능을 제공하고 있는데 이런 이유로 [Spring Container] 또는 [IoC Container]라고 부르기도 함

- 스프링은 트랜잭션 처리를 위한 일관된 방법을 제공

  JDBC, JTA 또는 컨테이너가 제공하는 트랜잭션을 사용하든, 설정 파일을 통해 트랜잭션 관련정보를 입력하기 때문에 트랜잭션 구현에 상관 없이 동일한 코드를 여러 환경에서 사용이 가능

- 스프링은 영속성과 관련된 다양한 API를 지원

  스프링은 JDBC를 비롯하여 iBatis, MyBatis, Hibernate, JPA등 DB처리를 위해 널리 사용되는 라이브러리와 연동을 지원하

- 스프링은 다양한 API에 대한 연동을 지원

  스프링은 JMS, 메일, 스케쥴링 등 엔터프라이즈 어플리케이션 개발에 필요한 다양한 API를 설정파일과 어노테이션을 통해서 손쉽게 사용할 수 있도록 지원

<br>

## 5. SpringFramework Module

![image](http://dawoonjeong.com/assets/images/posts/2021/spring_spring-overview.png)

필요할 때 필요한 모듈만 이용할 수 있기 때문에 경량 컨테이너

#### Core Container

- Core, Beans

  - 스프링의 핵심/기본 모듈

  - Bean 컨테이너 관련기능 제공

  - DI, IoC 제공

  - BeanFactory 구현

- Context

  - Beans 모듈에서 기능을 상속

  - 국제화(예 : 리소스 번들 사용)나 JAVA E 가 제공하는 JNDI, EJB, JMX등을 지원

- Expression Language

  - 런타임에 개체 그래프를 쿼리하고 조작 할 수 있는 강력한 표현 언어를 제공

  - 목록 프로젝션 및 선택은 물론 공통 목록 집계도 지원

#### AOP및 Aspects

- AOP : 구현하기 위하여 메소드 인터셉터 및 포인트 컷을 정의

- aspects : 별도의 aspects 모듈은 AspectJ와의 통합을 제공

- Instrumentation : 특정 애플리케이션 서버에서 사용할 클래스 계측 지원 및 클래스 로더 구현을 제공

  - spring-instrument-tomcat 모듈에는 Spring의 Tomcat 용 계측 에이전트가 포함

#### Messaging

- 메시징 기반 애플리케이션의 기반 역할

- 메시지를 메소드에 매핑하기위한 주석 세트를 포함

#### Data Access / Integration

- JDBC : JDBC 추상계층 기능을 제공하는 모듈. JDBC에 의한 데이터베이스 액세스를 지원

- ORM : Object Relational Mapping 기능 제공하는 모듈. ORM API(JPA, Hibernate, iBatis, JDO)를 지원

- OXM : JAXB, Castor, XMLBeans, JiBX, XStream에 대한 객체/XML매핑 구현을 지원하는 추상화 계층을 제공

- JMS : Java Messaging Service 모듈은 메시지를 생산하고 소비하는 기능을 포함(Spring Framework 4.
- 1부터 spring-messaging모듈 과의 통합을 제공)

- Transaction : 특수 인터페이스를 구현하는 클래스와 모든 POJO의 클래스에 대한 프로그래밍 및 선언적 트랜잭션 관리를 지원

#### Web

- Websocket

- Servlet : 웹 애플리케이션을 위한 MVC 프레임 워크 기능 제공

  - 웹 애플리케이션을위한 REST 웹 서비스의 구현

  - Spring의 MVC 프레임 워크는 도메인 모델 코드와 웹 양식 사이의 명확한 분리를 제공

  - Spring Framework의 다른 모든 기능과 통합

- Web : 웹 애플리케이션 이용에 편리한 기능을 제공

  - 멀티 파트 파일 업로드 기능 및 Servlet 리스너 및 웹 지향 애플리케이션 컨텍스트를 사용하는 IoC

- 컨테이너 초기화와 같은 기본적인 웹 지향 통합 기능을 제공

  - HTTP 클라이언트와 Spring의 원격 지원의 웹 관련 부분도 포함

- Portlet : 포틀릿 환경 미러 서블릿 기반의 기능에 사용될 MVC 구현 제공

#### Test

- JUnit , TestNG를 사용한 테스트 지원

- mock 객체 지원

<br>

## 6. SpringFramework 개발환경

JDK설치

Tomcat설치

Database 설치

(Spring Framework Document)

(Spring Framework API)

Eclipse 설치 후 Spring Toll Suite Plug in 추가

이클립스 실행 후 Help → Eclipse Marketpalce → STS검색

아니면 Spring Tool Suite (sts, AllInOne version) 설치
