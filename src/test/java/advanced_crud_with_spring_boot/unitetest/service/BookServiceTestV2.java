package advanced_crud_with_spring_boot.unitetest.service;

import advanced_crud_with_spring_boot.dto.BookDtoV2;
import advanced_crud_with_spring_boot.model.Book;
import advanced_crud_with_spring_boot.repository.BookRepository;
import advanced_crud_with_spring_boot.service.BookService;
import advanced_crud_with_spring_boot.unitetest.mock.MockDtoV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTestV2 {

    MockDtoV2 mockDtoV2;

    @InjectMocks
    BookService service;

    @Mock
    BookRepository repository;
    
    @Mock
    PagedResourcesAssembler<BookDtoV2> assembler;

    @BeforeEach
    void setUp(){
        mockDtoV2 = new MockDtoV2();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void createV2(){
        Book book = mockDtoV2.mockEntityV2(0);
        Book persisted = book;
        persisted.setId(2L);

        BookDtoV2 dtoV2 = mockDtoV2.mockDtoV2();

        when(repository.save(any(Book.class))).thenReturn(persisted);

        var result = service.createV2(dtoV2);

        var dataValidation = LocalDate.now();

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
        && link.getHref().endsWith("/advancedcrud/api/v2/2")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
        && link.getHref().endsWith("/advancedcrud/api/v2?page=0&size=5&direction=asc")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
        && link.getHref().endsWith("/advancedcrud/api/v2")
        && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
        && link.getHref().endsWith("/advancedcrud/api/v2")
        && link.getType().equals("PUT")));

        assertEquals("Author Name V2 Teste 0", result.getAuthorName());
        assertEquals("Title V2 Teste 0", result.getTitle());
        assertEquals(0, result.getPrice());
        assertEquals(dataValidation, result.getLaunchDate());
        assertEquals(0, result.getPage());
        assertEquals("Language Test 0", result.getLanguage());
        assertEquals(2L, result.getId());

    }

    @Test
    void findByIdv2(){
        Book book = mockDtoV2.mockEntityV2(3);
        Book persisted = book;
        persisted.setId(3L);

        BookDtoV2 dto = mockDtoV2.mockDtoV2(3);

        when(repository.findById(3L)).thenReturn(Optional.of(persisted));

        var result = service.findByIdV2(dto.getId());

        var dataValidation = LocalDate.now();

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getId());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
        && link.getHref().endsWith("/advancedcrud/api/v2/3")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
        && link.getHref().endsWith("/advancedcrud/api/v2?page=0&size=5&direction=asc")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
        && link.getHref().endsWith("/advancedcrud/api/v2")
        && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
        && link.getHref().endsWith("/advancedcrud/api/v2")
        && link.getType().equals("PUT")));

        assertEquals("Author Name V2 Teste 3", result.getAuthorName());
        assertEquals("Title V2 Teste 3", result.getTitle());
        assertEquals(3, result.getPrice());
        assertEquals(dataValidation, result.getLaunchDate());
        assertEquals(3, result.getPage());
        assertEquals("Language Test 3", result.getLanguage());
        assertEquals(3L, result.getId());
    }

    @Test
    void updateV2(){
        Book book = mockDtoV2.mockEntityV2(2);
        Book persisted = book;
        persisted.setId(2L);

        BookDtoV2 dto = mockDtoV2.mockDtoV2(2);

        when(repository.findById(2L)).thenReturn(Optional.of(persisted));
        when(repository.save(any(Book.class))).thenReturn(persisted);

        var result = service.updateV2(dto);

        var dataValidation = LocalDate.now();

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getId());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/advancedcrud/api/v2/2")
                && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/advancedcrud/api/v2?page=0&size=5&direction=asc")
                && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/advancedcrud/api/v2")
                && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/advancedcrud/api/v2")
                && link.getType().equals("PUT")));

        assertEquals("Author Name V2 Teste 2", result.getAuthorName());
        assertEquals("Title V2 Teste 2", result.getTitle());
        assertEquals(2, result.getPrice());
        assertEquals(dataValidation, result.getLaunchDate());
        assertEquals(2, result.getPage());
        assertEquals("Language Test 2", result.getLanguage());
        assertEquals(2L, result.getId());
    }
    
    @Test
    void findAllV2(){
        List<Book> books = mockDtoV2.mockListOfEntitiesV2();
        Page<Book> mockPage = new PageImpl<>(books);
        
        when(repository.findAll(any(Pageable.class))).thenReturn(mockPage);
        
        List<BookDtoV2> dtoV2List = mockDtoV2.mockListOfDtoV2();
        
        List<EntityModel<BookDtoV2>> entityModels = dtoV2List.stream().map(EntityModel::of).collect(Collectors.toList());

        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(
                mockPage.getSize(),
                mockPage.getNumber(),
                mockPage.getTotalElements(),
                mockPage.getTotalPages()
        );
        
        PagedModel<EntityModel<BookDtoV2>> mockPagedModel = PagedModel.of(entityModels, pageMetadata);
        when(assembler.toModel(any(Page.class), any(Link.class))).thenReturn(mockPagedModel);
        
        PagedModel<EntityModel<BookDtoV2>> result = service.finAllV2(PageRequest.of(0, 5));
        
        List<BookDtoV2> bookDtoList = result.getContent().stream().map(EntityModel::getContent).collect(Collectors.toList());
        
        assertNotNull(bookDtoList);
        assertEquals(5, bookDtoList.size());
        
        validateIndividualBook(bookDtoList.get(1), 1);
        validateIndividualBook(bookDtoList.get(3), 3);
        validateIndividualBook(bookDtoList.get(4), 4);
    }

    private void validateIndividualBook(BookDtoV2 dtoV2, Integer i) {
        assertNotNull(dtoV2);
        assertNotNull(dtoV2.getId());
        assertNotNull(dtoV2.getLinks());

        assertEquals("Author Name V2 Teste " + i, dtoV2.getAuthorName());
        assertEquals("Title V2 Teste "  + i, dtoV2.getTitle());
        assertEquals((int) i, dtoV2.getPrice());
        assertEquals(i, dtoV2.getPage());
        assertEquals("Language Test " + i, dtoV2.getLanguage());
        assertEquals(i.longValue(), dtoV2.getId());
    }
}
