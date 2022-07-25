package ai.ecma.appbranchservice.controller;


import ai.ecma.appbranchservice.service.BranchService;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.BranchCreateDTO;
import ai.ecma.library.payload.BranchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BranchControllerImpl implements BranchController{

    private final BranchService service;

    @Override
    public ApiResult<List<BranchDTO>> getAll() {
        return service.getAll();
    }

    @Override
    public ApiResult<BranchDTO> getById(UUID id) {
        return service.getById(id);
    }

    @Override
    public ApiResult<BranchDTO> create(@Valid BranchCreateDTO branchCreateDTO) {
        return service.create(branchCreateDTO);
    }

    @Override
    public ApiResult<BranchDTO> edit(UUID id, BranchDTO branchDTO) {
        return service.edit(id,branchDTO);
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        return service.delete(id);
    }
}
