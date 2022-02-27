# 📚 Kotlin

<br>

<br>

## 1. Kotlin 개요

main()은 실행 진입 지점, 클래스 없이 main() 함수 하나로 실행 가능

<br>

## 2. 변수

- val (value) : 불변형 (immutable)
- var (variable) : 가변형 (mutable)

자료형을 지정하지 않은 변수는 사용 불가

```kotlin
val userName = "name" // 자료형을 추론해 String 으로 결정
var userName          // 불가능!!! 자료형 지정해야함
val init: Int         // 사용 전, 혹은 생성자 시점에서 init변수를 초기화 해야함
val number = 10       // number변수는 Int형으로 추론
```

기본자료형은 모두 객체 - 엄격한 타입체크 (Double에 Int못들어감. 메소드로 형변환 시켜줘야함)

코틀린은 유니코드를 사용하고, 여러줄 문자열 표현은 큰따옴표 세개로 감쌈

- 문자열 비교 (==)

  - 문자열이 같은지는 == 로 비교
  - 오브젝트 비교할때는 === 로 동일한 오브젝트인지 비교

- 문자열 붙이기 (+)

- 문자열 템플릿

  - $변수명 혹은 ${변수명} : 문자열 중간에 변수가 들어가야 할 경우

- 타입 별명 붙이기 typealias

  - 타입의 이름이너무 길거나 복잡한경우 typealias 별명 = 타입과 같이 적어주면 타입에 새로운 이름 부여 가능

- 배열 : arrayOf()

  - 배열의 생성과 초기화를 함께 수행 (컴파일러가 유추가능한경우 생략)
  - 배열의 요소는 [] 대괄호로 접근

  ```kotlin
  val num : Array<Int> = arrayOf(1,2,3,4)
  val num2 = arrayOf(5,'a',"hi")
  println(num2[2])

  //Array<Int> 에는 int형만 넣어야하고
  //arrayOf() 로 선언한경우는 아무거나 넣어도됨
  ```

<br>

- Null 체크

  - 본래 null을 허용하지 않으나, ?로 Null 가능한 선언

  ```kotlin
  val a: Int = 24

  val aaa:Int? = null


  var tmp: String? = null
  var size = if(tmp != null) tmp.length else -1
  ```

- Non-null

  - 변수 뒤에 !!으로 표시 (Not null 단정기호)
  - null이 들어오면 npe 발생

- safe-call

  - Type뒤에 ?를 붙임. null 이 가능한 변수라는 뜻

- 엘비스 연산자
  - null인 경우 default를 주고 싶을때 ?:를 붙여줌

```kotlin
fun ignoreNulls(s: String?){ //null 들어오면 npe발생
  val sNotNull: String = s!!
  println(sNotNull.length)
}

fun strLenSafe(s: String?): Int = if(s!=null) s.length else 0

fun getName(str: String?){
  val name = str ?: "Unknown"
}
```

<br>

## 3. 자료형

```kotlin
val a: Int = 1
val b: Double = a   //자료형 불일치 오류
val b: Double = a.toDouble()
val c: Int = 1.12   //자료형 불일치 오류
```

<br>

#### 스마트 캐스트

구체적으로 명시되지 않은 자료형을 자동 변환해줌

Number : 숫자를 저장하기 위한 특수 자료형으로 스마트캐스팅됨

```kotlin
val test1: Number = 6.2 //Double형으로 스마트캐스팅
test1 = 12  //Integer형으로 스마트캐스트
test1 = 12L //Long형으로 스마트캐스트
test1 += 12.0f  //Float형으로 스마트캐스트
```

Is 키워드로 검사

```kotlin
val number = 256
if(number is Int)
  print(number)
else
  print("Not a Int")
```

<br>

#### 묵시적 변환

Any : 자료형이 정해지지 않은 경우. 모든 클래스의 뿌리

```kotlin
fun checkArg(x: Any){
  if(x is String)
    println("x is String: $x")
  else if(x is Int)
    println("x is Int: $x")
}

checkArg("Hello")
checkArg(5)
```

<br>

## 4. 조건문과 분기

<br>

#### If

if, else if, else 동일한데

```
val e = if(a<b) a else b
```

같은 방식으로도 쓸 수 있음

삼항연산자가 없음, if-else가 표현식이기 때문(if-else가 특정 값을 반환)

<br>

#### When

Switch 문에 대응

값이 하나인 경우 콤마나 in연산자로 값의 범위를 자유롭게 지정하고 싶을 때 사용

if문처럼 사용도 가능 (전제조건 처리를 위해 else구문이 있어야함)

```kotlin
val x = 1

when(x){
  1 -> println("x==1")
  2,3 -> println("x==2 or x==3")
  in 4..7 -> println("4<=x<=7")
  !in 8..10 -> println("x<8 && x>10")
  else ->{
    println("else!")
  }
}

val num2 = when (num%2){
  0 -> "짝"
  else -> "홀"
}

val result = when(a){
  1 -> "정수 1"
  "hello" -> "world"
  is Long -> "Long type"
  !is String -> "Not String type"
  else -> "Nothing"
}
```

