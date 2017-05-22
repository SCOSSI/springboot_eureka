package br.com.springbootapp.repository;

import br.com.springbootapp.model.entity.Livro;
import br.com.springbootapp.model.mapper.LivroRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by ilegra000034 on 03/05/2017.
 */
@Repository
public class LivroRepository {
    private JdbcTemplate jdbcTemplate;

    public LivroRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly=true)
    public List<Livro> findAll() {
        return jdbcTemplate.query("select * from livros",
                new LivroRowMapper());
    }

    public Livro findLivroById(long id) {
        return jdbcTemplate.queryForObject(
                "select * from livros where id=?",
                new Object[]{id}, new LivroRowMapper());
    }


    public Livro create(final Livro livro){
        final String sql = "insert into livros(name,autor) values(?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(getPreparedStatementCreateLivro(livro, sql), holder);

        long newLivroId = holder.getKey().intValue();
        livro.setId(newLivroId);
        return livro;
    }

    private PreparedStatementCreator getPreparedStatementCreateLivro(Livro livro, String sql) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, livro.getNome());
            ps.setString(2, livro.getAutor());
            return ps;
        };
    }

    public void updateLivro(Livro livro) {
        String query = "UPDATE livros SET name=?,autor=? WHERE id=?";
        jdbcTemplate.update(query,
                new Object[] {
                        livro.getNome(), livro.getAutor(), livro.getId()
                });

    }

    public int deleteLivro(long id) {
        String query = "DELETE from livros where id=?";
        return jdbcTemplate.update(query, new Object[] { id });

    }

}
