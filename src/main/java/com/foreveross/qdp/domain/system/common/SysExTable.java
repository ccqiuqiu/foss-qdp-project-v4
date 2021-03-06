/*******************************************************************************
 * Copyright (c) 2017-11-09 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation.
 * Auto Generate By foreveross.com Quick Deliver Platform. 
 ******************************************************************************/
package com.foreveross.qdp.domain.system.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.iff.infra.util.Assert;
import org.iff.infra.util.BeanHelper;
import org.iff.infra.util.Exceptions;
import org.iff.infra.util.MapHelper;
import org.iff.infra.util.ValidateHelper;
import org.iff.infra.util.mybatis.plugin.Page;
import org.iff.infra.util.mybatis.service.Dao;

import org.apache.commons.lang3.StringUtils;

/**
 * SysExTable
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since 2017-11-09
 * @version 1.0.0
 * auto generate by qdp v3.0.
 */
@SuppressWarnings("serial")
public class SysExTable implements Serializable {

	/** 主键 **/
	private String id;
	/** 关联表 **/
	private String refTable;
	/** 字段名称 **/
	private String colName;
	/** 字段类型 **/
	private String colType;
	/** 描述 **/
	private String description;
	/** 修改时间 **/
	private Date updateTime;
	/** 创建时间 **/
	private Date createTime;

	public SysExTable() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}
	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * <pre>
	 * get SysExTable by id	 
	 * Usage : SysExTable.get(id);
	 * </pre>
	 * @param sysExTable
	 * @return SysExTable
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public static SysExTable get(SysExTable sysExTable) {
		return Dao.queryOne("SysExTable.getSysExTableById", sysExTable);
	}
	
	/**
	 * <pre>
	 * get SysExTable by id	 
	 * Usage : SysExTable.get(id);
	 * </pre>
	 * @param id
	 * @return SysExTable
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public static SysExTable get(String id) {
		SysExTable sysExTable = new SysExTable();
		sysExTable.setId(id);
		return get(sysExTable);
	}
	
	/**
	 * <pre>
	 * remove SysExTable by id
	 * Usage : SysExTable.remove(id)
	 * </pre>
	 * @param sysExTable
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public static void remove(SysExTable sysExTable) {
		sysExTable.remove();
	}
	
	/**
	 * <pre>
	 * remove SysExTable by id
	 * Usage : SysExTable.remove(id)
	 * </pre>
	 * @param sysExTable
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public static void remove(String id) {
		SysExTable sysExTable = new SysExTable();
		sysExTable.setId(id);
		remove(sysExTable);
	}
	
	/**
	 * <pre>
	 * remove SysExTable by id
	 * Usage : SysExTable.remove(id)
	 * </pre>
	 * @param sysExTable
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public static void remove(String[] ids) {
		if (ids != null) {
			for (String id : ids) {
				SysExTable sysExTable = new SysExTable();
				sysExTable.setId(id);
				remove(sysExTable);
			}
		}
	}
	
	/**
	 * <pre>
	 * add SysExTable
	 * Usage : SysExTable.add()
	 * </pre>
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public SysExTable add() {
		ValidateHelper validate = validate("add");
		if (validate.hasErrors()) {
			Exceptions.runtime(validate.joinErrors("\n"));
		}
		Dao.save("SysExTable.insertSysExTable", this);
		return this;
	}

	/**
	 * <pre>
	 * update SysExTable
	 * Usage : SysExTable.update()
	 * </pre>
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public SysExTable update() {
		ValidateHelper validate = validate("edit");
		if (validate.hasErrors()) {
			Exceptions.runtime(validate.joinErrors("\n"));
		}
		Dao.save("SysExTable.updateSysExTable", this);
		return this;
	}
	
	/**
	 * <pre>
	 * add or update SysExTable
	 * Usage : SysExTable.update()
	 * </pre>
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public SysExTable addOrUpdate() {
		if (StringUtils.isBlank(getId())) {
			return add();
		} else {
			return update();
		}
	}
	
	/**
	 * <pre>
	 * remove SysExTable by id
	 * Usage : SysExTable.remove()
	 * </pre>
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	public void remove() {
		ValidateHelper validate = validate("delete");
		if (validate.hasErrors()) {
			Exceptions.runtime(validate.joinErrors("\n"));
		}
		// 删除本对象
		Dao.remove("SysExTable.deleteSysExTable", this);
	}
	
	
	
	
	/**
	 * <pre>
	 * validate add/update/delete
	 * </pre>
	 * @param type add/update/delete
	 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
	 * @since 2017-11-09
	 */
	private ValidateHelper validate(String type) {
		ValidateHelper validate = ValidateHelper.create();
		if ("add".equals(type)) {
			{//初始化值
				setUpdateTime(new java.util.Date());
				setCreateTime(new java.util.Date());
			}
			// validte the field
			// "NO":"不用验证","email":"EMAIL","tel":"电话号码","mobile":"手机号码","zipcode":"邮政编码","url":"网址",
			// "date":"日期","number":"数字","digits":"整数","creditcard" :"信用卡号","idcard":"身份证号码",
			// "chinese":"中文","ipv4":"IPv4","ipv6":"IPv6"
			{
				validate.required("SysExTable.refTable", getRefTable(), "iff.validate.required", "{0} is required!");
				validate.required("SysExTable.colName", getColName(), "iff.validate.required", "{0} is required!");
				validate.required("SysExTable.colType", getColType(), "iff.validate.required", "{0} is required!");
			}
			{// validate unique
			}
			{// validate value range
			}
			{// validate foreign key
			}
		} else if ("edit".equals(type)) {
			{//初始化值
				setUpdateTime(new java.util.Date());
				setCreateTime(null);
			}
			// validte the field
			// "NO":"不用验证","email":"EMAIL","tel":"电话号码","mobile":"手机号码","zipcode":"邮政编码","url":"网址",
			// "date":"日期","number":"数字","digits":"整数","creditcard" :"信用卡号","idcard":"身份证号码",
			// "chinese":"中文","ipv4":"IPv4","ipv6":"IPv6"
			{
				validate.required("SysExTable.refTable", getRefTable(), "iff.validate.required", "{0} is required!");
				validate.required("SysExTable.colName", getColName(), "iff.validate.required", "{0} is required!");
				validate.required("SysExTable.colType", getColType(), "iff.validate.required", "{0} is required!");
			}
			{// validate the primary key 
				validate.required("SysExTable.id", getId(), "iff.validate.required", "{0} is required!");
			}
			{// validate unique
			}
			{// validate value range
			}
			{// validate foreign key
			}
		} else if ("delete".equals(type)) {
			{// validate the primary key 
				validate.required("SysExTable.id", getId(), "iff.validate.required", "{0} is required!");
			}
		}
		return validate;
	}
}
