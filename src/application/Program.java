package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("\n====================");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);


        System.out.println("\n====================");
        List<Seller> sellers = sellerDao.findByDepartment(new Department(1, null));

        for (Seller s : sellers) {
            System.out.println(s);
        }

    }
}
