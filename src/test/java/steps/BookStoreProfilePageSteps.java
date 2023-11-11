package steps;

import bookstore.models.BookModel;
import bookstore.models.UserResponse;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.BookStoreProfilePage;
import pages.components.BookRow;
import utilities.TestStore;
import utils.StringUtilities;

import java.util.List;



public class BookStoreProfilePageSteps {

    BookStoreProfilePage bookStoreProfilePage = new BookStoreProfilePage();
    StringUtilities strUtils = new StringUtilities();

    @Given("Verify book details for the books of user in context")
    public void verifyBooks() {
        List<BookModel> bookList = ((UserResponse)TestStore.get("userResponse")).getBooks();

        for (BookModel book: bookList) {
            BookRow bookRow = bookStoreProfilePage.getBookRow(book.getTitle());
            bookRow.selectRow();

            String expectedTitle = book.getTitle();
            String actualTitle = bookStoreProfilePage.getDetailRow("Title").getValue();
            Assert.assertEquals("Titles are not match", expectedTitle, actualTitle);
            bookStoreProfilePage.log.success(
                    "Titles matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, book.getTitle()));

            String expectedIsbn = book.getIsbn();
            String actualIsbn = bookStoreProfilePage.getDetailRow("ISBN").getValue();
            Assert.assertEquals("Isbns not match", expectedIsbn, actualIsbn);
            bookStoreProfilePage.log.success(
                    "Isbns matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, book.getIsbn()));

            String expectedSubTitle = book.getSubTitle();
            String actualSubTitle = bookStoreProfilePage.getDetailRow("Sub Title").getValue();
            Assert.assertEquals("Sub Titles not match", expectedSubTitle, actualSubTitle);
            bookStoreProfilePage.log.success(
                    "Subtitles matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, book.getSubTitle()));

            String expectedAuthor = book.getAuthor();
            String actualAuthor = bookStoreProfilePage.getDetailRow("Author").getValue();
            Assert.assertEquals("Authors not match", expectedAuthor, actualAuthor);
            bookStoreProfilePage.log.success(
                    "Authors matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, book.getAuthor()));

            String expectedPublisher = book.getPublisher();
            String actualPublisher = bookStoreProfilePage.getDetailRow("Publisher").getValue();
            Assert.assertEquals("Publishers not match", expectedPublisher, actualPublisher);
            bookStoreProfilePage.log.success(
                    "Publishers matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, book.getPublisher()));

            String expectedPages = String.valueOf(book.getPages());
            String actualPages = bookStoreProfilePage.getDetailRow("Total Pages").getValue();
            Assert.assertEquals("Pages not match", expectedPages, actualPages);
            bookStoreProfilePage.log.success(
                    "Pages matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, String.valueOf(book.getPages())));

            String expectedDescription = book.getDescription().trim();
            String actualDescription = bookStoreProfilePage.getDetailRow("Description").getValue();
            Assert.assertEquals("Publishers not match", expectedDescription, actualDescription);
            bookStoreProfilePage.log.success(
                    "Publishers matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, book.getPublisher()));

            String expectedWebsite = book.getWebsite();
            String actualWebsite = bookStoreProfilePage.getDetailRow("Website").getValue();
            Assert.assertEquals("Websites not match", expectedWebsite, actualWebsite);
            bookStoreProfilePage.log.success(
                    "Websites matches for book named: " + book.getTitle() +
                            " -> " + strUtils.highlighted(StringUtilities.Color.BLUE, book.getWebsite()));

            bookStoreProfilePage.clickElementUntil(bookStoreProfilePage.backToBookStoreButton, true);
        }
    }

    @Given("Get {} from given book title")
    public void getBookAuthor(String headerContent) {
        UserResponse expectedResponse = ((UserResponse) TestStore.get("userResponse"));
        bookStoreProfilePage.getBookAttribute(expectedResponse.getBooks().get(0).getTitle(), "Publisher");
    }

}
