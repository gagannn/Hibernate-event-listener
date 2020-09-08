package com.spring.boot.interceptor;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.type.Type;

import com.spring.boot.model.Book;

public class HibernateEventInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger("app");
	private int updates;
	private int creates;
	private int loads;

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("Delete");
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		logger.info("On Flush Dirty");
		if (entity instanceof Book) {
			updates++;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("lastUpdateTimestamp".equals(propertyNames[i])) {
					currentState[i] = new Date();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("On Load");
		if (entity instanceof Book) {
			loads++;
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("On Save");
		if (entity instanceof Book) {
			creates++;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createTimestamp".equals(propertyNames[i])) {
					state[i] = new Date();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
		logger.info("On Transaction Completion. Status: " + tx.getStatus());
		if (tx.getStatus().isOneOf(TransactionStatus.COMMITTED, TransactionStatus.NOT_ACTIVE)) {
			logger.info("Creations: " + creates + ", Updates: " + updates + ", Loads: " + loads);
		}
		updates = 0;
		creates = 0;
		loads = 0;
	}

}
