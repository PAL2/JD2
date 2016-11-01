package com.hotel.service;

import com.hotel.entity.AbstractEntity;
import com.hotel.util.HibernateUtil;

/**
 * Created by Алексей on 01.10.2016.
 */
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {
    protected static HibernateUtil util = HibernateUtil.getInstance();
    protected final String TRANSACTION_SUCCESS = "Transaction is completed successfully";
    protected final String TRANSACTION_FAIL = "Transaction failed. Error in service.";
}
