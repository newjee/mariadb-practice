package bookshop.example;

public class Book {
	
	private int bookNo;
	private String title;
	private String author;
	private int stateCode;
	
	public Book() {
		bookNo = 1;
		title = null;
		author = null;
		stateCode = 1;
	}
	public Book(int bookNo, String title, String author) {
		this.bookNo=bookNo;
		this.title=title;
		this.author=author;
		this.stateCode = 1;

	}
	
	public void rent() {
		stateCode = 0;
		System.out.println(title+"이(가) 대여되었습니다.");
	}
	
	public void print() {
		System.out.println("책 제목:"+title+
							", 작가:"+author+
							", 대여 유무:"+(stateCode==0? "대여중" : "재고 있음")
							);
		System.out.println(stateCode);
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	
	


}
