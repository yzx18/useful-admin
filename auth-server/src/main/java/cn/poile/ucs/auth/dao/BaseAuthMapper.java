package cn.poile.ucs.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzx.model.ucenter.BaseAuth;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: BaseAuthMapper
 * @author: yzx
 * @date: 2025/8/26 7:56
 * @Version: 1.0
 * @description:
 */
@Mapper
public interface BaseAuthMapper extends BaseMapper<BaseAuth> {
}
