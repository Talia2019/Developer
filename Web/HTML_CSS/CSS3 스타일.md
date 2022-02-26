# CSS3 스타일

<br>

## 목차

1. CSS3 단위
2. 가시 속성
3. 박스 속성
4. 테두리 속성
5. 배경 속성
6. 폰트 속성
7. 위치 속성
8. float 속성
9. 그림자 속성
10. 그레이디언트

<br>

## 1. CSS3 단위

- 키워드

  각 스타일 속성에 따라 별도의 키워드가 존재

<br>

- 크기 단위

  | 단위 | 설명        |
  | ---- | ----------- |
  | %    | 백분율 단위 |
  | em   | 배수 단위   |
  | px   | 픽셀        |

  %, em은 상대적으로 크기 지정할 때, px는 절대적으로 크기 지정할 때 사용

<br>

- 색상 단위

  | 단위 형태                            | 설명           |
  | ------------------------------------ | -------------- |
  | #000000                              | HEX 코드 단위  |
  | rgb(red,green,blue)                  | RGB 색상 단위  |
  | rgba(red,green,blue,alpha)           | RGBA 색상 단위 |
  | hsl(hue,saturation,lightness)        | HSL 색상 단위  |
  | hsla(hue,saturation,lightness,alpha) | HSLA 색상 단위 |

  HEX코드단위는 RGB색상 단위를 짧게 입력하는 방법
  HSL : 색상, 채도, 명도

<br>

- URL 단위

```
<style>
  body{
    //현재 폴더
    background-image: url('Desert.jpg');

    //현재 폴더 내부의 Other 폴더
    background-image: url('Other/Desert.jpg');

    //루트 폴더
    background-image: url('/Desert.jpg');
  }
</style>
```

<br>

## 2. 가시 속성

태그가 화면에 보이는 방식을 지정

- display 속성

  display 속성에 사용 가능한 키워드

  | 키워드 이름  | 설명                              |
  | ------------ | --------------------------------- |
  | none         | 태그를 화면에서 보이지 않게 함    |
  | block        | 태그를 block 형식으로 지정        |
  | inline       | 태그를 inline 형식으로 지정       |
  | inline-block | 태그를 inline-block 형식으로 지정 |

  inline은 width 속성과 height속성이 적용되지 않고, margin속성이 div태그의 좌우로만 지정됨

  block과 inline-block 은 width, height, margin 속성 모두 적용됨

<br>

- visibility 속성

  대상을 보이거나 보이지 않게 지정

  | 키워드 이름 | 설명                        |
  | ----------- | --------------------------- |
  | visible     | 태그를 보이게 함            |
  | hidden      | 태그를 보이지 않게 함       |
  | collapse    | table 태그를 보이지 않게 함 |

display 속성을 사용하면 태그가 화면에서 제거됨

visibility 속성을 사용하면 화면에서 보이지 않을 뿐 공간은 차지함

- opacity 속성

  투명도를 조절. 0.0~1.0

  0.0은 투명한 상태, 1.0은 불투명 상태

<br>

## 3. 박스 속성

<img src="https://mblogthumb-phinf.pstatic.net/MjAxOTA1MjRfMjU2/MDAxNTU4NzA3NDAyOTgy.mH4Bif8OF6xWgDF6NdPupM7ofE48ivibdFVL7TEXzMYg.d7HL4-rFv24ZJrdYt5EXfW-WLrl7V8eJWGwR3eTdPC8g.JPEG.zlatmgpdjtiq/bandicam_2019-05-24_23-16-11-846.jpg?type=w800" width=400px>

박스 속성 : margin, border, height, width 속성

여기서 border(테두리) 는 4장에서 살펴볼것

<br>

### 3-1. width/height 속성

글자를 감싸는 영역의 크기를 지정하는 스타일 속성

```
<style>
  div{
    width: 100px;
    height: 100px;
  }
</style>
```

<br>

### 3-2. margin/padding 속성

```
<style>
  div{
    margin: 10px;
    padding: 30px;
  }
</style>
```

전체 너비 = width + 2\*(margin + border + padding)

전체 높이 = height + 2\*(margin + border + padding)

```
margin : [margin-top] [margin-right] [margin-bottom] [margin-left]

또는

margin : [margin-top&bottom] [margin-left&right]

<style>
  div{
    margin: 0 30px;
    padding: 0 30px;
    //0의 경우 단위 안붙여도 됨
  }
</style>
```

<br>

### 3-3. box-sizing 속성

width 속성과 height 속성이 차지하는 범위를 지정

```
<style>
  div:first-child{
    background: red;
    box-sizing: content-box;
  }

  div:last-child{
    background: red;
    box-sizing: border-box;
  }
</style>
```

