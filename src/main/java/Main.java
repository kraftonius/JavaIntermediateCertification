import customer.Customer;
import toy.Toy;
import toy.ToyToShip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {


        Toy toy1 = new Toy("Teddy Bear", 30, 2);
        Toy toy2 = new Toy("Kitten", 30, 1);
        Toy toy3 = new Toy("Puppy", 30, 3);
        Toy toy4 = new Toy("Parrot", 30, 1);
        Toy toy5 = new Toy("Dolphin", 30, 2);

        toy1.setWinRate(22);
        toy5.setWinRate(22);
        toy3.setWinRate(35);

        Customer customer1 = new Customer("Ivan");
        Customer customer2 = new Customer("Boris");
        Customer customer3 = new Customer("Anna");
        Customer customer4 = new Customer("Viktoria");
        Customer customer5 = new Customer("Kristina");

        lotterToy(customer1);
        lotterToy(customer2);
        lotterToy(customer3);
        lotterToy(customer4);
        lotterToy(customer5);

        ToyToShip.printWinnersToys();

        ToyToShip.shipWinToy();
        ToyToShip.shipWinToy();
        ToyToShip.shipWinToy();
        ToyToShip.shipWinToy();
        ToyToShip.shipWinToy();
    }

    public static void lotterToy(Customer customer) {
        Toy winToy = Toy.pickWinToy(customer);
        if (winToy == null) {
            System.out.println("no win");
        } else {
            System.out.println(">>> customer:" + customer.getName() + " won a toy: " + winToy.getName()
                    + "| Toy id: " + winToy.getId() + "| new toy quantity: " + winToy.getQuantity());
        }
    }
}
