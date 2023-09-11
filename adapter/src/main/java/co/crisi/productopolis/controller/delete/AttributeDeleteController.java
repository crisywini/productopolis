package co.crisi.productopolis.controller.delete;

import co.crisi.productopolis.boundaries.input.delete.IAttributeDeleteBoundary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Deletes an Attribute by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attribute Deleted")
    })
    public ResponseEntity<Long> deleteById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.deleteById(id));
    }

}
