package bookstore;

import bookstore.models.*;
import retrofit2.Call;
import retrofit2.http.*;

import static bookstore.BookStoreAPI.*;

public interface BookStoreServices {
    String BASE_URL = BookStoreAPI.BASE_URL;

    @POST(ACCOUNT_SUFFIX + USER_SUFFIX)
    Call<UserResponse> postUser(@Body CredentialModel body);

    @POST(ACCOUNT_SUFFIX + TOKEN_SUFFIX)
    Call<TokenResponse> generateToken(@Body CredentialModel body);

    @POST(ACCOUNT_SUFFIX + AUTH_SUFFIX)
    Call<Object> authorizeUser(@Body CredentialModel body);

    @GET(BOOKSTORE_SUFFIX + BOOKS_SUFFIX)
    Call<BookListModel> getBooks();

    interface Authorization {
        @GET(ACCOUNT_SUFFIX + USER_SUFFIX + USER_ID)
        Call<UserResponse> getUser(@Path("UUID") String Id);

        @POST(BOOKSTORE_SUFFIX + BOOKS_SUFFIX + ISBN_SUFFIX)
        Call<Object> postBook(@Body CollectionOfIsbnModel books);
    }

}
