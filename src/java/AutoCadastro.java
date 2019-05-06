import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/AutoCadastro"})
public class AutoCadastro extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        
         if (usuario == null || usuario.isEmpty()) {
            session.setAttribute("message","Usuario vazio");
            RequestDispatcher rd;
            rd = getServletContext().
                    getRequestDispatcher("/error.jsp");
                        rd.forward(request,response);
        }
         else if (senha == null || senha.isEmpty()) {
            session.setAttribute("message","Senha vazia");
            RequestDispatcher rd = getServletContext().
                                            getRequestDispatcher("/error.jsp");
                                    rd.forward(request,response);          
        }
        else if (email == null || email.isEmpty()) {
            session.setAttribute("message","Email vazio");
            RequestDispatcher rd = getServletContext().
                                            getRequestDispatcher("/error.jsp");
                                    rd.forward(request,response);          
        }
         else{
             // Criar DAO para autoCadastro
         }
        }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
                processRequest(request, response);
     }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        }
    }