/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.edu.iftm.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danilo
 */
@WebServlet(name = "ImagemServlet", urlPatterns = {"/documento"}, initParams = {
    @WebInitParam(name="nome", value="")
})
public class ImagemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String nome = request.getParameter("nome");
	if(nome == null || "".equals(nome)){
	    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    return;
	}
	File documento = new File("/home/danilo/Documentos/aula_pw2/", nome);
	byte[] byteDocumento = fileToByteArray(documento);
	response.setContentType("application/pdf");
	response.setContentLengthLong(documento.length());
	response.getOutputStream().write(byteDocumento);
    }
    
    public static byte[] fileToByteArray(File file) {

        if (file == null || !file.exists()) {
            System.out.println("Arquivo informado não existe.");
            return null;
        }
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = new FileInputStream(file);
            long length = file.length();
            bytes = new byte[(int) length];
            int offset = 0, n = 0;
            while (offset < bytes.length && (n = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += n;
            }
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(ImagemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(ImagemServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bytes;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
