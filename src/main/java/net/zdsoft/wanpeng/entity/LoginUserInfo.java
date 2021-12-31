/*
 * @(#)LoginUserInfo    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.entity;

import lombok.Data;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 14:10 $$
 */
@Data
public class LoginUserInfo {

    /**
     * 不同账号定制不同ua
     */
    private String userAgent;

    private String authorization;

    private String userName;

    // 上午是否打卡
    private Boolean amPeriod;

    // 下午是否打卡
    private Boolean pmPeriod;


}
