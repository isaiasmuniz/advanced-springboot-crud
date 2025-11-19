package advanced_crud_with_spring_boot.unitetest.service;

import advanced_crud_with_spring_boot.dto.BookDTO;
import advanced_crud_with_spring_boot.service.BookService;
import advanced_crud_with_spring_boot.unitetest.mock.MockDtoV2;
import advanced_crud_with_spring_boot.unitetest.mock.MocksBook;
import advanced_crud_with_spring_boot.model.Book;
import advanced_crud_with_spring_boot.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MocksBook mock;

    @InjectMocks
    BookService service;

    @Mock
    BookRepository repository;

    @Mock
    PagedResourcesAssembler<BookDTO> assembler;

    @BeforeEach
    void setUp() {
        mock = new MocksBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = mock.mockEntity(2);
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        var result = service.findById(1L);

        LocalDate validation = LocalDate.now();

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getId());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
        && link.getHref().endsWith("/advancedcrud/api/v1/1")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
        && link.getHref().endsWith("/advancedcrud/api/v1?page=0&size=5&direction=asc")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
        && link.getHref().endsWith("/advancedcrud/api/v1")
        && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
        && link.getHref().endsWith("/advancedcrud/api/v1")
        && link.getType().equals("PUT")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
        && link.getHref().endsWith("/advancedcrud/api/v1/1")
        && link.getType().equals("DELETE")));

        assertEquals("Author Name Test 2", result.getAuthorName());
        assertEquals("Book Title Test 2", result.getTitle());
        assertEquals(2, result.getPrice());
        assertEquals(validation, result.getLaunchDate());
        assertEquals(1, result.getId());

    }



    @Test
    void create() {
        Book book = mock.mockEntity(4);
        Book persisted = book;
        persisted.setId(2L);

        BookDTO dto = mock.mockDTO(4);

        when(repository.save(any(Book.class))).thenReturn(persisted);

        var result = service.create(dto);

        LocalDate validation = LocalDate.now();

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getId());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
        && link.getHref().endsWith("advancedcrud/api/v1/2")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
        && link.getHref().endsWith("/advancedcrud/api/v1?page=0&size=5&direction=asc")
        && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
        && link.getHref().endsWith("advancedcrud/api/v1")
        && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
        && link.getHref().endsWith("advancedcrud/api/v1")
        && link.getType().equals("PUT")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
        && link.getHref().endsWith("advancedcrud/api/v1/2")
        && link.getType().equals("DELETE")));

        assertEquals("Author Name Test 4", result.getAuthorName());
        assertEquals("Book Title Test 4", result.getTitle());
        assertEquals(4, result.getPrice());
        assertEquals(validation, result.getLaunchDate());
        assertEquals(2, result.getId());
    }

    @Test
    void update() {
        Book book = mock.mockEntity(3);
        Book persisted = book;
        persisted.setId(1L);

        BookDTO dto = mock.mockDTO(3);

        LocalDate validation = LocalDate.now();

        when(repository.findById(3L)).thenReturn(Optional.of(persisted));
        when(repository.save(any(Book.class))).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getId());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("advancedcrud/api/v1/1")
                && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/advancedcrud/api/v1?page=0&size=5&direction=asc")
                && link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("advancedcrud/api/v1")
                && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("advancedcrud/api/v1")
                && link.getType().equals("PUT")));

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("advancedcrud/api/v1/1")
                && link.getType().equals("DELETE")));

        assertEquals("Author Name Test 3", result.getAuthorName());
        assertEquals("Book Title Test 3", result.getTitle());
        assertEquals(3, result.getPrice());
        assertEquals(validation, result.getLaunchDate());
        assertEquals(1, result.getId());
    }

    @Test
    void delete() {
        Book book = mock.mockEntity(2);
        book.setId(3L);

        when(repository.findById(3L)).thenReturn(Optional.of(book));

        service.delete(3L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Book> entityList = mock.mockListOfEntities();
        Page<Book> mockPage = new PageImpl<>(entityList);

        when(repository.findAll(any(Pageable.class))).thenReturn(mockPage);

        List<BookDTO> dtoList = mock.mockListOfDTOs();

        List<EntityModel<BookDTO>> entitiesModel = dtoList.stream().map(EntityModel::of).collect(Collectors.toList());

        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(
                mockPage.getSize(),
                mockPage.getNumber(),
                mockPage.getTotalElements(),
                mockPage.getTotalPages()
        );

        PagedModel<EntityModel<BookDTO>> mockPageModel = PagedModel.of(entitiesModel, pageMetadata);
        when(assembler.toModel(any(Page.class), any(Link.class))).thenReturn(mockPageModel);

        PagedModel<EntityModel<BookDTO>> result = service.findAll(PageRequest.of(0, 5));

        List<BookDTO> books = result.getContent().stream().map(EntityModel::getContent).collect(Collectors.toList());

        assertNotNull(books);
        assertEquals(5, books.size());

        validateInvidualBook(books.get(1), 1);
        validateInvidualBook(books.get(3), 3);
        validateInvidualBook(books.get(4), 4);
    }

    private void validateInvidualBook(BookDTO bookDTO, int i) {
        assertNotNull(bookDTO);
        assertNotNull(bookDTO.getId());
        assertNotNull(bookDTO.getLinks());

        assertEquals("Author Name Test " + i, bookDTO.getAuthorName());
        assertEquals("Book Title Test " + i, bookDTO.getTitle());
        assertEquals(i, bookDTO.getPrice());
        assertEquals(i, bookDTO.getId());
    }
}