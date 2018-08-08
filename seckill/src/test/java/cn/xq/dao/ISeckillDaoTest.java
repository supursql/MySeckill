package cn.xq.dao;

import cn.xq.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test, junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ISeckillDaoTest {
    //注入Dao实现类依赖
    @Resource
    private ISeckillDao iSeckillDao;

    @Test
    public void reduceNumber() {
        Date killTime = new Date();
        int updateCount = iSeckillDao.reduceNumber(1000L, killTime);
        System.out.println("update = " + updateCount);
    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = iSeckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
        List<Seckill> seckills = iSeckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }
}