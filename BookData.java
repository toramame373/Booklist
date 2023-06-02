import javax.xml.stream.events.DTD;

public class BookData { //本の情報を格納する構造体代わりのクラス
    private String name;      //書名
    private String author;    //著者名
    private String publisher; //出版社
    private String isbn;      //ISBN

    public BookData(String na, String au, String pu, String is) {
        name = na;
        author = au;
        publisher = pu;
        isbn = is;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
