package co.crisi.productopolis.controller.update;

import co.crisi.productopolis.boundaries.input.update.IAttributeUpdateBoundary;
import co.crisi.productopolis.model.request.AttributeUpdateRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attributes")
@RequiredArgsConstructor
public class AttributeUpdateController {

    private final IAttributeUpdateBoundary boundary;

    @PutMapping
    public ResponseEntity<AttributeResponse> update(
            @RequestBody
                    AttributeUpdateRequest request) {
        return ResponseEntity.ok(boundary.update(request));
    }

}
