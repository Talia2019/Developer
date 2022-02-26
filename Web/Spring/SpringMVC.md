# Spring MVC

<br>

<br>

## 1. Spring MVC

#### Model : 데이터 처리

    - 어플리케이션의 상태 캡슐화

    - 상태 쿼리에 대한 응답

    - 어플리케이션의 기능 표현

    - 변경을 view에 통지

#### View : 화면단에서 보이는 부분

    - 모델을 화면에 시각적으로 표현

    - 모델에게 업데이트 요청

    - 사용자의 입력을 컨트롤러에 전달

    - 컨트롤러가 view를 선택하도록 허용

#### Controller : 웹 관련 일을 제어

    - 어플리케이션의 행위 정의

    - 사용자 액션을 모델 업데이트와 mapping

    - 응답에 대한 view 선택

<br>

## 2. Spring MVC 구성요소

<br>

#### 1. DispatcherServlet (Front Controller)

모든 클라이언트의 요청을 전달받음

Controller에게 클라이언트의 요청을 전달하고, Controller가 리턴한 결과값을 View에 전달하여 알맞은 응답을 생성

#### 2. HandlerMapping

클라이언트의 요청 URL을 어떤 Controller가 처리할지 결정

URL과 요청정보를 기준으로 어떤 핸들러 객체를 사용할지 결정하는 객체이며, DispatcherServlet은 하나이상의 핸들러 매핑을 가질 수 있음

#### 3. Controller

클라이언트의 요청을 처리한 뒤, Model을 호출하고 그 결과를 DispatcherServlet에 알림

#### 4. ModelAndView

Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체

#### 5. ViewResolver

Controller가 리턴한 뷰 이름을 기반으로 Controller의 처리 결과를 보여줄 View 결정

#### 6. View

Controller의 처리결과를 보여줄 응답화면 생성

<br>

