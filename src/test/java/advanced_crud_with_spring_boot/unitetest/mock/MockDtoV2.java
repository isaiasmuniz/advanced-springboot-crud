package advanced_crud_with_spring_boot.unitetest.mock;

import advanced_crud_with_spring_boot.dto.BookDtoV2;
import advanced_crud_with_spring_boot.model.Book;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockDtoV2 {

    public Book mockEntityV2(){
        return mockEntityV2(0);
    }

    public BookDtoV2 mockDtoV2(){
        return mockDtoV2(0);
    }

    public List<Book> mockListOfEntitiesV2(){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            books.add(mockEntityV2(i));
        }
        return books;
    }

    public List<BookDtoV2> mockListOfDtoV2(){
        List<BookDtoV2> books = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            books.add(mockDtoV2(i));
        }
        return books;
    }

    public Book mockEntityV2(Integer i){
        Book entityV2 = new Book();
        entityV2.setAuthorName("Author Name V2 Teste " + i);
        entityV2.setTitle("Title V2 Teste " + i);
        entityV2.setPrice(i);
        entityV2.setLaunchDate(LocalDate.now());
        entityV2.setPage(i);
        entityV2.setLanguage("Language Test " + i);
        entityV2.setId(i.longValue());
        return entityV2;
    }

    public BookDtoV2 mockDtoV2(Integer i){
        BookDtoV2 dtoV2 = new BookDtoV2();
        dtoV2.setAuthorName("Author Name V2 Teste " + i);
        dtoV2.setTitle("Title V2 Teste " + i);
        dtoV2.setPrice(i);
        dtoV2.setLaunchDate(LocalDate.now());
        dtoV2.setPage(i);
        dtoV2.setLanguage("Language Test " + i);
        dtoV2.setId(i.longValue());
        return dtoV2;
    }
}
