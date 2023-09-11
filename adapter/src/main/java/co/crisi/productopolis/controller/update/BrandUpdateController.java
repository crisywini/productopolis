package co.crisi.productopolis.controller.update;

import co.crisi.productopolis.boundaries.input.update.IBrandUpdateBoundary;
import co.crisi.productopolis.model.request.BrandUpdateRequest;
import co.crisi.productopolis.model.response.BrandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandUpdateController {

    private final IBrandUpdateBoundary boundary;

    @PutMapping
    @Operation(summary = "Updates a Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand Updated")
    })
    public ResponseEntity<BrandResponse> update(
            @RequestBody
                    BrandUpdateRequest request) {
        return ResponseEntity.ok(boundary.update(request));
    }

}
