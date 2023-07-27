package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.IAttributeRegisterBoundary;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attributes")
public class AttributeRegisterController {

    private final IAttributeRegisterBoundary boundary;

    @PostMapping
    public ResponseEntity<AttributeResponse> create(
            @RequestBody
                    AttributeRequest request) throws AttributeBusinessException {
        return ResponseEntity.ok(boundary.create(request));
    }

}
