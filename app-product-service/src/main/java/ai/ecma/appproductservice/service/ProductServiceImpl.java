package ai.ecma.appproductservice.service;

import ai.ecma.appproductservice.common.MessageService;
import ai.ecma.appproductservice.exception.RestException;
import ai.ecma.appproductservice.mapper.ProductMapper;
import ai.ecma.library.entity.Attachment;
import ai.ecma.library.entity.Category;
import ai.ecma.library.entity.Discount;
import ai.ecma.library.entity.Product;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.ProductDTO;
import ai.ecma.library.repository.AttachmentRepo;
import ai.ecma.library.repository.CategoryRepository;
import ai.ecma.library.repository.DiscountRepository;
import ai.ecma.library.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepo attachmentRepo;
    private final DiscountRepository discountRepository;
    private final ProductMapper productMapper;

    @Override
    public ApiResult<List<ProductDTO>> getAll() {
        List<Product> all = productRepository.findAll();
        List<ProductDTO> productDTOList = productMapper.toDTO(all);
        return ApiResult.successResponse(productDTOList);
    }

    @Override
    public ApiResult<ProductDTO> getById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> RestException.notFound("NOT_FOUND_PRODUCT0"));
        ProductDTO productDTO = productMapper.toDTO(product);
        return ApiResult.successResponse(productDTO);
    }

    @Override
    public ApiResult<List<ProductDTO>> getAllByCategoryId(UUID id) {
        List<Product> allByCategoryId = productRepository.findAllByCategoryId(id);
        List<ProductDTO> productDTOList = productMapper.toDTO(allByCategoryId);
        return ApiResult.successResponse(productDTOList);
    }


    @Override
    public ApiResult<ProductDTO> create(ProductDTO productDTO) {
        Attachment attachment = attachmentRepo.findById(productDTO.getAttachmentId()).orElseThrow(() -> RestException.notFound("NOT_FOUND_ATTACHMENT"));
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> RestException.notFound("NOT_FOUND_CATEGORY"));
        Optional<Discount> discount = discountRepository.findById(productDTO.getDiscountId());


        Product product = productMapper.toEntity(productDTO);
        product.setAttachment(attachment);
        product.setCategory(category);

        discount.ifPresent(product::setDiscount);

        productRepository.save(product);

        return ApiResult.successResponse(productMapper.toDTO(product), MessageService.getMessage("PRODUCT_SAVED"));
    }

    @Override
    public ApiResult<ProductDTO> edit(UUID id, ProductDTO productDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("NOT_FOUND_PRODUCT"));
        Attachment attachment = attachmentRepo.findById(productDTO.getAttachmentId())
                .orElseThrow(() -> RestException.notFound("NOT_FOUND_ATTACHMENT"));
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> RestException.notFound("NOT_FOUND_CATEGORY"));

        Optional<Discount> discount = discountRepository.findById(productDTO.getDiscountId());

        Product toEntity = productMapper.toEntity(productDTO);
        toEntity.setCategory(category);
        toEntity.setAttachment(attachment);

        discount.ifPresent(product::setDiscount);

        productRepository.save(product);

        return ApiResult.successResponse(productMapper.toDTO(product), MessageService.getMessage("PRODUCT_EDITED"));
    }

    @Override
    public ApiResult<List<ProductDTO>> addDiscountToCategory(UUID categoryId, UUID discountId) {
        List<Product> productList = productRepository.findAllByCategoryId(categoryId);
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> RestException.notFound("DISCOUNT_NOT_FOUND"));

        categoryRepository.findById(categoryId).orElseThrow(() -> RestException.notFound("CATEGORY_NOT_FOUND"));

        if (!productList.isEmpty()) {
            for (Product product : productList) {
                product.setDiscount(discount);
            }
            List<ProductDTO> productDTOList = productMapper.toDTO(productList);
            return ApiResult.successResponse(productDTOList, MessageService.getMessage("ADDED_DISCOUNT_TO_CATEGORY"));
        }
        return ApiResult.errorResponse(MessageService.getMessage("PRODUCTS_NOT_FOUND_IN_CATEGORY"));
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> RestException.notFound("NOT_FOUND_PRODUCT"));
        productRepository.delete(product);
        return ApiResult.successResponse(MessageService.getMessage("PRODUCT_DELETED"));
    }
}
