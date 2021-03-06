# π Kotlin Advanced

<br>

<br>

## 1. μΆμν΄λμ€μ μΈν°νμ΄μ€

λ³Έλ kotlinμμ μμλ°κΈ° μν΄μλ openν΄λμ€λ‘ μ μΈνλλ° μΆμν΄λμ€λ κ·Έλ΄νμ μμ

```kotlin
abstract class Vehicle(val name:String, val color:String){
  abstract var maxSpeed: Double  //μΆμ νλ‘νΌν° - λ°λμ νμν΄λμ€μμ μ¬μ μλ‘ μ΄κΈ°ν νμ
  var year = "2022" //μΌλ°νλ‘νΌν° - μ΄κΈ°κ°μΈ μνλ₯Ό μ μ₯ κ°λ₯
  abstract fun start()  //μΆμ λ©μλ - λ°λμ νμν΄λμ€μμ μ¬μ μλ‘ μ΄κΈ°ν νμ
  abstract fun stop()
  fun displaySpec(){  //μΌλ°λ©μλ
    println()
  }
}

class Car(name: String, color: String, override var maxSpeed: Double): Vehicle(name, color){
  override fun start(){}
  override fun stop(){}
}
```

<br>

## 2. λ°μ΄ν° ν΄λμ€ λ° λ€μν ν΄λμ€

<br>

#### λ°μ΄ν° ν΄λμ€

- data classλ‘ μ μΈν΄μ μ¬μ©
- μ€κ΄νΈκ° μλ μκ΄νΈλ‘ μ΄κ³ λ«μ
- λ³μλ μμλ§ μ μΈκ°λ₯. κ΅¬λΆμ μΌν
- νλ‘νΌν°λ₯Ό μΌλ° ν΄λμ€μ λ¬λ¦¬ μ΄κΈ°ν ν νμ μμ
- μμ±κ³Ό λμμ ν΄λμ€ λ΄μ νλ‘νΌν°λ₯Ό κΈ°μ€μΌλ‘ μμ±μ μμ±
- λκ° VO(value object)λ₯Ό λ€λ£° λ μ°λ©΄ μ μ©

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

#### μ€λΈμ νΈ ν΄λμ€

- objectλΌλ ν€μλλ‘ μ μΈ
- μμ±μλ₯Ό κ°μ§ μ μμ
- μ΄λμλ  λ³λμ κ°μ²΄ν κ³Όμ  μμ΄ μ κ·Ό κ°λ₯
- νλ‘κ·Έλ¨μ΄ μ€νλλ λμ μ μ₯λ λ°μ΄ν°λ μμ€λμ§ μκ³ , νλ‘κ·Έλ¨μ΄ μ’λ£λλ©΄ μλ©Έ
- μλλ‘μ΄λμμ μ‘ν°λΉν°, νλκ·Έλ¨ΌνΈ κ΅¬λΆνμ§ μκ³  λ°μ΄ν° μ λ¬ κ°λ₯

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

#### μ΄κ±°ν ν΄λμ€

- λ³λμ μΈμκ° μλ μ΄κ±°ν ν΄λμ€
- [ν΄λμ€μ΄λ¦.name] μ΄λ¦κ°μ κ°μ Έμ¬ μ μμ
- [ν΄λμ€μ΄λ¦.ordinal] λ‘ ν΄λΉ κ°μ΄ λͺλ²μ§Έμ μ νλμ§ (μμ κ°)μ κ°μ Έμ¬ μ μμ
- μΈμκ° μλ μ΄κ±°ν ν΄λμ€μ κ²½μ° κ° μ΄κ±°κ°λ€μ νννλ λ°©μμ λ€μ±λ‘­κ² ν  μ μμ

