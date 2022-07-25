package ai.ecma.appproductservice.controller;

import ai.ecma.appproductservice.service.CategoryService;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService service;

    @Override
    public ApiResult<List<CategoryDTO>> getAllByParent() {
        return service.getAllByParent();
    }

    @Override
    public ApiResult<List<CategoryDTO>> getByAllParentId(UUID id) {
        return service.getByAllParentId(id);
    }

    @Override
    public ApiResult<CategoryDTO> create(CategoryDTO categoryDTO) {
        return service.create(categoryDTO);
    }

    @Override
    public ApiResult<CategoryDTO> edit(UUID id, CategoryDTO categoryDTO) {
        return service.edit(id,categoryDTO);
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        return service.delete(id);
    }
}
