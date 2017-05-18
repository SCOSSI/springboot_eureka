package br.com.springbootapp.service;

import br.com.springbootapp.model.entity.Livro;
import br.com.springbootapp.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ilegra000034 on 03/05/2017.
 */

public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @Transactional
    public Livro createLivro(Livro livro){
        return livroRepository.create(livro);
    }

    public  List<Livro> findAllLivros(){
        return livroRepository.findAll();
    }

    public  Livro findLivroById(long id){
        return livroRepository.findLivroById(id);
    }
    @Transactional
    public void updateLivro(Livro livro){
        livroRepository.updateLivro(livro);
    }
    @Transactional
    public void deleteLivroById(long id){
        livroRepository.deleteLivro(id);
    }




}
