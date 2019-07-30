
package cadastrodao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beatriz
 */
public class CadastroRelacional implements CadastroInterface{

    private PreparedStatement stmGetAll;
    private PreparedStatement stmInsert;
    private PreparedStatement stmDelete;
    private PreparedStatement stmUpdate;
    private PreparedStatement stmSearch;

    public CadastroRelacional(ConexaoInterface conexao) {
        Connection connection = conexao.getConnection();
        String sql = "";
        try {
            sql = "SELECT nome, peso, altura, status FROM atletas";
            stmGetAll = connection.prepareStatement(sql);
            sql = "INSERT INTO atletas VALUES (?,?,?,?)";
            stmInsert = connection.prepareStatement(sql);
            sql = "DELETE FROM atletas WHERE nome=?";
            stmDelete = connection.prepareStatement(sql);
            sql = "UPDATE atletas SET status=? WHERE nome=?";
            stmUpdate = connection.prepareStatement(sql);
            sql = "SELECT nome, peso, altura, status FROM atletas where nome=?";
            stmSearch = connection.prepareStatement(sql);
            
        } catch (SQLException ex) {}
    }
    
    @Override
    public List<Atleta> getAtletas() {
        List<Atleta> atletas = new ArrayList<>();
        try {
            ResultSet resultados = stmGetAll.executeQuery();
            while (resultados.next()) {
                Atleta c = new Atleta(resultados.getString("nome"), 
                        resultados.getFloat("peso"),
                        resultados.getFloat("altura"),
                        resultados.getString("status"));
                atletas.add(c);
            }
        } catch (SQLException ex) {}
        return atletas;
    }

    @Override
    public int insert(Atleta novoAtleta) {
        int ret = -1;
        try {
            stmInsert.setString(1, novoAtleta.getNome());
            stmInsert.setFloat(2, novoAtleta.getAltura());
            stmInsert.setFloat(3, novoAtleta.getPeso());
            stmInsert.setString(4, novoAtleta.getStatus());
            ret = stmInsert.executeUpdate();
        } catch (SQLException ex) {}
        return ret;
    }

    @Override
    public int update(Atleta atleta) {
        int ret = -1;
        try {
            stmUpdate.setString(1, atleta.getStatus());
            ret = stmUpdate.executeUpdate();
        } catch(SQLException ex) {}
        return ret;
    }

    @Override
    public int delete(String nome) {
        int ret = -1;
        try {
            stmDelete.setString(1,nome);
            ret = stmDelete.executeUpdate();
        } catch (SQLException ex) {
        }
        return ret;
    }

    @Override
    public Atleta search(String nome) {
        Atleta atleta = null;
        try {
            stmSearch.setString(1, nome);
            ResultSet resultados = stmSearch.executeQuery();
            if (resultados.next()) {
              atleta = new Atleta(resultados.getString("nome"),
                      resultados.getFloat("peso"),
                      resultados.getFloat("altura"),
                      resultados.getString("status") );
            }
        } catch (SQLException ex) {}
        return atleta;
    }    
}