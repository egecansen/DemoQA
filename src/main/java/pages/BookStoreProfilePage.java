package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.BookDetailRow;
import pages.components.BookRow;
import utilities.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStoreProfilePage extends Utils {

    public Map<String, Integer> columnIndexes = new HashMap<>();

    @FindBy(css = ".rt-tr-group")
    public List<BookRow> bookRows;

    @FindBy(css = ".rt-resizable-header-content")
    public List<WebElement> columns;

    @FindBy(id = "addNewRecordButton")
    public WebElement backToBookStoreButton;

    @FindBy(css = "[class='mt-2 row']")
    public List<BookDetailRow> bookDetailRows;

    public BookStoreProfilePage() {
        updateIndexes();
    }

    void updateIndexes() {
        for (WebElement column : columns) {
            columnIndexes.put(column.getText(), columns.indexOf(column));
        }
    }

    public BookDetailRow getDetailRow(String label){
        for (BookDetailRow bookDetailRow : bookDetailRows) {
            if (bookDetailRow.getLabel().replaceAll(":", "").trim().equals(label))
                return bookDetailRow;
        }
        throw new RuntimeException("Label not found!");
    }

    public BookRow getBookRow(String bookTitle) {
        for (BookRow book : bookRows)
            if (book.getTitle(columnIndexes.get("Title")).equals(bookTitle))
                return book;
        throw new RuntimeException("Book not found!");
    }

    public String getBookAttribute(String bookTitle, String attributeName) {
        BookRow bookRow = getBookRow(bookTitle);
        return bookRow.cells.get(columnIndexes.get(attributeName)).getText();
    }

}
