/*
 * @(#)AttendHistory    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.Task;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import net.zdsoft.wanpeng.api.ApiList;
import net.zdsoft.wanpeng.config.UserAgent;
import net.zdsoft.wanpeng.entity.AttendInfo.AttendPeriod;

import static net.zdsoft.wanpeng.Task.LoginUserInfoHolder.userInfoMap;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 14:08 $$
 */
@Log4j2
public class AttendHistoryTask {

    public void getAttendHistory() {
        if (MapUtil.isNotEmpty(userInfoMap)) {
            userInfoMap.forEach((key, userInfo) -> {
                if (StrUtil.isEmpty(userInfo.getAuthorization())) {
                    log.info("{}用户没有取到token", userInfo.getUserName());
                    return;
                }
                String result = HttpRequest.get(ApiList.ATTEND_HISTORY)
                        .header(Header.AUTHORIZATION, userInfo.getAuthorization())
                        .header(UserAgent.X_ZDSOFT_AGENT, UserAgent.X_ZDSOFT_AGENT_VALUE)
//                        .setHttpProxy("127.0.0.1", 8888)
                        .timeout(20000)//超时，毫秒
                        .execute().body();

                AttendPeriod amAttend = null;
                AttendPeriod pmAttend = null;
                JSONObject resultObj = JSONUtil.parseObj(result);
                JSONObject data = (JSONObject) resultObj.get("data");
                if (!JSONUtil.isNull(data.get("am"))) {
                    amAttend = JSONUtil.toBean(data.get("am").toString(), AttendPeriod.class);
                }
                if (!JSONUtil.isNull(data.get("pm"))) {
                    pmAttend = JSONUtil.toBean(data.get("pm").toString(), AttendPeriod.class);
                }
                if (null == amAttend && null == pmAttend) {
                    log.info("{}当天暂未打卡", userInfo.getUserName());
                    return;
                }
                userInfo.setAmPeriod(amAttend != null);
                userInfo.setPmPeriod(pmAttend != null);
                log.info("{}获取打卡记录成功，上午{},下午{}", userInfo.getUserName(), amAttend, pmAttend);
            });
        }
    }
}
