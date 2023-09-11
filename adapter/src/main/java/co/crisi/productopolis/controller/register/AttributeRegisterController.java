package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.IAttributeRegisterBoundary;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attributes")
@RequiredArgsConstructor
public class AttributeRegisterController {

    private final IAttributeRegisterBoundary boundary;

    @Operation(summary = "Creates an attribute")
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Attribute Created")
            }
    )
    public ResponseEntity<AttributeResponse> create(
            @RequestBody
                    AttributeRequest request) {
        return new ResponseEntity<>(boundary.create(request), HttpStatus.CREATED);
    }

}
