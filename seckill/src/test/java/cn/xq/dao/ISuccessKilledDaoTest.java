package cn.xq.dao;

import cn.xq.entity.Seckill;
import cn.xq.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ISuccessKilledDaoTest {

    @Resource
    private ISuccessKilledDao iSuccessKilledDao;

    @Test
    public void insertSuccessKilled() {
        long id = 1000L;
        long phone = 15771803174L;

        int insertCount = iSuccessKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount = " + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1000L;
        long phone = 15771803174L;
        SuccessKilled successKilled = iSuccessKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}