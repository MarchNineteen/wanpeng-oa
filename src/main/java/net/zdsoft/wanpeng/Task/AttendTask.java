/*
 * @(#)Attend    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.Task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import net.zdsoft.wanpeng.api.ApiList;
import net.zdsoft.wanpeng.config.UserAgent;
import net.zdsoft.wanpeng.entity.CheckQuery;

import java.util.Calendar;

import static net.zdsoft.wanpeng.Task.LoginUserInfoHolder.userInfoMap;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 14:08 $$
 */
@Log4j2
public class AttendTask {

    public void doCheck() {
        if (MapUtil.isNotEmpty(userInfoMap)) {
            userInfoMap.forEach((key, userInfo) -> {
                if (StrUtil.isEmpty(userInfo.getAuthorization())) {
                    log.info("{}用户没有取到token", userInfo.getUserName());
                    return;
                }

                Calendar calendar = Calendar.getInstance();
                CheckQuery query = new CheckQuery();
                query.setCheckTime(calendar.getTimeInMillis());
                boolean isAM = DateUtil.isAM(calendar);
                query.setPeriod(isAM ? "AM" : "PM");

                if (isAM) {
                    // 没打过
                    if (!userInfo.getAmPeriod()) {
                        String result = doAttend(userInfo.getAuthorization(), query);
                        log.info("打卡成功{}", result);
                        return;
                    }
                    log.info("上午已经打过卡");
                } else {
                    if (!userInfo.getPmPeriod()) {
                        String result = doAttend(userInfo.getAuthorization(), query);
                        log.info("打卡成功{}", result);
                        return;
                    }
                    log.info("下午已经打过卡");
                }
            });
        }

    }

    private String doAttend(String auth, CheckQuery query) {
        String result = HttpRequest.post(ApiList.CHECK)
                .header(Header.AUTHORIZATION, auth)
                .header(UserAgent.X_ZDSOFT_AGENT, UserAgent.X_ZDSOFT_AGENT_VALUE)
                .body(JSONUtil.toJsonStr(query))
                .timeout(20000)//超时，毫秒
                .execute().body();
        log.info("打卡成功{}", result);
        return result;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        CheckQuery query = new CheckQuery();
        query.setCheckTime(calendar.getTimeInMillis());
        query.setPeriod(calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
        System.out.println(query.getPeriod());
        System.out.println(query.getCheckTime());
    }
}
