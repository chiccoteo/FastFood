package ai.ecma.appproductservice.service;

import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.CategoryDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    ApiResult<List<CategoryDTO>> getAllByParent();

    ApiResult<List<CategoryDTO>> getByAllParentId(UUID id);

    ApiResult<CategoryDTO> create(CategoryDTO categoryDTO);

    ApiResult<CategoryDTO> edit( UUID id, CategoryDTO categoryDTO);

    ApiResult<?> delete(UUID id);
}
