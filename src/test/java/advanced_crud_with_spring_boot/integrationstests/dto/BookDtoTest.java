package advanced_crud_with_spring_boot.integrationstests.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookDtoTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authorName;
    private String title;
    private String launchDate;
    private double price;
    private int page;
    private String language;

    public BookDtoTest() {}

    public int getPage() {
        return page;
    }

    public BookDtoTest setPage(int page) {
        this.page = page;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public BookDtoTest setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BookDtoTest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookDtoTest setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDtoTest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public BookDtoTest setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public BookDtoTest setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookDtoTest that)) return false;
        return Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(authorName, that.authorName) && Objects.equals(title, that.title) && Objects.equals(launchDate, that.launchDate) && Objects.equals(page, that.page) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, title, launchDate, price, page, language);
    }
}
