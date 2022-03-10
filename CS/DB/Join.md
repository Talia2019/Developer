# 📚 Join

<br>

<br>

## Join?

> 두 개 이상의 테이블이나 데이터베이스를 연결하여 데이터를 검색하는 방법
>
> 테이블을 연결하려면, 적어도 하나의 칼럼을 서로 공유하고 있어야 하므로 이를 이용하여 데이터 검색에 활용

<br>

## JOIN 종류

- INNER JOIN
- LEFT JOIN
- RIGHT JOIN
- FULL OUTER JOIN
- CROSS JOIN
- SELF JOIN

<br>

## INNER JOIN

<img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_INNER-JOIN-600x600.png">

교집합으로, 기준 테이블과 join 테이블의 중복된 값을 보여줌

MySQL에서는 JOIN, INNER JOIN, CROSS JOIN이 모두 같은 의미로 사용됨

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

첫번째 테이블을 기준으로 두번째 테이블을 조합

```SQL
SELECT *
FROM EMP_TABLE A
LEFT JOIN JOIN_TABLE B ON A.NO_EMP = B.NO_EMP
```

<br>

#### RIGHT OUTER JOIN

두번째 테이블을 기준으로 첫번째 테이블을 조합

```SQL
SELECT *
FROM EMP_TABLE A
RIGHT OUTER JOIN JOIN_TABLE B ON A.NO_EMP = B.NO_EMP
```

<br>

#### FULL OUTER JOIN

합집합

A와 B 테이블의 모든 데이터가 검색됨

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

한쪽 테이블의 모든 행과 다른 쪽 테이블의 모든 행을 조인시키는 기능

상호 조인 결과의 전체 행 개수는 두 테이블의 각 행의 개수를 곱한 수만큼 됨

카티션 곱(CARTESIAN PRODUCT)라고도 함

A가 3개, B가 4개면 총 3\*4 = 12개의 데이터가 검색됨

```sql
SELECT *
FROM EMP_TABLE A
CROSS JOIN JOIN_TABLE B
```

<br>

## SELF JOIN

  <img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_SELF-JOIN-600x600.png">

자기자신을 조인하는 것

자신이 갖고 있는 칼럼을 다양하게 변형시켜 활용할 때 자주 사용한다.

```sql
SELECT *
FROM EMP_TABLE A, EMP_TABLE B
```

<br>

> 참고
>
> https://hongong.hanbit.co.kr/sql-%EA%B8%B0%EB%B3%B8-%EB%AC%B8%EB%B2%95-joininner-outer-cross-self-join/
