package advanced_crud_with_spring_boot.controller;

import advanced_crud_with_spring_boot.controller.docs.BookControllerDoc;
import advanced_crud_with_spring_boot.controller.docs.BookControllerDocV2;
import advanced_crud_with_spring_boot.dto.BookDTO;
import advanced_crud_with_spring_boot.dto.BookDtoV2;
import advanced_crud_with_spring_boot.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advancedcrud/api/v2")
@Tag(name = "books", description = "Endpoints to books")
public class BookControllerv2 implements BookControllerDocV2 {
    @Autowired

    BookService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public ResponseEntity<PagedModel<EntityModel<BookDtoV2>>> findAllV2(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "direction", defaultValue = "asc") String direction)
    {
        var sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));
        return ResponseEntity.ok(service.finAllV2(pageable));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public BookDtoV2 findByIdV2(@PathVariable("id") Long id) {
        return service.findByIdV2(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookDtoV2 createV2(@RequestBody BookDtoV2 bookDtoV2){
        return service.createV2(bookDtoV2);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public BookDtoV2 updateV2(@RequestBody BookDtoV2 bookDTO) {
        return service.updateV2(bookDTO);
    }

}
