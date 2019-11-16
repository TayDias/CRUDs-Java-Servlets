/**
 *
 * @author tay_m
 */
package br.com.entidade;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private int codigoCadastro;
    private String nome;
    private int idade;
    private String sexo;
    private List<Disciplina> listDisciplinas;

    public Aluno() {
        listDisciplinas = new ArrayList<>();
    }
    public Aluno(String nome, String idade) {
        this.setNome(nome);
        this.setIdade(idade);
    }

    public int getCodigoCadastro() {
        return codigoCadastro;
    }

    public void setCodigoCadastro(int codigoCadastro) {
        this.codigoCadastro = codigoCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.setIdade(Integer.parseInt(idade));
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Disciplina> getListDisciplinas() {
        return listDisciplinas;
    }
    
    public void addDisciplina(Disciplina d){
        listDisciplinas.add(d);
    }
    
    public void removeDisciplina(Disciplina d){
        listDisciplinas.remove(d);
    }
}
