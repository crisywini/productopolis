package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.model.request.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRegisterController {

    private final IProductRegisterBoundary boundary;

    @PostMapping
    @Operation(summary = "Creates a new Product")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Product Created")
            }
    )
    public ResponseEntity<ProductResponse> create(
            @RequestBody
                    ProductRequest request) {
        return new ResponseEntity<>(boundary.create(request), HttpStatus.CREATED);
    }

}
