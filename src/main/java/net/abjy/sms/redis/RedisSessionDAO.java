package net.abjy.sms.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/6.
 */
@Component
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {
    @Resource
    private RedisServiceImpl redisServiceImpl;
    /**
     * 创建session，保存到redis数据库
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        try {
            redisServiceImpl.sAdd(sessionId.toString(), session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionId;
    }
    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            try {
                session = (Session)redisServiceImpl.get(sessionId.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return session;
    }
    /**
     * 更新session的最后一次访问时间
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        try {
            redisServiceImpl.set(session.getId().toString(), session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除session
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        try {
            redisServiceImpl.del(session.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
