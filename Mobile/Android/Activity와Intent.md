# 📚 Activity와 Intent

<br>

<br>

## 1. Activity

사용자에게 UI화면을 제공하는데 있어 가장 기본이 되는 앱 컴포넌트

안드로이드 앱은 최소 하나 이상의 Activity를 가져야 함

하나의 앱은 앱 최초 실행 시 보여지는 하나의 메인 액티비티를 가짐

액티비티에 다양한 컴포넌트들을 추가하여 다채로운 UI를 구성할 수 있음

<br>

## 2. Intent

Andtoid 어플리케이션을 구성하는 네가지 기본요소에는 Activity, Service, Broadcast Receiver, Content Provider가 있음

Intent는 해당 요소들간에 작업 수행을 위한 정보를 전달

Intent를 통해 메시지를 전달하고, 데이터를 주고받기도 함

인텐트는 명시적 인텐트 (Explicit Intent)와 암시적 인텐트 (Implicit Intent) 로 구분

<br>

## 3. 화면간 데이터 전달하기

#### Main activity와 Sub activity간 데이터 전달해보기

#### MainActivity

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("msg", "메인에서 왔습니다.")
            //그냥 다음화면으로 넘어가기 (stack 그대로 쌓임)
//            startActivity(intent)
            //다음화면에서 되돌아오도록 - 근데 이형식은 deprecated
//            startActivityForResult(intent, 10)
            //이 형식을 추천
            requestActivity.launch(intent)
        }
    }

    //deprecated
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Toast.makeText(this, "음..", Toast.LENGTH_SHORT).show()
//        if (resultCode == 10) {
//            Toast.makeText(this, data?.getStringExtra("result"), Toast.LENGTH_SHORT).show()
//        }
//    }

    private val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            Toast.makeText(this, intent?.getStringExtra("result"), Toast.LENGTH_SHORT).show()
        }
    }
}
```

#### SubActivity

```kotlin
class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val textView = findViewById<TextView>(R.id.textView2)
        val button =  findViewById<Button>(R.id.button2)

        val text = intent.getStringExtra("msg")
        textView.text = text

        button.setOnClickListener {
            val intentButton = Intent().apply{
                putExtra("result", "sub activity에서 보냅니다")
            }
            setResult(Activity.RESULT_OK, intentButton)
            finish()
        }
    }
}
```

<br>

## 4. Activity 생명주기

Activity는 메모리에 상주되며 소멸되기까지 생명주기(Life Cycle)를 가짐

`OnCreate()`, `OnStart()`, `OnResume()`, `OnPause()`, `OnStop()`, `OnDestroy()` 순으로 실행되며 경우에 따라 `OnReestart()` 메소드가 호출됨

<br>

#### onCreate()

- Activity가 생성될 때 호출되며 사용자 인터페이스 초기화에 사용되는 메서드
- 주로 View를 만드는 작업을 수행

<br>

#### onStart()

- Activity가 사용자에게 표시되기 직전에 호출됨
- onStop()과 짝을 이뤄 사용
- 예로, Service를 통해 Activity가 실행되는 중에만 음악을 재생하고 싶은 경우, onStart()에서 음악을 실행하고, onStop()에서 중단하면 됨
- 네트워크 통신을 위한 API 호출 관련 코드를 onStart()에서 주로 다룸

<br>

#### onResume()

- Activity가 사용자와 상호작용 하기 바로 전에 호출
- 함수들이 다 호출되고 나면 어플리케이션이 Activity Running 상태가 됨
- 이 시점에서 Activity는 Activity Stack의 최상단에 위치
- onPuase()와 짝지어 사용

<br>

#### onPause()

- Activity가 사용자에게 보이지 않을 때 호출
- onResume()과 짝지어 사용
- 예로, onPause()가 호출됐을 때 View나 데이터들을 Shared Preference를 통해 저장한 후, Activity가 재개되어 onResume()이 호출되면 해당 View와 데이터들을 불러옴
- 비디오 중지시, 중지 지점을 onPause()에서 저장할 수 있음

<br>

#### onStop()

- Activity가 더이상 사용자에게 보이지 않을 때 호출
- 이 경우 onRestart()를 거쳐 onStart()생명주기로 넘어가 다시 어플리케이션 실행 가능
- onStop은 아직 액티비티가 완전히 소멸된 상태는 아님
- Activity사용중 홈버튼을 눌렀을경우 onPause와 onStop이 순서대로 호출됨
- 아직 앱이 완전히 종료되지 않은 상태이기에 다시 앱을 실행 시키면 onRestart와 onStart가 호출됨

<br>

#### onDestroy()

- Activity를 완전히 종료, 즉 앱이 완전히 종료됐을 때 호출됨
- 앱을 다시 실행시키면 onCreate부터 시작
- finish()메소드가 호출되거나 시스템이 메모리확보를 위해 Activity를 제거할떄 호출됨

<br>

#### onRestart()

- onStop()이 호출됐다가 액티비티가 다시 화면에 보여질때 onRestart()를 거쳐 onStart()가 호출됨
- 비디오 재생중 홈화면으로 나갔다가 다시 돌아왔을 때 onRestart()에 필요한 작업들을 해줄 수 있음

<br>

## 5. LogCat을 이용한 로깅

VERBOSE(v) < DEBUG(d) < INFO(i) < WARNING(w) < ERROR(e)

로그 확인 시 지정한 로그 레벨 이상의 로그들 출력

Log.e(String tag, String message)

Log.e(String tag, String message, Throwable t)

<br>

## 6. ViewBinding 활용

findViewById 대신에 viewBinding을 써 더 안전하게 활용한다는거 같음

#### module의 build.gradle에 viewBinding속성 추가

```kotlin
kotlinOptions{
    jvmTarget = '1.8'
}
viewBinding{
    enable = true

}
```

#### MainActivity

```kotlin
class MainActivity: AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate는 xml의 뷰를 객체화 해준다 생각하면 됨
        binding = ActivityMainBinding.inflate(layoutInflater)
        //원래는 R.layout.activity_main을 넘겨주지만 우리가 생성한 루트 뷰를 넘겨주면 됨
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("msg", "메인에서 왔습니다.")
            requestActivity.launch(intent)
        }
    }
}
```
