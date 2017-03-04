package ttu.idu0080.rest.controller;

import java.io.IOException;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.ui.Model;

import ttu.idu0080.rest.service.*;
import ttu.idu0080.rest.data.*;


@Controller
public class RESTController {
	
	@Autowired
	private DataService dataService;
	@Autowired
	private RESTDataService restDataService;

	@RequestMapping(value="/service/animals",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Animal> getAnimals(HttpServletResponse response) throws IOException{
		
		List<Animal> animals = dataService.getAllAnimals();
		return animals;
	}
	
	@RequestMapping(value="/service/animal/{id}",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Animal getAnimal(@PathVariable int id) throws IOException{
		
		Animal animal = dataService.getAnimalById(id);
		return animal;
	}
	
	@Transactional
	@RequestMapping(value = "/service/animal/{id}", method=RequestMethod.POST)
	public @ResponseBody void updateAnimal(@RequestBody Animal animal)
	{
		dataService.update(animal);
		
	}
	
	
	
	@RequestMapping(value="/service/external/animals",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Animal> getExternalAnimals(HttpServletResponse response) throws IOException{
		List<Animal> animals = restDataService.getAllAnimals();
		return animals;
	}
	
	@Transactional
	@RequestMapping(value = "/service/animal/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void deleteAnimal(@PathVariable int id)
	{
		dataService.delete(id);
	}
	
	@Transactional
	@RequestMapping(value = "/service/animal/", method=RequestMethod.PUT)
	public @ResponseBody void insertAnimal(@RequestBody Animal animal)
	{
		dataService.save(animal);
	}
	
	@RequestMapping(value="/service/search",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Animal> searchAnimals(@RequestParam(value = "species") String species) throws IOException{
		List<Animal> animals= dataService.searchBySpecies(species);
		return animals;
	}
	
}
