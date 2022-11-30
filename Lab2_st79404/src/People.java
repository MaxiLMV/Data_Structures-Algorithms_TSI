import java.io.*;
import java.util.*;

public class People {
    boolean survived; // Survived or not
    int _class; // Passenger class
    String name; // Passenger name
    String gender; // Passenger gender
    float age; // Passenger age
    int siblings; // Siblings/Spouses
    int parents; // Parents/Children
    float cost; // Ticket cost
    public People(boolean survived, int _class, String name, String gender, float age, int siblings, int parents, float cost) {
        this.survived = survived;
        this._class = _class;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.siblings = siblings;
        this.parents = parents;
        this.cost = cost;
    }
    public String getSurvived(){
        if (survived) return "True";
        else return "False";
    }
    public int get_class(){ return _class; }
    public String getName(){ return name; }
    public String getGender(){ return gender; }
    public float getAge() {
        return age;
    }
    public int getSiblings() {
        return siblings;
    }
    public int getParents() {
        return parents;
    }
    public float getCost() {
        return cost;
    }
}

class operations {
    static void dataImport(String[] arr) {
        try {
            File fileIn = new File("dataset.csv");
            Scanner reader = new Scanner(fileIn);
            for (int i = 0; reader.hasNextLine(); i++) {
                arr[i] = reader.nextLine();
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File read error.");
        }
    }
    static void menu() {
        System.out.println("+----------------------------+");
        System.out.println("|       Titanic Lab 2        |");
        System.out.println("+---+------------------------+");
        System.out.println("| 1 | Append Value           |");
        System.out.println("+---+------------------------+");
        System.out.println("| 2 | See element at Index   |");
        System.out.println("+---+------------------------+");
        System.out.println("| 3 | Delete at Index        |");
        System.out.println("+---+------------------------+");
        System.out.println("| 4 | List Length            |");
        System.out.println("+---+------------------------+");
        System.out.println("| 5 | Insert Value at        |");
        System.out.println("+---+------------------------+");
        System.out.println("| 6 | Find eldest passenger  |");
        System.out.println("+---+------------------------+");
        System.out.println("| 7 | Quit                   |");
        System.out.println("+---+------------------------+");
        System.out.println("Enter one of the above listed numbers: ");
    }
    static People append(String value) {
        People send;
        boolean sur;
        String[] temp = value.split(",");
        sur = Objects.equals(temp[0], "1");
        send = new People(
                sur, // Survived or not
                Integer.parseInt(temp[1]), // Passenger Class
                temp[2], // Name
                temp[3], // Gender
                Float.parseFloat(temp[4]), // Age
                Integer.parseInt(temp[5]), // Number of siblings/spouses
                Integer.parseInt(temp[6]), // Number of parents/children
                Float.parseFloat(temp[7])); // Cost of ticket
        return send;
    }
    static void get(List<People> passengers, int index) {
        System.out.println("Survived: " + passengers.get(index).getSurvived());
        System.out.println("Class: " + passengers.get(index).get_class());
        System.out.println("Name: " + passengers.get(index).getName());
        System.out.println("Gender: " + passengers.get(index).getGender());
        if (passengers.get(index).getAge() % 1 == 0) System.out.println("Age: " + Math.round(passengers.get(index).getAge()));
        else System.out.println("Age: " + passengers.get(index).getAge());
        System.out.println("Siblings/Spouses: " + passengers.get(index).getSiblings());
        System.out.println("Parents/Children: " + passengers.get(index).getParents());
        System.out.println("Ticket Cost: " + passengers.get(index).getCost());
    }
    static void delete(List<People> passengers, int index) {
        passengers.remove(index);
    }
    static void length(List<People> passengers) {
        int counter = 0;
        for (People passenger : passengers) {
            if (!passenger.getName().isEmpty()) counter++;
        }
        System.out.println("Number of elements in the list: " + counter);
    }
    static void findEldest(List<People> passengers) {
        float oldest = 0;
        int rememberI = 0;
        for (int i = 0; i < passengers.size(); i++) {
            if ((oldest < passengers.get(i).getAge() && passengers.get(i).survived)) oldest = passengers.get(i).getAge();
            rememberI = i;
        }
        System.out.print("Eldest survived passenger is " + passengers.get(rememberI).getName() + " at age ");
        if (oldest % 1 == 0) System.out.println(Math.round(oldest));
        else System.out.println(oldest);
    }
    public static void main(String[] args) {
        String[] set = new String[888];
        dataImport(set);
        List<People> passengers = new ArrayList<>();
        for (int i = 1; i < set.length; i++) {
            passengers.add(append(set[i]));
        }
        Scanner scan = new Scanner(System.in);
        String input;
        String input2;
        do {
            menu();
            input = scan.nextLine();
            if (input.contains("1")) {
                System.out.println("Enter passenger information in the following format. ");
                System.out.println("Survived(0 for No, 1 for Yes), Class, Name, Gender, Age, Number of Siblings/Spouses Aboard, Number of Parents/Children Aboard, Ticket cost");
                System.out.println("(0,1), (1,2,3), Name, Gender, Full Number, Full Number, Full Number, Number (No spaces near ',')");
                input2 = scan.nextLine();
                passengers.add(append(input2));
                get(passengers, passengers.size() - 1);
            }
            if (input.contains("2")) {
                System.out.println("Enter a number to show contents at that location: ");
                input2 = scan.nextLine();
                if (!(Integer.parseInt(input2) > passengers.size()-1)) get(passengers, Integer.parseInt(input2));
                else System.out.println("Index is out of bounds! Try again.");
            }
            if (input.contains("3")) {
                System.out.println("Enter a number to remove contents at that location: ");
                input2 = scan.nextLine();
                if (!(Integer.parseInt(input2) > passengers.size()-1)) delete(passengers, Integer.parseInt(input2));
                else System.out.println("Index is out of bounds! Try again.");
                System.out.println("Data at Index " + Integer.parseInt(input2) + " deleted.");
            }
            if (input.contains("4")) {
                length(passengers);
            }
            if (input.contains("5")) {
                System.out.println("Current range: 0-" + passengers.size());
                System.out.println("Enter a number to add contents at that Index: ");
                input2 = scan.nextLine();
                int index = Integer.parseInt(input2);
                if (index > passengers.size()) System.out.println("Index is out of bounds! Try again.");
                else {
                    System.out.println("Enter passenger information in the following format. ");
                    System.out.println("Survived(0 for No, 1 for Yes), Class, Name, Gender, Age, Number of Siblings/Spouses Aboard, Number of Parents/Children Aboard, Ticket cost");
                    System.out.println("(0,1), (1,2,3), Name, Gender, Full Number, Full Number, Full Number, Number (No spaces near ',')");
                    input2 = scan.nextLine();
                    passengers.add(index, append(input2));
                }
            }
            if (input.contains("6")) {
                findEldest(passengers);
            }
        } while (!input.contains("7"));
    }
}
