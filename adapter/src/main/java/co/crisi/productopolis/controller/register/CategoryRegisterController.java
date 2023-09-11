package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.ICategoryRegisterBoundary;
import co.crisi.productopolis.model.request.CategoryRequest;
import co.crisi.productopolis.model.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRegisterController {

    private final ICategoryRegisterBoundary boundary;

    @PostMapping
    @Operation(summary = "Creates a new Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category Created")
    })
    public ResponseEntity<CategoryResponse> create(
            @RequestBody
                    CategoryRequest request) {
        return new ResponseEntity<>(boundary.create(request), HttpStatus.CREATED);
    }

}
