package ai.ecma.appbranchservice.controller;

import ai.ecma.appbranchservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.BranchCreateDTO;
import ai.ecma.library.payload.BranchDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(BranchController.BRANCH_CONTROLLER)
public interface BranchController {
    String BRANCH_CONTROLLER = AppConstant.BASE_PATH_V1 + "/branch";

    @GetMapping
    ApiResult<List<BranchDTO>> getAll();

    @GetMapping("/{id}")
    ApiResult<BranchDTO> getById(@PathVariable UUID id);

    @PostMapping
    ApiResult<BranchDTO> create(@RequestBody @Valid BranchCreateDTO branchCreateDTO);

    @PutMapping("{id}")
    ApiResult<BranchDTO> edit(@PathVariable UUID id, @RequestBody @Valid BranchDTO branchDTO);

    @DeleteMapping("{id}")
    ApiResult<?> delete(@PathVariable UUID id);
}
