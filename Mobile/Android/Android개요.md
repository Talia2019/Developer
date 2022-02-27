# 📚 Android 개요

<br>

<br>

## 1. 사용되는 언어들

- Java : 많이 사용하고 있음
- Kotlin : 현재 안드로이드에서 주력으로 사용하는 언어
- React : React Native 라는 프레임워크를 통해 하이브리드 (안드로이드, iOS 동시 개발) 앱을 만들 수 있음
- Dart : Flutter라는 프레임워크를 통해 하이브리드 (안드로이드, iOS동시 개발) 앱을 만들 수 있음

<br>

## 2. 안드로이드 플랫폼 아키텍처

안드로이드에서는 Virtual Machine은 앱마다 따로 있음

![image](https://developer.android.com/guide/platform/images/android-stack_2x.png?hl=ko)

- Linux Kernel

- HAL (Hardware Abstraction Layer, 하드웨어 추상화 계층)

- Native C/C++ Libraries

- Android Runtime

- Java API Framework : 안드로이드 어플리케이션 개발시 필요한 API를 제공

- System Apps : SMS 프로그램, 달력, 지도, 브라우저등의 코어 어플리케이션을 제공

<br>

## 3. App Component

<br>

#### 컴포넌트란?

1. 컴포넌트는 앱의 구성단위, 컴포넌트 여러개 조합하여 하나의 앱을 만듦

2. 컴포넌트는 앱 내에서 독립적인 실행 단위

   - 일반 클래스와 달리 생명주기를 안드로이드 시스템이 관리
   - 독립적인 실행단위라는 뜻은 직접 코드로 결합해서 실행하는 것이 아니라, 컴포넌트 간에 Intent라는 것을 매개로 하여 결합하지 않은 상태에서 독립적으로 실행하는 구조

3. Main 함수 같은 애플리케이션의 진입 지점이 따로 없음

   - 아이콘을 누르지 않더라도 카카오톡이 오면 알림을 눌러 따로 진입하는 방법이 있음

<br>

#### 컴포넌트의 종류

1. 액티비티 (Activity)

   - UI를 구성하기 위한 컴포넌트

2. 서비스 (Service)

   - UI없이 백그라운드에서 수행하는 컴포넌트.
   - 예) 음악

3. 콘텐트 프로바이더 (Content Provider)

   - 어플리케이션 간 데이터를 공유하기 위한 컴포넌트

4. 브로드캐스트 리시버 (Broadcast Receiver)
   - 이벤트로 수행되는 컴포넌트
   - 예) 시스템에 배터리가 부족하거나 시스템 부팅이 완료되는 등의 이벤트 발생시 해야될 작업이 있다면, 이벤트를 수신하는 컴포넌트

<br>

## 4. 디렉터리 파일 구조

- AndroidManifest.xml

  - 앱의 메인 환경 파일
  - 모든 activity, service등의 컴포넌트는 manifest에 등록되어야함! 단, 예외 broadcast

- MainActivity.java

  - 화면 구성을 위한 액티비티 컴포넌트로 실제 이 파일이 수행되어 화면에 UI가 출력됨

- res
  - 앱의 모든 리소스 파일은 res 폴더 하위에 위치
  - res/drawable : 리소스 중 이미지 파일을 저장하기 위한 폴더
  - res/layout : 리소스 중 UI 구성을 위한 레이아웃 XML 파일을 위한 폴더
  - res/minmap : 리소스 중 앱의 아이콘 이미지를 위한 폴더
  - res/values : 리소스 중 문자열 값 등을 위한 폴더

<br>

## 5. 간단한 코드 살펴보기

| java            | kotlin            |
| --------------- | ----------------- |
| extends         | :                 |
| method          | fun               |
| Nullable        | ?                 |
| DataType 변수명 | 변수명 : DataType |

Bundle 은 key와 value의 쌍으로 이뤄짐

Bundle 은 key와 value의 쌍으로 이뤄짐

앱은 항상 시스템에 의해 kill될 수 있음을 명심하고 개발해야함! savedInstance 는 죽었다 복구되었을때 쓰는것을 의미

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

- AppCompatActivity를 상속받아 사용 (AppCompatActivity는 Activity의 서브 클래스)

- onCreate()
  - 액티비티가 실행될때 시스템이 호출하는 함수
  - setContentView() 함수를 통해 화면을 출력
  - setContentView는 화면을 그리겠다는 것이고, R은 res폴더를 의미 (R.layout.activity_main : res/layout/activity_main을 의미함)
  - 즉 java돌더에서 res에 접근할때 R.을 사용하는것

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

  - 루트요소인 ContraintLayout은 자식의 요소들의 위치를 상대적으로 배치

- TextView
  - 문구를 화면에 표시하는 view
  - View객체를 상속받음

<br>

#### Gradle

- 안드로이드 빌드 자동화 시스템

- build.gradle 파일을 통해 여러가지 환경 설정

- 프로젝트를 위한 build.gradle과 모듈을 위한 build.gradle이 존재

`모듈수준의 Gradle`

```gradle
// 안드로이드의 IDE에 설정하려는 기능 선언
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
}

// 애플리케이션 환경 설정
android {
  //사용하려는 컴파일러의 버전
    compileSdk 30

    defaultConfig {
      //앱을 구별하는 식별자로 모든 앱에서 유니크 해야함
        applicationId "com.ssafy.helloapplication"
        //앱이 동작하기 위해 필요한 최소 버전
        minSdk 21
        //개발시 사용하는 라이브러리 버전으로 대부분 개발시점의 최신 버전 지정
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

//앱 개발시 필요한 라이브러리 - mvnrepository.com을 통해서 정보 확인 가능
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

최상단 탭 Tools -> Kotlin -> Show Kotlin Bytecode누르면 java로 변환된 코틀린을 볼 수 있음
