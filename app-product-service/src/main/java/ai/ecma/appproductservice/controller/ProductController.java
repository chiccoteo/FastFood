package ai.ecma.appproductservice.controller;

import ai.ecma.appproductservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.ProductDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(ProductController.PRODUCT_CONTROLLER)
public interface ProductController {
    String PRODUCT_CONTROLLER = AppConstant.BASE_PATH_V1 + "/product";

    @GetMapping
    ApiResult<List<ProductDTO>> getAll();

    @GetMapping("/getByCategory/{id}")
    ApiResult<List<ProductDTO>> getAllByCategoryId(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<ProductDTO> getById(@PathVariable UUID id);

    @PostMapping
    ApiResult<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO);

    @PutMapping("/{id}")
    ApiResult<ProductDTO> edit(@PathVariable UUID id, @RequestBody @Valid ProductDTO productDTO);

    @PutMapping("/{categoryId}/{discountId}")
    ApiResult<List<ProductDTO>> addDiscountToCategory(@PathVariable UUID categoryId, @PathVariable UUID discountId);

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

}
