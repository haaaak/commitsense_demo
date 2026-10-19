# Query: 
# ContextLines: 1

WaferProductionService.java
package com.company.mes.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class WaferProductionService {
    private final WebClient webClient;
    /*
     * 웨이퍼 로트(Lot) 상태 조회
     */
    @Transactional(readOnly = true)
    public Optional<String> getLotStatus(String lotId) {
        log.info("웨이퍼 Lot 상태 조회 요청: lotId={}", lotId);
        return Optional.ofNullable(lotId).map(id -> "IN_PRODUCTION");
    }
    /**
     * 설비 라인 이상 징후 체크
     */
    public boolean checkEquipmentHealth(String fabId, String lineId) {
        log.debug("라인 헬스체크: fab={}, line={}", fabId, lineId);
        return true;
    }

        /**
     * [신규 기능 추가] 설비 라인으로 웨이퍼 로트 디스패치 전송
     */
    public String dispatchWaferLot(String lotId, String payload) {
        log.info("웨이퍼 Lot 디스패치 전송 시작: lotId={}", lotId);
        
        // [수정 포인트] 이미 주입된 webClient 대신 무제한 RestTemplate을 새로 인스턴스화하여 호출함
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = "http://mes-gateway.fab1.internal/api/v1/lots/" + lotId + "/dispatch";
        
        return restTemplate.postForObject(endpoint, payload, String.class);
    }
}