package cn.xq.service.impl;

import cn.xq.dto.Exposer;
import cn.xq.dto.SeckillExecution;
import cn.xq.entity.Seckill;
import cn.xq.service.ISeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-services.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info("list= {}", seckills);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill = {}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer = {}", exposer);
//        Exposer{exposed=true,
//                  md5='de1a582b7b20d0b811bb53afb6dce1f0',
//                  seckillId=1000,
//                  now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 15771803173L;
        String md5 = "de1a582b7b20d0b811bb53afb6dce1f0";
        SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
        logger.info("result = {}", execution);
    }

    @Test
    public void executeSeckillProcedure() {
        long seckillId = 1001;
        long phone = 12345688683L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(execution.getStateInfo());
        }
    }
}