package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.IBrandExtractBoundary;
import co.crisi.productopolis.model.response.BrandResponse;
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
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandExtractController {

    private final IBrandExtractBoundary boundary;

    @GetMapping("/{id}")
    @Operation(summary = "Find a Brand by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand Found")
    })
    public ResponseEntity<BrandResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    @Operation(summary = "Find All Brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brands Found")
    })
    public ResponseEntity<List<BrandResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
