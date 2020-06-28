package com.estudo.webservices.service;

import com.estudo.webservices.entity.Category;
import com.estudo.webservices.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service //Classe para regra de negocio
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //Listar todos os usuarios
    public List<Category> findAll(){
        return repository.findAll();
    }

    //Buscar Category por Id
    public Category findById(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }
}
