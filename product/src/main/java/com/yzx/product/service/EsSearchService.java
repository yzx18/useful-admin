package com.yzx.product.service;

import com.yzx.model.Result;
import com.yzx.product.entity.SearchParam;

/**
 * @className: EsSearchService
 * @author: yzx
 * @date: 2025/9/18 13:09
 * @Version: 1.0
 * @description:
 */
public interface EsSearchService {
    Result search(SearchParam searchParam);
}
