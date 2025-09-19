package com.yzx.product.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @className: SkuEs
 * @author: yzx
 * @date: 2025/9/18 14:46
 * @Version: 1.0
 * @description:
 */
@Data
@Document(indexName = "pms_sku",shards = 3,replicas = 1)
public class SkuEs {
}
