package com.nixstack.base.model.logger.bean;

import com.nixstack.base.model.logger.base.UserBaseBean;
import lombok.Data;

/**
 * 用户点赞
 */
@Data
public class UserPraiseBean extends UserBaseBean {
    private String id; // 主键id
    private String tId; // 点赞的对象id, 作品/课程id...
    private int type; // 点赞类型 1问答点赞 2问答评论点赞 3 文章点赞数4 评论点赞
    private String createTime; // 添加时间

}
