# π Android κ°μ

<br>

<br>

## 1. μ¬μ©λλ μΈμ΄λ€

- Java : λ§μ΄ μ¬μ©νκ³  μμ
- Kotlin : νμ¬ μλλ‘μ΄λμμ μ£Όλ ₯μΌλ‘ μ¬μ©νλ μΈμ΄
- React : React Native λΌλ νλ μμν¬λ₯Ό ν΅ν΄ νμ΄λΈλ¦¬λ (μλλ‘μ΄λ, iOS λμ κ°λ°) μ±μ λ§λ€ μ μμ
- Dart : FlutterλΌλ νλ μμν¬λ₯Ό ν΅ν΄ νμ΄λΈλ¦¬λ (μλλ‘μ΄λ, iOSλμ κ°λ°) μ±μ λ§λ€ μ μμ

<br>

## 2. μλλ‘μ΄λ νλ«νΌ μν€νμ²

μλλ‘μ΄λμμλ Virtual Machineμ μ±λ§λ€ λ°λ‘ μμ

![image](https://developer.android.com/guide/platform/images/android-stack_2x.png?hl=ko)

- Linux Kernel

- HAL (Hardware Abstraction Layer, νλμ¨μ΄ μΆμν κ³μΈ΅)

- Native C/C++ Libraries

- Android Runtime

- Java API Framework : μλλ‘μ΄λ μ΄νλ¦¬μΌμ΄μ κ°λ°μ νμν APIλ₯Ό μ κ³΅

- System Apps : SMS νλ‘κ·Έλ¨, λ¬λ ₯, μ§λ, λΈλΌμ°μ λ±μ μ½μ΄ μ΄νλ¦¬μΌμ΄μμ μ κ³΅

<br>

## 3. App Component

<br>

#### μ»΄ν¬λνΈλ?

1. μ»΄ν¬λνΈλ μ±μ κ΅¬μ±λ¨μ, μ»΄ν¬λνΈ μ¬λ¬κ° μ‘°ν©νμ¬ νλμ μ±μ λ§λ¦

2. μ»΄ν¬λνΈλ μ± λ΄μμ λλ¦½μ μΈ μ€ν λ¨μ

   - μΌλ° ν΄λμ€μ λ¬λ¦¬ μλͺμ£ΌκΈ°λ₯Ό μλλ‘μ΄λ μμ€νμ΄ κ΄λ¦¬
   - λλ¦½μ μΈ μ€νλ¨μλΌλ λ»μ μ§μ  μ½λλ‘ κ²°ν©ν΄μ μ€ννλ κ²μ΄ μλλΌ, μ»΄ν¬λνΈ κ°μ IntentλΌλ κ²μ λ§€κ°λ‘ νμ¬ κ²°ν©νμ§ μμ μνμμ λλ¦½μ μΌλ‘ μ€ννλ κ΅¬μ‘°

3. Main ν¨μ κ°μ μ νλ¦¬μΌμ΄μμ μ§μ μ§μ μ΄ λ°λ‘ μμ

   - μμ΄μ½μ λλ₯΄μ§ μλλΌλ μΉ΄μΉ΄μ€ν‘μ΄ μ€λ©΄ μλ¦Όμ λλ¬ λ°λ‘ μ§μνλ λ°©λ²μ΄ μμ

<br>

#### μ»΄ν¬λνΈμ μ’λ₯

1. μ‘ν°λΉν° (Activity)

   - UIλ₯Ό κ΅¬μ±νκΈ° μν μ»΄ν¬λνΈ

2. μλΉμ€ (Service)

   - UIμμ΄ λ°±κ·ΈλΌμ΄λμμ μννλ μ»΄ν¬λνΈ.
   - μ) μμ

3. μ½ννΈ νλ‘λ°μ΄λ (Content Provider)

   - μ΄νλ¦¬μΌμ΄μ κ° λ°μ΄ν°λ₯Ό κ³΅μ νκΈ° μν μ»΄ν¬λνΈ

4. λΈλ‘λμΊμ€νΈ λ¦¬μλ² (Broadcast Receiver)
   - μ΄λ²€νΈλ‘ μνλλ μ»΄ν¬λνΈ
   - μ) μμ€νμ λ°°ν°λ¦¬κ° λΆμ‘±νκ±°λ μμ€ν λΆνμ΄ μλ£λλ λ±μ μ΄λ²€νΈ λ°μμ ν΄μΌλ  μμμ΄ μλ€λ©΄, μ΄λ²€νΈλ₯Ό μμ νλ μ»΄ν¬λνΈ

<br>

## 4. λλ ν°λ¦¬ νμΌ κ΅¬μ‘°

- AndroidManifest.xml

  - μ±μ λ©μΈ νκ²½ νμΌ
  - λͺ¨λ  activity, serviceλ±μ μ»΄ν¬λνΈλ manifestμ λ±λ‘λμ΄μΌν¨! λ¨, μμΈ broadcast

