/*
 * @(#)Login    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.Task;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import net.zdsoft.wanpeng.api.ApiList;
import net.zdsoft.wanpeng.config.Config;
import net.zdsoft.wanpeng.config.UserAgent;
import net.zdsoft.wanpeng.entity.LoginQuery;
import net.zdsoft.wanpeng.entity.LoginUserInfo;

import java.util.List;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 14:08 $$
 */
@Log4j2
public class LoginTask {

    public void doLogin(List<Config> configList) {
        if (CollectionUtil.isNotEmpty(configList)) {
            configList.forEach(config -> {
                if (StrUtil.isNotEmpty(config.getAuthorization())) {
                    LoginUserInfo userInfo = new LoginUserInfo();
                    userInfo.setAuthorization(config.getAuthorization());
                    userInfo.setUserName(config.getUserName());
                    LoginUserInfoHolder.userInfoMap.put(config.getUserName(), userInfo);
                } else {
                    LoginQuery query = new LoginQuery();
                    query.setUsername(config.getUserName());
                    query.setPassword(SecureUtil.md5(config.getPassword()));
                    String queryJson = JSONUtil.toJsonStr(query);
                    String result = HttpRequest.post(ApiList.GET_AUTH)
                            .header(UserAgent.X_ZDSOFT_AGENT, UserAgent.X_ZDSOFT_AGENT_VALUE)
                            .body(queryJson)
                            .timeout(20000)//超时，毫秒
                            .execute().body();
                    JSONObject obj = JSONUtil.parseObj(result);
                    if (!JSONUtil.isNull(obj.get("data"))) {
                        LoginUserInfo userInfo = new LoginUserInfo();
                        userInfo.setAuthorization(obj.getStr("data"));
//                    userInfo.setUserAgent(UserAgent.);
                        LoginUserInfoHolder.userInfoMap.put(config.getUserName(), userInfo);
                    }
                }

            });
        } else {
            log.info("未配置用户信息");
            throw new RuntimeException("未配置用户信息");
        }
    }


}
