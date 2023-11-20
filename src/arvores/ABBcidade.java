package arvores;

import cidade.Cidade;

public class ABBcidade {

    private class ARVORE {
        Cidade dado;
        ARVORE esq, dir;

        public ARVORE(Cidade dado) {
            this.dado = dado;
            this.esq = null;
            this.dir = null;
        }
    }

    public ARVORE root = null;

    public int comparisons = 0;

    public ARVORE inserir(ARVORE p, Cidade cidade) {
        if (p == null) {
            return new ARVORE(cidade);
        } else {
            comparisons++; // Increment comparison counter
            if (cidade.getPorcentagemVacina() < p.dado.getPorcentagemVacina()) {
                p.esq = inserir(p.esq, cidade);
            } else {
                p.dir = inserir(p.dir, cidade);
            }
            return p;
        }
    }

    public boolean consulta(ARVORE p, String nomeCidade) {
        if (p == null) {
            return false;
        } else {
            if (nomeCidade.equals(p.dado.getNomeCity())) {
                return true;
            } else if (nomeCidade.compareToIgnoreCase(p.dado.getNomeCity()) < 0) {
                return consulta(p.esq, nomeCidade);
            } else {
                return consulta(p.dir, nomeCidade);
            }
        }
    }

    public ARVORE removeCidade(ARVORE p, String nomeCidade) {
        if (p == null) {
            return null;
        } else {
            if (nomeCidade.compareToIgnoreCase(p.dado.getNomeCity()) == 0) {
                if (p.esq == null) {
                    return p.dir;
                } else if (p.dir == null) {
                    return p.esq;
                } else {
                    ARVORE aux, ref;
                    ref = p.dir;
                    aux = p.dir;
                    while (aux.esq != null) {
                        aux = aux.esq;
                    }
                    aux.esq = p.esq;
                    return ref;
                }
            } else {
                if (nomeCidade.compareToIgnoreCase(p.dado.getNomeCity()) < 0) {
                    p.esq = removeCidade(p.esq, nomeCidade);
                } else {
                    p.dir = removeCidade(p.dir, nomeCidade);
                }
            }
        }
        return p;
    }


    public void listaEmOrdem(ARVORE p) {
        if (p != null) {
            listaEmOrdem(p.esq);
            System.out.println("City: " + p.dado.getNomeCity() +
                    ", Vaccination Coverage: " + p.dado.getPorcentagemVacina() +
                    ", Number of Cases: " + p.dado.getNumCasos());
            listaEmOrdem(p.dir);
        }
    }
}