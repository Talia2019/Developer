# DI (Dependency Injection, 의존성 주입)

<br>

<br>

## 1. Bean 생성범위

<br>

#### 싱글톤 빈 (Singleton Bean)

스프링 빈은 기본적으로 싱글톤으로 만들어짐

그러므로 컨테이너가 제공하는 모든 빈의 인스턴스는 항상 동일

컨테이너가 항상 새로운 인스턴스를 반환하게 만들고 싶을 경우 scope를 prototype으로 설정

<br>

#### 빈의 생성 범위 지정

| 범위      | 설명                                                 |
| --------- | ---------------------------------------------------- |
| singleton | 스프링 컨테이너당 하나의 인스턴스 빈만 생성(default) |
| prototype | 컨테이너에 빈을 요청할 때마다 새로운 인스턴스 생성   |
| request   | HTTP Request 별로 새로운 인스턴스 생성               |
| session   | HTTP Session 별로 새로운 인스턴스 생성               |

request와 session은 웹에서만 사용 가능

<br>

## 2. 스프링 빈 설정

<br>

Bean 설정은 XML이나 java로 설정하는 방법이 있고, Annotation은 xml이나 java에 할 수 있음

<br>

#### 스프링 빈 설정 < XML >

XML 문서 형태로 빈의 설정 메타 정보를 기술

단순하며 사용하기 쉬움

직관적이라 가장 많이 사용하는 방식

\<bean> 태그를 통해 세밀한 제어 가능

최근은 xml보단 annotation써서 많이 함

<br>

#### 스프링 빈 설정 < Annotaion >

어플리케이션의 규모가 커지고 빈의 개수가 많아질 수록 XML 파일을 관리하는 것이 번거로움

빈으로 사용될 클래스에 특별한 annotation을 부여해주면 자동으로 빈 등록 가능

"오브젝트 빈 스캐너"로 "빈 스캐닝"을 통해 자동 등록

빈 스캐너는 기본적으로 클래스 이름을 빈의 아이디로 사용 (정확히는 클래스 이름의 첫 글자만 소문자로 바꾼것을 사용)

Annotation으로 빈을 설정할 경우 반드시 component-scan을 설정해야함 (프로젝트에서 어떤 패키지를 스캔할지를 베이스패키지에 선언해줘야함. 모든 패키지를 찾는게 아니라 설정한곳만 찾게됨)

<br>

#### Stereotype annotation 종류

빈 자동등록에 사용할 수 있는 annotation

빈 자동인식을 위한 annotation이 여러가지인 이유

> 계층별로 빈의 특성이나 종류를 구분
>
> AOP Pointcut표현식을 사용하면 특정 annotaion이 달린 클래스만 설정 가능
>
> 특정 계층의 빈에 부가기능을 부여

| Stereotype  | 적용 대상                                                                                                                        |
| ----------- | -------------------------------------------------------------------------------------------------------------------------------- |
| @Repository | Data Access Layer의 DAO 또는 Repository 클래스에 사용<br>DataAccessException 자동변환과 같은 AOP의 적용대상을 선정하기 위해 사용 |
| @Service    | Service Layer의 클래스에 사용                                                                                                    |
| @Controller | Presentation Layer의 MVC Controller에 사용 <br>스프링 웹 서블릿에 의해 웹 요청을 처리하는 컨트롤러 빈으로 선정                   |
| @Component  | Layer구분을 적용하기 어려운 일반적인 경우 설정                                                                                   |

컨트롤러엔 컨트롤러, 서비스엔 서비스, Dao엔 레포지토리등으로 하고 나머지 컴포넌트

<br>

## 3. Dependency Injection

객체 간의 의존관계를 자신이 아닌 `외부의 조립기`가 수행

제어의 역행 (Inversion of Control, IoC) 이라는 의미로 사용

DI를 통해 시스템에 있는 각 객체를 조정하는 외부개체가 객체들에게 생성시에 의존관계를 줌

<br>

- 느슨한 결합(loose coupling) 의 주요 강점

객체는 인터페이스에 의한 의존 관계만을 알고 있으며, 이 의존 관계는 구현 클래스에 대한 차이를 모르는채 서로 다른 구현으로 대체가 가능

<br>

- 스프링의 DI 지원

Spring Container가 DI 조립기를 제공

스프링 설정 파일을 통하여 객체 간의 의존관계를 설정

Spring Container가 제공하는 API를 이용해 객체를 사용

<br>

## 4. Spring 설정 < xml >

<br>

#### XML 문서 이용

Application에서 사용할 Spring 자원들을 설정하는 파일

Spring Container는 설정파일에 설정된 내용을 읽어 Application에서 필요한 기능들을 제공

Root tag 는 <beans> (루트태그 : 가장 상위 태그)

파일명은 상관없음 (예 applicationContext.xml)

<br>

- 기본설정 : 빈 객체 생성 및 주입

주입 할 객체를 설정파일에 설정

\<bean> : 스프링 컨테이너가 관리할 Bean 객체를 설정

- 기본 속성

name : 주입받을 곳에서 호출 할 이름 설정

id : 주입받을 곳에서 호출 할 이름 설정 (유일 값)

class : 주입할 객체의 클래스

factory-method : Singleton패턴으로 작성된 객체의 factory 메소드 호출

