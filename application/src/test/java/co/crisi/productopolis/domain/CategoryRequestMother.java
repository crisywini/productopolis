package co.crisi.productopolis.domain;

import co.crisi.productopolis.model.request.CategoryRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryRequestMother {

    public CategoryRequest random(){
        return new CategoryRequest("books", "everything as a book");
    }

}
