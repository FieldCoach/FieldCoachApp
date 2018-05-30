package fieldcoach.github.com.fieldcoachapp;

import java.util.ArrayList;
import java.util.List;

import fieldcoach.github.com.fieldcoachapp.model.Player;
import fieldcoach.github.com.fieldcoachapp.model.Team;

/**
 * Created by Aaron Crutchfield on 5/18/2018.
 */
public class DummyData {
    private static ArrayList<Player> playerSet1 = new ArrayList<Player>() {{
        add(new Player("Keylor", "Navas", 1, "Goalkeeper"));
        add(new Player("Sergio", "Ramos", 4, "Defender"));
        add(new Player("Nacho", "Fernandez", 6, "Defender"));
        add(new Player("Louis", "Marcelo", 12, "Defender"));
        add(new Player("Daniel", "Carvajal", 2, "Defender"));
        add(new Player("Toni", "Kross", 8, "Midfielder"));
        add(new Player("Luka", "Modric", 10, "Midfielder"));
        add(new Player("Alberto", "Isco", 22, "Midfielder"));
        add(new Player("Marcos", "Lorente", 18, "Midfielder"));
        add(new Player("Marco", "Asensio", 20, "Midfielder"));
        add(new Player("Christiano", "Ronaldo", 7, "Forward"));
        add(new Player("Karim", "Benzema", 9, "Forward"));
    }};

    private static ArrayList<Player> playerSet2 = new ArrayList<Player>() {{
        add(new Player("Ter", "Stegen", 1, "Goalkeeper"));
        add(new Player("Nelson", "Semendo", 2, "Defender"));
        add(new Player("Carlos", "Pique", 3, "Defender"));
        add(new Player("Yacoob", "Mina", 24, "Defender"));
        add(new Player("Alma", "Vermaelen", 25, "Defender"));
        add(new Player("Ivan", "Rakitic", 4, "Midfielder"));
        add(new Player("Sergio", "Busquets", 5, "Midfielder"));
        add(new Player("Andre", "Iniesta", 8, "Midfielder"));
        add(new Player("Raphael", "Coutinho", 14, "Midfielder"));
        add(new Player("Aleix", "Vidal", 22, "Midfielder"));
        add(new Player("Lionel", "Messi", 10, "Forward"));
        add(new Player("Luis", "Suarez", 9, "Forward"));
    }};

    public static List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("FC Real Madrid", 11, playerSet1, "10W 3D 1L"));
        teams.add(new Team("FC Barcelona", 11, playerSet2, "12W 1D 2L"));
        return teams;
    }
}