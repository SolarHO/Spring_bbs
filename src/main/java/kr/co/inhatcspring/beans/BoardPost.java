package kr.co.inhatcspring.beans;

import java.util.Date;
// 아래 주석들은 각 멤버의 변수와 메서드의 역할을 설명 , 클래스의 전반적인 목적을 명확히 한다.

public class BoardPost {
	// 게시글의 고유 ID
    private int id;
    // 게시글의 제목
    private String title;
    // 게시글의 내용
    private String content;
    // 게시글 작성자 id
    private String author;
    // 게시글 작성자 이름
    private String authorname;
    // 게시글 작성 일자
    private Date createdAt;
    // 게시글 조회수
    private int viewCount;
    // 게시글의 카테고리
    private String category; // 새로운 필드 추가

    // id의 getter 메서드
    public int getId() {
        return id;
    }
    // id의 setter 메서드
    public void setId(int id) {
        this.id = id;
    }
    // title의 getter 메서드.
    public String getTitle() {
        return title;
    }
    // title의 setter 메서드
    public void setTitle(String title) {
        this.title = title;
    }
    // content의 getter 메서드
    public String getContent() {
        return content;
    }
    // content의 setter 메서드
    public void setContent(String content) {
        this.content = content;
    }
    // author의 getter 메서드
    public String getAuthor() {
        return author;
    }
    // author의 setter 메서드
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	// createAt의 getter 메서드
    public Date getCreatedAt() {
        return createdAt;
    }
    // createAt의 setter 메서드
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    // viewCount의 getter 메서드
    public int getViewCount() {
        return viewCount;
    }
    // viewCount의 setter 메서드
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    // category의 getter 메서드
    public String getCategory() {
        return category;
    }
    // category의 setter 메서드
    public void setCategory(String category) {
        this.category = category;
    }
}
