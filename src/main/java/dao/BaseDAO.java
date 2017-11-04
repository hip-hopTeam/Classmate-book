package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作类
 * 
 * @author ss
 * 
 */
public interface BaseDAO<T> {

	/**
	 * 保存�?��对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 删除�?��对象
	 * 
	 * @param o
	 */
	public void delete(T o);



	/**
	 * 更新�?��对象
	 * 
	 * @param o
	 * @return 
	 */
	public boolean update(T o);

	/**
	 * 保存或更新对�?
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

    public List<T> find(String hql, Map<String, Object> params);
	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查询集合(带分�?
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几�?
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 查询集合(带分�?
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page, Integer rows);
	
	 public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	/**
	 * 获得�?��对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);
	public T load(Class<T> c, Serializable id);

	/**
	 * 获得�?��对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 获得�?��对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from �?
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public <K> List<K> findVO(String hql, Map<String, Object> params);
	/**
	 * select count(*) from �?
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */

	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from �?
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 执行HQL语句
	 *
	 */
	public int executeHql(String hql, Map<String, Object> params);
	public Integer executeHql(String hql, List<Object> param);
	public List<Object[]> findBySql(String sql, Map<String, Object> params) ;
	public List findBySql(Class<T> c, String sql, Map<String, Object> params) ;
	public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows);
}
