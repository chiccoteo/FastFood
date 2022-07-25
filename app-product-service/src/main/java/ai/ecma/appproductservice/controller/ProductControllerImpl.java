package ai.ecma.appproductservice.controller;

import ai.ecma.appproductservice.service.ProductService;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController{

    private final ProductService productService;

    @Override
    public ApiResult<List<ProductDTO>> getAll() {
        return productService.getAll();
    }

    @Override
    public ApiResult<List<ProductDTO>> getAllByCategoryId(UUID id) {
        return productService.getAllByCategoryId(id);
    }

    @Override
    public ApiResult<ProductDTO> getById(UUID id) {
        return productService.getById(id);
    }

    @Override
    public ApiResult<ProductDTO> create(ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @Override
    public ApiResult<ProductDTO> edit(UUID id, ProductDTO productDTO) {
        return productService.edit(id,productDTO);
    }

    @Override
    public ApiResult<List<ProductDTO>> addDiscountToCategory(UUID categoryId, UUID discountId) {
        return productService.addDiscountToCategory(categoryId,discountId);
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        return productService.delete(id);
    }
}
