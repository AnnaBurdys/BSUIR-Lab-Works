public class Book {

    private int bookId;
    private String Name;
    private String Author;
    private String Year;
    private String Genre;
    private String Price;
    private String Rate;

    public Book(int bookId, String name, String author, String year, String genre, String price, String rate) {
        this.bookId = bookId;
        Name = name;
        Author = author;
        Year = year;
        Genre = genre;
        Price = price;
        Rate = rate;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    @Override
    public String toString() {
        return "books {" +
                "bookId = " + bookId +
                ", Name = " + Name +
                ", Author = '" + Author + '\'' +
                ", Year = '" + Year + '\'' +
                ", Genre = '" + Genre + '\'' +
                ", Price = '" + Price + '\'' +
                ", Rate = '" + Rate + '\'' +
                '}';
    }
}
