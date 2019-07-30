package cadastrodao;

import java.util.List;

/**
 *
 * @author Beatriz
 */
public interface CadastroInterface {
    List<Atleta> getAtletas();
    int insert(Atleta atleta);
    int update(Atleta atleta);
    int delete(String nome);
    Atleta search(String nome);
    
}
