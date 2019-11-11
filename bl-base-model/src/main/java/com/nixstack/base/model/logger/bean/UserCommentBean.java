package com.nixstack.base.model.logger.bean;

import com.nixstack.base.model.logger.base.UserBaseBean;
import lombok.Data;

/**
 * 用户评论
 */
@Data
public class UserCommentBean extends UserBaseBean {
    private String commentId; // 评论id
    private String tId; // 点赞的对象id, 作品/课程id...
    private  String commentParentId; // 父级评论id(为0则是一级评论,不为0则是回复)
    private String commentContent; //评论内容
    private String createTime; // 创建时间
    private int commentPraiseCount; // 点赞数量
    private int commentReplyCount; // 回复数量

}
