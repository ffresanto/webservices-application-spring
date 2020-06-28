package com.estudo.webservices.service;

import com.estudo.webservices.entity.User;
import com.estudo.webservices.repository.UserRepository;
import com.estudo.webservices.service.exceptions.DatabaseException;
import com.estudo.webservices.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service //CLASSE PARA SUAS REGRAS DE NEGOCIO
public class UserService {

    @Autowired
    private UserRepository repository;

    //LISTA TODOS OS USUARIOS
    public List<User> findAll(){
        return repository.findAll();
    }

    //BUSCA USUARIO POR ID
    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //CRIA USUARIO
    public User insert(User obj){
        return repository.save(obj);
    }

    //DELETA USUARIO
    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    //ALTERA DADOS DO USUARIO
    public User update(Long id, User obj){
        try {
            User entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    //METODO PARA ESCOLHER QUAL CAMPO PODE SER ALTERADO
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
