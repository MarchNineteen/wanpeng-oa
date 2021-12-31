/*
 * @(#)ApiList    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.api;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 13:49 $$
 */
public class ApiList {

    public static final String HOST = "https://wpoapc.msyk.cn";
    /**
     * get 请求 打卡记录
     */
    public static final String ATTEND_HISTORY = HOST + "/api/szxyOffice4Com/attend/history";

    /**
     * POST 请求
     */
//    public static final String LOGIN = "https://gateway10086.msyk.cn/szxyOffice4Com/attend/history";
    public static final String LOGIN = HOST + "/api/dcAuth/szxy/pre-authentication";
    public static final String GET_AUTH = HOST + "/api/dcAuth/szxy/authentication";

    /**
     * post 请求
     * <p>
     * clientId
     */
    public static final String CHECK = HOST + "/api/szxyOffice4Com/attend/check/in?clientId=";

    public static final String PRE_CHECK = HOST + "/api/szxyOffice4Com/attend/pre/check/in";
}
