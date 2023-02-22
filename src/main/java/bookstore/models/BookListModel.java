package bookstore.models;

import lombok.Getter;

import java.util.List;

@Getter
public class BookListModel {
    List<BookModel> books;
    @Getter
    public static class BookModel{
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
}
