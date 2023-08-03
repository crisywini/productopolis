package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.IAttributeRegisterBoundary;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<AttributeResponse> create(
            @RequestBody
                    AttributeRequest request) {
        return ResponseEntity.ok(boundary.create(request));
    }

}
