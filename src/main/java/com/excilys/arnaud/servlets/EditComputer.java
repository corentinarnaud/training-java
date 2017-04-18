package com.excilys.arnaud.servlets;

import com.excilys.arnaud.mapper.MapperException;
import com.excilys.arnaud.model.dto.CompanyDto;
import com.excilys.arnaud.model.dto.CompanyDtoList;
import com.excilys.arnaud.model.dto.ComputerDto;
import com.excilys.arnaud.service.CompanyService;
import com.excilys.arnaud.service.ComputerService;
import com.excilys.arnaud.service.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {

  /**
   * serialVersionUID.
   * 
   */
  private static final long serialVersionUID = -6942829491462214116L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String idString = request.getParameter("id");
    long id = 0;

    if (idString == null) {
      this.getServletContext().getRequestDispatcher("/views/404.jsp").forward(request, response);
    }
    try {
      id = Long.valueOf(idString);
    } catch (NumberFormatException e) {
      this.getServletContext().getRequestDispatcher("/views/404.jsp").forward(request, response);
      return;
    }

    CompanyDtoList companyList = CompanyService.COMPANYSERVICE.getCompanyList();
    request.setAttribute("listCompany", companyList);

    Optional<ComputerDto> computerDto = ComputerService.COMPUTERSERVICE.findById(id);
    if (!computerDto.isPresent()) {
      this.getServletContext().getRequestDispatcher("/views/404.jsp").forward(request, response);
      return;
    }
    request.setAttribute("computer", computerDto.get());

    this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    String idString = request.getParameter("id");
    String name = request.getParameter("computerName");
    String introducedString = request.getParameter("introduced");
    String discontinuedString = request.getParameter("discontinued");
    String companyIDString = request.getParameter("companyId");
    CompanyDto company = null;

    
    if (name != null && !name.equals("")) {
      

      try {
        
        if (companyIDString != null && !companyIDString.isEmpty() && !companyIDString.equals("0")) {
          Optional<CompanyDto> opt = 
              CompanyService.COMPANYSERVICE.findById(Long.parseLong(companyIDString));
          company = opt.isPresent() ? opt.get() : null;
        }
      
        ComputerService.COMPUTERSERVICE.update(
            new ComputerDto(idString, name, company, introducedString, discontinuedString));
      } catch (ServiceException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (MapperException e) {
        e.printStackTrace();
      }
    }
    response.sendRedirect("/ComputerDatabase/dashboard");
  }

}