<br>

#### For

in 연산자로 배열 순회 가능
.., downTo, step, Until : 증가 범위, 증감 간격 조절

```kotlin
val num = arrayOf(1,2,3,4)
for(n in num)
  println(n)

for(i in 1..4)
  println(i)

for(i in 0..10 step 2)
  println(i) // 0; 2; 4; 6; 8; 10;

for(i in 0..10 downTo 0 step 2)
  println(i) // 10; 8; 6; 4; 2; 0;
```

<br>

#### While, DoWhile, Break, Continue

java와 동일

<br>

#### Label

레이블이 붙여진 곳으로 이동 가능

중첩된 반복문에서 완전히 탈출하고싶을때 사용하면 유용

```kotlin

out@for(i in 1..5){
  for(j int 1..5){
    println(i+j)
    if(i+j>5) break@out
  }
}
```

java랑 달리 @가 붙음

<br>

## 5. 함수 기본

절차지향 프로그래밍, 객체지향 프로그래밍, 함수형 프로그래밍

- 함수형 프로그래밍

  - 함수의 유기적 연결 및 동작이 프로그램의 최우선이 되는 프로그래밍 방식. 함수가 일급객체로써의 의미를 지님

  - 모든것이 객체
  - 변경가능한 상태를 불변상태(Immutab)로 바꿔 SideEffect를 없애자
  - 1급 객체란?
    - 변수나 데이터에 할당 가능해야 함
    - 객체의 인자로 넘길 수 있어야함
    - 객체의 리턴값으로 리턴할 수 있어야함

<br>

#### 람다

- 람다표현식 : 익명 함수를 뜻함
- `기존` fun 함수이름(매개변수) {함수내용}
- `람다` {매개변수 -> 함수내용}

- 람다의 규칙
  - 항상 {}으로 감싸서 표현해야 함
  - {}안에 ->가 있으며 ->왼쪽은 매개변수, 오른쪽은 함수 내용
  - 매개변수 타입을 선언해야 하며 추론할 수 있을 때는 생략 가능
  - 함수의 반환값은 함수 내용의 마지막 표현식
  - 매개변수가 하나인경우 생략 가능
  - 코드의 마지막줄은 return type으로 추론됨

```kotlin
val sum = {x1:Int, x2:Int -> x1+x2}

val multi: (Int, Int) -> Int = { x:Int, y:Int ->
  println("x*y")
  x*y //마지막 표현식이 반환됨
}
//자료형 생략
val multi: (Int, Int) -> Int = { x:Int, y:Int -> x*y } //생략되지 않은 형태
val multi: { x:Int, y:Int -> x*y }
val multi: (Int, Int) -> Int = { x, y -> x*y }
val multi: { x, y -> x*y } //에러! 추론불가
```

<br>

## 6. 클래스와 객체

```kotlin
class Computer{
  var name: String = "MyCom"
  var part: Int = 2
  var color: String = "red"

  fun play() = println("computer pat: $part")
  fun playGame(vol: Int) = println("game vol: $vol")
}

fun main(){
  val com = Computer()
  com.color = "blue"
  println("Color: ${com.color}")
  com.play()
  com.playGame(3)
}
```

<br>

#### 주생성자, 부생성자

- 주생성자 : 클래스명과 함께 기술되며 contsructor키워드 생략 가능
- 부생성자 : 클래스 본문에 기술되며 하나 이상의 부 생성자 정의 가능

```kotlin
class Human constructor(_name: String, _age: Int){ //주생성자
  var name: String = _name
  var age: Int = _age

  constructor(name: String, age: Int){  //부생성자
    this.name = name
    this.age = age
  }
}

//주생성자는 private, public 등이나 어노테이션 표기가 없을 경우 생략 가능
class Human(var name: String, var age: Int){} //프로퍼티가 이미 선언된것

class Human(var name: String = "Noname", var age: Int = 20){} //필요시 기본값 지정

class Human(var name: String, var age: Int){
  init{
    //초기화 블록 : 생성자는 아닌데 이후로 제일먼저불림?
  }
}

```

<br>

#### 부모 클래스

open 클래스를 통해 부모 클래스 생성 가능

```kotlin
open class 기반 클래스명{
  //open 으로 파생 가능(다른 클래스가 상속 가능한 상태가 됨)
}
class 파생 클래스명 : 기반 클래스명(){
  //기반 클래스로부터 상속, 최종 클래스로 파생 불가
}
// 코틀린의 모든 클래스는 묵시적으로 Any로부터 상속
```

하위 클래스는 부모의 프로퍼티나 메서드를 받아 오버라이딩하거나 커스텀해 재작성 가능

<br>

#### 다형성

open으로 오버라이딩 가능한 함수를 열어둠

