package com.jsf.controller;


//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;

import org.primefaces.PrimeFaces;

import com.jsf.dao.ProductDAO;
import com.jsf.model.Producto;

@ManagedBean(name="productBean")
@RequestScoped
public class ProductBean {
	public List<Producto> getItems() {
		ProductDAO pdao= new ProductDAO();
		return pdao.getAll();
	}
	
	public String create() {
		Producto p= new Producto();
		Map<String, Object> sessionMap= FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("nuevo", p);
		return "/faces/create.xhtml";
	}
	
	public String save(Producto p) {
		ProductDAO pdao= new ProductDAO();
		pdao.save(p);
		return "/faces/index.xhtml";
	}
	
	public String edit(int id) {
		ProductDAO pdao= new ProductDAO();
		Producto p= new Producto();
		p= pdao.getOne(id);
		//System.out.println("-");
		//System.out.println(p.getNombre());
		Map<String, Object> sessionMap= FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("producto", p);
		return "/faces/edit.xhtml";
	}
	
	public String update(Producto p) {
		ProductDAO pdao= new ProductDAO();
		pdao.edit(p);
		
		return "/faces/index.xhtml";
	}
	
	public void delete(int id) {
		ProductDAO pdao=new ProductDAO();
		pdao.delete(id);
		System.out.println("ci");
		 PrimeFaces.current().ajax().update("form:dt-products");
	}
	
}
