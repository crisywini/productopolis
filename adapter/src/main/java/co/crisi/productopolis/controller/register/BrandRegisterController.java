package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.IBrandRegisterBoundary;
import co.crisi.productopolis.model.request.BrandRequest;
import co.crisi.productopolis.model.response.BrandResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<BrandResponse> create(
            @RequestBody
                    BrandRequest request) {
        log.debug("Creating a new brand");
        return ResponseEntity.ok(boundary.create(request));
    }

}
