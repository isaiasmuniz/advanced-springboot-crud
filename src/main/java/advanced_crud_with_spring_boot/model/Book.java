package advanced_crud_with_spring_boot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "advancedcrud")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author_name", nullable = false, length = 80)
    private String authorName;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(name = "launch_date", nullable = false, length = 60)
    private LocalDate launchDate;
    @Column(nullable = false, length = 80)
    private double price;
    @Column(nullable = true, length = 80)
    private int page;
    @Column(nullable = true, length = 80)
    private String language;

    public Book() {}

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public int getPage() {
        return page;
    }

    public Book setPage(int page) {
        this.page = page;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Book setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Book setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public Book setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Book setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return Double.compare(price, book.price) == 0 && Objects.equals(id, book.id) && Objects.equals(authorName, book.authorName) && Objects.equals(title, book.title) && Objects.equals(launchDate, book.launchDate) && Objects.equals(page, book.page) && Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, title, launchDate, price, page, language);
    }
}
