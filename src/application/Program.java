package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
//
//        System.out.println("\n====================");
//        Seller seller = sellerDao.findById(9);
//        System.out.println(seller);
//
//
//        System.out.println("\n====================");
//        List<Seller> sellers = sellerDao.findByDepartment(new Department(1, null));
//
//        for (Seller s : sellers) {
//            System.out.println(s);
//        }
//
//        System.out.println("\n====================");
//        sellers = sellerDao.findAll();
//
//        for (Seller s : sellers) {
//            System.out.println(s);
//        }
//
//        System.out.println("\n=========INSERT===========");
//        Seller newSeller = new Seller(null, "Chris tiro certo 4", "chris@gmail.com", LocalDate.now(), 4200.00, new Department(1, null));
//        sellerDao.insert(newSeller);
//        System.out.println(newSeller);
//
//        System.out.println("\n=========UPDATE===========");
//        newSeller = sellerDao.findById(10);
//        newSeller.setName("Bruce Waine da Bhaia");
//        sellerDao.update(newSeller);
//        System.out.println(newSeller);
//
//        System.out.println("\n=========DELETE===========");
//        System.out.print("Enter the id for delete: ");
//        Integer id = sc.nextInt();
//        sellerDao.deleteById(id);



    }
}
