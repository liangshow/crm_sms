package com.liangjj.crm.dao.solrdao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.liangjj.crm.pojo.Customer;
import com.liangjj.crm.pojo.DBCustomer;
import com.liangjj.crm.utils.Page;


/**
 * @author 小亮
 * solr索引库，持久层实现类
 *
 */
@Repository
public class CustomerDao {
	@Autowired
	@Qualifier("httpSolrServer")
	private SolrServer solrServer;
	
	/**
	 * 根据query中查询条件查询solr索引库
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Page<Customer> search(SolrQuery query) throws Exception{
		//执行查询
		QueryResponse response=solrServer.query(query);
		
		//获取查询结果
		SolrDocumentList documentList=response.getResults();
		
		//取查询结果总记录数
		Page<Customer> page=new Page<>();
		page.setTotal((int) documentList.getNumFound());
		
		//客户列表
		List<Customer> customers=new ArrayList<>();
		
		//取结果集
		for(SolrDocument solrDocument:documentList){
			//创建一个客户对象
			Customer customer=new Customer();
			customer.setId(Integer.parseInt(solrDocument.get("id").toString()));
			customer.setCus_name((String)solrDocument.get("cus_name"));
			customer.setCus_address((String)solrDocument.get("cus_address"));
			customer.setCus_industry((String)solrDocument.get("cus_industry"));
			customer.setCus_sex((String)solrDocument.get("cus_sex"));
			customer.setCus_tel((String)solrDocument.get("cus_tel"));
			customer.setLevel((String)solrDocument.get("cus_level"));
			
			customers.add(customer);
		}
		
		page.setRows(customers);
		
		return page;
	}


	/**
	 * 根据客户id删除索引库中客户信息
	 * @param cid
	 * @throws Exception
	 */
	public void deleteCustomerMsgByCid(Integer cid) throws Exception {
		//调用solrserver删除对应id的客户信息
		solrServer.deleteById(String.valueOf(cid));
		//提交修改
		solrServer.commit();
	}

	/**
	 * 更新索引库客户信息
	 * @param dbCustomer 
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public void updateCustomer(DBCustomer dbCustomer) throws Exception {
		//创建文档对象
		SolrInputDocument document=new SolrInputDocument();
		//往文档中添加域数据
		document.addField("id", dbCustomer.getCid());
		document.addField("cus_industry",dbCustomer.getCus_industry());
		document.addField("cus_name",dbCustomer.getCus_name());
		document.addField("cus_tel", dbCustomer.getTelnum());
		document.addField("cus_address", dbCustomer.getCus_address());
		document.addField("cus_sex",dbCustomer.getCus_sex());
		document.addField("cus_level",dbCustomer.getCus_level());
		
		//将文档数据添加到索引库
		solrServer.add(document);
		
		//提交数据
		solrServer.commit();
	}
	
	
	/**
	 * 创建客户索引
	 * @param dbCustomer
	 * @throws Exception
	 */
	public void createCustomerIndex(DBCustomer dbCustomer) throws Exception {
		//创建文档对象
		SolrInputDocument document=new SolrInputDocument();
		//往文档中添加域数据
		document.addField("id", dbCustomer.getCid());
		document.addField("cus_industry",dbCustomer.getCus_industry());
		document.addField("cus_name",dbCustomer.getCus_name());
		document.addField("cus_tel", dbCustomer.getTelnum());
		document.addField("cus_address", dbCustomer.getCus_address());
		document.addField("cus_sex",dbCustomer.getCus_sex());
		document.addField("cus_level",dbCustomer.getCus_level());
		
		//将文档数据添加到索引库
		solrServer.add(document);
		
		//提交数据
		solrServer.commit();
	}
}
