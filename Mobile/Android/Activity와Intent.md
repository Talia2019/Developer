# ๐ Activity์ Intent

<br>

<br>

## 1. Activity

์ฌ์ฉ์์๊ฒ UIํ๋ฉด์ ์ ๊ณตํ๋๋ฐ ์์ด ๊ฐ์ฅ ๊ธฐ๋ณธ์ด ๋๋ ์ฑ ์ปดํฌ๋ํธ

์๋๋ก์ด๋ ์ฑ์ ์ต์ ํ๋ ์ด์์ Activity๋ฅผ ๊ฐ์ ธ์ผ ํจ

ํ๋์ ์ฑ์ ์ฑ ์ต์ด ์คํ ์ ๋ณด์ฌ์ง๋ ํ๋์ ๋ฉ์ธ ์กํฐ๋นํฐ๋ฅผ ๊ฐ์ง

์กํฐ๋นํฐ์ ๋ค์ํ ์ปดํฌ๋ํธ๋ค์ ์ถ๊ฐํ์ฌ ๋ค์ฑ๋ก์ด UI๋ฅผ ๊ตฌ์ฑํ  ์ ์์

<br>

## 2. Intent

Andtoid ์ดํ๋ฆฌ์ผ์ด์์ ๊ตฌ์ฑํ๋ ๋ค๊ฐ์ง ๊ธฐ๋ณธ์์์๋ Activity, Service, Broadcast Receiver, Content Provider๊ฐ ์์

Intent๋ ํด๋น ์์๋ค๊ฐ์ ์์ ์ํ์ ์ํ ์ ๋ณด๋ฅผ ์ ๋ฌ

Intent๋ฅผ ํตํด ๋ฉ์์ง๋ฅผ ์ ๋ฌํ๊ณ , ๋ฐ์ดํฐ๋ฅผ ์ฃผ๊ณ ๋ฐ๊ธฐ๋ ํจ

์ธํํธ๋ ๋ช์์  ์ธํํธ (Explicit Intent)์ ์์์  ์ธํํธ (Implicit Intent) ๋ก ๊ตฌ๋ถ

<br>

## 3. ํ๋ฉด๊ฐ ๋ฐ์ดํฐ ์ ๋ฌํ๊ธฐ

#### Main activity์ Sub activity๊ฐ ๋ฐ์ดํฐ ์ ๋ฌํด๋ณด๊ธฐ

#### MainActivity

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("msg", "๋ฉ์ธ์์ ์์ต๋๋ค.")
            //๊ทธ๋ฅ ๋ค์ํ๋ฉด์ผ๋ก ๋์ด๊ฐ๊ธฐ (stack ๊ทธ๋๋ก ์์)
//            startActivity(intent)
            //๋ค์ํ๋ฉด์์ ๋๋์์ค๋๋ก - ๊ทผ๋ฐ ์ดํ์์ deprecated
