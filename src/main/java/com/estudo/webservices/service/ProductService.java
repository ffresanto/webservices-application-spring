package com.estudo.webservices.service;

import com.estudo.webservices.entity.Product;
import com.estudo.webservices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service //Classe para regra de negocio
public class ProductService {

    @Autowired
    private ProductRepository repository;

    //Listar todos os usuarios
    public List<Product> findAll(){
        return repository.findAll();
    }

    //Buscar Product por Id
    public Product findById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }
}
