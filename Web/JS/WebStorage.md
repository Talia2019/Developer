# JavaScript 기본

<br>

## 목차

1. LocalStorage
2. SessionStorage
3. 비교

<br>

## 1. LocalStorage

- WebStorage API : LocalStorage

  데이터를 사용자 로컬에 보존하는 방식

  데이터를 저장, 덮어쓰기, 삭제 등 조작가능

  자바스크립트(JS)로 조작

  모바일에서도 사용 가능

<br>
    
- Cookie와의 차이점
    
    유효기간이 없고 영구적으로 이용가능
    
    5MB까지 사용가능(쿠키는4)
    
    필요할때 언제든 사용가능(쿠키는 서버 접속시에 자동송신)

<br>    
    
- LocalStorage 기본 구성
    
    키와 값을 하나의 세트로 저장
    
    도메인과 브라우저별로 저장
    
    값은 반드시 문자열로 저장됨
    
- LocalStorage에 data추가, 얻기, 삭제

<br>

```jsx
<script>
	function init(){
		//데이터 추가방법 세가지
		localStorage.Test = "A";
		localStorage["Test"]="A";
		localStorage.setItem("Test", "A");

		//데이터 취득방법 세가지
		var val = localStorage.Test;
		var val = localStorage["Test"];
		var val = localStorage.getItem("Test");

		//취득데이터 출력
		document.querySelector("#result").innerHTML = val;

		//데이터 삭제
		localStorage.removeItem("Test");
		//데이터 전체 삭제
		localStorage.clear();
	}
</script>
```

<br>

## 2. SessionStorage

```jsx
//세션저장
sessionStorage.setItem("key", value);
//세션값 불러오기
sessionStorage.getItem("key");
//세션삭제
sessionStorage.removeItem("key");
//세션전체삭제
sessionStorage.clear();
```

<br>

## 3. 비교

- SessionStorage와 차이점

  localStorage : 세션이 끊겨도 사용가능

  sessionStorage : 같은 세션만 사용 가능

- sessionStorage 의 경우에는 동일한 세션에서만 사용 가능하지만 localStorage는 세션이 끊기거나 동일한 세션이 아니더라도 사용가능

<br>
