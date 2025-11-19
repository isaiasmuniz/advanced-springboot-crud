package advanced_crud_with_spring_boot.integrationstests.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WrapperBookDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private BookEmbeddedDTO embeddedDTO;

    public WrapperBookDto() {}

    public BookEmbeddedDTO getEmbeddedDTO() {
        return embeddedDTO;
    }

    public WrapperBookDto setEmbeddedDTO(BookEmbeddedDTO embeddedDTO) {
        this.embeddedDTO = embeddedDTO;
        return this;
    }
}
