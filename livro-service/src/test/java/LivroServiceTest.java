import br.com.springbootapp.model.entity.Livro;
import br.com.springbootapp.repository.LivroRepository;
import br.com.springbootapp.service.LivroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import stub.LivroStub;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LivroServiceTest {

    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    private Livro livroStub;

    @Before
    public void setup(){
        livroService = new LivroService(livroRepository);
        livroStub = LivroStub.buildLivro();
    }

    @Test
    public void testCreateLivro(){
        when(livroRepository.create(any(Livro.class))).thenReturn(LivroStub.buildLivro());

        Livro livro = livroService.createLivro(livroStub);

        assertEquals(livroStub.getAutor(), livro.getAutor());
        assertEquals(livroStub.getNome(), livro.getNome());
        assertEquals(livroStub.getId(), livro.getId());
    }

    @Test
    public void testFindAllLivros(){
        List<Livro> livrosFake = new ArrayList<>();
        livrosFake.add(livroStub);
        livrosFake.add(livroStub);

        when(livroRepository.findAll()).thenReturn(livrosFake);

        List<Livro> livros = livroService.findAllLivros();
        assertEquals(livrosFake.size(), livros.size());
    }

    @Test
    public void testFindLivroById(){
        when(livroRepository.findLivroById(1)).thenReturn(livroStub);
        Livro livro = livroService.findLivroById(1);

        assertEquals(livroStub.getAutor(), livro.getAutor());
        assertEquals(livroStub.getNome(), livro.getNome());
        assertEquals(livroStub.getId(), livro.getId());
    }

}
