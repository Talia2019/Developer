# ๐ ์ธ๋ฑ์ค(Index)

<br>

<br>

## ์ธ๋ฑ์ค๋?

```
์ถ๊ฐ์ ์ธ ์ฐ๊ธฐ ์์๊ณผ ์ ์ฅ ๊ณต๊ฐ์ ํ์ฉํ์ฌ ๋ฐ์ดํฐ๋ฒ ์ด์ค ํ์ด๋ธ์ ๊ฒ์ ์๋๋ฅผ ํฅ์์ํค๊ธฐ ์ํ ์๋ฃ๊ตฌ์กฐ
```

ํ์ด๋ธ์ ์นผ๋ผ์ ์์ธํํจ (๋๊บผ์ด ์ฑ์ ๋ชฉ์ฐจ์ ๊ฐ๋ค๊ณ  ์๊ฐํ๋ฉด ํธํจ)

๋ฐ์ดํฐ๋ฒ ์ด์ค ์์ ๋ ์ฝ๋๋ฅผ ์ฒ์๋ถํฐ ํ์ค์บํ์ง ์๊ณ , B+ Tree๋ก ๊ตฌ์ฑ๋ ๊ตฌ์กฐ์์ Index ํ์ผ ๊ฒ์์ผ๋ก ์๋๋ฅผ ํฅ์์ํค๋ ๊ธฐ์ 

<br>

<br>

## ํ์ผ ๊ตฌ์ฑ

ํ์ด๋ธ ์์ฑ ์, 3๊ฐ์ง ํ์ผ์ด ์์ฑ๋จ

- FRM : ํ์ด๋ธ ๊ตฌ์กฐ ์ ์ฅ ํ์ผ
- MYD : ์ค์  ๋ฐ์ดํฐ ํ์ผ
- MYI : Index ์ ๋ณด ํ์ผ (Index ์ฌ์ฉ ์ ์์ฑ)

<br>

์ฌ์ฉ์๊ฐ ์ฟผ๋ฆฌ๋ฅผ ํตํด Index๋ฅผ ์ฌ์ฉํ๋ ์นผ๋ผ์ ๊ฒ์ํ๊ฒ ๋๋ฉด, ์ด๋ MYI ํ์ผ์ ๋ด์ฉ์ ํ์ฉ

<BR>

## ๋จ์ 

- Index ์์ฑ์, .mdb ํ์ผ ํฌ๊ธฐ๊ฐ ์ฆ๊ฐ
- **ํ ํ์ด์ง๋ฅผ ๋์์ ์์ ํ  ์ ์๋ ๋ณํ์ฑ**์ด ์ค์ด๋ฌ
- ์ธ๋ฑ์ค ๋ Field์์ Data๋ฅผ ์๋ฐ์ดํธํ๊ฑฐ๋, **Record๋ฅผ ์ถ๊ฐ ๋๋ ์ญ์ ์ ์ฑ๋ฅ์ด ๋จ์ด์ง**
- ๋ฐ์ดํฐ ๋ณ๊ฒฝ ์์์ด ์์ฃผ ์ผ์ด๋๋ ๊ฒฝ์ฐ, **Index๋ฅผ ์ฌ์์ฑ**ํด์ผ ํ๋ฏ๋ก ์ฑ๋ฅ์ ์ํฅ์ ๋ฏธ์นจ

<br>

## ์ํฉ ๋ถ์

- #### ์ฌ์ฉํ๋ฉด ์ข์ ๊ฒฝ์ฐ

  (1) Where ์ ์์ ์์ฃผ ์ฌ์ฉ๋๋ Column

  (2) ์ธ๋ํค๊ฐ ์ฌ์ฉ๋๋ Column

  (3) Join์ ์์ฃผ ์ฌ์ฉ๋๋ Column

  <br>

- #### Index ์ฌ์ฉ์ ํผํด์ผ ํ๋ ๊ฒฝ์ฐ

  (1) Data ์ค๋ณต๋๊ฐ ๋์ Column

  (2) DML์ด ์์ฃผ ์ผ์ด๋๋ Column

<br>

## DML์ด ์ผ์ด๋ฌ์ ๋์ ์ํฉ

- #### INSERT

  ๊ธฐ์กด Block์ ์ฌ์ ๊ฐ ์์ ๋, ์๋ก์ด Data๊ฐ ์๋ ฅ๋จ

  โ ์๋ก์ด Block์ ํ ๋น ๋ฐ์ ํ, Key๋ฅผ ์ฎ๊ธฐ๋ ์์์ ์ํ

  โ Index split ์์ ๋์, ํด๋น Block์ Key ๊ฐ์ ๋ํด์ DML์ด ๋ธ๋กํน ๋จ (๋๊ธฐ ์ด๋ฒคํธ ๋ฐ์)

- #### DELETE

  <Table๊ณผ Index ์ํฉ ๋น๊ต>

  Table์์ data๊ฐ delete ๋๋ ๊ฒฝ์ฐ : Data๊ฐ ์ง์์ง๊ณ , ๋ค๋ฅธ Data๊ฐ ๊ทธ ๊ณต๊ฐ์ ์ฌ์ฉ ๊ฐ๋ฅ

  Index์์ Data๊ฐ delete ๋๋ ๊ฒฝ์ฐ : Data๊ฐ ์ง์์ง์ง ์๊ณ , ์ฌ์ฉ ์ ๋จ ํ์๋ง ํด๋ 

  โ **Table์ Data ์์ Index์ Data ์๊ฐ ๋ค๋ฅผ ์ ์์**

- #### UPDATE

  Table์์ update๊ฐ ๋ฐ์ํ๋ฉด โ Index๋ Update ํ  ์ ์์

  Index์์๋ **Delete๊ฐ ๋ฐ์ํ ํ, ์๋ก์ด ์์์ Insert ์์** / 2๋ฐฐ์ ์์์ด ์์๋์ด ํ๋ฆ

<br>

<br>
