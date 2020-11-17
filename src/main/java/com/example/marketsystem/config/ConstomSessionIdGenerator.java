package com.example.marketsystem.config;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * ConstomSessionIdGenerator
 *
 * @author chuyx
 * @date 2020/11/16 14:38
 */
public class ConstomSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {

        return "chuyx.class"+UUID.randomUUID().toString().replace("-","l-p1");
    }
}
