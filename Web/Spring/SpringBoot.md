# SpringBoot & Swagger

<br>

<br>

## 1. SpringBoot 의 특징

<br>

Spring의 경우 Application을 개발하려면 사전에 많은 직업(library 추가, dependency 설정, SpringFramework 가 처리해야 하는 여러가지 구성 및 설정 파일 등)을 해야했음

<br>

#### SpringBoot의 장점

1. project에 따라 자주 사용되는 library들이 미리 조합되어 있음
2. 복잡한 설정을 자동으로 처리
3. 내장 서버를 포함해서 tomcat과 같은 WAS를 추가로 설치하지 않아도 개발 가능
4. WAS에 배포하지 않고도 실행할 수 있는 JAR파일로 Web Application 개발 가능

<br>

## 2. SpringBoot project 생성

<br>

Spring Starter Project를 이용해 윈도우의 Install Wizard와 같이 쉽게 SpringBoot 기반의 프로젝트 생성 가능

`Spring Boot` → `Spring Starter Project`

주로 Group + Artifact가 Package가 되도록 설정

SpringBoot의 버전 및 Dependency 설정 : `Spring Boot Dev Tools`, `Spring Web`, `Mysql Driver` 등 원하는것 선택

<br>

#### project 생성 구조 및 주요 구성 폴더/파일

| 프로젝트의 주요 파일            | 설명                                                                        |
| ------------------------------- | --------------------------------------------------------------------------- |
| src/main/java                   | java source directory                                                       |
| HelloSpringBootApplication.java | application을 시작할 수 있는 main method가 존재하는 스프링 구성 메인 클래스 |
| static                          | css, js, img 등의 정적 resource directory                                   |
| templates                       | SpringBoot에서 사용 가능한 여러가지 View Template위치                       |
| application.properties          | application 및 스프링의 설정 등에서 사용할 여러가지 property를 정의한 file  |
| src/main                        | jsp등의 리소스 directory                                                    |

실행 : `Run As` → `Spring Boot App`

브라우저를 열고 [http://localhost:8080](http://localhost:8080) 실행

src/main 에 webapp 폴더 생성 후 index.html 작성

<br>

#### application.properties 에서 설정 담당

~Application 클래스의 @SpringBootApplication 어노테이션 덕분에 component-scan 안하는데 @Controller 등 잘 찾아서 잘돌아감

컴포넌트 스캔은 @Component써서 하면 되긴 하는데 직접 할 일은 거의 없음

누가해도 같은거면 자동으로 돌아가는데, db연결이라던가, type alias/mapper 위치나, jsp쓸거면 쓴다는 세팅이나, interceptor나, file up/download 등은 사람마다 다를것이기에 자동으로 안됨 application.properties 안에 설정해줘야함

#### web mvc 에 관련된 설정은 WebMvcConfig.java 생성

WebMvcConfigurer 인터페이스를 구현해야하며 @Configuration 어노테이션 붙여줘야함

인터셉터는 여기서 선언

<br>

#### AOP 설정

~Application 클래스에 @EnableAspectJAutoProxy 어노테이션 선언

<br>

## 3. Swagger를 이용한 REST API 문서화

프로젝트 개발 시 일반적으로 FrontEnd 개발자와 BackEnd 개발자가 분리

FrontEnd 개발자의 경우 화면과 로직에 집중을하고, BackEnd 개발자가 만든 문서 API를 보며 데이터 처리를 하게됨

이때 개발 상황의 변화에 따른 API추가 도는 변경할때마다 문서에 적용하는 불편함 발생

→ 이를 해결하기 위해 Swagger 사용

<br>

## 4. Swagger

간단한 설정으로 프로젝트의 API목록을 웹에서 확인 및 테스트 할 수 있게 해주는 Library

Swagger 를 사용하면 Controller에 정의되어 있는 모든 URL을 바로 확인 할 수 있다

API 목록 뿐 아니라 API의 명세 및 설명도 볼 수 있으며, API 를 직접 테스트해 볼 수도 있음

<br>

## 5. Swagger 적용

- pom.xml 에 swagger2 dependency 추가

```xml
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.9.2</version>
</dependency>
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.9.2</version>
</dependency>
```

- SwaggerConfiguration.java

```java
@Configuration
@EnableSwagger2
public class SwaggerConfiguration{
  @Bean
  public Docket postsApi(){
    return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
      .apiInfo(apiInfo()).select().paths(postPaths()).build();
  }

  private Predicate<String> postPaths(){
    return or(regex("/api/posts.*"), regex("/api.*"));
  }

  private ApiInfo apiInfo(){
    return new ApiInfoBuilder().title("Swagger API")
      .description("<h3>Swagger 이용 HTM API</h3>")
      .contact(new Contact("Name", "https://~", "email@email.com")).license("license")
      .licenseUrl("https://~").version("1.0").build();
  }
}
```

- Swagger 적용 될 Controller

클래스 위에 `@Api(value="Swagger")`

메소드 위에 `@ApiOperation(value="모든 사원의 정보를 반환", response=List.class)`

- Swagger 적용 될 Model(Dto)

클래스 위에 `@ApiModel(value="EmployeeDto: 사원정보", description="사원의 상세 정보를 나타낸다")`

변수 위에 `@ApiModelProperty(value="사원 번호")`

- Swagger API를 이용한 테스트

parameter 입력이 필요하다면 입력 창이 나옴

<br>

## 6. 배포 - jar

pom.xml 에서 version태그 밑에 packaging태그로 jar선언

<packaging>jar</packaging>

`플젝 우클릭` → `Run As` → `Maven build` → `Goals : package, Profiles 비워두기`
