package bookstore.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PostBookResponse {
    List<Isbn> books;
}
