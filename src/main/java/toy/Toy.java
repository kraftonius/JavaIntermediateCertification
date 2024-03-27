package toy;


import customer.Customer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Toy {
    private static ArrayList<Toy> toys = new ArrayList<>();
    private static int maxId;
    private int id;
    private String name;
    private Integer winRate;
    private Integer quantity;

    public Toy(String name, Integer winRate, Integer quantity) {
        this.id = maxId + 1;
        maxId = this.id;
        this.name = name;
        this.winRate = winRate;
        this.quantity = quantity;
        toys.add(this);
    }

    public Toy(Toy t, Customer c) {

    }


    public static ArrayList<Toy> getToys() {
        return toys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWinRate() {
        return winRate;
    }

    public void setWinRate(Integer winRate) {
        this.winRate = winRate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static Toy pickWinToy(Customer customer) {
        int lotteryCounter = 1;
        Integer[][] lotteryArray = new Integer[toys.size()][3];

        System.out.println("-----------");
        System.out.println("We have toys for this lottery");
        for (Toy t : toys) {
            System.out.println(t.getName() + "| toy id: " + t.getId() + "| toy winRate: "
                    + t.getWinRate() + "| toy quantity: " + t.getQuantity());
        }

        for (int i = 0; i < toys.size(); i++) {
            if (toys.get(i).quantity > 0) {
                lotteryArray[i][0] = (toys.get(i).id);
                lotteryArray[i][1] = (lotteryCounter + 1);
                lotteryCounter += (toys.get(i).winRate * toys.get(i).quantity);
                lotteryArray[i][2] = lotteryCounter;
            } else {
                lotteryArray[i][0] = (toys.get(i).id);
                lotteryArray[i][1] = 0;
                lotteryArray[i][2] = 0;
            }

        }
        int rand = ThreadLocalRandom.current().nextInt(1, lotteryCounter + 1);
        System.out.println("-----------");
        System.out.println("lotteryArray");
        for (int i = 0; i < lotteryArray.length; i++) {

            for (int j = 0; j < lotteryArray[0].length; j++) {
                System.out.print("[");
                System.out.print(lotteryArray[i][j]);
                System.out.print("]");
            }
            System.out.println();
        }
        System.out.println("winRandomNumber = " + rand);

        int winToyId = 0;
        for (int i = 0; i < lotteryArray.length; i++) {
            if (rand >= lotteryArray[i][1] && (rand <= lotteryArray[i][2])) {
                winToyId = lotteryArray[i][0];
            }
            for (Toy t : toys) {
                if (t.id == winToyId) {
                    prepareWinnerToy(t, customer);
                    return t;
                }
            }
        }
        return null;
    }

    public static void prepareWinnerToy(Toy toy, Customer customer) {
        toy.setQuantity(toy.getQuantity() - 1);
        new ToyToShip(toy, customer);
    }
}