```kotlin
open class Human{
  fun walk(){}    //오버라이딩 불가
  open fun sing(){}   //오버라이딩 가능
}
```

#### super, this

자바와 동일

super : 상위 클래스의 메서드, 프로퍼티, 생성자를 사용하는 키워드

this : 현재 클래스의 메서드, 프로퍼티, 생성자를 사용하는 키워드

<br>

## 7. 프로퍼티와 초기화

코틀린의 Properties : 변수선언과 기본적인 접근메서드를 모두 가짐. 따로 접근 메서드를 만들지 않아도 내부적으로 생성

게터 + 세터 = 접근 메서드(Access methods) : 기본제공됨

```kotlin
class User(val id: Int, var name: String, var age:Int){}

val user = User(1, "Hello", 20)
val name = user.name
println("name: $name, age: ${user.age}")
```

게터와 세터 직접 지정 가능

불변형인 val은 게터만 설정 가능

```kotlin
class User(_id: Int, _name: String, _age:Int){
  val id: Int = _id
    get() = field
  var name: String = _name
    get() = field
    set(value){
      field = value
    }
  var age: Int = _age
    get() = field
    set(value){
      field = value
    }
}
```

this.등으로 바로 세팅못함. 필드로 접근해야됨! (this.age으로 부르면 setAge가 자동으로 불리기때문에)

<br>

#### 지연 초기화

- 지연 초기화가 필요한 이유

  - 변수나 객체의 값은 생성시 초기화 필요
  - 클래스에서 기본적으로 선언하는 프로퍼티 자료형들은 null을 못가짐. 그러나 객체의 정보가 나중에 나타나는 경우 초기화 방법이 필요. 지연 초기화를 위해 lateinit과 lazy 키워드 사용

- lateinit을 사용한 지연 초기화

  - 의존성이 있는 초기화나 unit테스트를 위한 코드 작성 시
  - 프로퍼티 지연 초기화
  - 클래스 선언시 프로퍼티 선언은 null을 허용치 않음
  - 그러나 지연초기화를 위한 lateinit키워드를 사용하면 프로퍼티에 값이 바로 할당되지 않아도 됨

- lateinit의 제한

  - var로 선언된 프로퍼티만 가능
  - 프로퍼티에 대한 게터세터를 사용 할 수 없음

- lazy를 이용한 지연 초기화
  - 호출 시점에 by lazy{...} 정의에 의해 블록부분읯 ㅗ기화를 진행
  - 불변의 변수 선언인 val에서만 사용가능
  - val이므로 값을 다시 변경 불가

```kotlin
//lateinit
class Person{
  lateinit var name: String //1. 늦은 초기화를 위한 선언
  fun test(){
    if(!::name.isInitialized){ //2. 프로퍼티의 초기화 여부 판단
      println("Not initialized")
    } else {
      println("Initialized")
    }
  }
}
fun main(){
  val person = Person()
  person.test()
  person.name = "Jyoung"
  person.test() //3. 이시점에서 초기화됨(지연초기화)
  println(person.name)
}

//lazy
class LazyTest{
  init{
    println("init block") //2
  }
  val subject by lazy{
    println("lazy initialized") //6
    "Kotlin" //lazy반환 값  //7. lazy 반환값
  }
  fun flow(){
    println("not Initialized")  //4
    println("subject one: $subject")  //5. 최초 초기화 시점
    println("subject two: $subject")  //8. 이미 초기화된 값 사용
  }
}

val test = LazyTest() //1
test.flow() //3
```

- Lazy: 세가지 모드 지정 가능
  - SYNCHRONIZED : 락을 사용해 단일 스레드만이 사용하는것을 보장(default)
  - PUBLICATION : 여러군데서 호출될 수 있으나 처음 초기회 된 후 반환 값을 사용
  - NONE : 락을 사용하지 않기 때문에 빠르지만 다중 스레드가 접근 가능(값의 일관성 보장 불가)

<br>

#### By를 이용한 위임

- 위임(delegation)

  - 위탁자(delegator)->수탁자(delegate) 형태이며 어떤 일의 책임 및 처리를 다른 클래스 또는 메서드에게 넘긴다는 의미
  - 한 객체가 기능 일부를 다른 객체로 넘겨주어 첫번재 객체대신 수행하도록 하는 일
  - 다른 클래스의 기능을 사용하되 그 기능을 변경하지 않으려면 상속 대신 위임
  - 위임을 활용하면 한 객체의 변경이 다른 객체에 미치는 영향이 작아짐
  - 코틀린 라이브러리는 대부분 open 되어있지 않아 상속이나 직접확장이 어려움. 위임을 통해 상속과 비슷하게 확장

by를 이용하거나 observable()함수, vetoable()함수 사용

<br>

## 8. 정적 변수

static은 코틀린에서 Companion object로 사용

프로그램 실행 시 고정적으로 가지는 메모리로 객체 엇이 생성 가능하나, 자주 사용되지 않는 변수나 객체를 만들면 메모리 낭비