- 기본 설정 (빈 객체 얻기)

설정 파일에 설정한 bean을 Container가 제공하는 주입기 역할의 api를 통해 주입받음

<br>

## 5. 스프링 빈 의존 관계 설정 < xml >

Constructor(생성자)와 Property(전역변수 - Getter/Setter 함수명에서 Get/Set제외하고 대문자 소문자로 변경한 이름)을 이용하는 두가지 방법이 있음

객체를 가져갈땐 ref, 값을 가져갈땐 value

생성자보단 프로퍼티많이씀

<br>

### Constructor 이용

객체 또는 값을 생성자를 통해 주입 받음

\<constructor-arg> : \<bean>의 하위태그로 설정한 bean 객체 또는 값을 생성자를 통해 주입하도록 설정

설정 방법 : \<ref\>, \<value> 와 같은 하위태그를 이용하여 설정하거나 또는 속성을 이용하여 설정

1. 하위태그 이용

a. 객체 주입 시 : \<ref bean = "bean name"/>

b. 문자열(String), primitive data 주입 시 : \<value>값\</value>

type 속성 : 값은 기본적으로 String 처리. 값의 타입을 명시해야 하는 경우 사용

예) \<value type="int"> 10 \</value>

2.  속성 이용

a. 객체 주입 시 : \<constructor-arg ref="bean name" />

b. 문자열 (String), primitive data 주입 시 : \<constructor-arg value="값" />

<br>

### Property 이용

property를 통해 객체 또는 값을 주입 받음 - setter method

주의 : setter를 통해서는 하나의 값만 받을 수 있음

\<property\> : \<bean> 의 하위태그로 설정한 bean 객체 또는 값을 property를 통해 주입하도록 설정

설정 방법 : \<ref\>, \<value> 와 같은 하위태그를 이용하여 설정하거나 속성을 이용해 설정

1. 하위태그 이용

a. 객체 주입 시 : \<ref bean = "bean name" />

b. 문자열(String), primitive data 주입 시 : \<value>값\</value>

2. 속성 이용 : name - 값을 주입할 property 이름 (setter의 이름)

a. 객체 주입 시 : \<property name = "propertyname" ref="bean name" />

b. 문자열 (String), primitive data 주입 시 : \<property name="propertyname" value="값"/>

3. xml namespace를 이용

<br>

### Collection 계열 주입

\<constructor-arg> 또는 \<property> 의 하위태그로 Collection 값을 설정하는 태그를 이용하여 값 주입 설정

| 태그     | Collection 종류      | 설명                                                  | 사용태그         |
| -------- | -------------------- | ----------------------------------------------------- | ---------------- |
| \<lsit>  | java.util.List       | List 계열 컬렉션 값 목록 전달                         | \<ref>, \<value> |
| \<set>   | java.util.Set        | Set 계열 컬렉션 값 목록 전달                          | \<value>, \<ref> |
| \<map>   | java.util.Map        | Map 계열 컬렉션에서 key-value의 값 목록 전달          | \<entry>         |
| \<props> | java.util.Properties | Properties에 key(String)-value(String)의 값 목록 전달 | \<prop>          |

<br>

## 6. 스프링 빈 의존 관계 설정 < annotation >

<br>

Annotation : 멤버변수에 직접 정의 하는 경우 setter method를 만들지 않아도 됨

| annotation | 설명                                                                                                                                                             |
| ---------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| @Resource  | Spring 2.5부터 지원<br>멤버변수, setter method에 사용 가능<br>타입에 맞춰서 연결                                                                                 |
| @Autowired | Spring 2.5부터 지원<br>Spring에서만 사용 가능<br>Required 속성을 통해 DI 여부 조정<br>멤버변수, setter, constructor, 일반 method 사용 가능<br>타입에 맞춰서 연결 |
| @Inject    | Spring 3부터 지원<br>Framework에 종속적이지 않음<br>Javax.inject-x.x.x.jar필요<br>멤버변수, setter, constructor, 일반 method에 사용 가능<br>이름으로 연결        |

특정 Bean의 기능 수행을 위해 다른 Bean을 참조해야 하는 경우 사용

<br>

## 7. 기타 설정

<br>

#### 빈 객체의 생성단위

BeanFactory를 통해 Bean요청 시 객체생성의 범위(단위)를 설정

\<bean>의 scope속성을 이용해 설정

| scope     | 설명                                                 |
| --------- | ---------------------------------------------------- |
| singleton | 스프링 컨테이너당 하나의 인스턴스 빈만 생성(default) |
| prototype | 컨테이너에 빈을 요청할 때마다 새로운 인스턴스 생성   |
| request   | HTTP Request별로 새로운 인스턴스 생성                |
| session   | HTTP Session 별로 새로운 인스턴스 생성               |

request, session은 WebApplicationContext에서만 적용 가능

<br>

#### Factory Method로부터 빈(bean)생성

getBean()으로 호출시 private 생성자도 호출하여 객체를 생성

그러므로 factory method만 호출해야 객체를 얻을 수 있는 것은 아님

<br>

## 8. 스프링 빈의 생명 주기(Life Cycle)

`빈 생성` → `의존성 주입` → `init-method 실행` → `빈 사용` → `destroy-method 실행` → `빈 소멸`
