package bookstore.models;

import lombok.Getter;
import lombok.Setter;
import utils.StringUtilities;

@Getter @Setter
public class CredentialModel {
    String userName;
    String password;

    public CredentialModel(){
    }

    public CredentialModel(String baseUsername){
        this.userName = new StringUtilities().generateRandomString(baseUsername, 4, false, true);
    }
}
