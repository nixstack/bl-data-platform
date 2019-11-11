package com.nixstack.service.logger.mock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nixstack.base.model.logger.base.AppBase;
import com.nixstack.base.model.logger.bean.AppStartup;
import com.nixstack.base.model.logger.bean.PageDetailBean;
import com.nixstack.base.model.logger.bean.PageListActionBean;
import com.nixstack.base.model.logger.bean.PageListBean;
import com.nixstack.service.logger.mock.util.LogUploader;
import com.nixstack.service.logger.mock.util.RandomNum;
import com.nixstack.service.logger.mock.util.RandomOpt;
import com.nixstack.service.logger.mock.util.RandomOptGrp;

import java.util.Random;

public class LoggerMockApplication {
    private static Random random = new Random();

    private static RandomOpt[] randomLogOpts = { new RandomOpt(true, 20), new RandomOpt(false, 80) };
    private static RandomOptGrp<Boolean> randomLogOptsGrp = new RandomOptGrp(randomLogOpts);

    private static RandomOpt[] randomLangOpts = { new RandomOpt("zh_cn", 80), new RandomOpt("en_us", 20) };
    private static RandomOptGrp<String> randomLangOptsGrp = new RandomOptGrp<String>(randomLangOpts);

    private static RandomOpt[] randomChannelOpts = { new RandomOpt("xiaomi", 10), new RandomOpt("wandoujia", 40),
            new RandomOpt("baidu", 10), new RandomOpt("website", 40) };
    private static RandomOptGrp<String> randomChannelOptsGrp = new RandomOptGrp<String>(randomChannelOpts);

    private static RandomOpt[] randomOSOpts = { new RandomOpt("android", 80), new RandomOpt("ios", 20) };
    private static RandomOptGrp<String> randomOSOptsGrp = new RandomOptGrp<String>(randomOSOpts);

    private static RandomOpt[] randomAreaOpts = { new RandomOpt("shanghai", 30), new RandomOpt("guangdong", 40), new RandomOpt("beijind", 30) };
    private static RandomOptGrp<String> randomAreaOptsGrp = new RandomOptGrp<String>(randomAreaOpts);

    private static RandomOpt[] randomResolutionOpts = { new RandomOpt("640*960", 30), new RandomOpt("640*1136", 40), new RandomOpt("750*1134", 30) };
    private static RandomOptGrp<String> randomResolutionOptsGrp = new RandomOptGrp<String>(randomResolutionOpts);

    private static RandomOpt[] randomNetworkOpts = { new RandomOpt("3G", 30), new RandomOpt("4G", 40), new RandomOpt("WIFI", 30) };
    private static RandomOptGrp<String> randomNetworkOptsGrp = new RandomOptGrp<String>(randomNetworkOpts);

    private static RandomOpt[] randomErrorCodeOpts = { new RandomOpt("30000", 5), new RandomOpt("40000", 5), new RandomOpt("", 90) };
    private static RandomOptGrp<String> randomErrorCodeOptsGrp = new RandomOptGrp<String>(randomErrorCodeOpts);

    public static void main(String[] args) {
        // 控制发送每条的延时时间
        Long delay = args.length > 0 ? Long.parseLong(args[0]) : 0L;

        // 循环遍历次数
        int loop_len = args.length > 1 ? Integer.parseInt(args[1]) : 1000;

        // 生成数据
        generateLog(delay, loop_len);
    }

