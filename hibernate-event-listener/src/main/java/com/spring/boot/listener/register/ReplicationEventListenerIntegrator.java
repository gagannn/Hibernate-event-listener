package com.spring.boot.listener.register;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import com.spring.boot.listeners.ReplicationInsertEventListener;

public class ReplicationEventListenerIntegrator implements Integrator {

	public static final ReplicationEventListenerIntegrator INSTANCE = new ReplicationEventListenerIntegrator();

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		System.out.println("------------------------------------Insert-1");

		final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

		eventListenerRegistry.appendListeners(EventType.POST_INSERT, ReplicationInsertEventListener.INSTANCE);

//		eventListenerRegistry.appendListeners(EventType.POST_UPDATE, ReplicationUpdateEventListener.INSTANCE);
//
//		eventListenerRegistry.appendListeners(EventType.PRE_DELETE, ReplicationDeleteEventListener.INSTANCE);
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

	}
}