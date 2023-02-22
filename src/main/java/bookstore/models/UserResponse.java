package bookstore.models;

import lombok.Getter;

import java.util.List;
@Getter
public class UserResponse {
    String userID;
    String username;
    List<BookListModel.BookModel> books;

}
