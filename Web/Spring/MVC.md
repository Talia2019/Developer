# MVC Pattern

<br>

<br>

1. Model : 데이터 처리

> Dao : 데이터베이스 관련 작업을 하는 객체
>
> Service : 비즈니스 관련 로직을 수행

    - 어플리케이션의 상태 캡슐화

    - 상태 쿼리에 대한 응답

    - 어플리케이션의 기능 표현

    - 변경을 view에 통지

2. View : 화면단에서 보이는 부분

> JSP

    - 모델을 화면에 시각적으로 표현

    - 모델에게 업데이트 요청

    - 사용자의 입력을 컨트롤러에 전달

    - 컨트롤러가 view를 선택하도록 허용

3. Controller → 웹 관련 일을 제어

> 요청에따라 어떤 메소드를 호출해야하는지
>
> 요청에 대한 응답페이지로 이동등

    - 어플리케이션의 행위 정의

    - 사용자 액션을 모델 업데이트와 mapping

    - 응답에 대한 view 선택

<br>

## 1. Web Applications Architecture

JSP를 이용하여 구성할 수 있는 Web Applications Architecture 는 크게 model1과 model2로 나뉨

JSP가 client요청에 대한 logic처리와 response page(view)에 대한 처리를 모두 하느냐, 아니면 response page(view)에 대한 처리만 하는지가 가장 큰 차이점

Model2구조는 MVC Pattern을 web 개발에 도입한 구조를 말함

<br>

## 2. Model 1 구조

model1 은 view와 logic을 JSP페이지 하나에서 처리하는 구조

client로부터 요청이 들어오게 되면 JSP는 java beans나 별도의 service class를 이용하여 작업을 처리, 결과를 client에 출력

JSP = View + Controller

Java Beans = Model

간단한 page를 구성하기 위해 과거에 가장 많이 사용되었던 architecture

| 장점                                                                             | 단점                                                                                                                                                                                                                                                       |
| -------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 구조가 단순하며 직관적이기에 배우기 쉬움<br>개발시간이 비교적 짧아 개발비용 감소 | 출력을 위한 View(html)코드와 로직처리를 위한 java코드가 섞여있어 JSP가 복잡해짐<br>JSP에 BackEnd(Developer)와 FrontEnd(Designer)가 혼재되기에 분업이 힘듦<br>규모가 커지면 코드가 복잡해지므로 유지보수가 어려움<br> 확장성(신기술 도입, framework)이 나쁨 |

<br>

## 3. Model 2구조

<br>

> 클라이언트는 브라우저를 지님(마크업언어, CSS, JS) : 데이터를 발생시킴

→

> 서버에 요청(request)

→

> 요청을 Servlet이 받음

→

> 서블릿에선
>
> 1. 데이터를 얻고(data get)
>
> 2. 로직을 부름(logic call - 서블렛에서 서비스를 호출, 서비스에선 Dao를 호출. Dao에서 SQL을 실행하여 결과 Entity를 받음)
>
> 3. 2의 결과에 따른 응답페이지로 이동

→

> 이 응답페이지를 응답(response)

<br>

데이터 값을 저장/전달하는 객체를 Dto, db에서 가져온값을 화면에 뿌리기위한 값으로 만들어놓은 객체를 Vo라고 하는 개념적 차이가 있음

Dto는 값을 가지고있는 객체라해서 entity라 부름

서블렛은 일을 제어하고 호출시키기만 하기에 Servlet을 Controller라고함

service, dao같이 데이터를 처리하는 애들을 Model이라함

화면에 보여주는 JSP를 View라 함

![image](https://t1.daumcdn.net/cfile/tistory/2357163B58A6CB201E)

<br>

- Model2 구조

model2는 모든 처리를 JSP페이지에서 하는것이 아니라, client요청에대한 처리는 servlet이, logic처리는 java class(Service, Dao, ..), client에게 출력하는 response page를 JSP가 담당

model2구조는 MVC pattern을 웹개발에 도입한 구조이며 완전히 같은 형태를 보임

| Model2                          | MVC        | 설명                                                                                                                                                                              |
| ------------------------------- | ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Service, Dao <br> or Java Beans | Model      | Login(Business & DB) 을 처리하는 모든 것<br>controller로부터 넘어온 data를 이용해 수행하고, 그 결과를 다시 controller에 리턴                                                      |
| JSP                             | View       | 모든 화면 처리를 담당<br>Client의 요청에 대한 결과 뿐 아니라 controller에 요청을 보내는 화면단도 jsp에서 처리<br>Logic처리를 위한 java는 사라지고 출력을 위한 코드만 존재         |
| Servlet                         | Controller | Client의 요청을 분석하여 Login처리를 위한 Model단을 호출<br>return 받은 data를 필요에 따라 requestm session등에 저장하고 redirect 또는 forward 방식으로 view page를 이용하여 출력 |

<br>

- 장단점

Model2는 Model1의 단점을 보완하기 위해 만들어 졌으나, 다루기 어렵다는 단점이 있음

| 장점                                                                                                    | 단점                               |
| ------------------------------------------------------------------------------------------------------- | ---------------------------------- |
| 출력을 위한 view코드와 로직처리를 위한 java코드가 분리되었기에 JSP는 Model1에 비해 코드가 복잡하지 않음 | 구조가 복잡하여 초기 진입이 어려움 |
| 화면단과 Logic단이 분리되었기에 분업이 용이                                                             | 개발시간의 증가로 개발비용 증가    |
| 기능에 따라 code가 분리되었기에 유지보수가 쉬움                                                         |                                    |
| 확장성이 뛰어남                                                                                         |                                    |

Dao나 Service는 객체마다 하나씩있을필요가 없으므로 싱글톤으로 구현

서비스에서는 쿠키나 세션, 리퀘스트등을 못써야함. 웹에 관련된건 무조건 컨트롤러에서 해야함

<br>

## 4. DataSource

프로젝트는 META-INF 폴더를 실행한 후 web.xml이 실행됨

META-INF 폴더 내부의 context.xml 에 DB Pool에 DB연결들을 넣어놓을 수 있음

DataSourece는 서버와 연결정보를 갖고있는 객체라고 할 수 있음

```js
// context.xml

<?xml version="1.0" encoding="UTF-8"?>
<Context>
<Resource name="jdbc/test" auth="Container"
type="javax.sql.DataSource"
factory="org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory"
driverClassName="com.mysql.cj.jdbc.Driver"
url="jdbc:mysql://127.0.0.1:3306/testdb?serverTimezone=UTC" username="user" password="user"
maxTotal="100" maxIdle="30" maxWaitMillis="10000"
removeAbandoned="true" removeAbandonedTimeout="60"
logAbandoned="true" />
<WatchedResource>WEB-INF/web.xml</WatchedResource>
</Context>
```

```java
//DBUtil

public Connection getConnection() throws SQLException{
  try{
    Context context = new InitialContext();
    Context root = (Context)context.lookup("java:comp/env");
    DataSource ds = (DataSource)root.lookup("jdbc/test");
    return ds.getConnection();
  } catch(NamingException e){
    e.printStackTrace();
  }
  return null;
}

```
