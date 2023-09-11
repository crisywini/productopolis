package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.IBrandRegisterBoundary;
import co.crisi.productopolis.model.request.BrandRequest;
import co.crisi.productopolis.model.response.BrandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandRegisterController {

    private final IBrandRegisterBoundary boundary;

    @PostMapping
    @Operation(summary = "Creates a new brand")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Brand Created")
            }
    )
    public ResponseEntity<BrandResponse> create(
            @RequestBody
                    BrandRequest request) {
        log.debug("Creating a new brand");
        return new ResponseEntity<>(boundary.create(request), HttpStatus.CREATED);
    }

}
