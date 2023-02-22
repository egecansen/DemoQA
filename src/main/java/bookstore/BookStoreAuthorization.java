package bookstore;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import bookstore.models.CollectionOfIsbnModel;
import bookstore.models.UserResponse;
import context.ContextStore;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;

public class BookStoreAuthorization extends ApiUtilities {
    BookStoreServices.Authorization bookStoreAuthorization = new ServiceGenerator(
            new Headers.Builder().add("Authorization", "Bearer " + ContextStore.get("token").toString()).build()
    ).generate(BookStoreServices.Authorization.class);

    public Response<Object> postBooks(CollectionOfIsbnModel books) {
        log.new Info("Posting the selected books");
        Call<Object> bookCall = bookStoreAuthorization.postBook(books);
        return getResponse(bookCall, true, true);
    }

    public Response<UserResponse> getUser(String Id) {
        log.new Info("Getting the user by id: " + Id);
        Call<UserResponse> userCall = bookStoreAuthorization.getUser(Id);
        return getResponse(userCall, true, true);
    }

}
