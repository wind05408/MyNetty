package com.dk.juc.mod.disruptor.demo1;

import com.lmax.disruptor.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-07 15:42
 **/
public class IntEventExceptionHandler implements ExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void handleEventException(Throwable ex, long sequence, Object event) {
        logger.error("handleEventException", ex);
    }

    public void handleOnStartException(Throwable ex) {
        logger.error("handleOnStartException", ex);
    }

    public void handleOnShutdownException(Throwable ex) {
        logger.error("handleOnShutdownException", ex);
    }
}
