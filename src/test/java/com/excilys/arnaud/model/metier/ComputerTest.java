package com.excilys.arnaud.model.metier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.junit.Test;

import com.excilys.arnaud.model.work.Company;
import com.excilys.arnaud.model.work.Computer;


public class ComputerTest {

  @Test
  public void equalsTest() {
    Computer computer1 = new Computer("toto2", new Company(1, "Apple Inc."),
        LocalDate.parse("1980-05-01").atStartOfDay(), LocalDate.parse("1984-04-01").atStartOfDay());
    Computer computer2 = new Computer("toto2", new Company(1, "Apple Inc."),
        LocalDate.parse("1980-05-01").atStartOfDay(), LocalDate.parse("1984-04-01").atStartOfDay());

    assertTrue(computer1.equals(computer2));

    computer1.setId(5);
    computer2.setId(3);
    assertFalse(computer1.equals(computer2));
  }

}
