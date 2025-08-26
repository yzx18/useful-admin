package com.yzx.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qinBinQi
 * Date: 2020/3/27
 * Time: 11:22
 * Description: No Description
 * @author admin
 */
@TableName("sys_pushmessage")
@Data
public class SysPushMessage implements Serializable {

    /**
     * 推送的id
     */
    private Integer pushId;

    private String userId;
    /**
     * 推送的标题
     */
    private String title;
    /**
     * 推送的内容
     */
    private String content;

    /**
     * CID
     */
    private String clientId;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 网格ID
     */
    private List<String> id;
}
