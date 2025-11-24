package advanced_crud_with_spring_boot.service;

import advanced_crud_with_spring_boot.controller.BookController;
import advanced_crud_with_spring_boot.controller.BookControllerv2;
import advanced_crud_with_spring_boot.dto.BookDTO;
import advanced_crud_with_spring_boot.dto.BookDtoV2;
import advanced_crud_with_spring_boot.exception.ResourceNotFoundException;
import advanced_crud_with_spring_boot.model.Book;
import advanced_crud_with_spring_boot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static advanced_crud_with_spring_boot.mapper.DozerObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    @Autowired
    PagedResourcesAssembler<BookDTO> pagedResourcesAssembler;

    @Autowired
    PagedResourcesAssembler<BookDtoV2> pagedResourcesAssembler2;

    Logger logger = Logger.getLogger(BookService.class.getName());

    public PagedModel<EntityModel<BookDTO>> findAll(Pageable pageable){
        logger.info("find all books");

        var result = repository.findAll(pageable).map(book -> {
            var dto = parseObject(book, BookDTO.class);
            addHateoasLink(dto);
            return dto;
        });

        Link findAllLinks = WebMvcLinkBuilder.linkTo(methodOn(BookController.class).findAll(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                String.valueOf(pageable.getSort())
        )).withSelfRel();

        return pagedResourcesAssembler.toModel(result, findAllLinks);
    }

    public PagedModel<EntityModel<BookDtoV2>> finAllV2(Pageable pageable){
        logger.info("find all book including page and language");

        var result = repository.findAll(pageable).map(book -> {
            var dto = parseObject(book, BookDtoV2.class);
            addHateoasLinkV2(dto);
            return dto;
        });

        Link findAllLinksV2 = WebMvcLinkBuilder.linkTo(methodOn(BookControllerv2.class).findAllV2(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                String.valueOf(pageable.getSort())
        )).withSelfRel();

        return pagedResourcesAssembler2.toModel(result, findAllLinksV2);
    }

    public BookDTO findById(Long id){
        logger.info("find a book by id");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLink(dto);
        return dto;
    }

    public BookDtoV2 findByIdV2(Long id){
        logger.info("find by id v2");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        var dto = parseObject(entity, BookDtoV2.class);
        addHateoasLinkV2(dto);
        return dto;
    }

    public BookDTO create(BookDTO bookDto){
        logger.info("creating a book");

        var entity = parseObject(bookDto, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLink(dto);
        return dto;
    }

    public BookDtoV2 createV2(BookDtoV2 bookV2){
        logger.info("creating a book");

        if (bookV2 == null) throw  new ResourceNotFoundException();

        var entity = parseObject(bookV2, Book.class);
        var dto = parseObject(repository.save(entity), BookDtoV2.class);
        addHateoasLinkV2(dto);
        return dto;
    }

    public BookDTO update(BookDTO bookDTO){
        logger.info("updating book records");

        var entity = repository.findById(bookDTO.getId()).orElseThrow(() -> new ResourceNotFoundException());
        entity.setAuthorName(bookDTO.getAuthorName());
        entity.setTitle(bookDTO.getTitle());
        entity.setLaunchDate(bookDTO.getLaunchDate());
        entity.setPrice(bookDTO.getPrice());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLink(dto);
        return dto;
    }

    public BookDtoV2 updateV2(BookDtoV2 bookV2){
        logger.info("updating a v2 book");

        if (bookV2 == null) throw  new ResourceNotFoundException();


        var entity = repository.findById(bookV2.getId()).orElseThrow(() -> new ResourceNotFoundException());
        entity.setTitle(entity.getTitle());
        entity.setLanguage(bookV2.getLanguage());
        entity.setPrice(bookV2.getPrice());
        entity.setPage(entity.getPage());
        entity.setAuthorName(bookV2.getAuthorName());
        entity.setAuthorName(bookV2.getAuthorName());

        var dto = parseObject(repository.save(entity), BookDtoV2.class);
        addHateoasLinkV2(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("deleting a book");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        repository.delete(entity);
    }

    private void addHateoasLink(BookDTO dto){
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookController.class).findAll(0, 5, "asc")).withRel("findAll").withType("GET"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

    private void addHateoasLinkV2(BookDtoV2 dto){
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookControllerv2.class).findByIdV2(dto.getId())).withSelfRel().withType("GET"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookControllerv2.class).findAllV2(0, 5, "asc")).withRel("findAll").withType("GET"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookControllerv2.class).createV2(dto)).withRel("create").withType("POST"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookControllerv2.class).updateV2(dto)).withRel("update").withType("PUT"));
        dto.add(WebMvcLinkBuilder.linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
