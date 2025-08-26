package cn.poile.ucs.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzx.model.ucenter.BaseUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: BaseUserMapper
 * @author: yzx
 * @date: 2025/8/8 0:31
 * @Version: 1.0
 * @description:
 */
@Mapper
public interface BaseUserMapper extends BaseMapper<BaseUser> {
}
