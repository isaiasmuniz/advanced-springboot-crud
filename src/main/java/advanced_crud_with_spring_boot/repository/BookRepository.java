package advanced_crud_with_spring_boot.repository;

import advanced_crud_with_spring_boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
