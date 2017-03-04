package ttu.idu0080.rest.service;

import ttu.idu0080.rest.data.*;

import java.util.*;
import java.text.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityTransaction;
@Repository
public class DataService  {




	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}


	public Animal getAnimalById(long id)  {


		Animal node = null;
		try {


			Query q =  em
					.createQuery(
							"select c from Animal c where c.id = :id")
							.setParameter("id", id);
			node = (Animal) q.getSingleResult();


		}

		catch(Exception ex)
		{
			System.out.println("DataService.getCarById():"+ ex.getMessage());
		
		}

		return node;
	}


	
	
	
	public List<Animal> getAllAnimals()  {


		List<Animal> animal_list = null;
		try {


			Query q =  em
					.createQuery(
							"select c from Animal c ");
			animal_list = (List<Animal>)  q.getResultList();


		}

		catch(Exception ex)
		{
			System.out.println("DataService.getAllAnimals():"+ ex.getMessage());
		}

		return animal_list;
	}

	
	
	public Animal update(Animal animal)  {


		System.out.println("animal update , animal model: " + animal.getName());
		try {

			em.merge(animal);
			em.flush();

		}

		catch(Exception ex)
		{
			System.out.println("DataService.update():"+ ex.getMessage());
		}

		return animal;
	}

	
	public Animal save(Animal animal) {

		System.out.println("new animal insert , animal model: " + animal.getName());

		try {
			
			em.persist(animal);


		}

		catch(Exception ex)
		{
			System.out.println("DataService.save():"+ ex.getMessage());
		}

		return animal;
	}
	
	public void delete(long id) {

		System.out.println("DELETE ");

		try {
			
	          Animal animal =  em.find(Animal.class,id);
	          em.remove(animal);


		}

		catch(Exception ex)
		{
			System.out.println("DataService.delete():"+ ex.getMessage());
		}


	}
	
	public  List<Animal> searchBySpecies(String s_species)  {

		List<Animal> animal = null;

		try {

			String sql = "from Animal c where upper(c.species) like upper(:species) order by c.birthyear";
            
			Query q = em.createQuery(sql);	
			q.setParameter("species", s_species+"%") ;                  
			animal =  (List<Animal>) q.getResultList();	 				
                        System.out.println("Otsingu tulemusi:" + animal.size());

		}

		catch(Exception ex)
		{
			System.out.println("DataService.searchByModel():" + ex.getMessage());

		}

		return animal;
	}
	
	
}
