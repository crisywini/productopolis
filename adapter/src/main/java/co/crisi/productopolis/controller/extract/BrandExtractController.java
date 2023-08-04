package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.IBrandExtractBoundary;
import co.crisi.productopolis.model.response.BrandResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandExtractController {

    private final IBrandExtractBoundary boundary;

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