```kotlin
//μΈμκ° μλ κΈ°λ³Έμ μΈ νν
enum class EnumClass{
  LOVE, PEACE
}
//μΈμ μλ μ΄κ±°ν ν΄λμ€
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

- λΆλͺ¨ ν΄λμ€μ μμμ λ°λ μμ ν΄λμ€μ μ’λ₯λ₯Ό μ ννλ ν΄λμ€
- Sealed classμ μ μλ νμ ν΄λμ€ μΈμ λ€λ₯Έ νμ ν΄λμ€λ μ‘΄μ¬νμ§ μλλ€λ κ²μ μ»΄νμΌλ¬μκ² μλ¦Ό
- sealed classλ₯Ό μ μΈν΄μ μ¬μ©
- sealed classλ abstractν΄λμ€λ‘, κ°μ²΄ μμ± λΆκ°
- sealed classμ μμ±μλ privateμΌλ‘ νμ λ¨
- sealed classμ κ·Έ νμ ν΄λμ€λ λμΌν νμΌμ μ μλμ΄μΌν¨. μλ‘ λ€λ₯ΈνμΌμμ μ μ λΆκ°
- νμ ν΄λμ€λ class, data class, object classμΌλ‘ μ μ κ°λ₯

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

- κ³΅ν΅μ 
  - λ λ€ νμμ μ νν  λ μ¬μ©
- μ°¨μ΄μ 
  - enumμ νλμ κ°μ²΄λ§ λ§λ€ μ μμ
  - sealed classλ κ°μ²΄λ₯Ό μ¬λ¬κ° μμ±ν  μ μλ€λ μ μμ λ€λ¦

<br>

#### Inner ν΄λμ€

- μλ°λ ν΄λμ€ μμ ν΄λμ€λ₯Ό μ μνλ©΄ μλμΌλ‘ λ΄λΆν΄λμ€κ° λμμ
- μ½νλ¦°μμ ν΄λμ€ μμ ν΄λμ€λ₯Ό μ μΈνλ©΄ μ€μ²©ν΄λμ€
  - λ΄λΆ ν΄λμ€λ‘ λ§λ€κ³  μΆμΌλ©΄ inner ν€μλλ‘ ν΄λμ€ μ μΈ νμ
  - λ΄λΆ ν΄λμ€λ κΈ°λ³Έμ μΌλ‘ μΈλΆ ν΄λμ€λ₯Ό μ°Έμ‘°κ°λ₯νμ§λ§ μ€μ²©ν΄λμ€λ κ·Έλ μ§ μμ

<br>

#### κ°μμ± μ§μμ

- κ°μμ±μ§μμ(Visibility modifiers)λ₯Ό ν΅ν΄ μΈλΆ μ κ·Ό λ²μλ₯Ό μ ν  μ μμ
  - private : μΈλΆμμ μ κ·Ό λΆκ°
  - protected : μμκ΄κ³μ μμ λλ§ μΈλΆμμ μ κ·Ό κ°λ₯
  - internal : κ°μ λͺ¨λ λ΄μμ μ κ·Ό κ°λ₯
  - public : μ΄λμλ  μ κ·Ό κ°λ₯ (κΈ°λ³Έκ°)

<br>

## 3. κ³ μ°¨ ν¨μ

- κ³ μ°¨ ν¨μλ λ§€κ°λ³μλ ν¨μμ λ°ν κ°μΌλ‘ ν¨μκ° μ¬μ©λλ ν¨μλ₯Ό λ»ν¨
- μ½νλ¦°μμλ λλ€λ ν¨μ μ°Έμ‘°λ₯Ό μ¬μ©ν΄ ν¨μλ₯Ό λ³μμ λκΈΈ μλ μκ³  κ·Έ μμ²΄κ° κ°μ΄ λκΈ°λ ν¨
  - ν¨μ μμ²΄λ₯Ό κ°μΌλ‘ μ μ₯ ν  μ μμ΄ κ³ μ°¨ ν¨μμ μ μμ²λΌ μ¬μ© κ°λ₯

```kotlin
//λ€λ₯Έν¨μλ₯Ό μΈμλ‘ μ¬μ©νκ±°λ ν¨μλ₯Ό κ²°κ³Όκ°μΌλ‘ λ°ννλ ν¨μ
//νλΌλ―Έν°λ‘ (Int, Int)->IntμΈ ν¨μ (sum)μ Int, Int μ΄3κ°λ₯Ό λ°λ ν¨μ
//ν¨μμ bodyμμλ μ λ¬λ°μ ν¨μ sumμ μ€ννλ©° λ κ°μ λ³μ a, bλ₯Ό λκ²¨μ€
private fun highOrderFunction(sum: (Int, Int)->Int, a: Int, b: Int): Int = sum(a, b)

