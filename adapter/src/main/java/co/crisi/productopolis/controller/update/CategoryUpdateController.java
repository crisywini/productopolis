package co.crisi.productopolis.controller.update;

import co.crisi.productopolis.boundaries.input.update.ICategoryUpdateBoundary;
import co.crisi.productopolis.model.request.CategoryUpdateRequest;
import co.crisi.productopolis.model.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryUpdateController {

    private final ICategoryUpdateBoundary boundary;

    @PutMapping
    @Operation(summary = "Updates an Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Updated")
    })
    public ResponseEntity<CategoryResponse> update(
            @RequestBody
                    CategoryUpdateRequest request) {
        return ResponseEntity.ok(boundary.update(request));
    }

}
