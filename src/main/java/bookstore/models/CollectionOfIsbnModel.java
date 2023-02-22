package bookstore.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class CollectionOfIsbnModel {
    String userId;
    List<isbn> collectionOfIsbns;
    @Setter @Getter
    public static class isbn{
        String isbn;
    }
}

