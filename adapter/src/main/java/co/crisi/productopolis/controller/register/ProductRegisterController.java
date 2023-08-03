package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.model.request.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ProductResponse> create(
            @RequestBody
                    ProductRequest request) {
        return ResponseEntity.ok(boundary.create(request));
    }

}
