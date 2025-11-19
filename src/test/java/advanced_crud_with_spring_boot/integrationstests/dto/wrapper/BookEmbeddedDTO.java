package advanced_crud_with_spring_boot.integrationstests.dto.wrapper;

import advanced_crud_with_spring_boot.integrationstests.dto.BookDtoTest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class BookEmbeddedDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("bookDTOList")
    private List<BookDtoTest> bookDtoTests;

    public BookEmbeddedDTO() {}

    public List<BookDtoTest> getBookDtoTests() {
        return bookDtoTests;
    }

    public BookEmbeddedDTO setBookDtoTests(List<BookDtoTest> bookDtoTests) {
        this.bookDtoTests = bookDtoTests;
        return this;
    }
}
