package ai.ecma.appproductservice.controller;


import ai.ecma.appproductservice.utils.AppConstant;
import ai.ecma.library.entity.Category;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.CategoryDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(CategoryController.CATEGORY_CONTROLLER)
public interface CategoryController {
    String CATEGORY_CONTROLLER = AppConstant.BASE_PATH_V1 + "/category";

    @GetMapping
    ApiResult<List<CategoryDTO>> getAllByParent();


    @GetMapping("/{id}")
    ApiResult<List<CategoryDTO>> getByAllParentId(@PathVariable UUID id);

    @PostMapping
    ApiResult<CategoryDTO> create(@RequestBody @Valid CategoryDTO categoryDTO);

    @PutMapping("/{id}")
    ApiResult<CategoryDTO> edit(@PathVariable UUID id, @RequestBody @Valid CategoryDTO categoryDTO);

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);
}
