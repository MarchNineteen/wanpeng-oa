/*
 * @(#)LoginQuery    Created on 2021/12/29
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package net.zdsoft.wanpeng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/12/29 15:59 $$
 */
@NoArgsConstructor
@Data
public class LoginQuery {


    private String username;

    private String password;

    private Integer ownerType = 2;
}
