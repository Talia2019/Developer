### 다트의 삼항연산자
```
var visibility = isPubllic ? 'public' : 'private';
String playerName(String name) => name ?? 'Guest';
```

### 다트 프로그램 예
```
//함수정의
printInteger(int aNum){
  print('The number is $aNum);
}

//main()함수에서 시작
main(){
  var num = 1;
  printInteger(1);
}
```
> The number is 1

### Null safety
```
//pubspec.yaml
//environment:
//  sdk: ">=2.12.0 <3.0.0"

int? coundReturnNull() => -3                           //null을 넣을 수 있음

void main(){
  int? couldBeNull = 1;                                //null로 변경 가능
  List<int?> listThatCouldHoldNulls = [2, null, 4];    //List의 int에 null 포함 가능
  List<int>? nullsList;                                //List자체가 null일 수 있음

  int b = listThatCouldHoldNulls.first!;               //null이 아님을 직접 표시
  int c = coundReturnNull().abs();                     //null일수도 있으므로 abs()에서 오류
}
```

### 비동기 처리
```
void main(){
  checkVersion();
  print('end process');
}
Future checkVersion() async{
  var version = await lookUpVersion();
  print(version);
}
int lookUpVersion(){
  return 12;
}
```
> end process<br/>
> 12

함수안에 await가 붙어있으면 해당 함수를 main함수의 나머지 실행 후 실행하게됨

```
void main() async {
  await getVersionName().then((value) => {
    print(value)
  });
  print('end process');
}
Future<String> getVersionName() async {
  var versionName = await lookUpVersion();
  return versionName;
}
String lookUpVersion(){
  return 'Android Q';
}
```
> Android Q<br/>
> end process

### 다트와 스레드
```
void main() {
  printOne();
  printTwo();
  printThree();
}
void printOne() {
  print('One');
}
void printTwo() {
  Future.delayed(Duration(seconds: 1), () {    //Duration기간동안 기다린 후 진행. seconds/minutes/milliseconds 등
    print('Future');
  });
  print('Two');
}
void printThree() {
  print('Three');
}
```
> One<br/>Two<br/>Three<br/>Future

```
void main() {
  printOne();
  printTwo();
  printThree();
}
void printOne() {
  print('One');
}
void printTwo() async {
  await Future.delayed(Duration(seconds: 1), () {    //Duration기간동안 기다린 후 진행. seconds/minutes/milliseconds 등
    print('Future');
  });
  print('Two');
}
void printThree() {
  print('Three');
}
```
> One<br/>Three<br/>Future<br/>Two

await가 붙었으므로 해당 함수를 벗어나 main의 나머지를 모두 실행 후 await붙은 코드부터 차례대로 실행

### JSON주고받기

```
// JSON 데이터 디코딩
import 'dart:convert';

void main() {
  var jsonString = '''
    [{'score': 40}, {'score': 40}]
  ''';

  var scores = jsonDecode(jsonString);
  print(scores is List);                //true
  var firstScore = scores[0]  
  print(firstScore is Map);             //true
  print(firstScore['score'] == 40)      //true
}
```

```
// JSON 데이터 인코딩
import 'dart:convert';

void main() {
  var scores = [
      {'score': 40},
      {'score': 80}
      {'score': 100, 'overtime':true, 'special_guest':null}
    ];

  var jsonText = jsonEncode(scores);
}
```

### 스트림 통신
순서를 보장받고 싶을 때 스트림을 이용 (선입선출구조)
```
import 'dart:async';

Future<int> sumStream(Stream<int> stream) async {
  var sum = 0;
  await for (var value in stream){
    print('sumStream: $value');
    sum += value;
  }
  return sum;
}

Stream<int> countStream(int to) async* {
  for(int i = 0;i <= to; i++){
    print('countStream: $i');
    yield i;
  }
}

main() async {
  var stream = countStream(10);
  var sum = await sumStream(stream);
  print(sum);   //55
}
```
> countStream: 1</br>
sumStream: 1</br>
countStream: 2</br>
sumStream: 2</br>
... </br>
countStream: 10</br>
sumStream: 10</br>
55

async* 명령어는 앞으로 yield를 이용해 지속적으로 데이터를 전달하겠다는 의미
위 코드에서 yeild는 int형 i를 반환하는데, return은 한번 반환시 함수가 끝나지만 yield는 반환후에도 계속 함수를 유지함
이렇게 받은 yield값을 인자로 sumStream()을 호출하면 값이 전달될때마다 sum변수에 누적해서 반환해줌

(흐름을 잘 모르겠네..)

```
main() {
  var stream = Stream.fromIterable([1, 2, 3, 4, 5]);

  stream.first.then((value) => print('first: $value'));        //1
  stream.last.then((value) => print('last: $value'));          //5
  stream.isEmpty.then((value) => print('isEmpty: $value'));    //false
  stream.length.then((value) => print('length: $value'));      //5

  //하지만 스트림을 통해 데이터를 사용하면 데이터는 사라지기 때문에 위 코드는 에러 발생. 네줄 중 하나만 실행해야 함
}
```
스트림은 실시간으로 서버를 살펴보다가 서버에서 데이터가 변경되면 화면을 새로고침하지 않더라도 자동으로 변경된 데이터가 반영되어야 할 때 사용할 수 있는 유용한 클래스

### 구구단
```
void main() {
  int i, j;

  for(i=2; i<=9; i++){
    for(j=2; j<=9; j++){
      print('$i * $j = ${i*j}');
    }
  }
}
```

### 클래스 구현
```Flutter
class Car{
  int maxSpeed;
  num price;
  String name;

  Car(int maxSpeed, num price, String name){
    this.maxSpeed = maxSpeed;
    this.price = price;
    this.name = name;
  }
}
```











