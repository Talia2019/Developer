# 📚 Transaction Isolation Level

<br>

<br>

## 트랜잭션 격리 수준

트랜잭션에서 일관성 없는 데이터를 허용하도록 하는 수준

<br>

## Isolation level의 필요성

데이터베이스는 ACID 특징과 같이 트랜잭션이 독립적인 수행을 하도록 함

따라서 Locking을 통해, 트랜잭션이 DB를 다루는 동안 다른 트랜잭션이 관여하지 못하도록 막는 것이 필요

하지만 무조건 Locking으로 동시에 수행되는 수많은 트랜잭션들을 순서대로 처리하는 방식으로 구현하게 되면 데이터베이스의 성능은 떨어지게 될 것

그렇다고 해서, 성능을 높이기 위해 Locking의 범위를 줄인다면, 잘못된 값이 처리될 문제가 발생하게 됨

따라서 최대한 효율적인 Locking 방법이 필요

<br>

## 트랜잭션 오류 현상

1. 오손읽기 (Dirty Read)

읽기 작업을 하는 트랜잭션 A가 작업을 하는 트랜잭셕 B의 중간 데이터를 읽기 때문에 발생하는 문제

작업중인 트랜잭션 B가 작업을 Rollback한 경우 트랜잭션 A는 무효가 된 데이터를 읽게 되고 잘못된 결과를 도출

<br>

2. 반복 불가능 읽기 (Non-repeatable Read)

트랜잭션 A가 데이터를 읽고 트랜잭션 B가 데이터를 쓰고(Update) 트랜잭션 A가 다시 한 번 데이터를 읽을 때 생기는 문제

트랜잭션 A가 읽기 작업을 다시 한 번 반복할 경우 이전의 결과와 다른 결과가 나오는 현상

<br>

3. 유령데이터 읽기 (Pahtom Read)

트랜잭션 A가 데이터를 읽고 트랜잭션 B가 데이터를 쓰고(Insert) 트랜잭션 A가 다시 한 번 데이터를 읽을 때 생기는 문제

트랜잭션 A가 읽기 작업을 다시 한 번 반복할 경우 이전에 없던 데이터(유령데이터)가 나타나는 현상

<br>

## Transaction Isolation level Instruction (트랜잭션 고립수준 명령어)

DBMS가 트랜잭션을 동시에 실행시키면서 락보다 좀 완화된 방법으로 문제를 해결하기 위해 제공하는 명령어

[!image](https://t1.daumcdn.net/cfile/tistory/99958E475A2FEAB22C)

1. ##### Read Uncommitted (레벨 0)

   > SELECT 문장이 수행되는 동안 해당 데이터에 Shared Lock이 걸리지 않는 계층

   트랜잭션에 처리중이거나, 아직 Commit되지 않은 데이터를 다른 트랜잭션이 읽는 것을 허용

   ```
   사용자1이 A라는 데이터를 B라는 데이터로 변경하는 동안 사용자2는 아직 완료되지 않은(Uncommitted) 트랜잭션이지만 데이터B를 읽을 수 있다
   ```

   데이터베이스의 일관성을 유지하는 것이 불가능

   <br>

2. ##### Read Committed (레벨 1)

   > SELECT 문장이 수행되는 동안 해당 데이터에 Shared Lock이 걸리는 계층

   트랜잭션이 수행되는 동안 다른 트랜잭션이 접근할 수 없어 대기하게 됨

   Commit이 이루어진 트랜잭션만 조회 가능

   SQL 서버가 Default로 사용하는 Isolation Level임

   ```
   사용자1이 A라는 데이터를 B라는 데이터로 변경하는 동안 사용자2는 해당 데이터에 접근이 불가능함
   ```

   <br>

3. ##### Repeatable Read (레벨 2)

   > 트랜잭션이 완료될 때까지 SELECT 문장이 사용하는 모든 데이터에 Shared Lock이 걸리는 계층

   트랜잭션이 범위 내에서 조회한 데이터 내용이 항상 동일함을 보장

   다른 사용자는 트랜잭션 영역에 해당되는 데이터에 대한 수정 불가능

   <br>

4. ##### Serializable (레벨 3)

   > 트랜잭션이 완료될 때까지 SELECT 문장이 사용하는 모든 데이터에 Shared Lock이 걸리는 계층

   완벽한 읽기 일관성 모드를 제공

   다른 사용자는 트랜잭션 영역에 해당되는 데이터에 대한 수정 및 입력 불가능

   <br>

<br>

**_선택 시 고려사항_**

Isolation Level에 대한 조정은, 동시성과 데이터 무결성에 연관되어 있음

동시성을 증가시키면 데이터 무결성에 문제가 발생하고, 데이터 무결성을 유지하면 동시성이 떨어지게 됨

레벨을 높게 조정할 수록 발생하는 비용이 증가

<br>

## 낮은 단계 Isolation Level을 활용할 때 발생하는 현상들

- Dirty Read

  > 커밋되지 않은 수정중인 데이터를 다른 트랜잭션에서 읽을 수 있도록 허용할 때 발생하는 현상
  >
  > 어떤 트랜잭션에서 아직 실행이 끝나지 않은 다른 트랜잭션에 의한 변경사항을 보게되는 경우

- Non-Repeatable Read

  > 한 트랜잭션에서 같은 쿼리를 두 번 수행할 때 그 사이에 다른 트랜잭션 값을 수정 또는 삭제하면서 두 쿼리의 결과가 상이하게 나타나는 일관성이 깨진 현상

- Phantom Read

  > 한 트랜잭션 안에서 일정 범위의 레코드를 두 번 이상 읽었을 때, 첫번째 쿼리에서 없던 레코드가 두번째 쿼리에서 나타나는 현상
  >
  > 트랜잭션 도중 새로운 레코드 삽입을 허용하기 때문에 나타나는 현상