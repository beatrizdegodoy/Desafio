/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backcadastro;

import cadastrodao.CadastroInterface;
import cadastrodao.CadastroRelacional;
import cadastrodao.ConexaoInterface;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Beatriz
 */
public class Recurso {
    private String url;
    private String driverJdbc;
    private String user;
    private String password;
    
    private CadastroInterface dao;

    public Recurso(String url, String driverJdbc, String user, String password) {
        this.url = url;
        this.driverJdbc = driverJdbc;
        this.user = user;
        this.password = password;
        
        ConexaoInterface con = new ConexaoJavaDb(url,driverJdbc,user,password);
        
        dao = new CadastroRelacional(con);
    
    }

    @GET
    @Path("/atletas")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response buscarAtletas() {
        List<Atleta> atletas;
        atletas = dao.obterTodos();
        return Response.ok(atletas).build();
    }
    
    @GET
    @Path("/atleta/{nome}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response buscarNome(@PathParam("nome") String id) {       
        Atleta atleta;
        atleta = dao.search(Long.parseLong(id));
        return Response.ok(atleta).build();
    }
    
    
    @DELETE
    @Path("/atleta/{nome}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deletarAtleta(@PathParam("nome") String id) {       
        int resp;        
        resp = dao.delete(Long.parseLong(id));
        return Response.ok(resp).build();
                      
    }
    
    @POST
    @Path("/atleta")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    //@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response inserirAtleta(Atleta atleta) {   
        dao.insert(atleta);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/atleta")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    //@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response atualizarAtleta(Atleta atleta) {   
        dao.update(atleta);
        return Response.ok().build();               
    }

    
}

