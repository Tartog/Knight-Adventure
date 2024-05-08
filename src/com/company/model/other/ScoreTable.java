package com.company.model.other;

import com.company.model.entites.Player;
import java.io.*;
import java.util.ArrayList;

public class ScoreTable implements Serializable{
    private static ArrayList<String> recordsPlayers = new ArrayList<String>();
    private static ArrayList<String> namesPlayers = new ArrayList<String>();

    public static ArrayList<String> getRecordsPlayers()
    {
        return recordsPlayers;
    }
    public static ArrayList<String> getNamesPlayers()
    {
        return namesPlayers;
    }

    public static final int numberOfPlayers = 5;
    public ScoreTable(int newRecord) {
        InputStream is = this.getClass().getResourceAsStream("/Records/ScoreTable.tbl");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            for(int y = 0;y < numberOfPlayers * 2;y++)
            {
                String line = br.readLine();
                if(y % 2 == 1) {
                    recordsPlayers.add(line);
                }
                else {
                    namesPlayers.add(line);
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setNewRecord(int newRecord)
    {
        boolean a = true;
        int temp = numberOfPlayers;
        String a1 = "", a2, a3 = "", a4;
        for(int y = 0;y < temp;y++) {
            if (newRecord > Integer.parseInt(recordsPlayers.get(y)) && a) {
                a1 = recordsPlayers.get(y);
                a3 = namesPlayers.get(y);
                recordsPlayers.set(y, Integer.toString(newRecord));
                namesPlayers.set(y, Player.getName());
                a = false;
                temp--;
                y--;
            } else if (!a) {
                a2 = recordsPlayers.get(y + 1);
                a4 = namesPlayers.get(y + 1);
                namesPlayers.set(y + 1, a3);
                recordsPlayers.set(y + 1, a1);
                a1 = a2;
                a3 = a4;
            }
        }
    }
    public void addRecord(int newRecord) {
        FileWriter writer;
        try {
            writer = new FileWriter("res/Records/ScoreTable.tbl");
            setNewRecord(newRecord);
            for(int i = 0;i < numberOfPlayers;i++) {
                writer.write(namesPlayers.get(i) + "\n");
                writer.write(recordsPlayers.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
