package org.sid;

import java.util.List;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class MyCatalogueApplication implements CommandLineRunner {
	@Autowired
    private ProduitRepository produitRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(MyCatalogueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		produitRepository.save(new Produit(null,"Ordinateur Lx 45",6700,3));
		produitRepository.save(new Produit(null,"Imprimante HP",1700,3));
		produitRepository.save(new Produit(null,"Smart Phone Samsung S9 ",8000,13));
        Page<Produit> produits=produitRepository.findByDesignationContains("H",PageRequest.of(0, 2));
        System.out.println(produits.getSize());
        System.out.println(produits.getTotalElements());
        System.out.println(produits.getTotalPages());     
		produits.getContent().forEach(p->{
			System.out.println(p.toString());
		});
		System.out.println("----------------------");
		Page<Produit> prods=produitRepository.chercher("%H%",400,PageRequest.of(0, 2));
        System.out.println(prods.getSize());
        System.out.println(prods.getTotalElements());
        System.out.println(prods.getTotalPages());     
		prods.getContent().forEach(p->{
			System.out.println(p.toString());
		});
	}

}
