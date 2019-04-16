/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DaoAlugadosImpl;
import dao.DaoVeiculoImpl;
import entities.Veiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JsonUtil;

/**
 *
 * @author caroline
 */
@WebServlet(name = "Veiculos", urlPatterns = {"/Veiculos"})
public class Veiculos extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DaoVeiculoImpl dao = new DaoVeiculoImpl();
        List<Veiculo> veiculos = dao.listaVeiculo();

        
        //VeiculosResponse veiculosResponse = new VeiculosResponse();
         JsonUtil.jsonGenerator(response, veiculos);
    }

}
