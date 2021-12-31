/*
 * @(#)LoginUserInfoHolder    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.Task;

import net.zdsoft.wanpeng.entity.LoginUserInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 14:19 $$
 */
public class LoginUserInfoHolder {

    public static ConcurrentHashMap<String, LoginUserInfo> userInfoMap = new ConcurrentHashMap<String, LoginUserInfo>();


}
