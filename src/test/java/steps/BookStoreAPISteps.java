package steps;

import bookstore.BookStoreAuthorization;
import bookstore.BookStore;
import bookstore.models.*;
import utilities.TestStore;
import io.cucumber.java.en.Given;

import java.util.ArrayList;
import java.util.List;

public class BookStoreAPISteps {

    BookStore bookStore = new BookStore();

    @Given("Generate token for the user in context")
    public void generateToken() {
        CredentialModel user = (CredentialModel) TestStore.get("contextUser");
        TokenResponse tokenResponse = BookStoreAuthorization.generateToken(user);
        TestStore.put("token", tokenResponse.getToken());
    }

    @Given("Authorize user")
    public void authorizeUser() {
        CredentialModel user = (CredentialModel) TestStore.get("contextUser");
        BookStoreAuthorization.authorizeUser(user);
    }

    @Given("Get user by id: {}")
    public void getUser(String userId) {
        UserResponse userResponse =  bookStore.getUser(userId);
        TestStore.put("userResponse", userResponse);
    }

    @Given("Get user in context")
    public void getUser() {
        getUser(TestStore.get("userId").toString());
    }

    @Given("Assign all books from bookstore published by {} to user in context")
    public void assignBooksByPublisher(String publisher) {
        List<Isbn> books = new ArrayList<>();

        for (BookModel book : bookStore.getAllBooks().getBooks())
            if (book.getPublisher().equals(publisher))
                books.add(new Isbn(book.getIsbn()));

        if (books.size() != 0) postBooks(books);
        else bookStore.log.new Warning("No books found by " + publisher + " publisher");
    }

    @Given("Post selected books for user: {}")
    public void postBooks(String userId, List<Isbn> isbns) {
        CollectionOfIsbnModel usersBooks = new CollectionOfIsbnModel();
        usersBooks.setUserId(userId);
        usersBooks.setCollectionOfIsbns(isbns);
        bookStore.postBooks(usersBooks);
    }

    @Given("Post following book for user: {}")
    public void postBook(String userId, Isbn isbn) {
        CollectionOfIsbnModel usersBooks = new CollectionOfIsbnModel();
        usersBooks.setUserId(userId);
        usersBooks.setCollectionOfIsbns(List.of(isbn));
        bookStore.postBook(usersBooks);
    }

    @Given("Post selected books for user in context")
    public void postBooks(List<Isbn> isbns) {
        postBooks(TestStore.get("userId").toString(), isbns);
    }

}
