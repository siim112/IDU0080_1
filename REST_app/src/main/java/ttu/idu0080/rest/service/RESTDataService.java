package ttu.idu0080.rest.service;

import ttu.idu0080.rest.data.*;

import java.util.*;
import java.text.*;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;;
@Service
public class RESTDataService  {



	
	
	public List<Animal> getAllAnimals()  {
		
		Animal[] animal_array = null;
		try
		{
			RestTemplate restTemplate = new RestTemplate();
		animal_array = restTemplate.getForObject("http://localhost:8080/REST_service/service/animals", Animal[].class) ;
		System.out.println("Loomi REST-teenusest:" + animal_array.length);
		}
		catch(Exception ex)
		{
			System.out.println("RESTDataService.getAllAnimals():"+ ex.getMessage());
		}

		List<Animal> animal_list= Arrays.asList(animal_array);
		return animal_list;
	}

	
	
	
}
