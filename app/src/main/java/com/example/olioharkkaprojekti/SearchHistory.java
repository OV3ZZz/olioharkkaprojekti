package com.example.olioharkkaprojekti;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;

public class SearchHistory {
    private ArrayList<String> history;
    private final String historyFile = "search_history.txt";

    public SearchHistory(Context context) {
        history = new ArrayList<>();
        loadHistory(context);
    }

    public void addSearch(Context context, String query) {
        history.add(query);
        saveHistory(context);
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String query : history) {
            builder.append(query).append("\n");
        }
        return builder.toString();
    }

    public void clearHistory(Context context) {
        history.clear();
        saveHistory(context);
    }

    private void saveHistory(Context context) {
        try (PrintWriter writer = new PrintWriter(new File(context.getFilesDir(), historyFile))) {
            for (String query : history) {
                writer.println(query);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHistory(Context context) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(context.getFilesDir(), historyFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            // If the file does not exist, do nothing
        }
    }

}