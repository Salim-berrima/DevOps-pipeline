package com.esprit.examen.services;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {
	@Autowired
	IProduitService  ProduitServicee;
	@Mock 
	ProduitRepository ProduitRepo;
	
	@InjectMocks 
	ProduitServiceImpl ProduitService;
	
	 @Before
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }
	
	@Test
	public void testAddProduit() {
	//	List<Stock> stocks = stockService.retrieveAllStocks();
	//	int expected=stocks.size();
		Produit s = new Produit("produit test",10,"100f");
		Produit savedStock= ProduitService.addProduit(s);
		
	//	assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleProduit());
		ProduitService.deleteProduit(savedStock.getIdProduit());
		
	} 
	
	 @Test
	    public void testGetAllProduit() {
	        List<Produit> toDoList = new ArrayList<Produit>();
	        toDoList.add(new Produit("produit test2",101,"100f7"));
	        toDoList.add(new Produit("produit test3",102,"100f6"));
	        toDoList.add(new Produit("produit test4",104,"100fd"));
	        when(ProduitRepo.findAll()).thenReturn(toDoList);

	        List<Produit> result = ProduitService.retrieveAllProduits();
	        assertEquals(3, result.size());
	    }
	 
	 @Test
		public void testDeleteStock() {
		 Produit s = new Produit("produit test2",101,"100f7");
		 Produit savedStock= ProduitService.addProduit(s);
		 ProduitService.deleteProduit(savedStock.getIdProduit());
			assertNull(ProduitService.retrieveProduit(savedStock.getIdProduit()));
		}

	
	
}
