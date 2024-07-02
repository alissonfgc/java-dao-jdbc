package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("\n====================");
        Seller seller = sellerDao.findById(9);
        System.out.println(seller);


        System.out.println("\n====================");
        List<Seller> sellers = sellerDao.findByDepartment(new Department(1, null));

        for (Seller s : sellers) {
            System.out.println(s);
        }

        System.out.println("\n====================");
        sellers = sellerDao.findAll();

        for (Seller s : sellers) {
            System.out.println(s);
        }

        System.out.println("\n====================");
        Seller newSeller = new Seller(null, "Chris tiro certo 4", "chris@gmail.com", LocalDate.now(), 4200.00, new Department(1, null));
        sellerDao.insert(newSeller);
        System.out.println(newSeller);

    }
}
