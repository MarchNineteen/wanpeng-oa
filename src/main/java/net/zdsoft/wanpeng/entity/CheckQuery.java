/*
 * @(#)CheckQuery    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 19:19 $$
 */
@NoArgsConstructor
@Data
public class CheckQuery {
    private String address = "盛大科技园万朋教育科技股份有限公司";

    private Long checkTime;

    private Boolean isAway = false;

    private String latitude = "30.276826";

    private String longitude = "120.115145";

    private String period;

    private Boolean isLate = false;
}
