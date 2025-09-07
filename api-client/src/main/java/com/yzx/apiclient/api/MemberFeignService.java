package com.yzx.apiclient.api;

import com.yzx.model.order.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @className: MemberFeignService
 * @author: yzx
 * @date: 2025/8/30 6:40
 * @Version: 1.0
 * @description:
 */
@FeignClient("member-service")
public interface MemberFeignService {
    @GetMapping(value = "/member/memberreceiveaddress/{memberId}/address")
    List<MemberAddressVo> getAddress(@PathVariable("memberId") Long memberId);
}
