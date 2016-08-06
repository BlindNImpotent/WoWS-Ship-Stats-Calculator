package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.Data;

/**
 * 
 * @author Aesis (BlindNImpotent)
 *
 */
@Data
public class API_Parser 
{
	private JSONParser JSONParser = new JSONParser();
	
	private File API_CommanderSkills = new File("src/main/java/API_JSON/CommanderSkills.json");
	private File API_Exterior_Camouflage = new File("src/main/java/API_JSON/Exterior_Camouflage.json");
	private File API_Exterior_Permoflage = new File("src/main/java/API_JSON/Exterior_Permoflage.json");
	private File API_Exterior_Flags = new File("src/main/java/API_JSON/Exterior_Flags.json");
	private File API_InfoEncyclopedia = new File("src/main/java/API_JSON/InfoEncyclopedia.json");
	private File API_Modules_Artillery = new File("src/main/java/API_JSON/Modules_Artillery.json");
	private File API_Modules_Engine = new File("src/main/java/API_JSON/Modules_Engine.json");
	private File API_Modules_Hull = new File("src/main/java/API_JSON/Modules_Hull.json");
	private File API_Modules_Radar = new File("src/main/java/API_JSON/Modules_Radar.json");
	private File API_Modules_Torpedoes = new File("src/main/java/API_JSON/Modules_Torpedoes.json");
	private File API_Upgrades = new File("src/main/java/API_JSON/Upgrades.json");
	private File API_Warships = new File("src/main/java/API_JSON/Warships.json");
	
	private JSONObject API_CommanderSkillsJSON;
	private JSONObject API_Exterior_CamouflageJSON;
	private JSONObject API_Exterior_PermoflageJSON;
	private JSONObject API_Exterior_FlagsJSON;
	private JSONObject API_InfoEncyclopediaJSON;
	private JSONObject API_Modules_ArtilleryJSON;
	private JSONObject API_Modules_EngineJSON;
	private JSONObject API_Modules_HullJSON;
	private JSONObject API_Modules_RadarJSON;
	private JSONObject API_Modules_TorpedoesJSON;
	private JSONObject API_UpgradesJSON;
	private JSONObject API_WarshipsJSON;
		
	private List<JSONObject> APIShipsJSONList = new ArrayList<JSONObject>();

	private HashMap<String, JSONObject> APIShipsHashMap = new HashMap<>();

	private JSONObject shipJSON;
	
	private String ship_id_str;

	private String shipSmallImage;
	private String shipContour;

	public API_Parser() throws IOException, ParseException
	{
		setup();
	}

	@SuppressWarnings("unchecked")
	private void setup() throws IOException, ParseException
	{
		BufferedReader reader ;
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_CommanderSkills),"UTF8"));
		API_CommanderSkillsJSON = (JSONObject) JSONParser.parse(reader);
		API_CommanderSkillsJSON = (JSONObject) API_CommanderSkillsJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Exterior_Camouflage),"UTF8"));
		API_Exterior_CamouflageJSON = (JSONObject) JSONParser.parse(reader);
		API_Exterior_CamouflageJSON = (JSONObject) API_Exterior_CamouflageJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Exterior_Permoflage),"UTF8"));
		API_Exterior_PermoflageJSON = (JSONObject) JSONParser.parse(reader);
		API_Exterior_PermoflageJSON = (JSONObject) API_Exterior_PermoflageJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Exterior_Flags),"UTF8"));
		API_Exterior_FlagsJSON = (JSONObject) JSONParser.parse(reader);
		API_Exterior_FlagsJSON = (JSONObject) API_Exterior_FlagsJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_InfoEncyclopedia),"UTF8"));
		API_InfoEncyclopediaJSON = (JSONObject) JSONParser.parse(reader);
		API_InfoEncyclopediaJSON = (JSONObject) API_InfoEncyclopediaJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Modules_Artillery),"UTF8"));
		API_Modules_ArtilleryJSON = (JSONObject) JSONParser.parse(reader);
		API_Modules_ArtilleryJSON = (JSONObject) API_Modules_ArtilleryJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Modules_Engine),"UTF8"));
		API_Modules_EngineJSON = (JSONObject) JSONParser.parse(reader);
		API_Modules_EngineJSON = (JSONObject) API_Modules_EngineJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Modules_Hull),"UTF8"));
		API_Modules_HullJSON = (JSONObject) JSONParser.parse(reader);
		API_Modules_HullJSON = (JSONObject) API_Modules_HullJSON.get("data");

		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Modules_Radar),"UTF8"));
		API_Modules_RadarJSON = (JSONObject) JSONParser.parse(reader);
		API_Modules_RadarJSON = (JSONObject) API_Modules_RadarJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Modules_Torpedoes),"UTF8"));
		API_Modules_TorpedoesJSON = (JSONObject) JSONParser.parse(reader);
		API_Modules_TorpedoesJSON = (JSONObject) API_Modules_TorpedoesJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Upgrades),"UTF8"));
		API_UpgradesJSON = (JSONObject) JSONParser.parse(reader);
		API_UpgradesJSON = (JSONObject) API_UpgradesJSON.get("data");
		
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(API_Warships),"UTF8"));
		API_WarshipsJSON = (JSONObject) JSONParser.parse(reader);
		API_WarshipsJSON = (JSONObject) API_WarshipsJSON.get("data");
		
		APIShipsJSONList.addAll(API_WarshipsJSON.values());

		for (JSONObject shipJSON : APIShipsJSONList)
		{
			APIShipsHashMap.put((String) shipJSON.get("name"), shipJSON);
		}
	}
	
	public void setShipJSON(String aShipName)
	{
		JSONObject images;

		shipJSON = APIShipsHashMap.get(aShipName);
		ship_id_str = (String) shipJSON.get("ship_id_str");

		images = (JSONObject) shipJSON.get("images");
		shipSmallImage = (String) images.get("small");
		shipContour = (String) images.get("contour");

//		for (int i = 0; i < APIShipsJSONList.size(); i++)
//		{
//			if (APIShipsJSONList.get(i).containsValue(aShipName))
//			{
//				shipJSON = APIShipsJSONList.get(i);
//				ship_id_str = (String) shipJSON.get("ship_id_str");
//
//				images = (JSONObject) shipJSON.get("images");
//				shipSmallImage = (String) images.get("small");
//				shipContour = (String) images.get("contour");
//				break;
//			}
//		}
	}
}
