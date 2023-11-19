package cidade;

public class Cidade {
    private String nomeCity;
    private double porcentagemVacina;
    private int numCasos;
    public Cidade(String nomeCity, double porcentagemVacina, int numCasos) {
        this.nomeCity = nomeCity;
        this.porcentagemVacina = porcentagemVacina;
        this.numCasos = numCasos;
    }
    public String getNomeCity() {
        return nomeCity;
    }
    public void setNomeCity(String nomeCity) {
        this.nomeCity = nomeCity;
    }
    public double getPorcentagemVacina() {
        return porcentagemVacina;
    }
    public void setPorcentagemVacina(double porcentagemVacina) {
        this.porcentagemVacina = porcentagemVacina;
    }
    public int getNumCasos() {
        return numCasos;
    }
    public void setNumCasos(int numCasos) {
        this.numCasos = numCasos;
    }

    

}
