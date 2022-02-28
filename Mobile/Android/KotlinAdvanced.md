# 📚 Kotlin Advanced

<br>

<br>

## 1. 추상클래스와 인터페이스

본래 kotlin에서 상속받기 위해서는 open클래스로 선언했는데 추상클래스는 그럴필요 없음

```kotlin
abstract class Vehicle(val name:String, val color:String){
  abstract var maxSpeed: Double  //추상 프로퍼티 - 반드시 하위클래스에서 재정의로 초기화 필요
  var year = "2022" //일반프로퍼티 - 초기값인 상태를 저장 가능
  abstract fun start()  //추상 메서드 - 반드시 하위클래스에서 재정의로 초기화 필요
  abstract fun stop()
  fun displaySpec(){  //일반메서드
    println()
  }
}

class Car(name: String, color: String, override var maxSpeed: Double): Vehicle(name, color){
  override fun start(){}
  override fun stop(){}
}
```

<br>

## 2. 데이터 클래스 및 다양한 클래스

<br>

#### 데이터 클래스

- data class로 선언해서 사용
- 중괄호가 아닌 소괄호로 열고닫음
- 변수나 상수만 선언가능. 구분은 쉼표
- 프로퍼티를 일반 클래스와 달리 초기화 할필요 없음
- 생성과 동시에 클래스 내의 프로퍼티를 기준으로 생성자 생성
- 대개 VO(value object)를 다룰 떄 쓰면 유용

```kotlin
data class Data{
  val name: String,
  val type: String,
  var num: Int,
  var own: Boolean
}

fun main(){
  val data = Data("name", "ENTP", 26, true)
  if(data.own)
    println()
}
```

<br>

#### 오브젝트 클래스

- object라는 키워드로 선언
- 생성자를 가질 수 없음
- 어디서든 별도의 객체화 과정 없이 접근 가능
- 프로그램이 실행되는 동안 저장된 데이터는 손실되지 않고, 프로그램이 종료되면 소멸
- 안드로이드에서 액티비티, 프래그먼트 구분하지 않고 데이터 전달 가능

```kotlin
object Object{
  var name: String = ""
  var type: String = ""
  var age: Int = 0
  fun myType(){
    println()
  }
}

fun main(){
  val name = "samsung"
  val type = "ENTP"
  val age = 26
  Object.name = name
  Object.type = type
  Object.age = age
  Object.myType()
}
```

<br>

#### 열거형 클래스

- 별도의 인자가 없는 열거형 클래스
- [클래스이름.name] 이름값을 가져올 수 있음
- [클래스이름.ordinal] 로 해당 값이 몇번째에 적혔는지 (순서 값)을 가져올 수 있음
- 인자가 있는 열거형 클래스의 경우 각 열거값들을 표현하는 방식을 다채롭게 할 수 있음

```kotlin
//인자가 없는 기본적인 형태
enum class EnumClass{
  LOVE, PEACE
}
//인자 있는 열거형 클래스
enum class Human(val age:Int){
  KIM(25), CHUNG(26)
}

fun main(){
  val enumLove: EnumClass = EnumClass.LOVE
  println("${enumLove.name}...${enumLove.ordinal}") //LOVE...0
  val enumClass: Array<EnumClass> = EnumClass.values()
  for(i in enumClass.indices){
    print("${enumClass[i].name} ") //LOVE PEACE
  }

  val human: Human = Human.KIM
  println("${human.name}, ${human.age}, ${human.ordinal}")  //KIM, 25, 0
}
```

<br>

#### Sealed class

- 부모 클래스의 상속을 받는 자식 클래스의 종류를 제한하는 클래스
- Sealed class에 정의된 하위 클래스 외의 다른 하위 클래스는 존재하지 않는다는 것을 컴파일러에게 알림
- sealed class를 선언해서 사용
- sealed class는 abstract클래스로, 객체 생성 불가
- sealed class의 생성자는 private으로 한정됨
- sealed class와 그 하위 클래스는 동일한 파일에 정의되어야함. 서로 다른파일에서 정의 불가
- 하위 클래스는 class, data class, object class으로 정의 가능

