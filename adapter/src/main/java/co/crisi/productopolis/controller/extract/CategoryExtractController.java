package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.ICategoryExtractBoundary;
import co.crisi.productopolis.model.response.CategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryExtractController {

    private final ICategoryExtractBoundary boundary;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
