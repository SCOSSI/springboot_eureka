package br.com.springbootapp.model.mapper;

import br.com.springbootapp.model.entity.Livro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ilegra000034 on 03/05/2017.
 */
public class LivroRowMapper implements RowMapper<Livro> {
    @Override
    public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
        Livro livro = new Livro();
        livro.setId(rs.getLong("id"));
        livro.setNome(rs.getString("name"));
        livro.setAutor(rs.getString("autor"));

        return livro;
    }
}
