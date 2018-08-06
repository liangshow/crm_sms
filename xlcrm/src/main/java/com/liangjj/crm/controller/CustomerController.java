package com.liangjj.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liangjj.crm.pojo.BaseDict;
import com.liangjj.crm.pojo.Customer;
import com.liangjj.crm.pojo.DBCustomer;
import com.liangjj.crm.pojo.QueryCus;
import com.liangjj.crm.service.BaseDictService;
import com.liangjj.crm.service.solrservice.CustomerService;
import com.liangjj.crm.utils.Page;

/**
 * @author 小亮
 *客户增删查改操作
 *
 */
@Controller//将实现类交给spring容器管理
public class CustomerController {
	
	//注入分类表业务类
	@Autowired
	private BaseDictService baseDictService;
	
	//注入客户业务类
	@Autowired
	private CustomerService customerService;
	
	
	/**
	 * 展示过滤后的客户信息
	 * @param queryCus 接收页面参数
	 * @param model 传递给页面的参数
	 * @return 返回字符串类型，由视图解析器跳转到相关页面或方法
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/customer/list")
	public String showCustomerList(QueryCus queryCus,Model model) throws Exception{
		
		//处理乱码
//		if(StringUtils.isNotBlank(queryCus.getCustName())){
//			queryCus.setCustName(new String(queryCus.getCustName().getBytes("iso8859-1"), "utf-8"));
//		}
		
		
		List<BaseDict> industryList = baseDictService.getDictList();
		for(BaseDict baseDict:industryList){
			if (String.valueOf(baseDict.getId()).equals(queryCus.getCustIndustory())) {
				queryCus.setCustIndustoryName(baseDict.getIndustry());;
				break;
			}
		}

		//把字典信息传递给页面
		model.addAttribute("industryType",industryList);
		
		//根据查询条件查询客户列表
		Page<Customer> page=customerService.queryCustomer(queryCus);
		
		//把数据传递给页面
		model.addAttribute("page",page);
		
		//参数回显
		model.addAttribute("custName",queryCus.getCustName());
		model.addAttribute("custIndustry",queryCus.getCustIndustory());
		
		return "/customer.jsp";
	}
	
	
	/**
	 * 显示客户详细信息
	 * @param cid 客户id
	 * @param model 返回数据对象
	 * @return 字符串，由视图解析器跳转到相应url地址
	 */
	@RequestMapping("/customer/showCustDetail/{id}")
	public String showCustDetail(@PathVariable("id") Integer cid,Model model){
		//调用业务层查询客户信息
		List<DBCustomer> dbCustomers=customerService.getCustomerByCid(cid);
		//System.out.println(cid);
		//System.out.println(dbCustomers.size());
		model.addAttribute("customer",dbCustomers.get(0));
		return "../../customerdetail.jsp";
	}
	
	
	/**
	 * 展示与商品相关的客户信息
	 * @param pid 商品id
	 * @param queryCus 相关查询信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/customer/listByPid/{pid}")
	public String listByPid(@PathVariable("pid") Integer pid,QueryCus queryCus,Model model) throws Exception{
		
		//处理乱码
		if(StringUtils.isNotBlank(queryCus.getCustName())){
			queryCus.setCustName(new String(queryCus.getCustName().getBytes("iso8859-1"), "utf-8"));
		}
		
		
		List<BaseDict> industryList = baseDictService.getDictList();
		for(BaseDict baseDict:industryList){
			if (String.valueOf(baseDict.getId()).equals(queryCus.getCustIndustory())) {
				queryCus.setCustIndustoryName(baseDict.getIndustry());;
				break;
			}
		}

		//把字典信息传递给页面
		model.addAttribute("industryType",industryList);
		
		//根据查询条件查询客户列表
		queryCus.setPid(pid);
		Page<DBCustomer> page=customerService.queryConnectionCustomerByPid(queryCus);
		
		//把数据传递给页面
		model.addAttribute("page",page);
		
		//参数回显
		model.addAttribute("custName",queryCus.getCustName());
		model.addAttribute("custIndustry",queryCus.getCustIndustory());
		model.addAttribute("pid",pid);
		
		return "../../linkcustomer.jsp";
	}
	
	
	/**
	 * 根据客户id删除客户信息
	 * @param cid
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/customer/delete/{id}")
	public void delete(@PathVariable("id") Integer cid,HttpServletResponse response) throws Exception{
		//根据客户id删除客户信息，以及关联的中间表信息
		//为了保持信息的原子性，使得中间表和客户表信息一致，放到业务层统一进行事务管理
		customerService.deleteMiddleAndCustomerMsgByCid(cid);
		response.getWriter().write("success");
	}
	
	/**
	 * 根据客户id获取客户信息，回显到页面进行更改
	 * @param cid
	 * @return
	 */
	@RequestMapping("/customer/edit/{id}")
	public @ResponseBody DBCustomer edit(@PathVariable("id") Integer cid){
		
		DBCustomer dbCustomer=customerService.getDBCustomerByCid(cid);
		
		//System.out.println(dbCustomer.getCus_birthday());
		return dbCustomer;
	}
	
	/**
	 * 接收页面参数，更新客户信息
	 * @param dbCustomer
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/customer/update")
	public void update(DBCustomer dbCustomer,HttpServletResponse response) throws Exception{
		customerService.updateCustomer(dbCustomer);
		response.getWriter().write("success");
	}
	
	/**
	 * 跳转到增加客户页面
	 * @return
	 */
	@RequestMapping("/customer/showcreate")
	public String showcreate(){
		
		return "../addcustomer.jsp";
	}
	
	
	/**
	 * 接收页面参数，创建新的客户
	 * @param dbCustomer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/customer/create")
	public String create(DBCustomer dbCustomer) throws Exception{ 
		System.out.println("开始创建数据");
		//将数据插入数据库，并同时更新solr索引库数据
		customerService.createCustomer(dbCustomer);
		System.out.println("创建数据结束");
		return "redirect:/customer/list";
	}
	
	
	
}
