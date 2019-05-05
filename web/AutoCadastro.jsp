<%-- 
    Document   : AutoCadastro
    Created on : 05/05/2019, 20:08:15
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AutoCadastro" 
                 method="POST"><br/>
               Nome: <input type="text" name="nome" /> <br/>
               E-mail: <input type="text" name="email" /> <br/>
               Senha: <input type="text" name="senha" /> <br/>
               <input type="reset" value="Limpar"/> <br/>
               <input type="submit" value="Salvar"/> <br/>
           </form>           
    </body>
</html>
