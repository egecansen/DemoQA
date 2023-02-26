package bookstore.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class CollectionOfIsbnModel {
    String userId;
    List<Isbn> collectionOfIsbns;
}
