package ai.ecma.appproductservice.service;

import ai.ecma.appproductservice.common.MessageService;
import ai.ecma.appproductservice.exception.RestException;
import ai.ecma.appproductservice.mapper.CategoryMapper;
import ai.ecma.library.entity.Attachment;
import ai.ecma.library.entity.Category;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.CategoryDTO;
import ai.ecma.library.repository.AttachmentRepo;
import ai.ecma.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;
    private final AttachmentRepo attachmentRepo;
    private final MessageService messageService;

    @Override
    public ApiResult<List<CategoryDTO>> getAllByParent() {
        List<Category> parentCategory = categoryRepository.findAllByParentCategoryIsNull(Sort.by(Category.Fields.parentCategory));
        return ApiResult.successResponse(mapper.toDto(parentCategory));
    }

    @Override
    public ApiResult<List<CategoryDTO>> getByAllParentId(UUID id) {
        List<Category> byParentCategoryId = categoryRepository.findByParentCategoryId(id);
        return ApiResult.successResponse(mapper.toDto(byParentCategoryId));
    }

    @Override
    public ApiResult<CategoryDTO> create(CategoryDTO categoryDTO) {
        Attachment attachment = attachmentRepo.findById(categoryDTO.getAttachmentId()).
                orElseThrow(() -> RestException.notFound(MessageService.getMessage("ATTACHMENT_NOT_FOUND")));

        Category category = mapper.toEntity(categoryDTO);
        category.setAttachment(attachment);
        if (categoryDTO.getParentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentCategoryId()).
                    orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));
            category.setParentCategory(parentCategory);
        }
        categoryRepository.save(category);

        return ApiResult.successResponse(mapper.toDto(category), MessageService.getMessage("CATEGORY_SAVED"));
    }

    @Override
    public ApiResult<CategoryDTO> edit(UUID id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> RestException.notFound("CATEGORY_NOT_FOUND"));
        Attachment attachment = attachmentRepo.findById(categoryDTO.getAttachmentId()).
                orElseThrow(() -> RestException.notFound("ATTACHMENT_NOT_FOUND"));

        category.setName(categoryDTO.getName());
        category.setAttachment(attachment);
        if (categoryDTO.getParentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentCategoryId()).
                    orElseThrow(() -> RestException.notFound(MessageService.getMessage("CATEGORY_NOT_FOUND")));
            category.setParentCategory(parentCategory);
        }

        categoryRepository.save(category);

        return ApiResult.successResponse(mapper.toDto(category), messageService.getMessage("CATEGORY_EDITED"));
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> RestException.notFound(messageService.getMessage("CATEGORY_NOT_FOUND")));
        categoryRepository.delete(category);
        return ApiResult.successResponse(messageService.getMessage("CATEGORY_DELETED"));
    }
}
