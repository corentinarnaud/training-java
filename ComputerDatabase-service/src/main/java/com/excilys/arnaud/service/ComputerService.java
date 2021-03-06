package com.excilys.arnaud.service;

import com.excilys.arnaud.dto.ComputerDto;
import com.excilys.arnaud.dto.ComputerPage;
import com.excilys.arnaud.mapper.ComputerMapper;
import com.excilys.arnaud.model.Computer;
import com.excilys.arnaud.persistence.ComputerDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ComputerService {

  private ComputerDAO computerDAO;
  private int numberComputer = -1;
  
  @Autowired
  public ComputerService(ComputerDAO computerDAO) {
    this.computerDAO = computerDAO;
  }

  /** Ask to DAO to add computerDto.
   * @param computerDto a computerDto to add
   * @return The id generated for the computer
   * @throws ServiceException if a DAO error occurred
   */
  @Transactional("txManager")
  public long add(ComputerDto computerDto) throws ServiceException {
    Computer computer = ComputerMapper.computerDtoToComputer(computerDto);
    Validator.checkDate(computer);
    Validator.checkName(computer.getName());
    long newId = computerDAO.add(computer);
    if (newId > 0) {
      manageComputerNumber(1);
    }
    return newId;
  }

  /** Ask to DAO to update computerDto.
   * @param computerDto a computerDto to add
   * @return true if computer is updated
   * @throws ServiceException if a DAO error occurred
   */
  @Transactional
  public boolean update(ComputerDto computerDto) throws ServiceException {
    Computer computer = ComputerMapper.computerDtoToComputer(computerDto);
    Validator.checkDate(computer);
    Validator.checkName(computer.getName());
    return computerDAO.update(computer);
  }

  @Transactional
  public boolean delComputer(long id) {
    if (computerDAO.del(id)) {
      manageComputerNumber(-1);
      return true;
    }
    return false;
  }

  /** Look for the computer with id equals to the input.
   * @param id of the computer to find
   * @return an optional of the computer
   */
  @Transactional(readOnly=true)
  public Optional<ComputerDto> findById(long id) {
    Optional<Computer> computer = computerDAO.findById(id);
    if (computer.isPresent()) {
      return Optional.of(
          ComputerMapper.computerToComputerDto(computer.get()));
    }
    return Optional.empty();
  }

  /** Look for a computer with name equals to the input.
   * @param name of the computer to find
   * @return an optional of the computer
   */
  @Transactional(readOnly=true)
  public Optional<ComputerDto> findByName(String name) {
    Optional<Computer> computer = computerDAO.findByName(name);
    if (computer.isPresent()) {
      return Optional.of(
          ComputerMapper.computerToComputerDto(computer.get()));
    }
    return Optional.empty();
  }
  
  @Transactional(readOnly=true)
  public ComputerPage getComputerPage(int page, String pattern, int orderBy, int nbElementsPage){
    List<ComputerDto> listComputer;
    int nbComputer;
    if (page < 0) {
      page = 0;
    }
    if (nbElementsPage < 0) {
      nbElementsPage = 10;
    }
    int begin = nbElementsPage * page;
    if (pattern == null){
      pattern = "";
    } if ("".equals(pattern)) {
      nbComputer = getNumberComputer();
    } else {
      nbComputer = computerDAO.getNumberOfComputer(pattern);
    }
    listComputer = ComputerMapper.computerListToComputerDtoList(
        computerDAO.getNComputers(pattern, begin, nbElementsPage, orderBy));
    return new ComputerPage(listComputer, nbComputer, page, pattern, orderBy);
  }

  @Transactional()
  public int delComputers(List<Long> longIds) {
    if(longIds.size()==1){
      return delComputer(longIds.get(0)) ? 1 : 0;
    }
    int dels = computerDAO.dels(longIds);
    manageComputerNumber(-dels);
    return dels;
    
  }
  @Transactional(readOnly=true)
  public int getNumberComputer() {
    if ( numberComputer == -1) {
      numberComputer = computerDAO.getNumberOfComputer();
    }
    return numberComputer;
  }
  
  private synchronized void manageComputerNumber(int change){
    if ( numberComputer == -1) {
      numberComputer = computerDAO.getNumberOfComputer();
    }
    numberComputer += change;
  }

}