//            startActivityForResult(intent, 10)
            //์ด ํ์์ ์ถ์ฒ
            requestActivity.launch(intent)
        }
    }

    //deprecated
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Toast.makeText(this, "์..", Toast.LENGTH_SHORT).show()
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
                putExtra("result", "sub activity์์ ๋ณด๋๋๋ค")
            }
            setResult(Activity.RESULT_OK, intentButton)
            finish()
        }
    }
}
```

<br>

## 4. Activity ์๋ช์ฃผ๊ธฐ

Activity๋ ๋ฉ๋ชจ๋ฆฌ์ ์์ฃผ๋๋ฉฐ ์๋ฉธ๋๊ธฐ๊น์ง ์๋ช์ฃผ๊ธฐ(Life Cycle)๋ฅผ ๊ฐ์ง

`OnCreate()`, `OnStart()`, `OnResume()`, `OnPause()`, `OnStop()`, `OnDestroy()` ์์ผ๋ก ์คํ๋๋ฉฐ ๊ฒฝ์ฐ์ ๋ฐ๋ผ `OnReestart()` ๋ฉ์๋๊ฐ ํธ์ถ๋จ

<br>

#### onCreate()

- Activity๊ฐ ์์ฑ๋  ๋ ํธ์ถ๋๋ฉฐ ์ฌ์ฉ์ ์ธํฐํ์ด์ค ์ด๊ธฐํ์ ์ฌ์ฉ๋๋ ๋ฉ์๋
- ์ฃผ๋ก View๋ฅผ ๋ง๋๋ ์์์ ์ํ

<br>

#### onStart()

- Activity๊ฐ ์ฌ์ฉ์์๊ฒ ํ์๋๊ธฐ ์ง์ ์ ํธ์ถ๋จ
- onStop()๊ณผ ์ง์ ์ด๋ค ์ฌ์ฉ
- ์๋ก, Service๋ฅผ ํตํด Activity๊ฐ ์คํ๋๋ ์ค์๋ง ์์์ ์ฌ์ํ๊ณ  ์ถ์ ๊ฒฝ์ฐ, onStart()์์ ์์์ ์คํํ๊ณ , onStop()์์ ์ค๋จํ๋ฉด ๋จ
- ๋คํธ์ํฌ ํต์ ์ ์ํ API ํธ์ถ ๊ด๋ จ ์ฝ๋๋ฅผ onStart()์์ ์ฃผ๋ก ๋ค๋ฃธ

<br>

#### onResume()

- Activity๊ฐ ์ฌ์ฉ์์ ์ํธ์์ฉ ํ๊ธฐ ๋ฐ๋ก ์ ์ ํธ์ถ
- ํจ์๋ค์ด ๋ค ํธ์ถ๋๊ณ  ๋๋ฉด ์ดํ๋ฆฌ์ผ์ด์์ด Activity Running ์ํ๊ฐ ๋จ
- ์ด ์์ ์์ Activity๋ Activity Stack์ ์ต์๋จ์ ์์น
- onPuase()์ ์ง์ง์ด ์ฌ์ฉ

<br>

#### onPause()

- Activity๊ฐ ์ฌ์ฉ์์๊ฒ ๋ณด์ด์ง ์์ ๋ ํธ์ถ
- onResume()๊ณผ ์ง์ง์ด ์ฌ์ฉ
- ์๋ก, onPause()๊ฐ ํธ์ถ๋์ ๋ View๋ ๋ฐ์ดํฐ๋ค์ Shared Preference๋ฅผ ํตํด ์ ์ฅํ ํ, Activity๊ฐ ์ฌ๊ฐ๋์ด onResume()์ด ํธ์ถ๋๋ฉด ํด๋น View์ ๋ฐ์ดํฐ๋ค์ ๋ถ๋ฌ์ด
- ๋น๋์ค ์ค์ง์, ์ค์ง ์ง์ ์ onPause()์์ ์ ์ฅํ  ์ ์์

<br>

#### onStop()

- Activity๊ฐ ๋์ด์ ์ฌ์ฉ์์๊ฒ ๋ณด์ด์ง ์์ ๋ ํธ์ถ
- ์ด ๊ฒฝ์ฐ onRestart()๋ฅผ ๊ฑฐ์ณ onStart()์๋ช์ฃผ๊ธฐ๋ก ๋์ด๊ฐ ๋ค์ ์ดํ๋ฆฌ์ผ์ด์ ์คํ ๊ฐ๋ฅ
- onStop์ ์์ง ์กํฐ๋นํฐ๊ฐ ์์ ํ ์๋ฉธ๋ ์ํ๋ ์๋
- Activity์ฌ์ฉ์ค ํ๋ฒํผ์ ๋๋ ์๊ฒฝ์ฐ onPause์ onStop์ด ์์๋๋ก ํธ์ถ๋จ
- ์์ง ์ฑ์ด ์์ ํ ์ข๋ฃ๋์ง ์์ ์ํ์ด๊ธฐ์ ๋ค์ ์ฑ์ ์คํ ์ํค๋ฉด onRestart์ onStart๊ฐ ํธ์ถ๋จ

<br>

#### onDestroy()

- Activity๋ฅผ ์์ ํ ์ข๋ฃ, ์ฆ ์ฑ์ด ์์ ํ ์ข๋ฃ๋์ ๋ ํธ์ถ๋จ
- ์ฑ์ ๋ค์ ์คํ์ํค๋ฉด onCreate๋ถํฐ ์์
- finish()๋ฉ์๋๊ฐ ํธ์ถ๋๊ฑฐ๋ ์์คํ์ด ๋ฉ๋ชจ๋ฆฌํ๋ณด๋ฅผ ์ํด Activity๋ฅผ ์ ๊ฑฐํ ๋ ํธ์ถ๋จ

<br>

#### onRestart()

- onStop()์ด ํธ์ถ๋๋ค๊ฐ ์กํฐ๋นํฐ๊ฐ ๋ค์ ํ๋ฉด์ ๋ณด์ฌ์ง๋ onRestart()๋ฅผ ๊ฑฐ์ณ onStart()๊ฐ ํธ์ถ๋จ
- ๋น๋์ค ์ฌ์์ค ํํ๋ฉด์ผ๋ก ๋๊ฐ๋ค๊ฐ ๋ค์ ๋์์์ ๋ onRestart()์ ํ์ํ ์์๋ค์ ํด์ค ์ ์์

<br>

## 5. LogCat์ ์ด์ฉํ ๋ก๊น

VERBOSE(v) < DEBUG(d) < INFO(i) < WARNING(w) < ERROR(e)

๋ก๊ทธ ํ์ธ ์ ์ง์ ํ ๋ก๊ทธ ๋ ๋ฒจ ์ด์์ ๋ก๊ทธ๋ค ์ถ๋ ฅ

Log.e(String tag, String message)

Log.e(String tag, String message, Throwable t)

<br>

## 6. ViewBinding ํ์ฉ

findViewById ๋์ ์ viewBinding์ ์จ ๋ ์์ ํ๊ฒ ํ์ฉํ๋ค๋๊ฑฐ ๊ฐ์

#### module์ build.gradle์ viewBinding์์ฑ ์ถ๊ฐ

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
        //inflate๋ xml์ ๋ทฐ๋ฅผ ๊ฐ์ฒดํ ํด์ค๋ค ์๊ฐํ๋ฉด ๋จ
        binding = ActivityMainBinding.inflate(layoutInflater)
        //์๋๋ R.layout.activity_main์ ๋๊ฒจ์ฃผ์ง๋ง ์ฐ๋ฆฌ๊ฐ ์์ฑํ ๋ฃจํธ ๋ทฐ๋ฅผ ๋๊ฒจ์ฃผ๋ฉด ๋จ
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("msg", "๋ฉ์ธ์์ ์์ต๋๋ค.")
            requestActivity.launch(intent)
        }
    }
}
```
