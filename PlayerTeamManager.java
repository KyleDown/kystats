import java.util.HashSet;
import java.util.Scanner;

class TeamNode {
    String teamName;
    TeamNode next, prev;

    public TeamNode(String teamName) {
        this.teamName = teamName;
        this.next = this.prev = null;
    }
}

class PlayerNode {
    String playerName;
    PlayerNode next, prev;
    TeamNode teamHead;

    public PlayerNode(String playerName) {
        this.playerName = playerName;
        this.next = this.prev = null;
        this.teamHead = null;
    }

    public void addTeam(String teamName) {
        if (teamHead == null) {
            teamHead = new TeamNode(teamName);
            return;
        }
        
        // Check for duplicate teams
        TeamNode temp = teamHead;
        while (temp != null) {
            if (temp.teamName.equals(teamName)) return;
            if (temp.next == null) break;
            temp = temp.next;
        }
        
        TeamNode newTeam = new TeamNode(teamName);
        temp.next = newTeam;
        newTeam.prev = temp;
    }

    public void printTeams() {
        HashSet<String> uniqueTeams = new HashSet<>();
        TeamNode temp = teamHead;
        System.out.print(playerName + " has played for: ");
        while (temp != null) {
            if (!uniqueTeams.contains(temp.teamName)) {
                System.out.print(temp.teamName + " ");
                uniqueTeams.add(temp.teamName);
            }
            temp = temp.next;
        }
        System.out.println();
    }
}

class DoublyLinkedList {
    PlayerNode head;

    public void addPlayer(String playerName, String teamName) {
        if (head == null) {
            head = new PlayerNode(playerName);
            head.addTeam(teamName);
            return;
        }
        
        PlayerNode temp = head;
        while (temp != null) {
            if (temp.playerName.equals(playerName)) {
                temp.addTeam(teamName);
                return;
            }
            if (temp.next == null) break;
            temp = temp.next;
        }
        
        PlayerNode newPlayer = new PlayerNode(playerName);
        newPlayer.addTeam(teamName);
        temp.next = newPlayer;
        newPlayer.prev = temp;
    }

    public void printAllPlayers() {
        PlayerNode temp = head;
        while (temp != null) {
            temp.printTeams();
            temp = temp.next;
        }
    }
}

public class PlayerTeamManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList playerList = new DoublyLinkedList();

        // Predefined data
        playerList.addPlayer("LeBron James", "Cavaliers");
        playerList.addPlayer("LeBron James", "Heat");
        playerList.addPlayer("LeBron James", "Lakers");
        playerList.addPlayer("Kevin Durant", "SuperSonics");
        playerList.addPlayer("Kevin Durant", "Thunder");
        playerList.addPlayer("Kevin Durant", "Warriors");
        playerList.addPlayer("Kevin Durant", "Nets");
        playerList.addPlayer("Michael Jordan", "Bulls");
        playerList.addPlayer("Michael Jordan", "Wizards");

        System.out.println("Enter player name to view their teams (type 'exit' to stop):");
        while (true) {
            System.out.print("Enter player name: ");
            String player = scanner.nextLine();
            if (player.equalsIgnoreCase("exit")) break;
            
            PlayerNode temp = playerList.head;
            boolean found = false;
            while (temp != null) {
                if (temp.playerName.equalsIgnoreCase(player)) {
                    temp.printTeams();
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            
            if (!found) {
                System.out.println("Player not found.");
            }
        }
        scanner.close();
    }
}
