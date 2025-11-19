package advanced_crud_with_spring_boot.unitetest.mock;

import advanced_crud_with_spring_boot.dto.BookDTO;
import advanced_crud_with_spring_boot.model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MocksBook {

    public Book mockEntity(){
        return mockEntity(0);
    }

    public BookDTO mockDTO(){
        return mockDTO(0);
    }

    public List<Book> mockListOfEntities(){
        List<Book> listOfEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listOfEntities.add(mockEntity(i));
        }
        return listOfEntities;
    }

    public List<BookDTO> mockListOfDTOs(){
        List<BookDTO> listOfDTOs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listOfDTOs.add(mockDTO(i));
        }
        return listOfDTOs;
    }

    public Book mockEntity(Integer number){
        Book mockEntity = new Book();
        mockEntity.setId(number.longValue());
        mockEntity.setAuthorName("Author Name Test " + number);
        mockEntity.setTitle("Book Title Test " + number);
        mockEntity.setPrice(number);
        mockEntity.setLaunchDate(LocalDate.now());
        return mockEntity;
    }

    public BookDTO mockDTO(Integer number){
        BookDTO mockDTO = new BookDTO();
        mockDTO.setId(number.longValue());
        mockDTO.setAuthorName("Author Name Test " + number);
        mockDTO.setTitle("Book Title Test " + number);
        mockDTO.setPrice(number);
        mockDTO.setLaunchDate(LocalDate.now());
        return mockDTO;
    }
}
