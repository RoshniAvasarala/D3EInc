package com.d3e.profitloss.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "pid")
	private String pid;
	
	@Column(name = "qty")
	private int qty;
	
	@Column(name = "unit_price")
	private double unit_price;
	
	@Column(name = "total_price")
	private double total_price;
	
	public Invoice() {
		super();
	}
	public Invoice(String name, int qty, double unit_price, double total_price) {
		super();
		
		this.name = name;
		this.qty = qty;
		this.unit_price = unit_price;
		this.total_price = total_price;
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
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", name=" + name + ", pid=" + pid + ", qty=" + qty + ", unit_price=" + unit_price
				+ ", total_price=" + total_price + "]";
	}
	
	
}