![image](https://media.vlpt.us/images/xordbs/post/4ca8190c-a34e-4370-9b6c-c1cecdfb3fd7/springmvc1.PNG)

##### 실행 순서

1. DispatcherServlet이 요청을 수신

   1. 단일 Front Controller Servlet
   2. 요청을 수신하여 처리를 다른 컴포넌트에 위임
   3. 어느 Controller에 요청을 전송할지 결정

2. DispatcherServlet 은 Handler Mapping에 어느 Controller를 사용할 것인지 문의

   1. URL과 Mapping

3. DispatcherServlet 은 요청을 Contoller에게 전송하고 Controller는 요청을 처리한 후 결과 리턴

   1. Business Logic 수행 후 결과 정보 (Model)가 생성되어 JSP와 같은 view에서 사용됨

4. ModelAndView Object에 수행결과가 포함되어 DispatcherServlet 에 리턴

5. ModelAndView는 실제 JSP정보를 갖고 있지 않으며, ViewResolver가 논리적 이름을 실제 JSP 이름으로 변환

6. View는 결과정보를 사용하여 화면을 표현함

<br>

## 3. Spring MVC 구현

<br>

#### Spring MVC를 이용한 Application 구현 Step

1. web.xml에 DispatcherServlet 등록 및 Spring 설정 파일 등록

2. 설정 파일에 HandlerMapping 설정

3. Controller구현 및 Context 설정 파일 (servlet-context.xml) 에 등록

4. Controller와 JSP의 연결을 위해 View Resolver설정

5. JSP 코드 작성

(가장 처음 META-INF 가 있으면 읽힌 후 web.xml 읽힘)

java 관련 코드는 src/main/java

web 관련 코드는 src → main → webapp

<br>

#### Controller 작성

좋은 디자인은 컨트롤러가 많은 일을 하지 않고 Service에 처리를 위임

![image](../img/controller.png)

web.xml 에서 프로젝트 관련 설정들을 함

#### web.xml - DispatcherServlet 설정

\<init-param>을 설정하지 않으면 "\<servlet-name>-servlet.xml" file에서 applicationContext의 정보를 load.

Spring Container는 설정파일의 내용을 읽고 ApplicationContext객체를 생성.

\<url-pattern>은 DispatcherServlet 이 처리하는 URL Mapping pattern을 정의.

Servlet이므로 1개 이상의 DispatcherServlet 설정 가능

\<load-on-startup>1\</load-on-startup> 설정 시 WAS startup시 초기화 작업 진행

DispatcherServlet을 여러개 설정 가능

각 DispatcherServlet마다 각각의 ApplicationContext 생성

<br>

#### web.xml - 최상위 Root ContextLoader설정

Context 설정 파일들을 로드하기 위해 web.xml 파일에 리스너 설정 (ContextLoaderListener)
리스너는 변경사항 있으면 다시 로드하는 등의 감시자라 생각하면 됨

리스너 설정이 되면 /WEB-INF/spring/root-context.xml 파일을 읽어서 공통적으로 사용되는 최상위 Context를 생성

그 외의 다른 컨텍스트 파일들을 최상의 어플리케이션 컨텍스트에 로드하기 위해서는 classpath:

<br>

#### Application Context 분리

어플리케이션 레이어에 따라 어플리케이션 컨텍스트 분리

| Layer             | 설정파일           |
| ----------------- | ------------------ |
| Security Layer    | board-security.xml |
| Web Layer         | board-servlet.xml  |
| Service Layer     | board-service.xml  |
| Persistence Layer | board-dao.xml      |

<br>

#### Controller Class 작성 (HomeController.java)

<br>

#### Context 설정 파일에 Controller 등록 (servlet-context.xml)

bean 또는 component scan 사용

<br>

#### Controller와 response page연결을 위한 ViewResolver 설정 (servlet-context.xml)

<br>

#### JSP (index.jsp)

<br>

## 4. Controller

컨트롤러 상단에 @Controller Annotation 설정

@RequestMapping("/reboard") 이런식으로 있으면 /reboard로 시작하는건 다 이 컨트롤러가 담당한다는 의미

클래스 내부에서는 /reboard/board 처럼 다 안쓰고 /board이런식으로만 추가매핑하면됨 → 여러 컨트롤러로 처리

<br>

#### @Controller와 @RequestMapping 선언

method 단위의 mapping이 가능

DefaultAnnotationHandlerMapping과 AnnotationHandlerAdapter를 사용

spring 3.0부터는 기본 설정이므로 별도의 추가 없이 사용 가능

Controller Class는 Client의 요청을 처리

@Controller 선언

    Class 타입에 적용

    Spring 3.0부터는 @Controller 사용을 권장

<br>

#### Controller Class를 \<bean>에 등록

```xml
<!-- servlet-context.xml에 설정 -->
<!-- 밑의 자동스캔을 이용하는게 더 편함 -->

<beans:bean id="reboardController" class="com.test.board.controller.ReboardContoroller">
    <beans:property name="reboardService" ref="reboardService"/>
</beans:bean>
```

#### Controller Class 자동 스캔

context:component-scan 선언

base-package에 설정된 package내의 class 중 @Controller annotation이 적용된 클래스는 자동 스캔 대상이 됨

```xml
<context:component-scan base-package="com.test.board.controller"/>
```

<br>

#### @RequestMapping 선언

요청 URL mapping 정보를 설정

클래스 타입과 메소드에 설정 가능

#### Controller method의 HTTP 에 한정

같은 URL 요청에 대하여 HTTP method(GET, POST..) 에 따라 서로 다른 메소드를 mapping 할 수 있음

하나밖에 없으면 알아서 찾아주고 두개 이상이면 구분

```java
@RequestMapping(value="/index.do" method=RequestMethod.GET)

혹은

@GetMapping("/index.do")
```

#### Controller method의 parameter type

Controller method의 parameter로 다양한 Object 를 받을 수 있음

| Parameter Type                                           | 설명                                                                                                                                    |
| -------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| HttpServletRequest<br>HttpServletResponse<br>HttpSession | 필요시 Servlet API사용 가능                                                                                                             |
| Java.util.Locale                                         | 현재 요청에 대한 Locale                                                                                                                 |
| InputStream, Reader                                      | 요청 컨텐츠에 직접 접근할 때 사용                                                                                                       |
| OutputStream, Writer                                     | 응답 컨텐츠를 사용할 때 사용                                                                                                            |
| @PathVaraible annotation 적용 파라미터                   | URI 템플릿 변수에 접근할 때 사용                                                                                                        |
| @RequestParm annotation 적용 파라미터                    | HTTP 요청 파라미터를 매핑                                                                                                               |
| @ReqeustHeader annotation 적용 파라미터                  | HTTP 요청 헤더를 매핑                                                                                                                   |
| @cookieValue annotation 적용 파라미터                    | HTTP 쿠키 매핑                                                                                                                          |
| @RequestBody annotation 적용 파라미터                    | HTTP 요청의 몸체 내용에 접근할 때 사용                                                                                                  |
| Map, Model, ModelMap                                     | view에 전달할 model data를 설정할 때 사용                                                                                               |
| 커맨드 객체                                              | Http 요청 parameter를 저장한 객체<br>기본적으로 클래스 이름을 모델명으로 사용<br>@ModelAttribute annotation 설정으로 모델명을 설정 가능 |
| Errors, BindingResult                                    | HTTP 요청 파라미ㅏ터를 커맨드 객체에 저장한 결과<br>커맨드 객체를 위한 파라미터 바로 다음에 위치                                        |
| SessionStatus                                            | 폼 처리를 완료 했음을 처리하기 위해 사용<br>@SessionAttributes annotation을 명시한 session속성을 제거하도록 이벤트를 발생시킴           |

<br>

#### @RequestMapping annotation 을 이용한 parameter mapping

@RequestParam은 생략 가능하나 변수명을 맞춰줘야함

```java
// [URL] http://localhost/web/index.do?name=hello&age=30

@GetMapping("/index.do")
public String home(@RequestParam(value="name", required=false) String name, @RequestParam(value="age", defaultValue="25") int age, Model model){
    model.addAttribute("msg", name+" "+age);
    return "index";
}
```

#### HTML form 과 Command Object (DTO, VO.. )

SpringMVC 는 form에 입력한 data를 JavaBean 객체를 이용해서 전송할 수 있음

form에서의 name 속성과 Dto의 property가 같다면 스프링에서 Dto에 자동으로 매핑해줌

```jsp
<form method="POST" action="${root}/board/write.do">
제목: <input type="text" name="subject">
내용: <textarea name="context"/>
<input type="submit" value="write">
```

```java
//controller class
@Controller
@RequestMapping("/board")
public class BoardController{
    @PostMapping("/write.do")
    public String write(BoardDto boardDto){
        return "board/writeok";
    }
}

//Dto class
public class BoardDto{
    private String subject;
    private String content;
    public void setSubject(String subject){ this.subject = subject; }
    public void setContent(String content){ this.content = content; }
}
```

#### Command 객체를 List로 받기

배열도 알아서 세팅됨

```jsp
<form method="POST" action="${root}/board/write.do">
    <input type="text" name="productList[0].pnum">
</form>
```

```java
//controller class
@Controller
@RequestMapping("/board")
public class BoardController{
    @PostMapping("/write.do")
    public String write(BoardDto boardDto){
        return "board/writeok";
    }
}

//Dto class
public class Products{
    private List<Product> productList
    public void setProductList(List<Product> productList){ this.productList = productList; }
}

public class Product{
    private int pnum;
    public setPum(int pnum){ this.pnum = pnum; }
}
```

<br>

#### View에서 Command 객체에 접근

Command 객체는 자동으로 반환되는 Model에 추가됨 (즉, 인자값으로 넘어온 데이터들이 자동으로 응답데이터에도 포함된다는 것)

Controller의 @RequestMapping annotation method에서 전달받은 Command 객체에 접근

혹은 @ModelAttribute를 사용해서 View에서 사용할 Command 객체의 이름을 변경 가능

```java
@Controller
@RequestMapping("/board")
public class BoardController{
    @PostMapping("/write.do")
    public String write (@ModelAttribute("article") BoardDto boardDto){
        return "board/writeok";
    }
}

```

```jsp
<!-- writeok.jsp -->
${article.subject}
${article.content}

@ModelAttribute를 사용하지 않는다면 request에 저장되는 command 객체의 이름은 BoardDto의 첫글자를 소문자로 변경한 boardDto
```

<br>

#### @CookieValue annotation을 이용한 Cookie mapping

```java
@Controller
public class HomeController{
    public String home (@CookieValue("author") String authorValue){
        return "ok";
    }
}
```

<br>

#### @RequestHeader annotation을 이용한 header mapping

```java
@Controller
public class HomeController{
    public String home (@RequestHeader("Accept-Language") String headerLanguage){
        return "ok";
    }
}
```

<br>

#### @RequestBody parameter type

Ajax상에서 parameter가 아닌 json등으로 넘어오는경우 사용됨 (REST API)

HTTP 요청 Body가 그대로 객체에 전달됨

AnnotationMethodHandlerAdapter에는 HttpMessageConverter타입의 메세지 변환기가 기본으로 여러개 등록되어 있음

@RequestBody 가 붙은 parameter가 있으면 해당 미디어 타입을 확인 후 처리 가능한 변환기(Converter)가 자동으로 객체로 변환시켜 줌

주로 @ResponseBody와 함께 사용됨

<br>

#### Servlet API 사용

HttpSession의 생성을 직접 제어해야 하는 경우

Controller가 Cookie를 생성해야 하는 경우

Servlet API를 선호하는 경우

```java
@Controller
public class HomeController{
    public String home (HttpServletRequest request, HttpServletResponse response){
        return "ok";
    }
}
```

#### Controller Class에서 method 의 return type 종류

ModelAndView 나 String을 가장 많이 사용

| Return Type                   | 설명                                                                                                                                                                |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ModelAndView                  | model 정보 및 view 정보를 담고 있는 ModelAndView 객체                                                                                                               |
| Model                         | view에 전달할 객체 정보를 담고 있는 Model반환<br>이때 view이름은 요청 URL로부터 결정됨                                                                              |
| Map                           | view에 전달할 객체 정보를 담고 있는 Map반환<br>이때 view이름은 요청 URL로부터 결정됨                                                                                |
| String                        | view의 이름을 반환                                                                                                                                                  |
| View                          | view객체를 직접 리턴, 해당 View객체를 이용해서 view생성                                                                                                             |
| void                          | method가 ServletResponse나 HttpServletResponse타입의 parameter를 갖는 경우 method가 직접 응답을 처리한다 가정<br>그렇지 않을 경우 요청 URL로부터 결정된 view를 보임 |
| @ResponseBody Annotation 적용 | method에서 @ResponseBody annotation이 적용된 경우 리턴 객체를 HTTP응답으로 전송<br>HttpMessageConverter를 이용해 객체를 HTTP응답 스트림으로 변환                    |

<br>

## 5. View

<br>

#### View 지정

Controller에서는 처리 결과를 보여 줄 View 이름이나 객체를 리턴하고, DispatcherServlet은 View이름이나 View 객체를 이용하여 View 를 생성

명시적 지정

자동 지정

<br>

#### ViewResolver : 논리적 view와 실제 JSP 파일 mapping

servlet-context.xml

#### View 이름 명시적 지정

ModelAndView와 String 리턴 타입

```java
@Controller
public class HomeController{
    public ModelAndView home (){
        ModelAndView mav = new ModelAndView("hello");
        return mav;
    }
}

//또는

@Controller
public class HomeController{
    public ModelAndView home (){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }
}

//또는

@Controller
public class HomeController{
    public String home (){
        return "hello";
        //딱히 모델에 담을게 없는 경우
        //스트링 리턴하면 자동으로 모델의 이름이 됨
    }
}

```

<br>

#### View 자동 지정

RequestToViewNameTranslator를 이용하여 URL로부터 view이름을 결정

자동지정 유형

> return type이 Model이나 Map인 경우
>
> return type이 void이면서 ServletResponse나 HttpServletResponse 타입의 parameter가 없는 경우

return Map으로 하는경우 이 Map이 Model이 됨, 이때 View의 이름은 RequestMapping()안의 값이 view의 이름이 됨

```java
@Controller
public class HomeController{

    //hello가 view이름이 됨
    @RequestMapping("/hello.do")
    public Map<String, Object> home (){
        Map<String, Object> model = new HashMap<String, Object>();
        return model;
    }
}
```

#### redirect view

View 이름에 "redirect:"접두어를 붙이면, 지정한 페이지로 redirect 됨 (Spring MVC는 forward가 default)

```java
@Controller
public class BoardController{

    @Autowired
    private BoardService boardService;

    @RequestMapping("/hello.do")
    public String write (BoardDto boardDto){
        boardService.writeBoard(boardDto);
        return "redirect:board/list.html?pg=1";
    }
}
```

<br>

## 6. Model

<br>

## 6-1. Model 생성

View에 전달하는 데이터

> @RequestMapping annotation이 적용된 method의 Map, Model, ModelMap
>
> @RequestMapping method가 return하는 ModelAndView
>
> @ModelAttribute annotation이 적용된 method가 return한 객체

#### Map, Model, ModelMap을 통한 설정

method의 argument로 받는 방식

```java
@Controller
public class HomeController{
    @RequestMapping("/hello.do")
    public String home (Map model){
        model.put("msg", "hello");
        return "hello";
    }
}

//또는
@Controller
public class HomeController{
    @RequestMapping("/hello.do")
    public String home (ModelMap model){
        model.addAttribute("msg", "hello");
        return "hello";
    }
}

//또는
@Controller
public class HomeController{
    @RequestMapping("/hello.do")
    public String home (Model model){
        model.addAttribute("msg", "hello");
        return "hello";
    }
}
```

<br>

#### Model Interface 주요 method

- Model addAttribute(String name, Object value);
- Model addAttribute(Object value);
- Model addAllAttributes(Collection<?> values);
- Model addAllAttributes(Map\<String, ?> attributes);
- Model mergeAttributes(Map\<String, ?> attributes);
- boolean containsAttribute(String name);

<br>

#### ModelAndView를 통한 Model 설정

Controller에서 처리결과를 보여줄 view와 view에 전달할 값(model)을 저장하는 용도로 사용

- setViewName(String viewname);
- addObject(String name, Object value);

<br>

#### @ModelAttribute annotation 을 이용한 model data 처리

@RequestMapping annotation이 적용되지 않은 별도 method로 모델이 추가될 객체를 생성

```java
@Controller
public class HomeController{
    @ModelAttribute("modelAttrMsg")
    public String maMsg(){
        return "Message";
    }

    @RequestMapping("/hello.do")
    public String home (Model model){
        model.addAttribute("msg", "hello");
        return "hello";
    }
}
```

```jsp
<!-- hello.jsp -->
${msg} <!-- home에서 전달됨 -->
${modelAttrMessage} <!-- maMsg에서 전달됨 -->
```

<br>

## 6-2. 요청 URL 매칭

<br>

#### 전체 경로와 Servlet 기반 경로 매칭

DispatcherServlet은 DefaultAnnotationHandlerMapping Class를 기본으로 HandlerMapping 구현체로 사용

Default 로 Context내의 경로가 아닌 Servlet 경로를 제외한 나머지 경로에 대해 mapping

```xml
<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>*.html</url-pattern>
    <url-pattern>/product/*</url-pattern>
</servlet-mapping>
```

```java
@RequestMapping("/board/list.html")

@RequestMapping("/product/list")
```

<br>

#### Servlet 기반 경로 매칭

Servlet 경로를 포함한 전체 경로를 이용해서 매칭하려는 경우

@RequestMapping("/product/list")

```xml
<bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping">
    <property name="alwaysUseFullPath" value="true"/>
</bean>

<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
    <property name="alwaysUseFullPath" value="true"/>
</bean>
```

<br>

#### @PathVariable annotation을 이용한 URI 템플릿

PathVariable : list?id=abc&no=123 같은 방식이 아니라 list/abc/123 과 같이 경로를 변수로 주는것

- RESTful 방식

> http://localhost/users/troment  
> http://localhost/forum/board1/10

- @RequestMapping Annotation 값으로 {템플릿변수}를 사용

- @Pathvariable Annoation 을 이용해서 {템플릿변수}와 동일한 이름을 갖는 parameter를 추가

```java
@Controller
public class BoardController{
    @Autowired
    private BoardService boardService;

    @RequestMapping("/list/{userId}/board1/{articleSeq}")
    public String viewArticle (@PathVariable String userId, @PathVriable int articleSeq, Model model){
        BoardDto boardDto = boardService.getArticle(userId, articleSeq);
        model.addAttribute("article", boardDto);
        return "view";
    }
}
```

<br>

#### @RequestMapping Annotation 의 추가 설정 방법

@RequestMapping Annotation을 class와 method에 함께 적용하는 경우

```java
@Controller
//얘가 추가됨
@RequestMapping("/list/{userId}")
public class BoardController{
    @Autowired
    private BoardService boardService;

    @RequestMapping("/board1/{articleSeq}")
    public String viewArticle (@PathVariable String userId, @PathVriable int articleSeq, Model model){
        BoardDto boardDto = boardService.getArticle(userId, articleSeq);
        model.addAttribute("article", boardDto);
        return "view";
    }
}
```

Ant스타일 URI패턴 지원

> ? : 하나의 문자열과 대치
>
> - : 하나 이상의 문자열과 대치  
>   \*\* : 하나 이상의 디렉토리와 대치

```java
@RequestMapping("/members/*.do")

@RequestMapping("/list/*/board/{articleSeq}")
```

<br>

## 7. Spring Web MVC 동작 정리

#### Spring Web Application 의 동작 원리

![image](https://t1.daumcdn.net/cfile/tistory/99D9B34B5C9C5B501C)

<br>

#### 실행 순서

1. 웹 어플리케이션이 실행되면 Tomcat(WAS)에 의해 web.xml이 loading
2. web.xml에 등록되어 있는 ContextLoaderListener(Java Class)가 생성. ContextLoaderListener class는 ServletContextListener interface를 구현하고 있으며 ApplicationContext를 생성하는 역할을 수행
3. 생성된 ContextLoaderListener는 root-context.xml을 loading
4. root-context.xml에 등록되어 있는 Spring Container가 구동. 이때 개발자가 작성한 Business Logic(Service)에 대한 부분과 Database Logic(DAO), VO객체들이 생성
5. Client로부토 요청(request)가 들어옴
6. DispatcherServlet(Servlet)이 생성. DispatcherServlet은 FrontController의 역할을 수행<br>Client로부터 요청 온 메세지를 분석해 알맞은 PageController에 전달하고 응답을 받아 요청에 따른 응답을 어떻게 할지 결정. 실질적인 작업은 PageController에서 이뤄짐.<br> 이러한 클래스들을 HandlerMapping, ViewResolver Class라 함
7. DispatcherServlet은 servlet-context.xml을 loading
8. 두번째 Spring Container가 구동되며 응답에 맞는 PageController들이 동작<br>이때 첫번째 SpringContainer가 구동되면서 생성된 DOA, VO, Service클래스들과 협업하여 알맞은 작업을 처리

<br>

포스트방식 한글안깨지게 하려면 web.xml에 filter랑 filtermapping 필요함

```js
	<filter>
	    <filter-name>encodingFilter</filter-name>
	    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	    <init-param>
	      <param-name>encoding</param-name>
	      <param-value>UTF-8</param-value>
	    </init-param>
	 </filter>

	 <filter-mapping>
	    <filter-name>encodingFilter</filter-name>
	    <url-pattern>/*</url-pattern>
	 </filter-mapping>
```
