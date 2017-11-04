package dao.impl;

import dao.BaseDAO;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository("baseDAO")
@SuppressWarnings("all")
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	public Serializable save(T o) {
		return getSession().save(o);
	}

	public void delete(T o) {
		getSession().delete(o);
	}


	public boolean update(T o) {
		getSession().update(o);
		return true;
	}

	public void saveOrUpdate(T o) {
        getSession().saveOrUpdate(o);
	}

	@Transactional
	public List<T> find(String hql) {
		return getSession().createQuery(hql).list();
	}

	public List<T> find(String hql, Object[] param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}

	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry entry : params.entrySet()) {
				Object value = entry.getValue();
				if (value.getClass().isArray()) {
					q.setParameterList(entry.getKey().toString(), (Object[]) value);
				} else if (Collection.class.isAssignableFrom(value.getClass())) {
					q.setParameterList(entry.getKey().toString(), (Collection) value);
				} else {
					q.setParameter(entry.getKey().toString(), value);
				}
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

    @Override
    public <K> List<K> findVO(String hql, Map<String, Object> params) {
        Query q = getSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.list();
    }
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry entry : params.entrySet()) {
				Object value = entry.getValue();
				if (value.getClass().isArray()) {
					q.setParameterList(entry.getKey().toString(), (Object[]) value);
				} else if (Collection.class.isAssignableFrom(value.getClass())) {
					q.setParameterList(entry.getKey().toString(), (Collection) value);
				} else {
					q.setParameter(entry.getKey().toString(), value);
				}
			}
		}
		return q.list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) getSession().get(c, id);
	}

	@Override
	public T load(Class<T> c,Serializable id) {
		return (T)getSession().get(c,id);
	}

	public T get(String hql, Object[] param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public Long count(String hql) {
		return (Long)getSession().createQuery(hql).uniqueResult();
	}

	public Long count(String hql, Object[] param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, List<Object> param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry entry : params.entrySet()) {
				Object value =  entry.getValue();
				if(value.getClass().isArray() ){
					//是数组
					q.setParameterList(entry.getKey().toString(), (Object[])value);
				}else if( Collection.class.isAssignableFrom(value.getClass()) ){
					//是集合
					q.setParameterList(entry.getKey().toString(), (Collection)value);
				}else{
					q.setParameter(entry.getKey().toString(), value);
				}
			}
		}
		return (Long) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		return getSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object[] param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

    @Override
    public int executeHql(String hql, Map<String, Object> params) {
        Query q = getSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.executeUpdate();
    }


    @Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params) {
        SQLQuery q = getSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //������
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //�Ǽ���
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.list();
    }

	@Override
	public List findBySql(Class<T> c, String sql, Map<String, Object> params) {
		SQLQuery q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry entry : params.entrySet()) {
				Object value =  entry.getValue();
				if(value.getClass().isArray() ){
					//������
					q.setParameterList(entry.getKey().toString(), (Object[])value);
				}else if( Collection.class.isAssignableFrom(value.getClass()) ){
					//�Ǽ���
					q.setParameterList(entry.getKey().toString(), (Collection)value);
				}else{
					q.setParameter(entry.getKey().toString(), value);
				}
			}
		}
		return q.addEntity(c).list();
	}

	@Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
        SQLQuery q = getSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //������
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //�Ǽ���
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }
}