//μΌλ°ν¨μλ₯Ό μΈμλ λ°νκ°μΌλ‘ μ¬μ©νλ κ³ μ°¨ν¨μ
private fun sum(a: Int, b: Int): Int = a+b
private fun sumFunction(): Int = sum(20, 30)

//λλ€μμ μΈμλ λ°νκ°μΌλ‘ μ¬μ©νλ κ³ μ°¨ν¨μ
val multiply = {x: Int, y: Int->x*y}

//λ°ν μλ£νμ΄ μμ μκ±°λ λ§€κ°λ³μκ° νλλ§ μμ λμ κ³ μ°¨ν¨μ
val helloWorld: () -> Unit = {println("HelloWorld")}
val ourSelfSum: (Int) -> Int = {a->a+a}

//λλ€μ μμ λλ€μμ λ£λ λλ€ μ ν¨μ
val nestedLamda: () -> () -> Unit = {{println()}}

//μΈμμ λ°ν κ°μ΄ μλ λλ€ μ ν¨μ
val print: ()->Unit = {println()}
val print2 = {println()}

fun main(){
  highOrderFunction({x, y -> x+y}, 20, 30)
  sumFunction()
  multiply(8, 8)
}
```

<br>

## 4. μ½νλ¦° νμ€ ν¨μ νμ©

- λνμ μΈ ν¨μ `let()`, `apply()`, `run()`, `with()`, `also()`

<br>

#### let()

- let()μ νΈμΆνλ κ°μ²΄μ λλ€μ μμ νλΌλ―Έν°λ‘ κ°μ²΄λ₯Ό λκΉ
- itμ ν΅ν΄ νΈμΆ κ°μ²΄μ μ κ·Ό
- λΈλ­μ κ²°κ³Όκ°μ λ°ν

```kotlin
var arr = arrayOf(1,2,3)
val result = arr.let{it[1] + it[2]}.plus(arr[0])
// arr[1] + arr[2]λ₯Ό λ°ννκ³  arr[0]μ λν΄μ μΆλ ₯
```

<br>

#### apply()

- apply()λ₯Ό νΈμΆνλ κ°μ²΄λ₯Ό λλ€μ μμ νλΌλ―Έν°λ‘ λκΉ
- thisλ₯Ό ν΅ν΄ νΈμΆ κ°μ²΄μ μ κ·Ό
- thisλ μλ΅μ΄ κ°λ₯
- νΈμΆκ°μ²΄(caller) μμ²΄λ₯Ό λ°ν

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

- νΈμΆκ°μ²΄λ₯Ό νλΌλ―Έν°λ‘ λκΈ°λ λ°©μ, κ°μ²΄ μμ΄ μ¬μ©νλ λ°©μ λ λ€ κ°λ₯
- λΈλ­μ κ²°κ³Όκ°μ λ°ν

```kotlin
fun runTest(){
  var a = 10
  var b = 15

  //κ°μ²΄ μμ΄ μ¬μ©
  var result = run{
    var c = a+b
    c //return c
  }

  //κ°μ²΄ κΈ°λ°μΌλ‘ μ¬μ©
  result = result.run {plus(5)}
}
```

<br>

#### with()

- μΈμλ‘ λ°λ κ°μ²΄λ₯Ό λΈλ­μ νλΌλ―Έν°λ‘ μ λ¬
- run()ν¨μμ κΈ°λ₯μ κ±°μ λμΌ
- Safe Call μ§μνμ§ μμ -> μ’μ’ letκ³Ό ν¨κ» μ¬μ©λ¨

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

- also()λ₯Ό νΈμΆνλ κ°μ²΄(caller)λ₯Ό λλ€μ λΈλ­μ νλΌλ―Έν°λ‘ λκΉ
- itμ ν΅ν΄ νΈμΆκ°μ²΄μ μ κ·Ό
- νΈμΆκ°μ²΄ μμ²΄λ₯Ό λ°ν

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

#### λΉκ΅

| Parameter μ κ·Ό | νΈμΆκ°μ²΄(Caller)λ₯Ό λ¦¬ν΄ | μ½λλΈλ½ μ€ν κ²°κ³Ό λ¦¬ν΄ |
| -------------- | ----------------------- | ----------------------- |
| this           | apply                   | run, with               |
| it             | also                    | let                     |

<br>

## 5. μ λ€λ¦­

- ν΄λμ€λ ν¨μλ₯Ό μ μν  λ νμμ μ νμ§ μκ³  ν¬κ΄μ μΌλ‘ λ°μλ€μΌ μ μλ μνλ‘ λλ 

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

- νμ λ§€κ°λ³μμ μ νΉμ λ€μ΄ μΊμ€νμ ν  μ μλλ‘ μ§μλλ ν€μλ
- out: B<Sub νμ>μ B<Super νμ>μ λμν  μ μλλ‘ μ§μ
- in: C<Super νμ>μ C<Sub νμ>μ λμν  μ μλλ‘ μ§μ
- _: B<_>μ νμ μΈμκ° λ¬΄μμ΄λ  μκ΄μμ΄ λμν  μ μλλ‘ μ§μ

<br>

## 6. μ»¬λ μ

- immutable vs mutable
- List
- Set
- Map

<br>

#### List

- λ°μ΄ν°κ° μ­μ λκ±°λ μ μ₯λ  λ μμλ₯Ό μ§ν€λ μ»¬λ μ
- Mutableκ³Ό Immutable λͺ¨λ μ§μ

- List: Immutable

  - listOf<type>(item)
  - νμμλ΅κ°λ₯
  - getλ§ κ°λ₯
  - getterλ get(index) νΉμ [index]
  - indexOf(value)

- List: Mutable
  - mutableListOf<type>(item)
  - listOfμ λΉμ·νλ μΆκ° λ° μ­μ  κ°λ₯

<br>

#### Set

- μμ΄νλ€μ μμλ νΉλ³ν μ ν΄μ Έ μμ§ μμ
- null κ°λ₯

- Set: Immutable

  - setOf<type>(item)

- Set: Mutable
  - mutableSetOf<type>(item)

<br>

#### Map

- keyμ valueλ‘ μ μ₯. keyλ μ μΌνλ―λ‘ μ€λ³΅ νμ© μν¨
- Map: Immutable
  - mapOf<key type, value type>(item)
    - itemμ Pairκ°μ²΄λ‘ νν. Pair(A, B)
- Map: Mutable
  - mutableMapOf<key, value>(item)

<br>

#### Collection

- Listμ Setμ Collectionμ μμ

<br>

#### μ»¬λ μ API

`filter`, `map`, `all`, `any`, `count`, `find`, `groupBy`, `flatMap`, `flatten`

- filter

  - filter μμ μ‘°κ±΄λ¬Έμ λ§μ‘±νλ μμλ‘ μ΄λ€μ§ μλ‘μ΄ collection λ°ν

- map
  - κ° μμλ₯Ό μνλ ννλ‘ λ³ννκ³  λ³νν κ²°κ³Όλ₯Ό λͺ¨μ μ μ»¬λ μ λ°ν

```kotlin
val peoples = listOf(Person("Tom", 20), Person("David", 22))
//30μΈ μ΄μμΈ μ¬λ μ΄λ¦ λͺ©λ‘
val peoplesOver30 = peoples.filter{it.age >= 30}.map{it.name}

