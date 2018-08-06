package com.liangjj.crm.dao;

import java.util.List;

import com.liangjj.crm.pojo.Customer;
import com.liangjj.crm.pojo.DBCustomer;
import com.liangjj.crm.pojo.QueryCus;
import com.liangjj.crm.utils.Page;

/**
 * @author 小亮
 * mysql数据库，持久层增删查接口
 *
 */
public interface DBCustomerDao {

	List<DBCustomer> getCustomerByCid(Integer cid);


	List<DBCustomer> getConnectionCustomerByPid(QueryCus queryCus);


	Integer getTotalCountOfCustomerByPid(Integer pid);


	void deleteMiddleMsgByCid(Integer cid);


	void deleteCustomerMsgByCid(Integer cid);


	void updateDBCustomer(DBCustomer dbCustomer);


	void createCustomer(DBCustomer dbCustomer);



}
