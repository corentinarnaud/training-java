package com.excilys.arnaud.persistance;

import com.excilys.arnaud.model.metier.Company;
import com.excilys.arnaud.model.metier.CompanyList;

import java.util.Optional;

public interface CompanyDAO extends DAO{

  public Optional<Company> findById(long id) throws DAOException;

  public Optional<Company> findByName(String name) throws DAOException;

  public CompanyList getCompanies() throws DAOException;
  
  public CompanyList getNCompanies(int begin, int nbCompanies) throws DAOException;
  
  public int getNumberOfCompany();
  
  public boolean delCompany(long id);

}
