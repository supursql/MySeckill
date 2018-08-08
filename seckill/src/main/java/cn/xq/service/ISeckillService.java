package cn.xq.service;

import cn.xq.dto.Exposer;
import cn.xq.dto.SeckillExecution;
import cn.xq.entity.Seckill;
import cn.xq.exception.RepeatKillException;
import cn.xq.exception.SeckillCloseException;
import cn.xq.exception.SeckillException;

import java.util.List;

/**
 * service interface : design the interfaces from the user's
 *
 * three aspects : grading, parameters, returning/exception
 */
public interface ISeckillService {

    /**
     * query all records
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * query by id
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * output the interface address at the beginning
     * or output the system time and seckill time
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * execute seckill
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException;

    /**
     * execute seckill by procedure
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
