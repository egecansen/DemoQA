package bookstore;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import bookstore.models.*;
import retrofit2.Call;
import utils.Printer;

public class BookStoreAuthorization extends ApiUtilities {

    static BookStoreServices.Authorization bookStore = new ServiceGenerator().generate(BookStoreServices.Authorization.class);
    static Printer log = new Printer(BookStoreAuthorization.class);

    public static UserResponse createUser(CredentialModel user) {
        log.new Info("Creating a new user");
        Call<UserResponse> userCall = bookStore.postUser(user);
        return perform(userCall, true, true);
    }

    public static TokenResponse generateToken(CredentialModel user) {
        log.new Info("Generating a token for the user named: " + user.getUserName());
        Call<TokenResponse> tokenCall = bookStore.generateToken(user);
        return perform(tokenCall, true, true);
    }

    public static Boolean authorizeUser(CredentialModel user) {
        log.new Info("Authorizing...");
        Call<Boolean> userCall = bookStore.authorizeUser(user);
        return perform(userCall, true, true);
    }

}
