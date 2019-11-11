package com.nixstack.base.model.logger.bean;

import com.nixstack.base.model.logger.base.PageBaseBean;
import lombok.Data;

/**
 * 列表页，记录用户动作
 */
@Data
public class PageListActionBean extends PageBaseBean {
    private String tId; // 标识，作品/课程id...
    private String type; // 类型：曝光=1，点击=2，
    private String place; // 位置（列表中的第几项，第一条为0，第二条为1，如此类推）
    private String cId; // 分类ID
    private String action; // 曝光商品1，点击商品2
}
