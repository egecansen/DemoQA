package pages;

import org.openqa.selenium.support.FindBy;
import pages.component.BookRow;
import utilities.Utils;

import java.util.List;

public class BookStoreProfilePage extends Utils {
    @FindBy(css = "[role='row']")
    public List<BookRow> bookListComponent;

    public BookRow getBookRow(String bookTitle) {
        for (BookRow book : bookListComponent)
            if (book.getTitle().equals(bookTitle))
                return book;
        throw new RuntimeException("Book not found!");
    }

}
