package bookstore;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import bookstore.models.*;
import utilities.TestStore;
import okhttp3.Headers;
import retrofit2.Call;

public class BookStore extends ApiUtilities {

    BookStoreServices.Authorized bookStoreAuthorized = new ServiceGenerator(
            new Headers.Builder().add("Authorization", "Bearer " + TestStore.get("token").toString()).build()
    ).generate(BookStoreServices.Authorized.class);

    BookStoreServices bookStore = new ServiceGenerator().generate(BookStoreServices.class);

    public PostBookResponse postBooks(CollectionOfIsbnModel books) {
        log.new Info("Posting the selected books");
        Call<PostBookResponse> bookCall = bookStoreAuthorized.postBooks(books);
        return perform(bookCall, true, true);
    }

    public Isbn postBook(CollectionOfIsbnModel books) {
        log.new Info("Posting the selected books");
        Call<Isbn> bookCall = bookStoreAuthorized.postBook(books);
        return perform(bookCall, true, true);
    }

    public UserResponse getUser(String Id) {
        log.new Info("Getting the user by id: " + Id);
        Call<UserResponse> userCall = bookStoreAuthorized.getUser(Id);
        return perform(userCall, true, true);
    }

    public BookListModel getAllBooks() {
        log.new Info("Getting all books in the bookstore");
        Call<BookListModel> bookCall = bookStore.getBooks();
        return perform(bookCall, true, true);
    }

}
