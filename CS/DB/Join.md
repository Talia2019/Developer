# ๐ Join

<br>

<br>

## Join?

> ๋ ๊ฐ ์ด์์ ํ์ด๋ธ์ด๋ ๋ฐ์ดํฐ๋ฒ ์ด์ค๋ฅผ ์ฐ๊ฒฐํ์ฌ ๋ฐ์ดํฐ๋ฅผ ๊ฒ์ํ๋ ๋ฐฉ๋ฒ
>
> ํ์ด๋ธ์ ์ฐ๊ฒฐํ๋ ค๋ฉด, ์ ์ด๋ ํ๋์ ์นผ๋ผ์ ์๋ก ๊ณต์ ํ๊ณ  ์์ด์ผ ํ๋ฏ๋ก ์ด๋ฅผ ์ด์ฉํ์ฌ ๋ฐ์ดํฐ ๊ฒ์์ ํ์ฉ

<br>

## JOIN ์ข๋ฅ

- INNER JOIN
- LEFT JOIN
- RIGHT JOIN
- FULL OUTER JOIN
- CROSS JOIN
- SELF JOIN

<br>

## INNER JOIN

<img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_INNER-JOIN-600x600.png">

๊ต์งํฉ์ผ๋ก, ๊ธฐ์ค ํ์ด๋ธ๊ณผ join ํ์ด๋ธ์ ์ค๋ณต๋ ๊ฐ์ ๋ณด์ฌ์ค

MySQL์์๋ JOIN, INNER JOIN, CROSS JOIN์ด ๋ชจ๋ ๊ฐ์ ์๋ฏธ๋ก ์ฌ์ฉ๋จ

```sql
SELECT *
FROM EMP_TABLE A
INNER JOIN JOIN_TABLE B ON A.NO_EMP = B.NO_EMP
```

<br>

## OUTER JOIN

<img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_OUTER-JOIN-600x600.png">

<br>

#### LEFT OUTER JOIN

์ฒซ๋ฒ์งธ ํ์ด๋ธ์ ๊ธฐ์ค์ผ๋ก ๋๋ฒ์งธ ํ์ด๋ธ์ ์กฐํฉ

```SQL
SELECT *
FROM EMP_TABLE A
LEFT JOIN JOIN_TABLE B ON A.NO_EMP = B.NO_EMP
```

<br>

#### RIGHT OUTER JOIN

๋๋ฒ์งธ ํ์ด๋ธ์ ๊ธฐ์ค์ผ๋ก ์ฒซ๋ฒ์งธ ํ์ด๋ธ์ ์กฐํฉ

```SQL
SELECT *
FROM EMP_TABLE A
RIGHT OUTER JOIN JOIN_TABLE B ON A.NO_EMP = B.NO_EMP
```

<br>

#### FULL OUTER JOIN

ํฉ์งํฉ

A์ B ํ์ด๋ธ์ ๋ชจ๋  ๋ฐ์ดํฐ๊ฐ ๊ฒ์๋จ

```sql
SELECT *
FROM EMP_TABLE A
FULL OUTER JOIN JOIN_TABLE B ON A.NO_EMP = B.NO_EMP
```

<br>

![image](https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/OUTER-JOIN_%EB%8D%94%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0-600x600.png)

<br>

## CROSS JOIN

  <img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_CROSS-JOIN-600x600.png">

ํ์ชฝ ํ์ด๋ธ์ ๋ชจ๋  ํ๊ณผ ๋ค๋ฅธ ์ชฝ ํ์ด๋ธ์ ๋ชจ๋  ํ์ ์กฐ์ธ์ํค๋ ๊ธฐ๋ฅ

์ํธ ์กฐ์ธ ๊ฒฐ๊ณผ์ ์ ์ฒด ํ ๊ฐ์๋ ๋ ํ์ด๋ธ์ ๊ฐ ํ์ ๊ฐ์๋ฅผ ๊ณฑํ ์๋งํผ ๋จ

์นดํฐ์ ๊ณฑ(CARTESIAN PRODUCT)๋ผ๊ณ ๋ ํจ

A๊ฐ 3๊ฐ, B๊ฐ 4๊ฐ๋ฉด ์ด 3\*4 = 12๊ฐ์ ๋ฐ์ดํฐ๊ฐ ๊ฒ์๋จ

```sql
SELECT *
FROM EMP_TABLE A
CROSS JOIN JOIN_TABLE B
```

<br>

## SELF JOIN

  <img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_SELF-JOIN-600x600.png">

์๊ธฐ์์ ์ ์กฐ์ธํ๋ ๊ฒ

์์ ์ด ๊ฐ๊ณ  ์๋ ์นผ๋ผ์ ๋ค์ํ๊ฒ ๋ณํ์์ผ ํ์ฉํ  ๋ ์์ฃผ ์ฌ์ฉํ๋ค.

```sql
SELECT *
FROM EMP_TABLE A, EMP_TABLE B
```

<br>

> ์ฐธ๊ณ 
>
> https://hongong.hanbit.co.kr/sql-%EA%B8%B0%EB%B3%B8-%EB%AC%B8%EB%B2%95-joininner-outer-cross-self-join/
