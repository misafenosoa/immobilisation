<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="main.DAO.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String profile = request.getParameter("profile");
    String fois = request.getParameter("fois");
        try {
            Formuletauxhoraire.insert(profile,fois);
            response.sendRedirect("FormuleTauxHoraire.jsp?message=" + URLEncoder.encode("TypeRoute inserted successfully!", "UTF-8"));
        } catch (Exception e ) {
            response.sendRedirect("FormuleTauxHoraire.jsp?error=" + URLEncoder.encode("Error inserting TypeRoute: " + e.getMessage(), "UTF-8"));
        }
 
%>