package ru.satcit.kolpak.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * User: Aleksei
 * Date: 07.01.16 21:07
 */
public class ManageTest {
  private static SessionFactory factory;

  public static void main(String[] args) {
    try {
      factory = new AnnotationConfiguration().
          configure().
          //addPackage("com.xyz") //add package if used.
              addAnnotatedClass(Article.class).
          buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
    ManageTest ME = new ManageTest();

      /* Add few employee records in database */
    Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
    Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
    Integer empID3 = ME.addEmployee("John", "Paul", 10000);

      /* List down all the employees */
    ME.listEmployees();

      /* Update employee's records */
    ME.updateEmployee(empID1, 5000);

      /* Delete an employee from the database */
    ME.deleteEmployee(empID2);

      /* List down new list of the employees */
    ME.listEmployees();
  }

  /* Method to CREATE an employee in the database */
  public Integer addEmployee(String fname, String lname, int salary) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try {
      tx = session.beginTransaction();
      Article employee = new Article();
      employee.setName(fname);
      employeeID = (Integer) session.save(employee);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return employeeID;
  }

  /* Method to  READ all the employees */
  public void listEmployees() {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      List<Article> employees = session.createQuery("FROM Article").list();
      for (Article employee : employees) {
        System.out.println("Name: " + employee.getName());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /* Method to UPDATE salary for an employee */
  public void updateEmployee(Integer EmployeeID, int salary) {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      Article employee =
          (Article) session.get(Article.class, EmployeeID);
      employee.setName(employee.getName() + "hohoho");
      session.update(employee);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /* Method to DELETE an employee from the records */
  public void deleteEmployee(Integer EmployeeID) {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      Article employee =
          (Article) session.get(Article.class, EmployeeID);
      session.delete(employee);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }
}
