package com.practise.lld.restapi;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


class RestAPIAssignment1 {

    // Helper class remains the same - it's good practice
    static class TeamData {
        long stadiumCapacity;
        int silverwareCount;

        TeamData(long capacity, int silverware) {
            this.stadiumCapacity = capacity;
            this.silverwareCount = silverware;
        }
    }

    // --- Optimization 2: Helper method for ticket cost ---
    private static double getTicketCost(int silverwareCount) {
        if (silverwareCount > 40) {
            return 1.5;
        } else if (silverwareCount > 25) {
            return 1.25;
        } else if (silverwareCount > 10) {
            return 1.0;
        } else {
            return 0.5;
        }
    }

    public static List<Integer> leagueEarnings(String league) {

        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        List<TeamData> allTeams = new ArrayList<>();
        int totalTeams = 0; // N
        int totalPages = 1;
        int currentPage = 1;

        try {
            String encodedLeague = URLEncoder.encode(league, StandardCharsets.UTF_8); // Keep UTF_8 constant directly
            String baseUrl = "https://jsonmock.hackerrank.com/api/football_teams?league=" + encodedLeague;

            // --- Pagination Loop (largely unchanged) ---
            do {
                String url = baseUrl + "&page=" + currentPage;
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    System.err.println("HTTP Error: " + response.statusCode() + " for URL: " + url);
                    // --- Optimization 3: Use List.of() for error result ---
                    return List.of(0, 0);
                }

                String responseBody = response.body();
                JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);

                if (currentPage == 1) {
                    // Defensive check if 'total' or 'total_pages' might be missing/null
                    if (!jsonResponse.has("total") || !jsonResponse.has("total_pages")) {
                        System.err.println("Error: Missing 'total' or 'total_pages' in first page response.");
                        return List.of(0, 0);
                    }
                    totalTeams = jsonResponse.get("total").getAsInt();
                    totalPages = jsonResponse.get("total_pages").getAsInt();
                }

                // Defensive check for 'data' array
                if (jsonResponse.has("data") && jsonResponse.get("data").isJsonArray()) {
                    JsonArray teamsData = jsonResponse.getAsJsonArray("data");
                    for (JsonElement teamElement : teamsData) {
                        // Add more defensive checks inside the loop if needed (e.g., has("field"))
                        JsonObject teamObject = teamElement.getAsJsonObject();
                        try {
                            long capacity = teamObject.get("stadium_capacity").getAsLong();
                            int silverware = teamObject.get("total_silverware_count").getAsInt();
                            allTeams.add(new TeamData(capacity, silverware));
                        } catch (Exception e) {
                            System.err.println("Skipping team due to missing/invalid field: " + teamObject);
                            // Optionally continue to next team or handle differently
                        }
                    }
                } else if (currentPage == 1 && totalTeams > 0) {
                    // If first page promises teams but has no 'data' array, it's an issue
                    System.err.println("Warning: 'data' array missing or not an array on page " + currentPage);
                }


                currentPage++;

            } while (currentPage <= totalPages);

            // --- Calculations ---

            if (totalTeams <= 1) {
                // --- Optimization 3: Use List.of() here too ---
                return List.of(0, 0);
            }

            double totalLeagueEarnings = 0.0;
            long N = totalTeams;

            for (TeamData team : allTeams) {
                // --- Optimization 2: Use helper method ---
                double ticketCost = getTicketCost(team.silverwareCount);
                double teamContribution = ticketCost * team.stadiumCapacity * (N - 1);
                totalLeagueEarnings += teamContribution;
            }

            double totalMatches = (double) N * (N - 1);
            double averageEarningsPerMatch = 0.0;
            if (totalMatches > 0) {
                averageEarningsPerMatch = totalLeagueEarnings / totalMatches;
            }

            int finalTotalEarnings = (int) Math.floor(totalLeagueEarnings);
            int finalAverageEarnings = (int) Math.floor(averageEarningsPerMatch);

            // --- Optimization 3: Use List.of() for the final result ---
            return List.of(finalTotalEarnings, finalAverageEarnings);

        } catch (IOException | InterruptedException e) {
            System.err.println("Error during API call: " + e.getMessage());
            e.printStackTrace(); // Keep for debugging if needed
            return List.of(0, 0); // Use List.of()
        } catch (JsonSyntaxException e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
            e.printStackTrace();
            return List.of(0, 0); // Use List.of()
        } catch (Exception e) { // Catch potential NumberFormatException, etc. from getAsInt/getAsLong
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return List.of(0, 0); // Use List.of()
        }
    }
}