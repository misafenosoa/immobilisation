<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="main.DAO.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String nom = request.getParameter("nom");
    String date = request.getParameter("date");
    String adresse = request.getParameter("adresse");
    String experience = request.getParameter("experience");
        try {
            Personnel.insert(experience,nom,date,adresse);
            response.sendRedirect("InsertionPersonnel.jsp?message=" + URLEncoder.encode("TypeRoute inserted successfully!", "UTF-8"));
        } catch (Exception e ) {
            response.sendRedirect("InsertionPersonnel.jsp?error=" + URLEncoder.encode("Error inserting TypeRoute: " + e.getMessage(), "UTF-8"));
        }
 
%>