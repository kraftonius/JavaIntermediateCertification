package toy;

import customer.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ToyToShip extends Toy {
    private static ArrayList<ToyToShip> winnersToys = new ArrayList<>();
    private static int maxUnitId;
    int unitId;
    Customer customer;
    Toy toy;

    public int getUnitId() {
        return unitId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ToyToShip(Toy t, Customer c) {
        super(t, c);
        toy = t;
        customer = c;
        this.unitId = maxUnitId + 1;
        maxUnitId = this.unitId;
        winnersToys.add(this);
    }

    public static void shipWinToy() throws IOException {
        System.out.println("Toy: " + winnersToys.get(0).toy.getName() + " has been shipped to: " +
                winnersToys.get(0).getCustomer().getName());
        StringBuilder writeToFileSB = new StringBuilder();
        writeToFileSB.append("win_id: ").append(winnersToys.get(0).getUnitId()).append(" toy_name: ");
        writeToFileSB.append(winnersToys.get(0).toy.getName()).append(" to customer: ");
        writeToFileSB.append(winnersToys.get(0).customer);
        ToyToShip.writeToFile(writeToFileSB);
        winnersToys.remove(0);
    }


    public static void printWinnersToys() {
        System.out.println("-----------");
        System.out.println("Winner toys will be shipped:");
        for (ToyToShip tss : winnersToys) {
            System.out.println("Toy: " + tss.toy.getName() + " will be shipped to: " + tss.customer.getName());
        }
        System.out.println("-----------");
    }

    public static void writeToFile(StringBuilder stringBuilder) throws IOException {
        Path currentPath = Paths.get("");
        String folderPath = currentPath.toAbsolutePath().getParent().toString();
        File file = new File(folderPath, "winnersToys.txt");
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write("\n" + String.valueOf(stringBuilder));
            } catch (IOException e) {
                throw new IOException("Ошибка при записи: " + file.getName());
            }
        } else {
            try {
                if (!file.createNewFile()) {
                    throw new IOException("Ошибка при создании файла: " + file.getName());
                }
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(String.valueOf(stringBuilder));
                } catch (IOException e) {
                    throw new IOException("Ошибка при записи: " + file.getName());
                }
            } catch (IOException e) {
                throw new IOException("Ошибка при создании файла: " + file.getName());
            }
        }
        System.out.println("Записано в файл: winnersToys.txt в папке: " + folderPath);
    }


}
