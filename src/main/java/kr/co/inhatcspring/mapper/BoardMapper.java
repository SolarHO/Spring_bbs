package kr.co.inhatcspring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import kr.co.inhatcspring.beans.BoardPost;
import kr.co.inhatcspring.beans.UserInfo;


// MyBatis 매퍼 인터페이스로, BoardPost 객체와 DB간의 매핑을 처리
public interface BoardMapper {
	// 게시글 삽입 쿼리
    @Insert("INSERT INTO board_post (title, content, author, createdAt, viewCount, category) VALUES (#{title}, #{content}, #{author}, SYSDATE, 0, #{category})")
    void insertPost(BoardPost post);
    
    // 특정 카테고리의 게시글 목록을 조회
    @Select("SELECT bp.*, u.username AS authorname FROM board_post bp JOIN users u ON bp.author = u.userid WHERE bp.category = #{category}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "author", column = "author"),
        @Result(property = "authorname", column = "authorname"),
        @Result(property = "createdAt", column = "createdAt"),
        @Result(property = "viewCount", column = "viewCount"),
        @Result(property = "category", column = "category")
    })
    List<BoardPost> getPosts(String category);
    
    // 특정 ID의 게시글을 조회하는 쿼리 , Result -> 쿼리 결과를 BoardPost 객체의 속성에 매핑.
    @Select("SELECT bp.*, u.username AS authorname FROM board_post bp JOIN users u ON bp.author = u.userid WHERE bp.id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "author", column = "author"),
        @Result(property = "authorname", column = "authorname"),
        @Result(property = "createdAt", column = "createdAt"),
        @Result(property = "viewCount", column = "viewCount"),
        @Result(property = "category", column = "category")
    })
    BoardPost getPost(int id);

    
    // 게시글 업데이트 쿼리
    @Update("UPDATE board_post SET title = #{title}, content = #{content}, author = #{author}, viewCount = viewCount + 1, category = #{category} WHERE id = #{id}")
    void updatePost(BoardPost post);
    
    // 게시글 삭제 쿼리
    @Delete("DELETE FROM board_post WHERE id = #{id}")
    void deletePost(int id);
    
    //내가 쓴 게시글 검색
    @Select("SELECT p.*, u.username AS authorname FROM board_post p JOIN Users u ON p.author = u.userid WHERE p.author = #{userid}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "content", column = "content"),
        @Result(property = "author", column = "author"),
        @Result(property = "authorname", column = "authorname"),
        @Result(property = "createdAt", column = "createdAt"),
        @Result(property = "viewCount", column = "viewCount"),
        @Result(property = "category", column = "category")
    })
    List<BoardPost> getPostsByUser(@Param("userid") String userid);
    
    // 등록된 회원 확인 쿼리
    @Select("SELECT COUNT(*) FROM Users WHERE userId = #{userid}")
    int countUserById(@Param("userid") String userid);
    
    // 회원가입 쿼리
    @Insert("INSERT INTO Users (userId, password, username, isAdmin) VALUES (#{userid}, #{password}, #{username}, 0)")
    void insertUser(UserInfo user);
    
    //로그인 시도 유저 검색 쿼리
    @Select("SELECT * FROM Users WHERE userId = #{userid} AND password = #{password}")
    UserInfo getUser(@Param("userid") String userId, @Param("password") String password);
    
    //관리자 권한 확인 메서드(isAdmin이 1이면 관리자 계정)
    @Select("SELECT isAdmin FROM Users WHERE userId = #{userId}")
    void isAdmin(@Param("userId") String userId);
    
    //현재 회원 정보 검색
    @Select("SELECT * FROM Users WHERE userid = #{userid}")
    UserInfo getUserById(String userid);
    
    //회원 정보 수정 쿼리
    @Update("UPDATE Users SET password = #{password}, username = #{username} WHERE userId = #{userid}")
    void updateUser(UserInfo user);
    
    //회원 탈퇴 쿼리
    @Delete("DELETE FROM Users WHERE userId = #{userId}")
    void deleteUser(String userId);
    
    //현재 비밀번호를 확인하는 쿼리
    @Select("SELECT * FROM Users WHERE userId = #{userId} AND password = #{currentPassword}")
    UserInfo checkPassword(@Param("userId") String userId, @Param("currentPassword") String currentPassword);

}
