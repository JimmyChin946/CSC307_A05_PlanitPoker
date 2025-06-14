package planitpoker;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fetches story information given a username, password, and project id from taiga
 *
 * @author javiergs 
 */
public class T7TaigaStoryFetcher {
	private static Logger logger = LoggerFactory.getLogger(T7CreateRoomController.class);

	public static JSONArray getStories(String username, String password, String projectId) {
		try {
			String authToken = loginAndGetToken(username, password);
			int projectIdNumber = getProjectId(authToken, projectId);
			JSONArray stories = fetchUserStories(authToken, projectIdNumber);						
			return stories;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static String loginAndGetToken(String username, String password) throws Exception {
		URL url = new URL("https://api.taiga.io/api/v1/auth");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		String jsonInput = String.format("{\"type\": \"normal\", \"username\": \"%s\", \"password\": \"%s\"}", username, password);
		try (OutputStream os = conn.getOutputStream()) {
			os.write(jsonInput.getBytes());
			os.flush();
		}
		int responseCode = conn.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(
					responseCode == 200 ? conn.getInputStream() : conn.getErrorStream()
					));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			response.append(line);
		}
		in.close();
		JSONObject json = new JSONObject(response.toString());
		if (responseCode != 200) {
			String errorMessage = json.optString("_error_message", "Unknown error");
			throw new RuntimeException("Login failed: " + errorMessage);
		}
		String authToken = json.getString("auth_token");
		System.out.println("Auth token: " + authToken);
		return authToken;
	}

	public static int getProjectId(String token, String projectSlug) throws Exception {
		URL url = new URL("https://api.taiga.io/api/v1/projects/by_slug?slug=" + projectSlug);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", "Bearer " + token);
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
		reader.close();
		JSONObject json = new JSONObject(response.toString());
		return json.getInt("id");
	}

	public static JSONArray fetchUserStories(String token, int projectId) throws Exception {
		URL url = new URL("https://api.taiga.io/api/v1/userstories?project=" + projectId);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", "Bearer " + token);		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
		reader.close();		
		JSONArray allStories = new JSONArray(response.toString());
		JSONArray backlogStories = new JSONArray();
		Map<String, String> roleIdToName = Map.of(
				"5100817", "UX",
				"5100818", "Design",
				"5100816", "Front",
				"5100815", "Back"
				);		
		Map<Integer, Integer> pointIdToValue = Map.of(
				10136072, 8,  // UX
				10136073, 1,  // Design
				10136071, 2,  // Front
				10136075, 3   // Back
				);		
		System.out.println("Backlog stories:");
		for (int i = 0; i < allStories.length(); i++) {
			JSONObject story = allStories.getJSONObject(i);
			if (story.isNull("milestone")) {
				backlogStories.put(story);				
				int id = story.getInt("id");
				String subject = story.optString("subject", "(no title)");				
				String responsible = "Unassigned";
				if (!story.isNull("assigned_to_extra_info")) {
					responsible = story.getJSONObject("assigned_to_extra_info")
						.optString("full_name_display", "Unassigned");
				}				
				String totalPoints = story.isNull("total_points")
					? "—"
					: String.valueOf(story.getDouble("total_points"));				
				System.out.printf("• #%d - %s\n   Responsible: %s\n   Total Points: %s\n",
						id, subject, responsible, totalPoints);				
				if (!story.isNull("points")) {
					JSONObject pointsObj = story.getJSONObject("points");
					int sum = 0;					
					for (String roleId : pointsObj.keySet()) {
						int pointId = pointsObj.getInt(roleId);
						int value = pointIdToValue.getOrDefault(pointId, -1);
						String role = roleIdToName.getOrDefault(roleId, "Unknown");

						System.out.printf("     - %s (roleId: %s) → pointId: %d → value: %s\n",
								role, roleId, pointId, (value >= 0 ? value : "?"));
						if (value >= 0) sum += value;
					}				
					System.out.println("     = Computed Sum: " + sum + "\n");
				} else {
					System.out.println("     No per-role points assigned.\n");
				}
			}
		}		
		return backlogStories;
	}

}
