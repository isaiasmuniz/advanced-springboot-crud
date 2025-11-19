package advanced_crud_with_spring_boot.integrationstests.controller;

import advanced_crud_with_spring_boot.integrationstests.config.TestConfig;
import advanced_crud_with_spring_boot.integrationstests.dto.BookDtoTest;
import advanced_crud_with_spring_boot.integrationstests.dto.wrapper.WrapperBookDto;
import advanced_crud_with_spring_boot.integrationstests.testecontainers.AbstractionIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTest extends AbstractionIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper mapper;
    private static BookDtoTest bookDTO;

    @BeforeAll
    static void setUp(){
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        bookDTO =  new BookDtoTest();
    }

    @Test
    @Order(1)
    void create() throws IOException {
        mockBook();

        specification = new RequestSpecBuilder().addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_LOCAL).setBasePath("/advancedcrud/api/v1")
                .setPort(TestConfig.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL)).build();

        var content = given(specification).body(bookDTO).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post().then().log().all()
                .statusCode(200).extract().body().asString();

        BookDtoTest created = mapper.readValue(content, BookDtoTest.class);
        bookDTO = created;

        assertEquals("Isaias", created.getAuthorName());
        assertEquals("Um morto muito louco", created.getTitle());
        assertEquals(5.2, created.getPrice());
        assertEquals("15/02/2025", created.getLaunchDate());
    }

    @Test
    @Order(2)
    void findById() throws IOException {

        var content = given(specification).pathParam("id", bookDTO.getId())
                .when().get("{id}").then().log().all()
                .statusCode(200).extract().body().asString();

        BookDtoTest created = mapper.readValue(content, BookDtoTest.class);
        bookDTO = created;

        assertEquals("Isaias", created.getAuthorName());
        assertEquals("Um morto muito louco", created.getTitle());
        assertEquals(5.2, created.getPrice());
        assertEquals("15/02/2025", created.getLaunchDate());
    }

    @Test
    @Order(3)
    void update() throws IOException {
        bookDTO.setAuthorName("Felipe");

        var content = given(specification).body(bookDTO).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put().then().log().all()
                .statusCode(200).extract().body().asString();

        BookDtoTest created = mapper.readValue(content, BookDtoTest.class);
        bookDTO = created;

        assertEquals("Felipe", created.getAuthorName());
        assertEquals("Um morto muito louco", created.getTitle());
        assertEquals(5.2, created.getPrice());
        assertEquals("15/02/2025", created.getLaunchDate());
    }

    @Test
    @Order(4)
    void delete() {
        given(specification).pathParam("id", bookDTO.getId())
                .when().delete("{id}").then().log().all()
                .statusCode(204);
    }

    @Test
    @Order(5)
    void findAll() throws IOException {

       var content = given(specification).accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParams("page", 0, "size", 5, "direction", "asc")
                .when().get().then().log().all()
                .statusCode(200).extract().body().asString();

        WrapperBookDto wrapper = mapper.readValue(content, WrapperBookDto.class);
        List<BookDtoTest> books = wrapper.getEmbeddedDTO().getBookDtoTests();

        BookDtoTest bookOne = books.get(0);

        assertEquals("Alberik", bookOne.getAuthorName());
        assertEquals("Assassination on the Tiber", bookOne.getTitle());
        assertEquals(2.45, bookOne.getPrice());
        assertEquals("20/12/2024", bookOne.getLaunchDate());

        BookDtoTest bookThree= books.get(2);

        assertEquals("Malinda", bookThree.getAuthorName());
        assertEquals("Touch of Evil", bookThree.getTitle());
        assertEquals(5.93, bookThree.getPrice());
        assertEquals("02/05/2025", bookThree.getLaunchDate());

        BookDtoTest bookFour= books.get(4);

        assertEquals("Kathye", bookFour.getAuthorName());
        assertEquals("Calendar", bookFour.getTitle());
        assertEquals(2.59, bookFour.getPrice());
        assertEquals("12/01/2025", bookFour.getLaunchDate());
    }

    public void mockBook(){
        bookDTO.setAuthorName("Isaias");
        bookDTO.setTitle("Um morto muito louco");
        bookDTO.setPrice(5.2);
        bookDTO.setLaunchDate("15/02/2025");
        bookDTO.setPage(546);
        bookDTO.setLanguage("pt");
    }
}