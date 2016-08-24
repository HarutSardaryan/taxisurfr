/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *  * ATRON electronic GmbH ("ATRON") CONFIDENTIAL                                          *
 *  * Unpublished Copyright (c) 2008-2020 ATRON electronic GmbH, All Rights      *
 *  * Reserved.                                                                                                                 *
 *  *                                                                            *
 *  * NOTICE: All information contained herein is, and remains the property of   *
 *  * ATRON. The intellectual and technical concepts contained herein are        *
 *  * proprietary to ATRON and may be covered by U.S. and Foreign Patents,       *
 *  * patents in process, and are protected by trade secret or copyright law.    *
 *  * Dissemination of this information or reproduction of this material is      *
 *  * strictly forbidden unless prior written permission is obtained             *
 *  * from ATRON. Access to the source code contained herein is hereby forbidden *
 *  * to anyone except current ATRON employees, managers or contractors who have *
 *  * executed. Confidentiality and Non-disclosure agreements explicitly         *
 *  * covering such access.                                                      *
 *  *                                                                            *
 *  *                                                                            *
 *  * The copyright notice above does not evidence any actual or intended        *
 *  * publication or disclosure of this source code, which includes information  *
 *  * that is confidential and/or proprietary, and is a trade secret, of ATRON.  *
 *  * ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC PERFORMANCE, OR       *
 *  * PUBLIC DISPLAY OF OR THROUGH USE OF THIS SOURCE CODE WITHOUT THE EXPRESS   *
 *  * WRITTEN CONSENT OF ATRON IS STRICTLY PROHIBITED, AND IN VIOLATION OF       *
 *  * APPLICABLE LAWS AND INTERNATIONAL TREATIES. THE RECEIPT OR POSSESSION OF   *
 *  * THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY   *
 *  * RIGHTS TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO            *
 *  * MANUFACTURE, USE, OR SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN    *
 *  * PART.                                                                      *
 *  *                                                                            *                                                               *
 *  *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.taxisurfr.manager;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

//@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public abstract class AbstractDao<T extends Serializable> implements Serializable {

    private final Class<T> clazz;

    @Inject
    private EntityManager em;

    public AbstractDao(){
        clazz = null;
    }

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T find(Object id) {
        return em.find(clazz, id);
    }

    public void remove(T entity) {
        em.remove(em.merge(entity));
        em.flush();
    }

    public T edit(T entity) {
        T t = em.merge(entity);
        em.flush();
        return t;
    }

    public void persist(final T entity) {
        em.persist(entity);
    }

    public List<T> findAll() {
        final CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));
        return em.createQuery(criteriaQuery).getResultList();
    }

    public void deleteAll() {
        final CriteriaDelete<T> criteriaDelete = em.getCriteriaBuilder().createCriteriaDelete(clazz);
        criteriaDelete.from(clazz);
        em.createQuery(criteriaDelete).executeUpdate();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    protected Class<T> getClazz() {
        return clazz;
    }
}
