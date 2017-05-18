package br.com.springbootapp.controller;

import br.com.springbootapp.model.entity.Livro;
import br.com.springbootapp.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ilegra000034 on 03/05/2017.
 */

@RestController
@RequestMapping(value = "/livro/")
public class LivroController {

    @Autowired
    LivroService livroService;


    @RequestMapping("/getLivro")
    public String getLivro() {
        return "livro teste";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> listAllLivros() {
        List<Livro> livros = livroService.findAllLivros();
        if(livros.isEmpty()){
            return new ResponseEntity<List<Livro>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Livro>>(livros, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Livro> getLivro(@PathVariable("id") long id) {
        System.out.println("Fetching Livro with id " + id);
        try {
            Livro livro = livroService.findLivroById(id);
            return new ResponseEntity<Livro>(livro, HttpStatus.OK);
        }catch (EmptyResultDataAccessException ex){
            System.out.println(ex.getMessage());
            System.out.println("Livro with id " + id + " not found");
            return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
         livroService.createLivro(livro);
        return new ResponseEntity<Livro>(livro, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateLivro(@RequestBody Livro livro) {
        livroService.updateLivro(livro);
        return new ResponseEntity( HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public  ResponseEntity deleteLivro(@RequestBody long id){
        livroService.deleteLivroById(id);
        return new ResponseEntity(HttpStatus.OK);
    }



}
