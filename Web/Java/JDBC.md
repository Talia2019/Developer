# JDBC

<br>

## JDBC 작업 순서

1. Driver Loading
2. DB 연결 (Connection 생성)
3. SQL 실행 준비  
   3-1. SQL 작성  
   3-2. Statement 생성 (Statement, PreparedStatement)
4. SQL 실행  
   4-1. I, U, D  
    int x = stmt.execteUpdate(sql);  
    int x = pstmt.executeUpdate();  
   4-2. S  
    ResultSet rs = pstmt.executeQuery();  
    rs.next() [단독, if, while]  
    값얻기 : rs.getString(), rs.getInt() 등
5. DB 연결 종료 : 연결 역순으로 종료, finally, AutoCloseable, try-with-resource (JDK7이상)

```
if(rs != null)
  rs.close()
if(pstmt != null)
  pstmt.close();
if(conn != null)
  conn.close();
```

\*\* Driver download : https://dev.mysql.com/downloads/connector/j/

\*\*\* DB 정보
driver ==> com.mysql.cj.jdbc.Driver
url ==> jdbc:mysql://127.0.0.1:3306/dbname?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8

<br>

### DBConnection

```java
import java.sql.*;

public class DBConnection {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/dbname?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private static final String DB_ID = "ssafy";
	private static final String DB_PASS = "ssafy";

	static {
		try {
			Class.forName(DRIVER);
//			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//			System.out.println("드라이버 로딩 실패");
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, DB_ID, DB_PASS);
	}
}
```

### DBClose

```java
import java.sql.*;

public class DBClose {

	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(AutoCloseable...autoCloseables) {
		for(AutoCloseable ac : autoCloseables) {
			if(ac != null) {
				try {
					ac.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
```