- content-box

  기본적으로 적용되는 키워드. width 속성과 height 속성이 글자가 들어가는 영역의 크기를 지정하게 됨

  박스 너비 = width + 2\*(margin + border + padding)

  박스 높이 = height + 2\*(margin + border + padding)

- border-box

  width 속성과 height 속성이 테두리를 포함한 영역의 크기를 지정하게 만듦

  박스 너비 = width + 2\*margin

  박스 높이 = height + 2\*margin

<br>

## 4. 테두리 속성

<br>

### 4-1. border-width 속성과 border-style 속성

border-width : 테두리 너비 지정

border-style : 테두리 형태 지정

테두리를 넣을 때는 border-width, border-style, border-color 속성을 모두 사용해야 함

```
<style>
  .box{
    border-width: thick;
    border-style: dashed;
    border-color: black;
  }

  또는

  .box{
    border: thick dashed black;
  }

  띄어쓰기로 구분
</style>
```

<style>
  .box{
    border-width: thick;
    border-style: dashed;
    border-color: black;
  }
</style>
<div class="box"> Hello</div>

<br>

### 4-2. border-radius 속성

테두리가 둥근 사각형/원을 만들 수 있음

```
<style>
  .box{
    border: thick dashed black;
    border-radius: 20px;
  }

  또는

  .box{
    border: thick dashed black;
    //왼위 오른위 오른아래 왼아래
    border-radius: 50px 40px 20px 10px;
  }

  다 따로 줄 수 있음
</style>
```

<style>
  .box2{
    border: thick dashed black;
    border-radius: 20px;
  }

  .box3{
    border: thick dashed black;
    border-radius: 50px 40px 20px 10px;
  }
</style>

<div class="box2">Hello</div>
<br>
<div class="box3">Hello</div>

<br>

## 5. 배경 속성

특정 태그의 배경 이미지 또는 색상을 지정

<br>

### 5-1. background-image 속성

배경에 넣을 그림을 지정. URL 단위 또는 그레이디언트 등을 입력

```
<style>
  body{
    background-image: url('Background.png'), url('Background2.png');
  }
</style>
```

여러개의 배경이미지를 적용할 수도 있음. 이 경우 왼쪽의 이미지가 앞으로 나옴(층을 이루게 됨)

<br>

### 5-2. background-size 속성

그림 크기 조절 (익스플로러 8 이하 사용 불가)

```
<style>
  body{
    background-image: url('Background.png'), url('Background2.png');
    background-size: 100%; // 너비를 꽉 채움
    background-size: 100% 250px; // 높이가 250픽셀이 됨
    background-size: 100%, 200%; // 쉼표로 구분하면 각각의 배경에 서로 다른 크기를 적용하는 것
  }
</style>
```

background-size 속성에 contain 키워드와 cover키워드를 적용할 수 있음

- contain : 너비를 100% 적용한 것 같은 효과

- cover : 높이를 100% 적용한 것 같은 효과

<br>

### 5-3. background-repeat 속성

그림이 반복되도록(패턴을 이루도록). 기본키워드가 repeat 으로 되어 있음

- repeat : 이미지가 패턴을 이룸
- repeat-x : X축 방향으로 이미지 반복
- repeat-y : Y축 방향으로 이미지 반복
- no-repeat : 반복 없음

<br>

### 5-4. background-attachment 속성

배경화면을 어떤 방식으로 화면에 붙일 것인지

- scroll : 기본 키워드. 화면 스크롤에 따라 배경 이미지가 함께 이동함 (그림을 넘어가면 안보이게 됨)
- fixed : 스크롤을 내려도 배경이미지가 고정됨 (그림을 넘어가도 그림이 계속 보임)

<br>

### 5-5. background-position 속성

다음과 같은 형태로 값을 적용

- background-position: 키워드;
- background-position: X축크기;
- background-position: X축크기 Y축크기;

스프라이트 이미지를 만들 때 자주 사용

<br>

## 6. 폰트 속성

글자와 관련된 스타일 속성

<br>

### 6-1. font-size 속성

글자의 크기를 지정하는 스타일 속성

h1의 기본크기는 32픽셀, p의 기본크기는 16픽셀

<br>

### 6-2. font-family 속성

폰트를 지정하는 스타일 속성

한단어로 이뤄진 폰트는 따옴표를 사용하지 않으나, 두단어 이상으로 이뤄진 단어는 따옴표를 반드시 사용해야 함

```
<style>
  .font_arial{ font-family: Arial; }
  .font_roman{ font-family: 'Times New Roman'; }
</style>
```

사용자에게 개발 시 사용한 폰트가 설치되어 있지 않은 경우를 대비하여 font-family속성을 여러개 사용 가능

```
<style>
  .font_arial{ font-family: 'No Font', Arial; }
  .font_roman{ font-family: 'No Font', 'Times New Roman'; }
</style>
```

