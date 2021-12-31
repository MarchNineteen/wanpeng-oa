/*
 * @(#)AttendInfo    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.entity;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 14:33 $$
 */
@NoArgsConstructor
@Data
public class AttendInfo {

    private AttendPeriod am;
    private AttendPeriod pm;

    @Data
    public static class AttendPeriod {

        private Long checkTime;

        private String id;

        private Boolean isAway;

        private Double latitude;

        private Object remark;

        private Double longitude;

        private String address;

        private String period;

        private Boolean isLate;

        @Override
        public String toString() {
            return "{" +
                    "checkTime=" + DateUtil.format(new Date(checkTime), "yyyy-MM-dd HH:mm:ss") +
                    ", id='" + id + '\'' +
                    ", isAway=" + isAway +
                    ", latitude=" + latitude +
                    ", remark=" + remark +
                    ", longitude=" + longitude +
                    ", address='" + address + '\'' +
                    ", period='" + period + '\'' +
                    ", isLate=" + isLate +
                    '}';
        }
    }

}
