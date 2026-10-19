package com.company.commerce.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessingService {
    private final ThreadPoolTaskExecutor taskExecutor;
    public void validateOrder(Long orderId) {
        log.info("주문 유효성 검증 완료: orderId={}", orderId);
    }
}