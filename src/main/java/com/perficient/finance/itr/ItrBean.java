package com.perficient.finance.itr;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class ItrBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addAlbum(ItrUserStore itrUserStore) {
        entityManager.persist(itrUserStore);
    }

    public ItrUserStore find(long id) {
        return entityManager.find(ItrUserStore.class, id);
    }

    public List<ItrUserStore> getAlbums() {
        CriteriaQuery<ItrUserStore> cq = entityManager.getCriteriaBuilder().createQuery(ItrUserStore.class);
        cq.select(cq.from(ItrUserStore.class));
        return entityManager.createQuery(cq).getResultList();
    }
}
