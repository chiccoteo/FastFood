package ai.ecma.apporderservice.controller;

import ai.ecma.library.repository.BranchRepository;
import ai.ecma.library.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/order/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final BranchRepository branchRepository;
    private final OrdersRepository ordersRepository;

    @GetMapping
    public boolean getBranchFromQuery() {
//        return branchRepository.getClosestBranch(LocalTime.now(), 41.32643672692621, 69.22871268742759).get()
        return ordersRepository.isReliable(UUID.fromString("2957fd8c-d922-4931-ab9b-98acb72087e3"));
    }
}
