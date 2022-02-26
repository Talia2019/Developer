# Interceptor

<br>

<br>

## 1. HandlerInterceptor 를 통한 요청 가로채기

Controller가 요청을 처리하기 전/후 처리

로깅, 모니터링 정보 수집, 접근 제어 처리 등의 실제 Business Logic 과는 분리되어 처리해야 하는 기능들을 넣고 싶을 때 유용

interceptor를 여러 개 설정할 수 있음 (순서 주의)

<br>

## 2. HandlerInterceptor 제공 method

- boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

false를 반환하면 request를 바로 종료

- void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)

Controller 수행 후 호출

- void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)

view를 통해 클라이언트에 응답을 전송한 뒤 실행

예외가 발생하여도 실행

<br>

## 3. HandlerInterceptor 를 통한 요청 가로채기

#### Interceptor 호출 순서

```xml
<!-- servlet-context.xml -->
<mvc:interceptors>
  <mvc:interceptor>
    <mvc:mapping path="/*.html"/>
    <bean class="com.test.hello.AInterceptor">
  <mvc:interceptor>
  <mvc:interceptor>
    <mvc:mapping path="/*.html"/>
    <bean class="com.test.hello.BInterceptor">
  <mvc:interceptor>
  <mvc:interceptor>
    <mvc:mapping path="/*.html"/>
    <bean class="com.test.hello.CInterceptor">
  <mvc:interceptor>
</mvc:interceptors>
```

> pre → post → after
>
> preHandle : A → B → C → Controller
>
> postHandle : Controller → C → B → A
>
> afterCompletion : Controller → C → B → A

<br>

#### Interceptor 구현

HandlerInterceptor 인터페이스 구현

HandlerInterceptorAdaptor 클래스 제공

```java
public class LoggingInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    System.out.println("interceptor-pre");
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
    System.out.println("interceptor-post");
  }

  @Override
  public afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
    System.out.println("interceptor-after");
  }
}
```

#### Interceptor 설정하기 : servlet-context.xml

```xml
<!-- servlet-context.xml -->
<mvc:interceptors>
  <mvc:interceptor>
    <mvc:mapping path="/*.html"/>
    <bean class="com.test.hello.LoggingInterceptor">
  <mvc:interceptor>
</mvc:interceptors>
```

.html 로 들어오면 위 인터셉터가 실행됨

<br>

## 4. Interceptor - session check

로그인 해야 접근할 수 있는 페이지의 경우 세션을 통해 확인하기

```xml
<!-- servlet-context.xml -->
<beans:bean id="confirm" class="com.test.interceptor.ConfirmInterceptor"/>

<interceptors>
  <interceptor>
    <mapping path="/article/write"/>
    <mapping path="/article/modify"/>
    <mapping path="/article/delete"/>
    <beans:ref bean="confirm"/>
  <interceptor>
</interceptors>
```

```java
//ConfirmInterceptor.java
public class ConfirmInterceptor implements HandlerInterceptorAdaptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    HttpSession session = request.getSession();
    MemberDto memberDto = (MemberDto)session.getAttribute("userinfo");
    if(memberDto == null){
      response.sendRedirect(request.getContextPath());
      return false;
    }
    return true;
  }
}
```
