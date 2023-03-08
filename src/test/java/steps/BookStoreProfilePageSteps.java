package steps;

import bookstore.models.BookModel;
import bookstore.models.UserResponse;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.BookStoreProfilePage;
import pages.components.BookRow;
import utilities.TestStore;

import java.util.List;
import static resources.Colors.BLUE;

public class BookStoreProfilePageSteps {

    BookStoreProfilePage bookStoreProfilePage = new BookStoreProfilePage();

    @Given("Verify book details for the books of user in context")
    public void verifyBooks() {
        List<BookModel> bookList = ((UserResponse)TestStore.get("userResponse")).getBooks();

        for (BookModel book: bookList) {
            BookRow bookRow = bookStoreProfilePage.getBookRow(book.getTitle());
            bookRow.selectRow();

            String expectedIsbn = book.getIsbn();
            String actualIsbn = bookStoreProfilePage.getDetailRow("ISBN").getValue();
            Assert.assertEquals("Isbns not match", expectedIsbn, actualIsbn);
            bookStoreProfilePage.log.new Success("Isbns matches for book named: " +BLUE+book.getTitle());

            String expectedTitle = book.getTitle();
            String actualTitle = bookStoreProfilePage.getDetailRow("Title").getValue();
            Assert.assertEquals("Titles are not match", expectedTitle, actualTitle);
            bookStoreProfilePage.log.new Success("Titles matches for book named: " +BLUE+book.getTitle());

            String expectedSubTitle = book.getSubTitle();
            String actualSubTitle = bookStoreProfilePage.getDetailRow("Sub Title").getValue();
            Assert.assertEquals("Sub Titles not match", expectedSubTitle, actualSubTitle);
            bookStoreProfilePage.log.new Success("Subtitles matches for book named: " +BLUE+book.getTitle());

            String expectedAuthor = book.getAuthor();
            String actualAuthor = bookStoreProfilePage.getDetailRow("Author").getValue();
            Assert.assertEquals("Authors not match", expectedAuthor, actualAuthor);
            bookStoreProfilePage.log.new Success("Authors matches for book named: " +BLUE+book.getTitle());

            String expectedPublisher = book.getPublisher();
            String actualPublisher = bookStoreProfilePage.getDetailRow("Publisher").getValue();
            Assert.assertEquals("Publishers not match", expectedPublisher, actualPublisher);
            bookStoreProfilePage.log.new Success("Publishers matches for book named: " +BLUE+book.getTitle());

            String expectedPages = String.valueOf(book.getPages());
            String actualPages = bookStoreProfilePage.getDetailRow("Total Pages").getValue();
            Assert.assertEquals("Pages not match", expectedPages, actualPages);
            bookStoreProfilePage.log.new Success("Pages matches for book named: " +BLUE+book.getTitle());

            String expectedDescription = book.getDescription().trim();
            String actualDescription = bookStoreProfilePage.getDetailRow("Description").getValue();
            Assert.assertEquals("Publishers not match", expectedDescription, actualDescription);
            bookStoreProfilePage.log.new Success( "Publishers matches for book named: " +BLUE+book.getTitle());

            String expectedWebsite = book.getWebsite();
            String actualWebsite = bookStoreProfilePage.getDetailRow("Website").getValue();
            Assert.assertEquals("Websites not match", expectedWebsite, actualWebsite);
            bookStoreProfilePage.log.new Success("Websites matches for book named: " +BLUE+book.getTitle());

            bookStoreProfilePage.clickElementUntil(true, bookStoreProfilePage.backToBookStoreButton);
        }
    }

    @Given("Get {} from given book title")
    public void getBookAuthor(String headerContent) {
        UserResponse expectedResponse = ((UserResponse) TestStore.get("userResponse"));
        bookStoreProfilePage.getBookAttribute(expectedResponse.getBooks().get(0).getTitle(), "Publisher");
    }

}