```kotlin
sealed class Color{
  Object Red: Color()
  Object Green: Color()
  Object Blue: Color()
}

fun main(){
  val color: Color = Color.Red
  val font = when(color){
    is Color.Red -> "Noto Sans"
    is Color.Green -> "Open Sans"
  }
}
```

<br>

#### Sealed class VS Enum

- 공통점
  - 둘 다 타임을 제한할 떄 사용
- 차이점
  - enum은 하나의 객체만 만들 수 있음
  - sealed class는 객체를 여러개 생성할 수 있다는 점에서 다름

<br>

#### Inner 클래스

- 자바는 클래스 안에 클래스를 정의하면 자동으로 내부클래스가 되었음
- 코틀린에서 클래스 안에 클래스를 선언하면 중첩클래스
  - 내부 클래스로 만들고 싶으면 inner 키워드로 클래스 선언 필요
  - 내부 클래스는 기본적으로 외부 클래스를 참조가능하지만 중첩클래스는 그렇지 않음

<br>

#### 가시성 지시자

- 가시성지시자(Visibility modifiers)를 통해 외부 접근 범위를 정할 수 있음
  - private : 외부에서 접근 불가
  - protected : 상속관계에 있을 때만 외부에서 접근 가능
  - internal : 가은 모듈 내에서 접근 가능
  - public : 어디서든 접근 가능 (기본값)

<br>

## 3. 고차 함수

- 고차 함수는 매개변수나 함수의 반환 값으로 함수가 사용되는 함수를 뜻함
- 코틀린에서는 람다나 함수 참조를 사용해 함수를 변수에 넘길 수도 있고 그 자체가 값이 되기도 함
  - 함수 자체를 값으로 저장 할 수 있어 고차 함수의 정의처럼 사용 가능

```kotlin
//다른함수를 인자로 사용하거나 함수를 결과값으로 반환하는 함수
//파라미터로 (Int, Int)->Int인 함수 (sum)와 Int, Int 총3개를 받는 함수
//함수의 body에서는 전달받은 함수 sum을 실행하며 두 개의 변수 a, b를 넘겨줌
private fun highOrderFunction(sum: (Int, Int)->Int, a: Int, b: Int): Int = sum(a, b)

//일반함수를 인자나 반환값으로 사용하는 고차함수
private fun sum(a: Int, b: Int): Int = a+b
private fun sumFunction(): Int = sum(20, 30)

//람다식을 인자나 반환값으로 사용하는 고차함수
val multiply = {x: Int, y: Int->x*y}

//반환 자료형이 아예 없거나 매개변수가 하나만 있을 때의 고차함수
val helloWorld: () -> Unit = {println("HelloWorld")}
val ourSelfSum: (Int) -> Int = {a->a+a}

//람다식 안에 람다식을 넣는 람다 식 함수
val nestedLamda: () -> () -> Unit = {{println()}}

//인자와 반환 값이 없는 람다 식 함수
val print: ()->Unit = {println()}
val print2 = {println()}

fun main(){
  highOrderFunction({x, y -> x+y}, 20, 30)
  sumFunction()
  multiply(8, 8)
}
```

<br>

## 4. 코틀린 표준 함수 활용

- 대표적인 함수 `let()`, `apply()`, `run()`, `with()`, `also()`

<br>

#### let()

- let()을 호출하는 객체의 람다식 안에 파라미터로 객체를 넘김
- it을 통해 호출 객체에 접근
- 블럭의 결과값을 반환

```kotlin
var arr = arrayOf(1,2,3)
val result = arr.let{it[1] + it[2]}.plus(arr[0])
// arr[1] + arr[2]를 반환하고 arr[0]을 더해서 출력
```

<br>

#### apply()

- apply()를 호출하는 객체를 람다식 안에 파라미터로 넘김
- this를 통해 호출 객체에 접근
- this는 생략이 가능
- 호출객체(caller) 자체를 반환

```kotlin
data class Student(var name: String, var age: Int)

fun applyTest(){
  var student = Student("Chung", 26)
  var student2 = student.apply{
    age = 15
    age = "kim"
  }
}
```

<br>