하지만 다국어 웹페이지를 제공할 경우 사용자에게 무슨 폰트가 있는지 일일히 확인 불가능.

-> font-family속성의 가장 마지막 폰트에는 Serif 폰트(명조체), Sans-serif 폰트(고딕체), Mono space 폰트(고정폭 글꼴)를 적용

이 폰트는 웹브라우저에서 지정하는 폰트로 generic-family폰트라 부름

```
<style>
  .font_arial{ font-family: 'No Font', sans-serif; }
  .font_roman{ font-family: 'No Font', serif; }
  //익스는 sans-serif, serif에 따옴표 적용하면 안됨
</style>
```

<br>

### 6-3. font-style 속성과 font-weight 속성

폰트의 기울기 또는 두께를 조정하는 스타일 속성

일반 폰트의 두께는 400이고 두꺼운 폰트의 두께는 700. 두께를 지원하지 않는 폰트는 font-weight 속성을 사용해 두께를 조절할 수 없음

<br>

### 6-4. line-height 속성

글자의 높이 지정. 글자를 수직 중앙 정렬할 때 사용

CSS에 block 형식의 태그를 수직 정렬할 수 있는 스타일 속성이 없기에, line-height속성을 사용

```
.test_btn, .test_btn2{
  width: 150px;
  height: 70px;
  font-size: 2em;
  font-weight: bold;
  text-align: center;
  background-color: #FF6A00;
  border-radius: 30px;
  margin-bottom: 4px;
}

.test_btn > a{
  display: block;
}
.test_btn2 > a{
  display: block;
  line-height: 70px;
}
```

<style>
.test_btn, .test_btn2{
  width: 150px;
  height: 70px;
  font-size: 2em;
  font-weight: bold;
  text-align: center;
  background-color: #FF6A00;
  border-radius: 30px;
  margin-bottom: 4px;
}

.test_btn > a{
  display: block;
  /* line-height: 70px; */
}
.test_btn2 > a{
  display: block;
  line-height: 70px;
}
</style>

<div class="test_btn"> <a>Click</a></div>
<div class="test_btn2"> <a>Click</a></div>

<br>

### 6-5. text-align 속성

글자의 정렬

단, span태그같이 inline 형식을 갖는 태그는 너비가 존재하지 않아 중앙이라는 개념이 없음. 그러므로 inline 형식 태그는 text-align 속성 사용 불가능

<br>

### 6-6. text-decoration 속성

a 태그에 href 속성을 사용하면 밑줄이 생기는데, text-decoration을 사용해 밑줄 제거 가능

```
a{
  text-decoration: none;
}
```

<br>

## 7. 위치 속성

- 절대 위치 좌표 : 요소의 X좌표와 Y좌표를 설정해 절대 위치를 지정
- 상대 위치 좌표 : 요소를 입력한 순서를 통해 상대적으로 위치를 지정

일반적으로 절대 위치 좌표는 특정 크기의 영역을 지정한 태그 내부에서만 사용

<br>

### 7-1. position 속성

HTML 태그의 위치 설정 방법을 변경할 때는 position 속성 사용

상대 위치 좌표를 사용 할 때는 position 속성에 static 키워드 또는 relative 키워드 적용

- static : 위에서 아래로, 왼쪽에서 오른쪽으로 순서에 맞게 배치 (direction으로 오->왼으로 변경 가능)
- relative : static 키워드로 초기 위치 지정된 상태에서 상하좌우로 이동 가능

절대 위치 좌표를 사용 할 때는 position 속성에 absolute 키워드 또는 fixed 키워드 적용

| 키워드   | 설명                                    |
| -------- | --------------------------------------- |
| static   | 태그가 위에서 아래로 순서대로 배치      |
| relative | 초기 위치 상태에서 상하좌우로 위치 이동 |
| absolute | 절대 위치 좌표 설정                     |
| fixed    | 화면을 기준으로 절대 위치 좌표 설정     |

기본적으로 HTML 페이지의 뒤에 입력한 태그가 상위에 올라감

<br>

### 7-2. z-index 속성

숫자를 적용하며 숫자가 클수록 앞에 위치

반드시 앞에 있어야 하는 태그면 9999등 매우 큰 값을 적용하면 됨. 단, 너무 크게 적용하면 웹브라우저가 인식 못할수도 있음

<br>

### 7-3. 위치 속성과 관련된 공식

중요!

1. position 속성에 absolute를 적용하면 부모 태그가 영역을 차지하지 않음

&#8594; "자손의 position 속성에 absolute 키워드를 적용하면 부모는 height 속성을 사용합니다"

<br>

2.  position 속성에 absolute를 적용하면 자식이 부모 태그를 기준으로 위치를 잡지 않음

