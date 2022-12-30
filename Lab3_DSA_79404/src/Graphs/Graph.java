package Graphs;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Graph {
    static class Nodes {
        String Id;
        String Label;
        Nodes(String Id, String Label) {
            this.Id = Id;
            this.Label = Label;
        }
    }
    static void nodesImport(LinkedList<Nodes> addingNodes, String filename) {
        try {
            File fileIn = new File(filename);
            Scanner reader = new Scanner(fileIn);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("Id")) continue;
                String[] tempArr = line.split(",");
                Nodes temp = new Nodes(
                        tempArr[0],
                        tempArr[1]
                );
                addingNodes.add(temp);
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File read error.");
        }
    }

    static void addingUndirectedWeight(LinkedList<Nodes> nodes, int[][] adj, String filename) {
        try {
            File fileIn = new File(filename);
            Scanner reader = new Scanner(fileIn);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("Source")) continue;
                String[] tempArr = line.split(",");
                for (int i = 0; i < adj.length; i++) {
                    if (Objects.equals(nodes.get(i).Id, tempArr[0])) {
                        for (int j = 0; j < adj[i].length; j++) {
                            if (Objects.equals(nodes.get(j).Id, tempArr[1])) {
                                adj[i][j] = Integer.parseInt(tempArr[3]);
                                adj[j][i] = Integer.parseInt(tempArr[3]);
                            }
                        }
                    }
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File read error.");
        }
    }

    public static void printMatrix(int[][] adj) {
        String empty = "";
        String dash = "-";
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                if (i == j) {
                    System.out.format("%3s", empty);
                    continue;
                }
                if (adj[i][j] == 0) {
                    System.out.format("%3s", dash);
                }
                else {
                    System.out.format("%3d", adj[i][j]);
                }
            }
            System.out.println();
        }
    }
    public static void find(String name, LinkedList<Nodes> nodes) {
        boolean found = false;
        for (Nodes node : nodes) {
            if (Objects.equals(name, node.Id)) {
                System.out.println("Name found!");
                found = true;
                break;
            }
            if (Objects.equals(name, node.Label)) {
                System.out.println("Name found!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No such name.");
        }
    }
    public static void deleteEdge(LinkedList<Nodes> nodes, int[][] adj) {
        int current_random1;
        int current_random2;
        while (true) {
            current_random1 = (int) (Math.random() * (adj.length));
            current_random2 = (int) (Math.random() * (adj[0].length));
            if (adj[current_random1][current_random2] > 0) {
                adj[current_random1][current_random2] = 0;
                adj[current_random2][current_random1] = 0;
                if (current_random1 > current_random2) {
                    System.out.println("Edge from " + nodes.get(current_random2).Label + " to " + nodes.get(current_random1).Label + " deleted.");
                }
                if (current_random2 > current_random1) {
                    System.out.println("Edge from " + nodes.get(current_random1).Label + " to " + nodes.get(current_random2).Label + " deleted.");
                }
                break;
            }
        }

    }

    public static void compareBooks(LinkedList<Nodes> nodes1,
                                    LinkedList<Nodes> nodes2
                                    ) {
        ArrayList<String> removed = new ArrayList<>();
        ArrayList<String> newCharacters = new ArrayList<>();
        boolean found = false;
        for (Nodes item : nodes2) {
            for (Nodes nodes : nodes1) {
                if (Objects.equals(item.Id, nodes.Id)) {
                    found = true;
                    break;
                } else found = false;
            }
            if (!found) {
                removed.add(item.Label);
            }
        }
        for (Nodes value : nodes1) {
            for (Nodes nodes : nodes2) {
                if (Objects.equals(value.Id, nodes.Id)) {
                    found = true;
                    break;
                } else found = false;
            }
            if (!found) {
                newCharacters.add(value.Label);
            }
        }
        System.out.println("-----------------Removed characters-----------------");
        for (String s : removed) {
            System.out.println(s);
        }
        System.out.println("-------------------New characters-------------------");
        for (String s : newCharacters) {
            System.out.println(s);
        }
    }

    public static void menu() {
        System.out.println("+----------------------------+");
        System.out.println("|          GoT Lab 3         |");
        System.out.println("+---+------------------------+");
        System.out.println("| 1 | Print Adjacency Matrix |");
        System.out.println("+---+------------------------+");
        System.out.println("| 2 | Find by name           |");
        System.out.println("+---+------------------------+");
        System.out.println("| 3 | Delete Random Edge     |");
        System.out.println("+---+------------------------+");
        System.out.println("| 4 | Compare Book 4 & 5     |");
        System.out.println("+---+------------------------+");
        System.out.println("| 5 | Quit                   |");
        System.out.println("+---+------------------------+");
        System.out.println("Enter one of the above listed numbers: ");
    }

    public static void main(String[] args) {
        LinkedList<Nodes> nodes1 = new LinkedList<>();
        String filenameNodes = "asoiaf-book5-nodes.csv";
        nodesImport(nodes1, filenameNodes);
        int[][] adjMatrix1 = new int[nodes1.size()][nodes1.size()];
        String filenameEdges = "asoiaf-book5-edges.csv";
        addingUndirectedWeight(nodes1, adjMatrix1, filenameEdges);
        LinkedList<Nodes> nodes2 = new LinkedList<>();
        filenameNodes = "asoiaf-book4-nodes.csv";
        nodesImport(nodes2, filenameNodes);
        int[][] adjMatrix2 = new int[nodes2.size()][nodes2.size()];
        filenameEdges = "asoiaf-book4-edges.csv";
        addingUndirectedWeight(nodes2, adjMatrix2, filenameEdges);
        Scanner scan = new Scanner(System.in);
        String input;
        String input2;
        do {
            menu();
            input = scan.nextLine();
            if (input.contains("1")) {
                printMatrix(adjMatrix1);
            }
            if (input.contains("2")) {
                System.out.print("Please enter character name you wish to find: ");
                input2 = scan.nextLine();
                find(input2, nodes1);
            }
            if (input.contains("3")) {
                deleteEdge(nodes1, adjMatrix1);
            }
            if (input.contains("4")) {
                compareBooks(nodes1, nodes2);
            }
        } while (!input.contains("5"));
    }
}