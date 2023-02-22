package bookstore;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import bookstore.models.*;
import context.ContextStore;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;

public class BookStore extends ApiUtilities {
    BookStoreServices bookStore = new ServiceGenerator().generate(BookStoreServices.class);

    public UserResponse createUser(CredentialModel user) {
        log.new Info("Creating a new user");
        Call<UserResponse> userCall = bookStore.postUser(user);
        return perform(userCall, true, true);
    }

    public TokenResponse generateToken(CredentialModel user) {
        log.new Info("Generating a token for the user named: " + user.getUserName());
        Call<TokenResponse> tokenCall = bookStore.generateToken(user);
        return perform(tokenCall, true, true);
    }

    public Object authorizeUser(CredentialModel user) {
        log.new Info("Authorizing the user named: " + user.getUserName());
        Call<Object> userCall = bookStore.authorizeUser(user);
        return perform(userCall, true, true);
    }

    public Response<BookListModel> getBooks() {
        log.new Info("Getting all books in the bookstore");
        Call<BookListModel> bookCall = bookStore.getBooks();
        return getResponse(bookCall, true, true);
    }

}
