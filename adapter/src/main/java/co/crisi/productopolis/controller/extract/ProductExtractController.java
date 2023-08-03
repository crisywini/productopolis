package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.IProductExtractBoundary;
import co.crisi.productopolis.model.response.ProductResponse;
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
    public ResponseEntity<ProductResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
