package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class JdbcMemberRepository implements MemberRepository {
    
    //db에 붙으려면 필요하다.
    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        //ResultSet 클래스는 Statement 객체로 SELECT 문을 사용하여 얻어온 레코드 값을 테이플의 형태로 갖는 객체입니다.
        ResultSet rs = null;
        try {
            //db와의 통로생성, 반환형은 Connection이다.
            conn = getConnection();
            //Statement, PreparedStatement : Connection으로 자바 프로그램과 DB 사이에 연결이 되었다면, 이 연결을 통해 자바프로그램은 DB쪽으로 SQL 문을 전송하고, DB는 처리된 결과를 자바프로그램으로 전달한다.
            //이 역할을 하는 객체가 Statement와 PreparedStatement 이다.
            //Statement는 객체 생성 이후 sql문을 완성하여 데이터베이스에 바로바로 처리하는 반면, PreparedStatement는 객체를 '?' 가 포함된 SQL문으로 생성하고, 이후에 '?' 자리만 바꿔가며 데이터베이스를 처리한다.
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            //sql문의 ?를 채워준다.
            pstmt.setString(1, member.getName());
            //SELECT 구문을 제외한 나머지 구문을 수행할 때 사용.
            //INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환합니다.
            //CREATE / DROP 관련 구문에서는 -1 을 반환합니다.
            pstmt.executeUpdate();
            //Retrieves any auto-generated keys created as a result of executing this Statement object.
            //If this Statement object did not generate any keys, an empty ResultSet object is returned.
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                //column 은 열, 즉 테이블에서 세로축을 의미한다.
                //getLong : retrieving column values from the current row. Values can be retrieved using either the index number of the column or the name of the column.
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            //void setLong(int parameterIndex, long x) throws SQLException
            //Sets the designated parameter(지정된 인자) to the given Java long value
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                //column value를 가져온다.
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}