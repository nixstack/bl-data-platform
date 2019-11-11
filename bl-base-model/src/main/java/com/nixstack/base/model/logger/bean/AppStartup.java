package com.nixstack.base.model.logger.bean;

import com.nixstack.base.model.logger.base.AppBase;
import lombok.Data;

/**
 * 启动日志
 */
@Data
public class AppStartup extends AppBase {
    private String entry; // 入口：推送push=1，小部件widget=2，图标icon=3，通知notification=4
    private String LaunchScreenADType; // 开屏广告类型：原生广告=1，插屏广告=2
    private String status; // 启动状态：成功=1，失败=2
    private String errorCode; // 错误码
    private String errorMsg; // 错误信息
    private String eventType = "startup"; // 启动日志类型，默认startup
}
