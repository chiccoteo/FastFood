package ai.ecma.appbranchservice.service;

import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.BranchCreateDTO;
import ai.ecma.library.payload.BranchDTO;

import java.util.List;
import java.util.UUID;

public interface BranchService {

    ApiResult<List<BranchDTO>> getAll();

    ApiResult<BranchDTO> getById(UUID id);

    ApiResult<BranchDTO> create(BranchCreateDTO branchCreateDTO);

    ApiResult<BranchDTO> edit(UUID id, BranchDTO branchDTO);

    ApiResult<?> delete(UUID id);
}
