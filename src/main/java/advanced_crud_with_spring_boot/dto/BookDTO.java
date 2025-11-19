package advanced_crud_with_spring_boot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authorName;
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate launchDate;
    private double price;

    public BookDTO() {}

    public Long getId() {
        return id;
    }

    public BookDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public BookDTO setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public BookDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookDTO bookDTO)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(price, bookDTO.price) == 0 && Objects.equals(id, bookDTO.id) && Objects.equals(authorName, bookDTO.authorName) && Objects.equals(title, bookDTO.title) && Objects.equals(launchDate, bookDTO.launchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, authorName, title, launchDate, price);
    }
}
