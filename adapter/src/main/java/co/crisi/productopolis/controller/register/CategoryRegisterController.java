package co.crisi.productopolis.controller.register;

import co.crisi.productopolis.boundaries.input.register.ICategoryRegisterBoundary;
import co.crisi.productopolis.model.request.CategoryRequest;
import co.crisi.productopolis.model.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRegisterController {

    private final ICategoryRegisterBoundary boundary;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(
            @RequestBody
                    CategoryRequest request) {
        return ResponseEntity.ok(boundary.create(request));
    }

}
