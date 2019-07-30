package cadastrodao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Beatriz
 */
public class ConexaoJavaDb implements ConexaoInterface{
        private String url;
    private String usuario;
    private String senha;
    private String driverJdbc;
    private Connection connection;

    public ConexaoJavaDb() { }

    public ConexaoJavaDb(String url, String driverJdbc, String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
        this.url = url;
        this.driverJdbc = driverJdbc;
    }

    public Connection getConnection(){
        if (connection == null) {
            try {
                Class.forName(driverJdbc);
                connection = DriverManager.getConnection(url, usuario, senha);
            } catch (ClassNotFoundException | SQLException ex) {
            }
        }
        return connection;
    }

    public void close(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {}
        }
    }
    
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}

    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public String getDriverJdbc() {return driverJdbc;}
    public void setDriverJdbc(String driverJdbc) {this.driverJdbc = driverJdbc;}

}