- MainActivity.java

  - νλ©΄ κ΅¬μ±μ μν μ‘ν°λΉν° μ»΄ν¬λνΈλ‘ μ€μ  μ΄ νμΌμ΄ μνλμ΄ νλ©΄μ UIκ° μΆλ ₯λ¨

- res
  - μ±μ λͺ¨λ  λ¦¬μμ€ νμΌμ res ν΄λ νμμ μμΉ
  - res/drawable : λ¦¬μμ€ μ€ μ΄λ―Έμ§ νμΌμ μ μ₯νκΈ° μν ν΄λ
  - res/layout : λ¦¬μμ€ μ€ UI κ΅¬μ±μ μν λ μ΄μμ XML νμΌμ μν ν΄λ
  - res/minmap : λ¦¬μμ€ μ€ μ±μ μμ΄μ½ μ΄λ―Έμ§λ₯Ό μν ν΄λ
  - res/values : λ¦¬μμ€ μ€ λ¬Έμμ΄ κ° λ±μ μν ν΄λ

<br>

## 5. κ°λ¨ν μ½λ μ΄ν΄λ³΄κΈ°

| java            | kotlin            |
| --------------- | ----------------- |
| extends         | :                 |
| method          | fun               |
| Nullable        | ?                 |
| DataType λ³μλͺ | λ³μλͺ : DataType |

Bundle μ keyμ valueμ μμΌλ‘ μ΄λ€μ§

Bundle μ keyμ valueμ μμΌλ‘ μ΄λ€μ§

μ±μ ν­μ μμ€νμ μν΄ killλ  μ μμμ λͺμ¬νκ³  κ°λ°ν΄μΌν¨! savedInstance λ μ£½μλ€ λ³΅κ΅¬λμμλ μ°λκ²μ μλ―Έ

<br>

#### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

- AppCompatActivityλ₯Ό μμλ°μ μ¬μ© (AppCompatActivityλ Activityμ μλΈ ν΄λμ€)

- onCreate()
  - μ‘ν°λΉν°κ° μ€νλ λ μμ€νμ΄ νΈμΆνλ ν¨μ
  - setContentView() ν¨μλ₯Ό ν΅ν΄ νλ©΄μ μΆλ ₯
  - setContentViewλ νλ©΄μ κ·Έλ¦¬κ² λ€λ κ²μ΄κ³ , Rμ resν΄λλ₯Ό μλ―Έ (R.layout.activity_main : res/layout/activity_mainμ μλ―Έν¨)
  - μ¦ javaλλμμ resμ μ κ·Όν λ R.μ μ¬μ©νλκ²

<br>

#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

- ConstraintLayout

  - λ£¨νΈμμμΈ ContraintLayoutμ μμμ μμλ€μ μμΉλ₯Ό μλμ μΌλ‘ λ°°μΉ

- TextView
  - λ¬Έκ΅¬λ₯Ό νλ©΄μ νμνλ view
  - Viewκ°μ²΄λ₯Ό μμλ°μ

<br>

#### Gradle

- μλλ‘μ΄λ λΉλ μλν μμ€ν

- build.gradle νμΌμ ν΅ν΄ μ¬λ¬κ°μ§ νκ²½ μ€μ 

- νλ‘μ νΈλ₯Ό μν build.gradleκ³Ό λͺ¨λμ μν build.gradleμ΄ μ‘΄μ¬

`λͺ¨λμμ€μ Gradle`

```gradle
// μλλ‘μ΄λμ IDEμ μ€μ νλ €λ κΈ°λ₯ μ μΈ
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
}

// μ νλ¦¬μΌμ΄μ νκ²½ μ€μ 
android {
  //μ¬μ©νλ €λ μ»΄νμΌλ¬μ λ²μ 
    compileSdk 30

    defaultConfig {
      //μ±μ κ΅¬λ³νλ μλ³μλ‘ λͺ¨λ  μ±μμ μ λν¬ ν΄μΌν¨
        applicationId "com.ssafy.helloapplication"
        //μ±μ΄ λμνκΈ° μν΄ νμν μ΅μ λ²μ 
        minSdk 21
        //κ°λ°μ μ¬μ©νλ λΌμ΄λΈλ¬λ¦¬ λ²μ μΌλ‘ λλΆλΆ κ°λ°μμ μ μ΅μ  λ²μ  μ§μ 
        targetSdk 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

//μ± κ°λ°μ νμν λΌμ΄λΈλ¬λ¦¬ - mvnrepository.comμ ν΅ν΄μ μ λ³΄ νμΈ κ°λ₯
dependencies {

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}
```

μ΅μλ¨ ν­ Tools -> Kotlin -> Show Kotlin Bytecodeλλ₯΄λ©΄ javaλ‘ λ³νλ μ½νλ¦°μ λ³Ό μ μμ
