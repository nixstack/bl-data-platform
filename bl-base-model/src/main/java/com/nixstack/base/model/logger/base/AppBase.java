package com.nixstack.base.model.logger.base;

import lombok.Data;

@Data
public class AppBase {
    private String mId; // 设备Id
    private String uId; // 用户Id
    private String versionCode; // 程序版本号
    private String versionName; // 程序版本名
    private String lang; // 系统语言
    private String channel; // 渠道
    private String os; // 操作系统
    private String area; // 地区
    private String phoneBrand; // 手机品牌
    private String phoneModel; // 手机型号
    private String sdkVersion; // sdk版本
    private String resolution; // 屏幕分辨率（width*height）
    private String network; // 网络模式
    private String longitude; // 经度
    private String latitude; // 纬度
    private String time; // 日志产生时间
}
