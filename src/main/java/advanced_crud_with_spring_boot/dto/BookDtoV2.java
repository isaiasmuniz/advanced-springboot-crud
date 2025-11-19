package advanced_crud_with_spring_boot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookDtoV2 extends RepresentationModel<BookDtoV2> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authorName;
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate launchDate;
    private double price;
    private int page;
    private String language;

    public BookDtoV2() {}

    public Long getId() {
        return id;
    }

    public BookDtoV2 setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookDtoV2 setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDtoV2 setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public BookDtoV2 setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public BookDtoV2 setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getPage() {
        return page;
    }

    public BookDtoV2 setPage(int page) {
        this.page = page;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public BookDtoV2 setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookDtoV2 bookDtoV2)) return false;
        return Double.compare(price, bookDtoV2.price) == 0 && Objects.equals(id, bookDtoV2.id) && Objects.equals(authorName, bookDtoV2.authorName) && Objects.equals(title, bookDtoV2.title) && Objects.equals(launchDate, bookDtoV2.launchDate) && Objects.equals(page, bookDtoV2.page) && Objects.equals(language, bookDtoV2.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, title, launchDate, price, page, language);
    }
}
