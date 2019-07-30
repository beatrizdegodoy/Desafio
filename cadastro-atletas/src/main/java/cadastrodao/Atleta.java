package cadastrodao;

/**
 *
 * @author Beatriz
 */
public class Atleta {
    private String nome, status;
    private float peso, altura, imc;

    public Atleta(String nome, float peso, float altura, String status){
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.status = status;
    }
    
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}

    public float getAltura() {return altura;}
    public void setAltura(float altura) {this.altura = altura;}

    public float getImc() {return imc;}
    public void setImc() {this.imc = peso / (altura * altura);}
    
}
