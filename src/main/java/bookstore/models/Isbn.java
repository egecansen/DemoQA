package bookstore.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Isbn {
    String isbn;

    public Isbn(String isbn) {
        this.isbn = isbn;
    }

    public Isbn() {
    }
}
