package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.IProductExtractBoundary;
import co.crisi.productopolis.model.response.ProductResponse;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductExtractController {

    private final IProductExtractBoundary boundary;

    @GetMapping("/{id}")
    @Operation(summary = "Find a Product by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Found")
    })
    public ResponseEntity<ProductResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    @Operation(summary = "Find All Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products Found")
    })
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
