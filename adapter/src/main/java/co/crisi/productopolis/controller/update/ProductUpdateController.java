package co.crisi.productopolis.controller.update;

import co.crisi.productopolis.boundaries.input.update.IProductUpdateBoundary;
import co.crisi.productopolis.model.request.ProductUpdateRequest;
import co.crisi.productopolis.model.response.ProductResponse;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductUpdateController {

    private final IProductUpdateBoundary boundary;

    @PutMapping
    @Operation(summary = "Updates a Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Updated")
    })
    public ResponseEntity<ProductResponse> update(
            @RequestBody
                    ProductUpdateRequest request) {
        return ResponseEntity.ok(boundary.update(request));
    }

}
