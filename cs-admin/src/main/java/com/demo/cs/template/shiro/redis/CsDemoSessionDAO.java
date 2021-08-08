package com.demo.cs.template.shiro.redis;

import com.demo.cs.template.util.SerializeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 自定义redis的session存储器
 */
public class CsDemoSessionDAO extends AbstractSessionDAO {
    
    /**
     * 字符串redis句柄
     */
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * sessionKey前缀
     */
    private String sessionKeyPrefix;
    
    @Override
    protected Serializable doCreate(Session session) {
        //生成sessionId
        Serializable sessionId = super.generateSessionId(session);
        //分配sessionId
        super.assignSessionId(session, sessionId);
        //存储session
        try {
            stringRedisTemplate.opsForValue().set(sessionKeyPrefix+session.getId().toString(), SerializeUtil.serialize(session));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionId;
    }
    
    @Override
    protected Session doReadSession(Serializable sessionId) {
        try {
            //读取session
            return SerializeUtil.deserialize(stringRedisTemplate.opsForValue().get(sessionKeyPrefix+sessionId.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void update(Session session) throws UnknownSessionException {
        try {
            //更新session
            stringRedisTemplate.opsForValue().set(sessionKeyPrefix+session.getId().toString(), SerializeUtil.serialize(session));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(Session session) {
        try {
            //删除session
            stringRedisTemplate.delete(sessionKeyPrefix+session.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Collection<Session> getActiveSessions() {
        //获取所有session缓存
        List<Session> sessionList=new ArrayList<>();
        Set<String> sessionKeySet=stringRedisTemplate.keys(sessionKeyPrefix+"*");
        if(CollectionUtils.isNotEmpty(sessionKeySet)){
            for(String sessionKey:sessionKeySet){
                try {
                    String sessionStr=stringRedisTemplate.opsForValue().get(sessionKey);
                    sessionList.add(SerializeUtil.deserialize(sessionStr));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sessionList;
    }
    
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    
    public void setSessionKeyPrefix(String sessionKeyPrefix) {
        this.sessionKeyPrefix = sessionKeyPrefix;
    }
}
