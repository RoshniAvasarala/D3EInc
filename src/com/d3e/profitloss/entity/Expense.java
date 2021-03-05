package com.d3e.profitloss.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense implements Serializable{
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "pid")
	private String pid;
	
	@Column(name= "qty")
	private int qty;
	
	@Column(name = "unitprice")
	private double unitprice;
	
	@Column(name = "total")
	private double total;
	public Expense() {
		super();
	}
	public Expense( String name, int qty, double unitprice, double total) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.qty = qty;
		this.unitprice = unitprice;
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", pid=" + pid + ", qty=" + qty + ", unitprice=" + unitprice
				+ ", total=" + total + "]";
	}
	
	
	
	
}




