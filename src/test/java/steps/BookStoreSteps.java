package steps;

import bookstore.BookStoreAuthorization;
import bookstore.BookStore;
import bookstore.models.*;
import io.cucumber.java.en.Given;
import utilities.ContextStore;

import java.util.List;

public class BookStoreSteps {

    BookStore bookStore = new BookStore();

    @Given("Create a new user by following:")
    public void postUser(CredentialModel credentialModel) {
        UserResponse response = bookStore.createUser(credentialModel);
        assert response != null;
        ContextStore.put("contextUser", credentialModel);
        ContextStore.put("userId", response.getUserID());
    }

    @Given("Generate token for the given user")
    public void generateToken() {
        CredentialModel user = (CredentialModel) ContextStore.get("contextUser");
        TokenResponse tokenResponse = bookStore.generateToken(user);
        ContextStore.put("token", tokenResponse.getToken());
    }

    @Given("Authorize user")
    public void authorizeUser() {
        CredentialModel user = (CredentialModel) ContextStore.get("contextUser");
        bookStore.authorizeUser(user);
    }

    @Given("Get user by the given id")
    public void getUser() {
        System.out.println(ContextStore.get("userId"));
        System.out.println(ContextStore.get("token"));
        BookStoreAuthorization bookStoreAuth = new BookStoreAuthorization();
        bookStoreAuth.getUser(ContextStore.get("userId").toString());
    }

    @Given("Get all the books published by {} in the bookstore and register the books for user")
    public void getBooks(String title) {
        BookStoreAuthorization bookStoreAuth = new BookStoreAuthorization();
        CollectionOfIsbnModel.isbn isbn = new CollectionOfIsbnModel.isbn();
        CollectionOfIsbnModel usersBooks = new CollectionOfIsbnModel();

        for (BookListModel.BookModel book : bookStore.getBooks().body().getBooks())
            if (book.getTitle().equals(title))
                isbn.setIsbn(book.getIsbn());

        usersBooks.setCollectionOfIsbns(List.of(isbn));
        usersBooks.setUserId(ContextStore.get("userId").toString());
        bookStoreAuth.postBooks(usersBooks);
    }

}
