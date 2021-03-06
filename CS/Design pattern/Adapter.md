# π Adapter Pattern, μ΄λν° ν¨ν΄

<br>

<br>

## μ΄λν° ν¨ν΄μ΄λ?

> - μ©λ : ν΄λμ€λ₯Ό λ°λ‘ μ¬μ©ν  μ μλ κ²½μ°κ° μμ (λ€λ₯Έ κ³³μμ κ°λ°νλ€κ±°λ, μμ ν  μ μμ λ)
>   μ€κ°μμ λ³ν μ­ν μ ν΄μ£Όλ ν΄λμ€κ° νμ β μ΄λν° ν¨ν΄
>
> - μ¬μ© λ°©λ² : μμ
> - νΈνλμ§ μμ μΈν°νμ΄μ€λ₯Ό μ¬μ©νλ ν΄λΌμ΄μΈνΈ κ·Έλλ‘ νμ© κ°λ₯
>
> - ν₯ν μΈν°νμ΄μ€κ° λ°λλλΌλ, λ³κ²½ λ΄μ­μ μ΄λν°μ μΊ‘μν λλ―λ‘ ν΄λΌμ΄μΈνΈλ λ°λ νμX

μ΄λν° ν¨ν΄μ μ΄λ¦λλ‘ μ΄λν°μ²λΌ μ¬μ©λλ ν¨ν΄

220V λ₯Ό μ¬μ©νλ νκ΅­μμ μ°λ κΈ°κΈ°λ€μ, μ΄λν°λ₯Ό μ¬μ©νλ©΄ 110V λ₯Ό μ°λκ³³μ κ°μλ κ·Έλλ‘ μΈ μ μμ

μ΄μ²λΌ νΈνμ±μ΄ μλ μΈν°νμ΄μ€ λλ¬Έμ ν¨κ» λμν  μ μλ ν΄λμ€λ€μ΄ ν¨κ» μλνλλ‘ ν΄μ£Όλ ν¨ν΄μ΄ μ΄λν° ν¨ν΄

μ΄λ₯Ό μν΄ μ΄λν° μ­ν μ νλ ν΄λμ€λ₯Ό μλ‘ λ§λ€μ΄μΌ ν¨

κΈ°μ‘΄μ μλ μμ€νμ μλ‘μ΄ μ¨λνν° λΌμ΄λΈλ¬λ¦¬κ° μΆκ°λλ€λμ§, λ κ±°μ μΈν°νμ΄μ€λ₯Ό μλ‘μ΄ μΈν°νμ΄μ€λ‘ κ΅μ²΄νλ κ²½μ°μ μ½λμ μ¬μ¬μ©μ±μ λμΌ μ μλ λ°©λ²μ΄ μ΄λν° ν¨ν΄μ μ¬μ©νλ κ²

<br>

μμ΄ν°μ μ΄μ΄ν°μ μκ°ν΄λ³΄λ©΄

κ°μ₯ νν μ΄μ΄ν° μ­μ μμ΄ν°μ μ¬μ©νλ €λ©΄, μ­ μμ²΄κ° λ§μ§ μμ

λ°λΌμ μ°λ¦¬λ μ΄λν°λ₯Ό λ°λ‘ κ΅¬λ§€ν΄μ μ°κ²°ν΄μΌ μ΄λ° μ΄μ΄ν°λ€μ μ¬μ©ν  μ μμ

μ΄μ²λΌ **μ΄λν°λ νμλ‘ νλ μΈν°νμ΄μ€λ‘ λ°κΏμ£Όλ μ­ν **μ ν¨

![img](https://t1.daumcdn.net/cfile/tistory/99F3134C5C6A152D31)

μ΄μ²λΌ μμ²΄μμ μ κ³΅ν ν΄λμ€κ° κΈ°μ‘΄ μμ€νμ λ§μ§ μμΌλ©΄?

> κΈ°μ‘΄ μμ€νμ μμ ν  κ²μ΄ μλλΌ, μ΄λν°λ₯Ό νμ©ν΄ μ μ°νκ² ν΄κ²° κ°λ₯

<br>

## μ½λλ‘ μ΄ν΄νκΈ°

![image](../image/adapter_class.png)

MediaPackageλ₯Ό MediaPlayerμμ μ¬μ©ν  μ μκ² λμμ£Όλ FormatAdapter Class

<br>

- MediaPlayer.java

```java
public interface MediaPlayer{
   void play(String filename);
}
```

- MP3.java

```java
public class MP3 implements MediaPlayer{
   @Override
   void play(String filename){
      System.out.println("Playing MP3 File βͺ : "filename);
   }
}
```

- MediaPackage.java

```java
public interface MediaPackage{
   void play(String filename);
}
```

- MP4.java

```java
public class MP4 implements MediaPlayer{
   @Override
   void play(String filename){
      System.out.println("Playing MP4 File βΆ : "filename);
   }
}
```

- MKV.java

```java
public class MKV implements MediaPlayer{
   @Override
   void play(String filename){
      System.out.println("Playing MKV File βΆ : "filename);
   }
}
```

- FormatAdapter.java

```java
public class FormatAdapter implements MediaPlayer{

   private MediaPackage media;

   public FormatAdapter(MediaPackage video){
      this.media = video;
   }

   @Override
   void play(String filename){
      System.out.println("Using Adapter : ");
      media.playFile(filename);
   }

}
```

- Main.java

```java
public class Main{

   public static void main(String[] args){

   MediaPlayer mp3Player = new MP3();
   mp3Player.play("file.mp3");

   mp3Player = new FormatAdapter(new MP4());
   mp3Player.play("file.mp4");

   mp3Player = new FormatAdapter(new MKV());
   mp3Player.play("file.mkv");

   }

}
```

μ΄λ κ² μ΄λν° ν¨ν΄μ ν΅ν΄ MediaPlayerμμ MediapackageνμΌμ μ¬μμν¬ μ μμ
