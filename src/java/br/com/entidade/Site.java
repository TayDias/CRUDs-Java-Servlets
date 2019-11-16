
package br.com.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tay_m
 */
public class Site {
    List<Aluno> listAlunos = new ArrayList<>();    
    List<Disciplina> listDisciplina = new ArrayList<>(); 
    
    public Site(){
        inserirDisciplinas();
    }
    public List<Aluno> getAlunos(){
        return listAlunos;
    }
    public List<Disciplina> getDisciplinas(){
        return listDisciplina;
    }
    public void addAluno(Aluno a){
        listAlunos.add(a);
    }
    
     public void inserirDisciplinas(){
        Disciplina dis = new Disciplina();
        dis.setCodigo(1);
        dis.setNome("Calculo 1");
        listDisciplina.add(dis);
        dis = new Disciplina();
        dis.setCodigo(2);
        dis.setNome("Calculo 2");
        listDisciplina.add(dis);
        dis = new Disciplina();
        dis.setCodigo(3);
        dis.setNome("Algoritimos 1");
        listDisciplina.add(dis);
        dis = new Disciplina();
        dis.setCodigo(4);
        dis.setNome("Algoritimos 2");
        listDisciplina.add(dis);
    }
}
