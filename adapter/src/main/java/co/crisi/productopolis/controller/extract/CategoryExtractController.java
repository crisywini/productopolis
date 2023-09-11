package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.ICategoryExtractBoundary;
import co.crisi.productopolis.model.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Find a Category by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Found")
    })
    public ResponseEntity<CategoryResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    @Operation(summary = "Find All Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories Found")
    })
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
