package br.com.springbootapp.service;

import br.com.springbootapp.repository.LivroRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ilegra000034 on 04/05/2017.
 */
@Configuration
public class ModuleService {

    @Bean
    public LivroService livroService(LivroRepository livroRepository){
        return new LivroService(livroRepository);
    }
}