    private static void generateLog(Long delay, int loop_len) {
        String log;
        for (int i=0; i<loop_len; i++) {
            if (randomLogOptsGrp.getRandomOpt().getValue()) {
                AppStartup appStartup = genStartupLog();
                log = JSON.toJSONString(appStartup);
            } else {
                log = genEventLog();
            }

            sendLog(log);

            // 延迟
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String genEventLog() {
        JSONObject json = new JSONObject();
        JSONArray eventsArray = new JSONArray();

        json.put("source", "app");
        json.put("common", JSON.toJSON(genCommonField()));

        // 页面列表
        if (random.nextBoolean()) {
            eventsArray.add(genPageListLog());
        }

        // 页面列表用户操作
        if (random.nextBoolean()) {
            eventsArray.add(genPageListActionLog());
        }

        // 详情页
        if (random.nextBoolean()) {
            eventsArray.add(genPageDetailLog());
        }

        json.put("event", eventsArray);

        return json.toJSONString();
    }

    private static AppBase genCommonField() {
        int flag;
        AppBase appBase = new AppBase();

        appBase.setMId("" + RandomNum.getRandomNum(1000, 99999)); // 设备id
        appBase.setUId("" + RandomNum.getRandomNum(1000, 99999)); // 用户id
        appBase.setVersionCode("" + random.nextInt(30)); // 程序版本号
        appBase.setVersionName("v1." + random.nextInt(6) + "." + random.nextInt(10)); // 程序版本名
        appBase.setLang(randomLangOptsGrp.getRandomOpt().getValue()); // 系统语言
        appBase.setChannel(randomChannelOptsGrp.getRandomOpt().getValue()); // 渠道
        appBase.setOs(randomOSOptsGrp.getRandomOpt().getValue()); // 操作系统
        appBase.setArea(randomAreaOptsGrp.getRandomOpt().getValue()); // 地区

        flag = random.nextInt(3);
        switch (flag) {
            case 0:
                appBase.setPhoneBrand("Sumsung");
                appBase.setPhoneModel("sumsung-" + random.nextInt(30));
                break;
            case 1:
                appBase.setPhoneBrand("Xiaomi");
                appBase.setPhoneModel("xiaomi-" + random.nextInt(30));
                break;
            case 2:
                appBase.setPhoneBrand("Huawei");
                appBase.setPhoneModel("huawei-" + random.nextInt(30));
                break;
        }

        appBase.setResolution(randomResolutionOptsGrp.getRandomOpt().getValue()); // 屏幕分辨率
        appBase.setNetwork(randomNetworkOptsGrp.getRandomOpt().getValue()); // 网络模式
        appBase.setTime("" + (System.currentTimeMillis() - random.nextInt(99999999))); // 日志产生时间
        return appBase;
    }

    private static AppStartup genStartupLog() {
        AppStartup appStartup = new AppStartup();
        AppBase appBase = genCommonField();

        appStartup.setMId(appBase.getMId()); // 设备id
        appStartup.setUId(appBase.getUId()); // 用户id
        appStartup.setVersionCode(appBase.getVersionCode()); // 程序版本号
        appStartup.setVersionName(appBase.getVersionName()); // 程序版本名
        appStartup.setLang(appBase.getLang()); // 系统语言
        appStartup.setChannel(appBase.getChannel()); // 渠道
        appStartup.setOs(appBase.getOs()); // 操作系统
        appStartup.setArea(appBase.getArea()); // 地区
        appStartup.setPhoneBrand(appBase.getPhoneBrand());
        appStartup.setPhoneModel(appBase.getPhoneModel());
        appStartup.setResolution(appBase.getResolution()); // 屏幕分辨率
        appStartup.setNetwork(appBase.getNetwork()); // 网络模式
        appStartup.setTime(appBase.getTime()); // 日志产生时间

        appStartup.setEntry("" + (random.nextInt(4) + 1)); // 入口
        appStartup.setLaunchScreenADType("" + (random.nextInt(2) + 1)); // 开屏广告类型
        appStartup.setStatus("" + (random.nextInt(2) + 1)); // 启动状态

        return appStartup;
    }

    private static Object genPageDetailLog() {
        PageDetailBean pageDetail = new PageDetailBean();

        // 页面入口来源
        int flag = random.nextInt(3);
        pageDetail.setReferrer(flag + "");

        // 状态
        pageDetail.setStatus(random.nextInt(4) + 1 + "");

        // 作品/课程id...
        pageDetail.setTId("" + RandomNum.getRandomNum(10000, 99999));

        // 页面停留时长
        pageDetail.setStayTime(random.nextInt(10) * random.nextInt(8) + "");

        // 加载时长
        pageDetail.setLoadingTime(random.nextInt(10) * random.nextInt(8) + "");

        // 错误码
        pageDetail.setErrorCode(randomErrorCodeOptsGrp.getRandomOpt().getValue());

        // 分类id
        pageDetail.setCId(random.nextInt(100) + 1 + "");

        return packEventToJson("page_detail", pageDetail);
    }

    private static Object genPageListLog() {
        PageListBean pageList = new PageListBean();

        // 页面状态
        int flag = random.nextInt(3) + 1;

        pageList.setStatus(flag + "");

        // 加载时长
        flag = random.nextInt(10) * random.nextInt(8);
        pageList.setLoadingTime(flag + "");

        // 错误码
        pageList.setErrorCode(randomErrorCodeOptsGrp.getRandomOpt().getValue());

        // 加载方式
        flag = random.nextInt(2) + 1;
        pageList.setLoadingWay(flag + "");

        // 加载类型
        flag = random.nextInt(3) + 1;
        pageList.setLoadingType(flag + "");

        Object json = JSON.toJSON(pageList);
        return packEventToJson("page_list", json);
    }

    private static Object genPageListActionLog() {
        PageListActionBean pageListAction = new PageListActionBean();
        boolean boolFlag = random.nextInt(10) < 7;

        // 设置状态
        if (boolFlag) {
            pageListAction.setStatus("1");
        } else {
            pageListAction.setStatus("2");
        }

        // 作品/课程id...
        pageListAction.setTId("" + RandomNum.getRandomNum(10000, 99999));

        // 位置
        int i = random.nextInt(6);
        pageListAction.setPlace(i+"");

        // 用户操作类型
        int i2 = random.nextInt(6);
        pageListAction.setType(i2 + "");

        int i1 = random.nextInt(100) + 1;
        pageListAction.setCId(i1 + "");

        pageListAction.setAction("" + (random.nextInt(2) + 1));

        Object json = JSON.toJSON(pageListAction);
        return packEventToJson("page_list_action", json);
    }

    private static JSONObject packEventToJson(String eventType, Object json) {
        JSONObject eventJson = new JSONObject();
        eventJson.put("time", (System.currentTimeMillis() - random.nextInt(99999999)) + "");
        eventJson.put("type", eventType);
        eventJson.put("map", json);

        return eventJson;
    }

    private static void sendLog(String log) {
        LogUploader.sendLogStream(log);
    }
}