#### run()

- 호출객체를 파라미터로 넘기는 방식, 객체 없이 사용하는 방식 둘 다 가능
- 블럭의 결과값을 반환

```kotlin
fun runTest(){
  var a = 10
  var b = 15

  //객체 없이 사용
  var result = run{
    var c = a+b
    c //return c
  }

  //객체 기반으로 사용
  result = result.run {plus(5)}
}
```

<br>

#### with()

- 인자로 받는 객체를 블럭에 파라미터로 전달
- run()함수와 기능은 거의 동일
- Safe Call 지원하지 않음 -> 종종 let과 함께 사용됨

```kotlin
data class People(var name: String, var age: Int)
fun withTest(){
  var People = People("Park", 15)
  var newName = with(people){ //this = people
    age = 20  //this.age = 20
    name  //return name
  }
}
```

<br>

#### also()

- also()를 호출하는 객체(caller)를 람다식 블럭에 파라미터로 넘김
- it을 통해 호출객체에 접근
- 호출객체 자체를 반환

```kotlin
data class Student(var name: String, var age: Int)
fun alsoTest(){
  var student = Student("Park", 11)
  var student2 = student.also{
    it.age = 15
    it.name = "Kim"
  }
}
```

<br>

#### 비교

| Parameter 접근 | 호출객체(Caller)를 리턴 | 코드블락 실행 결과 리턴 |
| -------------- | ----------------------- | ----------------------- |
| this           | apply                   | run, with               |
| it             | also                    | let                     |

<br>

## 5. 제네릭

- 클래스나 함수를 정의할 때 타입을 정하지 않고 포괄적으로 받아들일 수 있는 상태로 놔둠

```kotlin
class Box<T>(t: T){
  var value = t
}

class Car2{}

interface Container<T>{
  fun put(item: T)
  fun take(index: Int): T
}

class Garage: Container<Car>{
  private val items: MutableList<Car> = arrayListOf();
}
```

<br>

#### in/out

- 타입 매개변수의 업 혹은 다운 캐스팅을 할 수 있도록 지원되는 키워드
- out: B<Sub 타입>을 B<Super 타입>에 대입할 수 있도록 지원
- in: C<Super 타입>을 C<Sub 타입>에 대입할 수 있도록 지원
- _: B<_>은 타입 인수가 무엇이든 상관없이 대입할 수 있도록 지원

<br>

## 6. 컬렉션

- immutable vs mutable
- List
- Set
- Map

<br>

#### List

- 데이터가 삭제되거나 저장될 떄 순서를 지키는 컬렉션
- Mutable과 Immutable 모두 지원

- List: Immutable

  - listOf<type>(item)
  - 타입생략가능
  - get만 가능
  - getter는 get(index) 혹은 [index]
  - indexOf(value)

- List: Mutable
  - mutableListOf<type>(item)
  - listOf와 비슷하나 추가 및 삭제 가능

<br>

#### Set

- 아이템들의 순서는 특별히 정해져 있지 않음
- null 가능

- Set: Immutable

  - setOf<type>(item)

- Set: Mutable
  - mutableSetOf<type>(item)

<br>

#### Map

- key와 value로 저장. key는 유일하므로 중복 허용 안함
- Map: Immutable
  - mapOf<key type, value type>(item)
    - item은 Pair객체로 표현. Pair(A, B)
- Map: Mutable
  - mutableMapOf<key, value>(item)

<br>

#### Collection

- List와 Set은 Collection을 상속

<br>

#### 컬렉션 API

`filter`, `map`, `all`, `any`, `count`, `find`, `groupBy`, `flatMap`, `flatten`

- filter

  - filter 안의 조건문을 만족하는 원소로 이뤄진 새로운 collection 반환

- map
  - 각 원소를 원하는 형태로 변환하고 변환한 결과를 모아 새 컬렉션 반환

```kotlin
val peoples = listOf(Person("Tom", 20), Person("David", 22))
//30세 이상인 사람 이름 목록
val peoplesOver30 = peoples.filter{it.age >= 30}.map{it.name}

//나이가 가장 많은사람 이름
//비효율적
var oldest = peoples.filter{it.age == peoples.maxByorNull(Person::age)!!.age}
//효율적
var maxAge = peoples.maxByorNull(Person::age)!!.age
oldest = peoples.filter {it.age == maxAge}
```

