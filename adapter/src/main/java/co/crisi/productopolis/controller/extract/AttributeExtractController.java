package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.IAttributeExtractBoundary;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AttributeExtractController {

    private final IAttributeExtractBoundary boundary;

    @GetMapping("/{id}")
    public ResponseEntity<AttributeResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AttributeResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
