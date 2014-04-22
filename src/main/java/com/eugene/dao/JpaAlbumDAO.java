package com.eugene.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.eugene.model.Album;

@Repository
@Transactional
public class JpaAlbumDAO implements AlbumDAO {

	private static final Logger LOG = Logger.getLogger(JpaAlbumDAO.class);
	
	private EntityManager entityManager;

	private static final String LOAD_ALL_ALBUMS = "from Album";
	private static final String LOAD_ALBUM_BY_ID = "from Album a where a.albumID = :albumID";

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Album createOrUpdateAlbum(Album album) {
		return entityManager.merge(album);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void deleteAlbum(Long albumID) {
		LOG.info("Is Transactions active? " + TransactionSynchronizationManager.isActualTransactionActive());
		Album album = entityManager.find(Album.class, albumID);
		if (album != null) {
			entityManager.remove(album);
		} else {
			LOG.error("Album was not deleted as we didn't find one with the ID: " + albumID);
		}
	}

	public Album getAlbum(Long albumID) {
		try {
			return (Album) entityManager.createQuery(LOAD_ALBUM_BY_ID).setParameter("albumID", albumID).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAllAlbums() {
		return entityManager.createQuery(LOAD_ALL_ALBUMS).getResultList();
	}

}