- filter와 map을 Map(Key, Value)에 적용
  - Key 추출 : filterKeys, mapKeys
  - Value 추출 : filterValues, mapValues

```kotlin
@RequiresApi(Build.VERSION_CODES.N)
fun mapToMap(){
  val numbers = mapOf(1 to "zero", 2 to "one")
  //key는 제곱수로, value는 대문자로 변환
  val newMap = numbers.mapValues {it.value,uppercase()}.mapKeys {it.key*it.key}
  newMap.forEach{ println("${it.key} - ${it.value}") }
}
```

<br>

- all, any, count, find : 컬렉션 조건 함수

| 이름  | 설명                                                         | 리턴타입 |
| ----- | ------------------------------------------------------------ | -------- |
| all   | collection의 모든 원소가 조건을 만족하는지 판단              | Boolean  |
| any   | collection원소중 조건을 만족하는 원소가 하나라도 있는지 판단 | Boolean  |
| count | 조건을 만족하는 원소의 개수를 반환                           | Int      |
| find  | 조건을 만족하는 첫번째 원소를 반환                           | <T>      |

```kotlin
//all, any
val under30 = {p:Person -> p.age<30}
val people = listOf(Person("Tom", 20), Person("David", 22))
println(people.all(under30))
println(people.any(under30))

//count, find
println(people.count(under30))
println(people.find(under30))
```

<br>

- groupBy : 리스트를 여러 그룹으로 이뤄진 맵으로 변경

```kotlin
val map = people.groupBy{it.age}
map.forEach{
  println("${it.key}, ${it.value}")
}
```

<br>

- flatMap : 리스트의 요소들을 flat하게 처리할때 쓰는 함수

```kotlin
val strings = listOf("abc", "def")
val newMap = strings.flatMap{ it.toList() }
// [a,b,c,d,e,f]
val nestedList = listOf(listOf("abc", "def"), listOf("hij", "klm"))
val newMap2 = nestedList.flatMap{it.toList()}
// [abc, def, hij, klm]
val newMap3 = nestedList.flatMap{it}.flatMap{it.toList()}
// [a,b,c,d,e,f,h,i,j,k,l,m]
```

<br>

- flatten : list안에 여러개의 lsit가 포함된경우 flatten사용시 하나의 list로 반환

```kotlin
val nestedList = listOf(listOf("abc", "def"), listOf("hij", "klm"))
val newMap = strings.flatten()
// [abc,def,hij,klm]
```

<br>

## 7. 예외처리

- 코틀린의 모든 예외 클래스는 Throwable의 자식 클래스

- Checked Exception 개념이 업ㅇ서 try~catch문을 작성하지 않아도 됨

- AutoCloable 처리 : use 사용
  - Closeable 인터페이스가 구현된 클래스에 한해 use 사용 가능

<br>

## 8. 확장 함수

어떤 클래스의 멤버 메소드인것처럼 호출할 수 있지만 그 클래스의 밖에 선언된 함수

따로 상속받지 않고 하나의 클래스에 추가적인 메소드를 구현하고 싶을 때 사용하는 함수

확장함수를 통해 새로운 class를 만드는 번거로움을 줄일 수 있음

<br>

## 9. 문자열

여러줄로 구성된 문자열은 """으로 앞뒤를 감싸 선언함

그냥 써도 되나 가독성이 떨어질경우 구분자(|) 앞을 공백으로 채우고 마지막에 trimMargein() 사용

```kotlin
val heroes = """
  |Spiderman
  |Ironman
  """.trimMargin()
```

리스트 내에 포함된 값들을 구분자와 함께 하나의 문자열로 표현하고 싶은경우 joinToString() 사용

<br>

## 10. 연산자 오버로딩

연산자 오버로딩 함수명 앞에 operator 키워드 필요

비교연산자는 Any에 정의된 함수이므로 override 필요 (operator는 안씀) : equals(), compareTo()

<br>
