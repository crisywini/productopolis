package co.crisi.productopolis.controller.extract;

import co.crisi.productopolis.boundaries.input.extract.IAttributeExtractBoundary;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
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
@RequestMapping("/attributes")
@RequiredArgsConstructor
public class AttributeExtractController {

    private final IAttributeExtractBoundary boundary;

    @GetMapping("/{id}")
    @Operation(summary = "Find an Attribute by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attribute Found")
    })
    public ResponseEntity<AttributeResponse> getById(
            @PathVariable(name = "id")
                    Long id) {
        return ResponseEntity.ok(boundary.getById(id));
    }

    @GetMapping
    @Operation(summary = "Find All Attributes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attributes Found")
    })
    public ResponseEntity<List<AttributeResponse>> getAll() {
        return ResponseEntity.ok(boundary.getAll());
    }

}
