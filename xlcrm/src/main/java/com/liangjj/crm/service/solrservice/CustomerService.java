package com.liangjj.crm.service.solrservice;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liangjj.crm.dao.DBCustomerDao;
import com.liangjj.crm.dao.solrdao.CustomerDao;
import com.liangjj.crm.pojo.Customer;
import com.liangjj.crm.pojo.DBCustomer;
import com.liangjj.crm.pojo.QueryCus;
import com.liangjj.crm.pojo.SolrResultModel;
import com.liangjj.crm.utils.Page;

/**
 * @author 小亮
 *客户业务类
 */
@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Page<Customer> queryCustomer(QueryCus queryCus) throws Exception{
		//根据参数创建查询对象
		SolrQuery solrQuery=new SolrQuery();
		//调用dao执行查询
		//设置查询条件
		if(null!=queryCus.getCustName()&&!"".equals(queryCus.getCustName())){
			solrQuery.setQuery("cus_name:"+queryCus.getCustName());
		}else {
			solrQuery.setQuery("*:*");
		}
		
		//客户行业过滤
		if(null!=queryCus.getCustIndustory()&&!"".equals(queryCus.getCustIndustory())){
			solrQuery.addFilterQuery("cus_industry:"+queryCus.getCustIndustoryName());
		}
		//分页处理
		if (queryCus.getPage()==null) {
			queryCus.setPage(1);
		}
		solrQuery.setStart((queryCus.getPage()-1)*queryCus.getSize());
		solrQuery.setRows(queryCus.getSize());
		
		//默认搜索域
		solrQuery.set("df", "cus_name");
		
		//设置查询排序
		solrQuery.setSort("id", ORDER.asc);
		
		//执行查询
		Page<Customer> page=customerDao.search(solrQuery);
		
		//根据总记录获取总页数,自动获取
		page.setPage(queryCus.getPage());
		page.setSize(queryCus.getSize());
		
		return page;
		
	}
	
	@Autowired
	private DBCustomerDao dbCustomerDao; 
	
	public List<DBCustomer> getCustomerByCid(Integer cid) {
		return dbCustomerDao.getCustomerByCid(cid);
	}


	public Page<DBCustomer> queryConnectionCustomerByPid(QueryCus queryCus) {
		Page<DBCustomer> page=new Page<>();
		//分页处理
		if (queryCus.getPage()==null) {
			queryCus.setPage(1);
		}
		
		//获取分页起始数值
		Integer start=(queryCus.getPage()-1)*queryCus.getSize();
		queryCus.setStart(start);
		
		//获得总纪录数
		Integer totalCount=dbCustomerDao.getTotalCountOfCustomerByPid(queryCus.getPid());
		
		//根据总记录获取总页数,自动获取
		page.setTotal(totalCount);
		page.setPage(queryCus.getPage());
		page.setSize(queryCus.getSize());
		
		page.setRows(dbCustomerDao.getConnectionCustomerByPid(queryCus));
		
		return page;

	}


	public void deleteMiddleMsgByCid(Integer cid) {
		dbCustomerDao.deleteMiddleMsgByCid(cid);
	}


	public void deleteMiddleAndCustomerMsgByCid(Integer cid) throws Exception {
		//删除数据库中间表
		dbCustomerDao.deleteMiddleMsgByCid(cid);
		//删除数据库中客户信息
		dbCustomerDao.deleteCustomerMsgByCid(cid);
		//删除solr索引库客户信息
		customerDao.deleteCustomerMsgByCid(cid);
	}


	public DBCustomer getDBCustomerByCid(Integer cid) {
		// TODO Auto-generated method stub
		return dbCustomerDao.getCustomerByCid(cid).get(0);
	}


	public void updateCustomer(DBCustomer dbCustomer) throws Exception {
		//更新数据库客户数据
		dbCustomerDao.updateDBCustomer(dbCustomer);
		//更新数据库索引数据
		customerDao.updateCustomer(dbCustomer);
	}


	public void createCustomer(DBCustomer dbCustomer) throws Exception {
		//增加数据库记录，并在更新后获取id
		dbCustomerDao.createCustomer(dbCustomer);
		//更新solr索引库数据
		customerDao.createCustomerIndex(dbCustomer);
	}
}
