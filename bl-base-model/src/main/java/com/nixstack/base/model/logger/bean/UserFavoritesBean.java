package com.nixstack.base.model.logger.bean;

import com.nixstack.base.model.logger.base.UserBaseBean;
import lombok.Data;

/**
 * 用户收藏
 */
@Data
public class UserFavoritesBean extends UserBaseBean {
    private String id; // 主键
    private String tId; // 收藏的作品/课程id...
    private String createTime; // 创建时间

}
