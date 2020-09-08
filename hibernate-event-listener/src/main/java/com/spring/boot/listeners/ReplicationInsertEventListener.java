package com.spring.boot.listeners;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

import com.spring.boot.model.Book;

public class ReplicationInsertEventListener implements PostInsertEventListener {
	private static final long serialVersionUID = 1L;
	public static final ReplicationInsertEventListener INSTANCE = new ReplicationInsertEventListener();

	@Override
	public void onPostInsert(PostInsertEvent event) throws HibernateException {
		System.out.println("------------------------------------Insert-2");

		final Object entity = event.getEntity();

		if (entity instanceof Book) {
			Book post = (Book) entity;

			event.getSession()
					.createNativeQuery("INSERT INTO book_event_listener (book_id, book_title) " + "VALUES (:id, :title)")
					.setParameter("id", post.getId()).setParameter("title", post.getTitle())
					.setFlushMode(FlushMode.MANUAL).executeUpdate();
		}
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}
}