package com.bionic.edu;

import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class MerchantDaoImpl implements MerchantDao {
    @PersistenceContext
    private EntityManager em;

    public Merchant findById(int id){
        return em.find(Merchant.class, id);
    }

    public void save(Merchant merchant){
        em.persist(merchant);
    }

    public void remove(int id){
        Merchant merchant = em.find(Merchant.class, id);
        if (merchant != null){
            em.remove(merchant);
        }
    }

    public void updateAccount(int id, String newAccount){
        Merchant merchant = em.find(Merchant.class, id);
        if (merchant != null){
            merchant.setAccount(newAccount);
        }
    }

    public List<Merchant> findAll(){
        TypedQuery<Merchant> query =
                em.createQuery("SELECT m FROM Merchant m", 	   Merchant.class);
        List<Merchant> listM = query.getResultList();
        return listM;
    }

    public List<Merchant> getSortedByNeedToPay(){
        String txt = "SELECT m FROM Merchant m ORDER BY 	m.needToSend";
        TypedQuery<Merchant> query = em.createQuery(txt, 	Merchant.class);
        return query.getResultList();
    }
}