package com.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jsf.model.JPAUtil;
import com.jsf.model.Producto;

public class ProductDAO {	
	EntityManager entity= JPAUtil.getEntityManagerFactory().createEntityManager();
	
	//Guarda el Registro
	
	public void save(Producto product) {
		entity.getTransaction().begin();
		entity.persist(product);
		entity.getTransaction().commit();
		//JPAUtil.shutdown(); cierra la conexion de ser necesario
	}
	
	
	// edita y/o actualiza el registro
	public void edit(Producto product) {
		entity.getTransaction().begin();
		entity.merge(product);
		entity.getTransaction().commit();
	}
	
	//elimina el registro 
	
	public void delete(int id) {
		Producto p=new Producto();
		p= entity.find(Producto.class, id);
		entity.getTransaction().begin();
		entity.remove(p);
		entity.getTransaction().commit();
	}
	
	// traer item
	
	public Producto getOne(int id) {
		Producto p = new Producto();
		p = entity.find(Producto.class, id);
		return p;
	}
	
	public List<Producto> getAll(){
		List<Producto> listProducts= new ArrayList<>();
		Query q= entity.createQuery("SELECT p FROM Producto p");
		listProducts=q.getResultList();
		return listProducts;
	}
}
