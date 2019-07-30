/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backcadastro;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuracao extends io.dropwizard.Configuration{

    @JsonProperty 
    private String url;
    @JsonProperty
    private String diverJdbc;
    @JsonProperty
    private String user;
    @JsonProperty
    private String password;

    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}

    public String getDiverJdbc() {return diverJdbc;}
    public void setDiverJdbc(String diverJdbc) {this.diverJdbc = diverJdbc;}

    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}

