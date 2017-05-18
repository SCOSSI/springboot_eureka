package stub;

import br.com.springbootapp.model.entity.Livro;

/**
 * Created by ilegra000034 on 04/05/2017.
 */
public class LivroStub {

    public static Livro buildLivro(){
        Livro livro = new Livro();
        livro.setId(1L);
        livro.setNome("O Senhor dos Anéis");
        livro.setAutor("Tolkien");
        return  livro;
    }
}
