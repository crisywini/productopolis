package co.crisi.productopolis.controller.delete;

import co.crisi.productopolis.boundaries.input.delete.IBrandDeleteBoundary;
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
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandDeleteController {

    private final IBrandDeleteBoundary boundary;

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Brand by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand Deleted")
    })
    public ResponseEntity<Long> deleteById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.deleteById(id));
    }

}
