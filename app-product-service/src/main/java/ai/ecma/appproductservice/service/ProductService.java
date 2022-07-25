package ai.ecma.appproductservice.service;

import ai.ecma.library.entity.Product;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ApiResult<List<ProductDTO>> getAll();

    ApiResult<ProductDTO> getById(UUID id);

    ApiResult<List<ProductDTO>> getAllByCategoryId(UUID id);

    ApiResult<ProductDTO> create(ProductDTO productDTO);

    ApiResult<ProductDTO> edit(UUID id, ProductDTO productDTO);

    ApiResult<List<ProductDTO>> addDiscountToCategory(UUID categoryId, UUID discountId);

    ApiResult<?> delete(UUID id);
}
