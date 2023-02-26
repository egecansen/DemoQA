package steps;

import bookstore.models.BookModel;
import bookstore.models.UserResponse;
import pages.BookStoreProfilePage;
import utilities.TestStore;

import java.util.List;

public class BookStoreProfilePageSteps {

    BookStoreProfilePage bookStoreProfilePage = new BookStoreProfilePage();

    public void verifyBooks() {
        List<BookModel> bookList = ((UserResponse)TestStore.get("userResponse")).getBooks();
        for (BookModel book: bookList) {
            bookStoreProfilePage.getBookRow(book.getTitle());

        }
    }
}
