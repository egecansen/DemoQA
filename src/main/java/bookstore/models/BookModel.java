package bookstore.models;

import lombok.Getter;

@Getter
public class BookModel {
    String isbn;
    String title;
    String subTitle;
    String author;
    String publish_date;
    String publisher;
    int pages;
    String description;
    String website;
}
