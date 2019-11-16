package br.com.servlet;

import br.com.entidade.Usuario;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author tay_m
 */
public class AppServlet extends HttpServlet {
    List<Usuario> listUsuario = new ArrayList<>();    
    
    public AppServlet(){
        inserirAdmin();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {       
        String acao = req.getParameter("acao");
 
        if(acao.equals("cadUsuario")){
            Usuario u = new Usuario();
            String senha = req.getParameter("txtSenha");
            String conf = req.getParameter("txtConfSenha");            
            if(senha.equals(conf)){
                u.setLogin(req.getParameter("txtLogin"));
                u.setSenha(senha);  
                u.setAdmin(false);
                listUsuario.add(u); 
                log("Usuário cadastrado com sucesso");
            } 
            else{
                log("A senha e a confirmação da senha não conferem.");
            }
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.forward(req, resp);
        }
        if(acao.equals("login")){
            boolean logado = false; 
            String senha = req.getParameter("txtSenha");
            String login = req.getParameter("txtLogin");
            
            //fazer uma sessão httpsession
            HttpSession session = req.getSession(false);  
            //String n = (String)session.getAttribute(login);  
            
            //usar getEncoder para criptografar
            
            for (Usuario usu: listUsuario) {
                if(usu.getLogin().equals(login) && usu.getSenha().equals(senha)){
                    logado = true;
                    //session = req.getSession(true); 
                }
            }            
            if(logado){
                RequestDispatcher rd = req.getRequestDispatcher("alunoServlet?acao=listarAlunos");
                rd.forward(req, resp);
            }
            else{
                RequestDispatcher rd = req.getRequestDispatcher("index.html");
                rd.forward(req, resp);
                log("Usuário inesistente ou senha incorreta");
            }
        }              
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    public void inserirAdmin(){
        Usuario usu = new Usuario();
        usu.setAdmin(true);
        usu.setLogin("adm");
        usu.setSenha("123");
        listUsuario.add(usu);
    }
    
}
