package com.excilys.arnaud.service;

import com.excilys.arnaud.model.metier.Computer;

public class ServiceUtils {
  
  /** Check if name contains forbidden character.
   * @param name a String
   * @throws ServiceException if name contains [<>&${}()[]]
   */
  public static void checkName(String name) throws ServiceException {
    if (name.matches("(.*)[<>&\\$\\{}\\()\\[\\]](.*)")) {
      throw new ServiceException("Name shall not contain [<>&${}()[]]");
    }
  }
  
  /** Check if introduced Date is before discontinued Date.
   * @param computer The computer to test
   * @throws ServiceException if introduced date is after discontinued date
   */
  public static void checkDate(Computer computer) throws ServiceException {
    if (computer.getIntroduced() == null || computer.getDiscontinued() == null
        || computer.getIntroduced().isBefore(computer.getDiscontinued())) {
      return;
    }
    throw new ServiceException("Date of discontinuation "
        + computer.getDiscontinued() + " must be after date of introduction "
        + computer.getIntroduced());
  }

}