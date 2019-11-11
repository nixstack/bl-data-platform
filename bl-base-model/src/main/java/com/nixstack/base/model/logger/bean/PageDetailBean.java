package com.nixstack.base.model.logger.bean;

import com.nixstack.base.model.logger.base.PageBaseBean;
import lombok.Data;

/**
 * 详情页
 */
@Data
public class PageDetailBean extends PageBaseBean {
    private String tId; // 标识，作品/课程id...
    private String stayTime; // 页面停留时长：从商品开始加载时开始计算，到用户关闭页面所用的时间。
    private String loadingTime; // 加载时长：计算页面开始加载到接口返回数据的时间
    private String cId; // 分类ID
}
