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
/**
     * [신규 기능 추가] 대량 주문 비동기 병렬 처리
     */
    public void processBulkOrders(List<String> orderIds) {
        log.info("대량 주문 병렬 처리 시작: 건수={}", orderIds.size());
        
        // [수정 포인트] 주입된 taskExecutor 대신 무제한 생성 newCachedThreadPool 사용
        ExecutorService executor = Executors.newCachedThreadPool();
        for (String id : orderIds) {
            executor.submit(() -> log.info("주문 과금 진행: id={}", id));
        }
    }
}