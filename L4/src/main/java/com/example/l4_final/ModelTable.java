package com.example.l4_final;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTable {
    private final StringProperty bookId;
    private final StringProperty bookName;
    private final StringProperty bookAuthor;
    private final StringProperty bookYear;
    private final StringProperty bookGenre;
    private final StringProperty bookPrice;
    private final StringProperty bookRate;

    public String getBookId() {
        return bookId.get();
    }

    public StringProperty bookIdProperty() {
        return bookId;
    }

    public String getBookName() {
        return bookName.get();
    }

    public StringProperty bookNameProperty() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor.get();
    }

    public StringProperty bookAuthorProperty() {
        return bookAuthor;
    }

    public String getBookYear() {
        return bookYear.get();
    }

    public StringProperty bookYearProperty() {
        return bookYear;
    }

    public String getBookGenre() {
        return bookGenre.get();
    }

    public StringProperty bookGenreProperty() {
        return bookGenre;
    }

    public String getBookPrice() {
        return bookPrice.get();
    }

    public StringProperty bookPriceProperty() {
        return bookPrice;
    }

    public String getBookRate() {
        return bookRate.get();
    }

    public StringProperty bookRateProperty() {
        return bookRate;
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public void setBookName(String bookName) {
        this.bookName.set(bookName);
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor.set(bookAuthor);
    }

    public void setBookYear(String bookYear) {
        this.bookYear.set(bookYear);
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre.set(bookGenre);
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice.set(bookPrice);
    }

    public void setBookRate(String bookRate) {
        this.bookRate.set(bookRate);
    }

    public ModelTable (String bookId, String bookName, String bookAuthor, String bookYear, String bookGenre,
                        String bookPrice, String bookRate) {
        this.bookId = new SimpleStringProperty(bookId);
        this.bookName = new SimpleStringProperty(bookName);
        this.bookAuthor = new SimpleStringProperty(bookAuthor);
        this.bookYear = new SimpleStringProperty(bookYear);
        this.bookGenre = new SimpleStringProperty(bookGenre);
        this.bookPrice = new SimpleStringProperty(bookPrice);
        this.bookRate = new SimpleStringProperty(bookRate);
    }

    public StringProperty idProperty() {
        return bookId;
    }

    public StringProperty nameProperty() {
        return bookName;
    }
    public StringProperty authorProperty() {
        return bookAuthor;
    }
    public StringProperty yearProperty() {
        return bookYear;
    }
    public StringProperty genreProperty() {
        return bookGenre;
    }
    public StringProperty priceProperty() {
        return bookPrice;
    }
    public StringProperty rateProperty() {
        return bookRate;
    }
}