//λμ΄κ° κ°μ₯ λ§μμ¬λ μ΄λ¦
//λΉν¨μ¨μ 
var oldest = peoples.filter{it.age == peoples.maxByorNull(Person::age)!!.age}
//ν¨μ¨μ 
var maxAge = peoples.maxByorNull(Person::age)!!.age
oldest = peoples.filter {it.age == maxAge}
```

- filterμ mapμ Map(Key, Value)μ μ μ©
  - Key μΆμΆ : filterKeys, mapKeys
  - Value μΆμΆ : filterValues, mapValues

```kotlin
@RequiresApi(Build.VERSION_CODES.N)
fun mapToMap(){
  val numbers = mapOf(1 to "zero", 2 to "one")
  //keyλ μ κ³±μλ‘, valueλ λλ¬Έμλ‘ λ³ν
  val newMap = numbers.mapValues {it.value,uppercase()}.mapKeys {it.key*it.key}
  newMap.forEach{ println("${it.key} - ${it.value}") }
}
```

<br>

- all, any, count, find : μ»¬λ μ μ‘°κ±΄ ν¨μ

| μ΄λ¦  | μ€λͺ                                                         | λ¦¬ν΄νμ |
| ----- | ------------------------------------------------------------ | -------- |
| all   | collectionμ λͺ¨λ  μμκ° μ‘°κ±΄μ λ§μ‘±νλμ§ νλ¨              | Boolean  |
| any   | collectionμμμ€ μ‘°κ±΄μ λ§μ‘±νλ μμκ° νλλΌλ μλμ§ νλ¨ | Boolean  |
| count | μ‘°κ±΄μ λ§μ‘±νλ μμμ κ°μλ₯Ό λ°ν                           | Int      |
| find  | μ‘°κ±΄μ λ§μ‘±νλ μ²«λ²μ§Έ μμλ₯Ό λ°ν                           | <T>      |

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

- groupBy : λ¦¬μ€νΈλ₯Ό μ¬λ¬ κ·Έλ£ΉμΌλ‘ μ΄λ€μ§ λ§΅μΌλ‘ λ³κ²½

```kotlin
val map = people.groupBy{it.age}
map.forEach{
  println("${it.key}, ${it.value}")
}
```

<br>

- flatMap : λ¦¬μ€νΈμ μμλ€μ flatνκ² μ²λ¦¬ν λ μ°λ ν¨μ

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

- flatten : listμμ μ¬λ¬κ°μ lsitκ° ν¬ν¨λκ²½μ° flattenμ¬μ©μ νλμ listλ‘ λ°ν

```kotlin
val nestedList = listOf(listOf("abc", "def"), listOf("hij", "klm"))
val newMap = strings.flatten()
// [abc,def,hij,klm]
```

<br>

## 7. μμΈμ²λ¦¬

- μ½νλ¦°μ λͺ¨λ  μμΈ ν΄λμ€λ Throwableμ μμ ν΄λμ€

- Checked Exception κ°λμ΄ μγμ try~catchλ¬Έμ μμ±νμ§ μμλ λ¨

- AutoCloable μ²λ¦¬ : use μ¬μ©
  - Closeable μΈν°νμ΄μ€κ° κ΅¬νλ ν΄λμ€μ νν΄ use μ¬μ© κ°λ₯

<br>

## 8. νμ₯ ν¨μ

μ΄λ€ ν΄λμ€μ λ©€λ² λ©μλμΈκ²μ²λΌ νΈμΆν  μ μμ§λ§ κ·Έ ν΄λμ€μ λ°μ μ μΈλ ν¨μ

λ°λ‘ μμλ°μ§ μκ³  νλμ ν΄λμ€μ μΆκ°μ μΈ λ©μλλ₯Ό κ΅¬ννκ³  μΆμ λ μ¬μ©νλ ν¨μ

νμ₯ν¨μλ₯Ό ν΅ν΄ μλ‘μ΄ classλ₯Ό λ§λλ λ²κ±°λ‘μμ μ€μΌ μ μμ

<br>

## 9. λ¬Έμμ΄

μ¬λ¬μ€λ‘ κ΅¬μ±λ λ¬Έμμ΄μ """μΌλ‘ μλ€λ₯Ό κ°μΈ μ μΈν¨

κ·Έλ₯ μ¨λ λλ κ°λμ±μ΄ λ¨μ΄μ§κ²½μ° κ΅¬λΆμ(|) μμ κ³΅λ°±μΌλ‘ μ±μ°κ³  λ§μ§λ§μ trimMargein() μ¬μ©

```kotlin
val heroes = """
  |Spiderman
  |Ironman
  """.trimMargin()
```

λ¦¬μ€νΈ λ΄μ ν¬ν¨λ κ°λ€μ κ΅¬λΆμμ ν¨κ» νλμ λ¬Έμμ΄λ‘ νννκ³  μΆμκ²½μ° joinToString() μ¬μ©

<br>

## 10. μ°μ°μ μ€λ²λ‘λ©

μ°μ°μ μ€λ²λ‘λ© ν¨μλͺ μμ operator ν€μλ νμ

λΉκ΅μ°μ°μλ Anyμ μ μλ ν¨μμ΄λ―λ‘ override νμ (operatorλ μμ) : equals(), compareTo()

<br>
