import br.com.springbootapp.controller.LivroController;
import br.com.springbootapp.model.entity.Livro;
import br.com.springbootapp.service.LivroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import stub.LivroStub;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class LivroControllerTest {

    @Mock
    private LivroService livroService;

    @InjectMocks
    private LivroController livroController;

    private Livro livroStub;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(livroController);
        mockMvc = MockMvcBuilders.standaloneSetup(livroController).build();
        livroStub = LivroStub.buildLivro();
    }

    @Test
    public void testListAllLivros() throws Exception {

        List<Livro> livroList = new ArrayList<>();
        livroList.add(livroStub);
        livroList.add(livroStub);
        when(livroService.findAllLivros()).thenReturn(livroList);
        mockMvc.perform(get("/livro/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)));


    }

    @Test
    public void testListAllLivrosNoContent() throws Exception {
        when(livroService.findAllLivros()).thenReturn(new ArrayList<Livro>());
        mockMvc.perform(get("/livro/"))
                .andExpect(status().isNoContent());
    }
}
