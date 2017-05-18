package br.com.springbootapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ModuleRepository {
    @Bean
    public LivroRepository livroRepository(@Autowired JdbcTemplate jdbcTemplate){
        return new LivroRepository(jdbcTemplate);
    }
}
