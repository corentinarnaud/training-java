package com.excilys.arnaud.persistance;

import com.excilys.arnaud.model.metier.Computer;
import com.excilys.arnaud.model.metier.ComputerList;
import com.excilys.arnaud.persistance.exception.DAOException;

import java.util.Optional;


public interface ComputerDAO{

  /** .
   * @param computer : Computer to add
   * @return int : the auto-generated id
   * @throws DAOException : if the connection fail
   */
  public long add(Computer computer) throws DAOException;

  /** .
   * @param computer : Computer to update
   * @return true if update works, false otherwise
   * @throws DAOException : if the connection fail
   */
  public boolean update(Computer computer) throws DAOException;

  /** .
   * @param id : ID of the computer to delete
   * @return true if deletion works, false otherwise
   * @throws DAOException : if the connection fail
   */
  public boolean del(long id) throws DAOException;
  
  /** .
   * @param ids : IDs of computers to delete
   * @return true if deletion works, false otherwise
   * @throws DAOException : if the connection fail
   */
  public boolean[] dels(long[] ids) throws DAOException;
  
  /** Delete all computer of a company.
   * @param id : ID of a company
   * @return true if deletion works, false otherwise
   * @throws DAOException : if the connection fail
   */
  public boolean delsFromCompany(long id) throws DAOException;


  /** .
   * @param id : ID of the computer to find
   * @return An optional computer containing the computer find or empty if no computer 
   * @throws DAOException : if the connection fail
   */
  public Optional<Computer> findById(long id) throws DAOException;

  /** .
   * @param name : name of the computer to find
   * @return An optional computer containing the computer find or empty if no computer 
   * @throws DAOException : if the connection fail
   */
  public Optional<Computer> findByName(String name) throws DAOException;

  /** .
   * @return List of computers
   * @throws DAOException : if the connection fail
   */
  public ComputerList getComputers() throws DAOException;
  
  /** .
   * @return List of computers
   * @throws DAOException : if the connection fail
   */
  public ComputerList getNComputers(int begin, int nbComputer, int orderBy) 
      throws DAOException;
  
  
  /** .
   * @return List of computers that contain patern in name
   * @throws DAOException : if the connection fail
   */
  public ComputerList getNComputers(String pattern, int begin, int nbComputer, int orderBy) 
      throws DAOException;
  
  
  public int getNumberOfComputer() throws DAOException;
  
  public int getNumberOfComputer(String pattern) throws DAOException;

}
