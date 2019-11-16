package br.com.servlet;

import br.com.entidade.Site;
import br.com.entidade.Aluno;
import br.com.entidade.Disciplina;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author tay_m
 */
public class AlunoServlet extends HttpServlet {
    Site site;
    
    public AlunoServlet(){
        site = new Site();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {       
        String acao = req.getParameter("acao");
     
        if (acao.equals("cadAluno")) {
            Aluno a;
            a = new Aluno();
            try {
                a.setNome(req.getParameter("txtNome"));
                a.setIdade(Integer.parseInt(req.getParameter("txtIdade")));
                a.setSexo(req.getParameter("sexo"));     
                a.setCodigoCadastro(site.getAlunos().size()+1);
                
                for (int i = 0; i < 5; i++) {
                    String disc = req.getParameter(("txtDisciplina"+i));
                    if(disc != null){
                        Disciplina d = new Disciplina();
                        d.setCodigo(i);
                        d.setNome(disc);
                        a.addDisciplina(d);
                    }
                }

                site.addAluno(a); 
                
            } catch (NullPointerException e) {
                log("É necessário preencher todos os campos");
            }           
            RequestDispatcher rd = req.getRequestDispatcher("alunoServlet?acao=listarAlunos");
            rd.forward(req, resp);
        }
        
        if (acao.equals("listarAlunos")) {
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cadastro de Aluno</title>\n" +
                "    <link rel=\"canonical\" href=\"https://getbootstrap.com/docs/4.3/examples/cover/\">\n" +
                "    <!-- Bootstrap Core CSS -->\n" +
                "    <link href=\"css/style.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"/docs/4.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">"+
                "</head>\n" +
                "<body class=\"text-center\">\n" +
                "   <div class=\"cover-container d-flex w-100 h-100 p-3 mx-auto flex-column\">\n"+
                "       <div class=\"grid-container\">\n"+
                "           <form class=\"mastfoot mt-auto\" action=\"alunoServlet?acao=listarAlunos\">\n" +
                "           <h3>Lista de Alunos:</h3>\n" +
                "           <table>\n" +
                "               <tr>\n" +
                "                   <th>Codigo</th>\n" +    
                "                   <th>Nome</th>\n" +
                "                   <th>Idade</th>\n" +
                "                   <th>Sexo</th>\n" +
                "                   <th>Disciplinas</th>\n" +
                "               </tr>\n");
                    for (Aluno aluno : site.getAlunos()) {
                    out.println(
                    "            <tr>\n" +
                    "                <td>" + aluno.getCodigoCadastro()+ "</td>\n" +       
                    "                <td>" + aluno.getNome() + "</td>\n" +
                    "                <td>" + aluno.getIdade() + "</td>\n" +
                    "                <td>" + aluno.getSexo() + "</td>\n" +
                    "                <td>\n");
                    
                        List<Disciplina> listD = aluno.getListDisciplinas();                       
                        if(!listD.isEmpty()){
                            for (Disciplina d : listD) {
                                out.println(
                                    d.getNome()+ "<br />");
                            }
                        }else{
                            out.println("-");
                        } 
                    //out.println(
                    //"     <a href=\"alunoServlet?acao=listarDiscip?codigo="+aluno.getCodigoCadastro()+"\">Disciplinas</a>\n" +
                }out.println(
                "                   </td>" +
                "               </tr>\n" +
                "           </table>\n" +
                "       </form><br />" +
                "       <form class=\"mastfoot mt-auto\" action=\"alunoServlet\">\n" +
                "           <h3>Cadastrar Aluno:</h3>" +
                "           <h4>Nome: <input type=\"text\" name=\"txtNome\"></h4>" + 
                "           <h4>Idade: <input type=\"number\" name=\"txtIdade\" min=\"0\" max=\"120\"></h4>" +
                "           <h4>Sexo:\n" +
                "               <input type=\"radio\" name=\"sexo\" value=\"Feminino\"> Feminino\n" +
                "               <input type=\"radio\" name=\"sexo\" value=\"Masculino\"> Masculino\n" +
                "               <input type=\"radio\" name=\"sexo\" value=\"Nenhum\"> Nenhum\n"+
                "           </h4>" +
                "           <h4> Disciplinas: <br>");
                    for (Disciplina d : site.getDisciplinas()) {
                        out.println(
                "               <input type = \"checkbox\" name= \"txtDisciplina"+d.getCodigo()+"\" value = \""+d.getNome()+"\">\n" +
                "               <label for = \"txtDisciplina"+d.getCodigo()+"\"> "+d.getNome()+" </ label><br>\n");
                    }out.println(
                "           </h4>\n" +
                "           <br />\n" +
                "           <input type=\"hidden\" name=\"acao\" value=\"cadAluno\">\n" +
                "           <input class=\"btn\" type=\"submit\" value=\"Cadastrar\">\n" +
                "       </ div>\n" +
                "    </form>\n" + 
                "   </div>\n"+
                "   </body>\n" +     
                "</html>");
        }
        
//        if (acao.equals("listarDiscip")) {           
//            Aluno aluno = new Aluno();
//            int cod = 0;
//            cod = Integer.parseInt(req.getParameter("codigo"));
//            
//            for (Aluno a: listAlunos) {
//                if(a.getCodigoCadastro() == cod){
//                    aluno = a;
//                }
//            }
//            List<Disciplina> listD = aluno.getListDisciplinas();
//            
//            PrintWriter out = resp.getWriter();
//                     out.println("<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Cadastro de Disciplinas</title>\n" +
//                "    <link rel=\"canonical\" href=\"https://getbootstrap.com/docs/4.3/examples/cover/\">\n" +
//                "    <!-- Bootstrap Core CSS -->\n" +
//                "    <link href=\"css/style.css\" rel=\"stylesheet\">\n" +
//                "    <link href=\"/docs/4.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">"+
//                "</head>\n" +
//                "<body class=\"text-center\">\n" +
//                "    <form action=\"alunoServlet?acao=listarDiscip?codigo="+aluno.getCodigoCadastro()+"\"> " +
//                "       <h3>Lista de Disciplinas:</h3>" +
//                "       <h4>Aluno: "+aluno.getNome()+"</h4>" +
//                "       <table>\n" +
//                "            <tr>\n" +
//                "                <th>Id</th>\n" +
//                "                <th>Nome</th>\n" +
//                "            </tr>\n");
//                if(!listD.isEmpty()){
//                    for (Disciplina d : listD) {
//                    out.println(
//                    "        <tr>\n" +
//                    "            <td>" + d.getCodigo()+ "</td>\n" +
//                    "            <td>" + d.getNome()+ "</td>\n" +
//                    "        </tr>\n");
//                    }
//                }else{
//                    out.println(
//                    "        <tr>\n" +
//                    "            <td>-</td>\n" +
//                    "            <td>-</td>\n" +
//                    "        </tr>\n");
//                }
//                out.println(
//                "        </table>\n" +
//                "     </form>\n" +
//                "   </body>\n" +     
//                "</html>");
//        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
}
