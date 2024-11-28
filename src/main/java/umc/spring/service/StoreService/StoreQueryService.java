package umc.spring.service.StoreService;

import umc.spring.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    List<Store> findStoresByNameAndScore(String name, Float score);
    boolean storeExist(Long storeId);
}