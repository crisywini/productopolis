package co.crisi.productopolis.controller.delete;

import co.crisi.productopolis.boundaries.input.delete.IAttributeDeleteBoundary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attributes")
@RequiredArgsConstructor
public class AttributeDeleteController {

    private final IAttributeDeleteBoundary boundary;

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.deleteById(id));
    }

}