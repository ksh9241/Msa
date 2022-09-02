package com.example.userservice.client;

import com.example.userservice.vo.ResponseOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// name = 유레카 서버에 등록된 오더 서비스 명, fallbackFactory = 콜백 처리
@FeignClient(name = "order-service", fallbackFactory = OrderServiceClientFallFactory.class)
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders_2")   // /gateway url/controller url
    List<ResponseOrder> getOrders(@PathVariable String userId) throws Exception;
}

@Component
@Slf4j
class OrderServiceClientFallFactory implements FallbackFactory<OrderServiceClient> {
    @Override
    public OrderServiceClient create(Throwable cause) {
        return userId -> {
            String errMsg = "Feignclient를 이용한" + userId + "의 Order 주문목록 호출에 문제가 있습니다.";
            log.error(errMsg, cause);
            throw new Exception(errMsg);
        };
    }
}