&#8594; "자손의 position 속성에 absolute 키워드를 적용하면 부모의 position 속성에 relative 키워드를 적용합니다"

<br>

### 7-4. overflow 속성

내부의 요소가 부모의 범위를 벗어날 때 어떻게 처리할지 지정하는 속성

| 키워드 이름 | 설명                                    |
| ----------- | --------------------------------------- |
| hidden      | 영역을 벗어나는 부분을 보이지 않게 만듦 |
| scroll      | 영역을 벗어나는 부분을 스크롤로 만듦    |

애니메이션을 구현할 때나, float속성의 부유를 막기 위한 방법으로 주로 사용

<br>

## 8. float 속성

웹페이지를 만들 때 가장 많이 사용하는 스타일 속성

| 키워드 | 설명                 |
| ------ | -------------------- |
| left   | 태그를 왼쪽에 붙임   |
| right  | 태그를 오른쪽에 붙임 |

<br>

### 8-1. float 속성 개요

float 속성은 부유하는 대상을 만들 때 사용하는 스타일 속성

이미지를 부유시키거나, 웹페이지 레이아웃 만들 때 많이 사용

<br>

### 8-2. float 속성을 사용한 수평 정렬

left를 사용하면 1,2,3 식으로 왼쪽부터 붙음

right를 사용하면 3,2,1 식으로 오른쪽부터 붙음

<br>

### 8-3. float 속성을 사용한 레이아웃 구성

"자손에 float 속성을 적용하면 부모의 overflow 속성에 hidden 키워드를 적용합니다"

태그에 width 속성을 사용하고 margin-left 와 marin-right 속성에 auto 키워드를 적용하면 자동 중앙 정렬 됨

```
//body를 중앙 정렬
body{
  width: 900px;
  margin: 0 auto;
}
```

부유를 막고 싶으면 float 속성을 사용한 태그의 부모에 overfloat 속성을 사용하고 hidden 키워드를 적용해야함

또는 clear속성에 both키워드를 적용해도 됨. (overflow 속성을 더 많이 사용)

이 방법을 One True Layout방식이라 부름

```
<style>
  body {
      width: 960px;
      margin: 0 auto;
  }
  #aside {
      width: 200px;
      float: left;
  }

  #section {
      width: 760px;
      float: left;
  }
  #wrap{
    overflow: hidden;
  }
</style>

<div>
  <div id="header"><h1>Header</h1></div>
  <div id="navigation"><h1>Navigation</h1></div>
  <div id="wrap">
      <div id="aside">
          <h1>Aside</h1>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. </p>
      </div>
      <div id="section">
          <h1>Section</h1>
          <p>Nam enim sem, pulvinar sed nibh non, vestibulum suscipit dui.</p>
      </div>
  </div>
  <div id="footer"><h1>Footer</h1></div>
</div>
```

또는 clear both사용하기

```
<style>
    body {
        width: 960px;
        margin: 0 auto;
    }
    .clear {
        clear: both;
    }
    #aside {
        float: left;
        width: 260px;
    }
    #section {
        float: right;
        width: 700px;
    }
</style>

<div id="header"><h1>Header</h1></div>
<div id="navigation"><h1>Navigation</h1></div>
<div class="clear"></div>
<div id="aside">
    <h1>Aside</h1>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. </p>
</div>
<div id="section">
    <h1>Section</h1>
    <p>Nam enim sem, pulvinar sed nibh non, vestibulum suscipit dui. </p>
</div>
<div class="clear"></div>
<div id="footer"><h1>Footer</h1></div>
<div class="clear"></div>
```

<br>

## 9. 그림자 속성

태그에 그림자를 부여하는 스타일 속성

<br>

### 9-1. text-shadow 속성

글자에 그림자를 부여하는 스타일 속성

```
text-shadow : 5px 5px 5px black;
// 오른쪽 아래 흐림도 색상
```

<br>

### 9-2. box-shadow 속성

박스에 그림자를 부여하는 스타일 속성

```
box-shadow : 5px 5px 5px black;
// 오른쪽 아래 흐림도 색상

// 흐림도와 색상 사이에 inset 키워드를 적용하면 그림자가 태그 안쪽에 생성됨
```

그림자 속성은 쉼표를 사용해 여러 개의 그림자 키워드를 사용 할 수 있음

<br>

CSS3 Generator라는 툴을 이용하면 쉽게 그림자를 만들어 볼 수 있음

<br>

## 10. 그레이디언트

두가지 이상의 색상을 혼합해서 채색하는 기능

http://www.colorzilla.com/gradient-editor/ 에서 간단히 만들 수 있음

Ultimate CSS Gradient Generator 의 왼쪽에서 원하는 형태의 그레이디언트를 생성하고 우측에 생성된 CSS코드를 복사

<br>
