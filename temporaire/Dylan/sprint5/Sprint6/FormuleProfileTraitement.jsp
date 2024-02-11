<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="main.DAO.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String min = request.getParameter("min");
    String max = request.getParameter("max");
    String profile = request.getParameter("profile");
        try {
            FormuleProfil.insert(min,max,profile);

            response.sendRedirect("FormuleProfile.jsp?message=" + URLEncoder.encode("TypeRoute inserted successfully!", "UTF-8"));
        } catch (Exception e ) {
            response.sendRedirect("FormuleProfile.jsp?error=" + URLEncoder.encode("Error inserting TypeRoute: " + e.getMessage(), "UTF-8"));
        }
 
%>