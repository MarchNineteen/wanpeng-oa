/*
 * @(#)HutoolTest    Created on 2021/12/30
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import net.zdsoft.wanpeng.api.ApiList;
import net.zdsoft.wanpeng.entity.AttendInfo;
import net.zdsoft.wanpeng.entity.AttendInfo.*;
import org.junit.Test;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/30 15:40 $$
 */
public class HutoolTest {

    @Test
    public void http() {

        //链式构建请求
        String result2 = HttpRequest.get(ApiList.ATTEND_HISTORY)
                .header(Header.AUTHORIZATION, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJhcFVzZXJJZFwiOlwiOTQ2NDU2RDY5QjhENEVCMDgwNUM5OTIyQTUxOTQ0RERcIixcImFwQ29kZVwiOlwid2FuZ3liXCIsXCJwd2RIYXNoXCI6XCJmZTE0NTk2ODViYzg3NmZjMWJhYmVjMmI5ZGVlNDNkNlwiLFwic2FsdFwiOlwic3p4eVwifSIsInRpbWVzdGFtcCI6MTY0MDc2MDAwNzc3Mn0.1uQsiklifdgosQY0iNbKrFMtMkXHCE5K8-rIIzViP6_w31YGB5k7k1IKd8tdPi9zsJoeHirJl6rFKD3qE97ngA")//头信息，多个头信息多次调用此方法即可
                .header("x-zdsoft-agent", "mobile")
                .setHttpProxy("127.0.0.1", 8888)
                .timeout(20000)//超时，毫秒
                .body("json")
                .execute().body();
        Console.log(result2);
    }

    /**
     * {"code":200,"data":{"batch":"2021-12-30","reissue":null,"overtime":null,"am":{"id":"58533438930418317736014238796111","checkTime":1640827318239,"longitude":120.115145,"latitude":30.276826,"type":"00","address":"盛大科技园万朋教育科技股份有限公司","period":"AM","isLate":false,"isAway":false,"remark":null},"pm":null,"getScore":false,"hasTasks":false,"isWeekend":false,"leaveList":[]}}
     */
    @Test
    public void json() {
        String jsonStr = "{\"code\":200,\"data\":{\"batch\":\"2021-12-30\",\"reissue\":null,\"overtime\":null,\"am\":{\"id\":\"58533438930418317736014238796111\",\"checkTime\":1640827318239,\"longitude\":120.115145,\"latitude\":30.276826,\"type\":\"00\",\"address\":\"盛大科技园万朋教育科技股份有限公司\",\"period\":\"AM\",\"isLate\":false,\"isAway\":false,\"remark\":null},\"pm\":null,\"getScore\":false,\"hasTasks\":false,\"isWeekend\":false,\"leaveList\":[]}}\")";
        JSONObject obj = JSONUtil.parseObj(jsonStr);
        JSONObject data = (JSONObject) obj.get("data");
        AttendInfo.AttendPeriod amAttend = null;
        AttendPeriod pmAttend = null;
        if (!JSONUtil.isNull(data.get("am"))) {
            amAttend = JSONUtil.toBean(data.get("am").toString(), AttendPeriod.class);
        }
        if (!JSONUtil.isNull(data.get("pm"))) {
            pmAttend = JSONUtil.toBean(data.get("pm").toString(), AttendPeriod.class);
        }
        if (null == amAttend && null == pmAttend) {
            Console.log("当天暂未打卡");
            return;
        }
    }
}

