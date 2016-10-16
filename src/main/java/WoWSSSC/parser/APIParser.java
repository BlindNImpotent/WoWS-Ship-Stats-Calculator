package WoWSSSC.parser;

import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Aesis on 2016-10-15.
 */
@Data
public class APIParser
{
    private org.json.simple.parser.JSONParser JSONParser = new JSONParser();

    private Resource API_Warships = new ClassPathResource("static/json/API_JSON/Warships.json");
    private Resource API_Modules_Artillery = new ClassPathResource("static/json/API_JSON/Modules_Artillery.json");
    private Resource API_Modules_DiveBomber = new ClassPathResource("static/json/API_JSON/Modules_DiveBomber.json");
    private Resource API_Modules_Engine = new ClassPathResource("static/json/API_JSON/Modules_Engine.json");
    private Resource API_Modules_Fighter = new ClassPathResource("static/json/API_JSON/Modules_Fighter.json");
    private Resource API_Modules_FireControl = new ClassPathResource("static/json/API_JSON/Modules_FireControl.json");
    private Resource API_Modules_FlightControl = new ClassPathResource("static/json/API_JSON/Modules_FlightControl.json");
    private Resource API_Modules_Hull = new ClassPathResource("static/json/API_JSON/Modules_Hull.json");
    private Resource API_Modules_Torpedoes = new ClassPathResource("static/json/API_JSON/Modules_Torpedoes.json");
    private Resource API_Modules_TorpedoBomber = new ClassPathResource("static/json/API_JSON/Modules_TorpedoBomber.json");
    private Resource API_Upgrades = new ClassPathResource("static/json/API_JSON/Upgrades.json");

    private JSONObject API_Modules_ArtilleryJSON = new JSONObject();
    private JSONObject API_Modules_DiveBomberJSON = new JSONObject();
    private JSONObject API_Modules_EngineJSON = new JSONObject();
    private JSONObject API_Modules_FighterJSON = new JSONObject();
    private JSONObject API_Modules_FireControlJSON = new JSONObject();
    private JSONObject API_Modules_FlightControlJSON = new JSONObject();
    private JSONObject API_Modules_HullJSON = new JSONObject();
    private JSONObject API_Modules_TorpedoesJSON = new JSONObject();
    private JSONObject API_Modules_TorpedoBomberJSON = new JSONObject();
    private JSONObject API_UpgradesJSON = new JSONObject();
    private JSONObject API_WarshipsJSON = new JSONObject();

    private HashMap<String, JSONObject> API_WarshipsHashMap = new HashMap<>();

    private JSONObject FranceAirCarrier = new JSONObject();
    private JSONObject FranceBattleship = new JSONObject();
    private JSONObject FranceCruiser = new JSONObject();
    private JSONObject FranceDestroyer = new JSONObject();
    private JSONObject FrancePremium = new JSONObject();

    private JSONObject GermanyAirCarrier = new JSONObject();
    private JSONObject GermanyBattleship = new JSONObject();
    private JSONObject GermanyCruiser = new JSONObject();
    private JSONObject GermanyDestroyer = new JSONObject();
    private JSONObject GermanyPremium = new JSONObject();

    private JSONObject JapanAirCarrier = new JSONObject();
    private JSONObject JapanBattleship = new JSONObject();
    private JSONObject JapanCruiser = new JSONObject();
    private JSONObject JapanDestroyer = new JSONObject();
    private JSONObject JapanPremium = new JSONObject();

    private JSONObject PanAsiaAirCarrier = new JSONObject();
    private JSONObject PanAsiaBattleship = new JSONObject();
    private JSONObject PanAsiaCruiser = new JSONObject();
    private JSONObject PanAsiaDestroyer = new JSONObject();
    private JSONObject PanAsiaPremium = new JSONObject();

    private JSONObject PolandAirCarrier = new JSONObject();
    private JSONObject PolandBattleship = new JSONObject();
    private JSONObject PolandCruiser = new JSONObject();
    private JSONObject PolandDestroyer = new JSONObject();
    private JSONObject PolandPremium = new JSONObject();

    private JSONObject UKAirCarrier = new JSONObject();
    private JSONObject UKBattleship = new JSONObject();
    private JSONObject UKCruiser = new JSONObject();
    private JSONObject UKDestroyer = new JSONObject();
    private JSONObject UKPremium = new JSONObject();
    
    private JSONObject USAAirCarrier = new JSONObject();
    private JSONObject USABattleship = new JSONObject();
    private JSONObject USACruiser = new JSONObject();
    private JSONObject USADestroyer = new JSONObject();
    private JSONObject USAPremium = new JSONObject();

    private JSONObject USSRAirCarrier = new JSONObject();
    private JSONObject USSRBattleship = new JSONObject();
    private JSONObject USSRCruiser = new JSONObject();
    private JSONObject USSRDestroyer = new JSONObject();
    private JSONObject USSRPremium = new JSONObject();

    private JSONObject shipListJSON = new JSONObject();

    private JSONObject ship;
    private JSONObject modules;
    private JSONObject modules_tree;
    private JSONArray artillery;
    private JSONArray dive_bomber;
    private JSONArray engine;
    private JSONArray fighter;
    private JSONArray fire_control;
    private JSONArray flight_control;
    private JSONArray hull;
    private JSONArray torpedoes;
    private JSONArray torpedo_bomber;
    private JSONArray upgrades;

    public void setUp() throws IOException, ParseException
    {
        BufferedReader reader;
        
        reader = new BufferedReader(new InputStreamReader(API_Modules_Artillery.getInputStream(),"UTF8"));
        API_Modules_ArtilleryJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_ArtilleryJSON = (JSONObject) API_Modules_ArtilleryJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_DiveBomber.getInputStream(),"UTF8"));
        API_Modules_DiveBomberJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_DiveBomberJSON = (JSONObject) API_Modules_DiveBomberJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_Engine.getInputStream(), "UTF8"));
        API_Modules_EngineJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_EngineJSON = (JSONObject) API_Modules_EngineJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_Fighter.getInputStream(),"UTF8"));
        API_Modules_FighterJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_FighterJSON = (JSONObject) API_Modules_FighterJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_FireControl.getInputStream(),"UTF8"));
        API_Modules_FireControlJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_FireControlJSON = (JSONObject) API_Modules_FireControlJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_FlightControl.getInputStream(),"UTF8"));
        API_Modules_FlightControlJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_FlightControlJSON = (JSONObject) API_Modules_FlightControlJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_Hull.getInputStream(),"UTF8"));
        API_Modules_HullJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_HullJSON = (JSONObject) API_Modules_HullJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_Torpedoes.getInputStream(),"UTF8"));
        API_Modules_TorpedoesJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_TorpedoesJSON = (JSONObject) API_Modules_TorpedoesJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Modules_TorpedoBomber.getInputStream(),"UTF8"));
        API_Modules_TorpedoBomberJSON = (JSONObject) JSONParser.parse(reader);
        API_Modules_TorpedoBomberJSON = (JSONObject) API_Modules_TorpedoBomberJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Upgrades.getInputStream(),"UTF8"));
        API_UpgradesJSON = (JSONObject) JSONParser.parse(reader);
        API_UpgradesJSON = (JSONObject) API_UpgradesJSON.get("data");

        reader = new BufferedReader(new InputStreamReader(API_Warships.getInputStream(),"UTF8"));
        API_WarshipsJSON = (JSONObject) JSONParser.parse(reader);
        API_WarshipsJSON = (JSONObject) API_WarshipsJSON.get("data");

        setUpWarshipsHashMap();
    }

    private void setUpWarshipsHashMap()
    {
        Set<String> keySet = API_WarshipsJSON.keySet();
        keySet.forEach(k ->
        {
            JSONObject x = (JSONObject) API_WarshipsJSON.get(k);
            String name = (String) x.get("name");
            API_WarshipsHashMap.put(name, x);

            modules = (JSONObject) x.get("modules");
            artillery = (JSONArray) modules.get("artillery");
            dive_bomber = (JSONArray) modules.get("dive_bomber");
            engine = (JSONArray) modules.get("engine");
            fighter = (JSONArray) modules.get("fighter");
            fire_control = (JSONArray) modules.get("fire_control");
            flight_control = (JSONArray) modules.get("flight_control");
            hull = (JSONArray) modules.get("hull");
            torpedoes = (JSONArray) modules.get("torpedoes");
            torpedo_bomber = (JSONArray) modules.get("torpedo_bomber");
            upgrades = (JSONArray) x.get("upgrades");
            modules_tree = (JSONObject) x.get("modules_tree");

//            setArtillery();
//            setDiveBomber();
//            setEngine();
//            setFighter();
//            setFireControl();
//            setFlightControl();
//            setHull();
//            setTorpedoBomber();
//            setTorpedoes();
            setModulesTree(x);
            setUpgrades(x);
            setShipList(x);
        });

        JSONObject France = new JSONObject();
        France.put("AirCarrier", FranceAirCarrier);
        France.put("Battleship", FranceBattleship);
        France.put("Cruiser", FranceCruiser);
        France.put("Destroyer", FranceDestroyer);
        France.put("Premium", FrancePremium);
        
        JSONObject Germany = new JSONObject();
        Germany.put("AirCarrier", GermanyAirCarrier);
        Germany.put("Battleship", GermanyBattleship);
        Germany.put("Cruiser", GermanyCruiser);
        Germany.put("Destroyer", GermanyDestroyer);
        Germany.put("Premium", GermanyPremium);
        
        JSONObject Japan = new JSONObject();
        Japan.put("AirCarrier", JapanAirCarrier);
        Japan.put("Battleship", JapanBattleship);
        Japan.put("Cruiser", JapanCruiser);
        Japan.put("Destroyer", JapanDestroyer);
        Japan.put("Premium", JapanPremium);
        
        JSONObject PanAsia = new JSONObject();
        PanAsia.put("AirCarrier", PanAsiaAirCarrier);
        PanAsia.put("Battleship", PanAsiaBattleship);
        PanAsia.put("Cruiser", PanAsiaCruiser);
        PanAsia.put("Destroyer", PanAsiaDestroyer);
        PanAsia.put("Premium", PanAsiaPremium);
        
        JSONObject Poland = new JSONObject();
        Poland.put("AirCarrier", PolandAirCarrier);
        Poland.put("Battleship", PolandBattleship);
        Poland.put("Cruiser", PolandCruiser);
        Poland.put("Destroyer", PolandDestroyer);
        Poland.put("Premium", PolandPremium);
        
        JSONObject UK = new JSONObject();
        UK.put("AirCarrier", UKAirCarrier);
        UK.put("Battleship", UKBattleship);
        UK.put("Cruiser", UKCruiser);
        UK.put("Destroyer", UKDestroyer);
        UK.put("Premium", UKPremium);
        
        JSONObject USA = new JSONObject();
        USA.put("AirCarrier", USAAirCarrier);
        USA.put("Battleship", USABattleship);
        USA.put("Cruiser", USACruiser);
        USA.put("Destroyer", USADestroyer);
        USA.put("Premium", USAPremium);
        
        JSONObject USSR = new JSONObject();
        USSR.put("AirCarrier", USSRAirCarrier);
        USSR.put("Battleship", USSRBattleship);
        USSR.put("Cruiser", USSRCruiser);
        USSR.put("Destroyer", USSRDestroyer);
        USSR.put("Premium", USSRPremium);
        
        shipListJSON.put("France", France);
        shipListJSON.put("Germany", Germany);
        shipListJSON.put("Japan", Japan);
        shipListJSON.put("Pan_Asia", PanAsia);
        shipListJSON.put("Poland", Poland);
        shipListJSON.put("UK", UK);
        shipListJSON.put("USA", USA);
        shipListJSON.put("USSR", USSR);
    }

    private void setShipList(JSONObject x)
    {
        if (x.get("nation").equals("france"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    FranceAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    FranceBattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    FranceCruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    FranceDestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                FrancePremium.put(x.get("name"), x);
            }
        }
        else if (x.get("nation").equals("germany"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    GermanyAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    GermanyBattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    GermanyCruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    GermanyDestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                GermanyPremium.put(x.get("name"), x);
            }
        }
        else if (x.get("nation").equals("japan"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    JapanAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    JapanBattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    JapanCruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    JapanDestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                JapanPremium.put(x.get("name"), x);
            }
        }
        else if (x.get("nation").equals("pan_asia"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    PanAsiaAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    PanAsiaBattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    PanAsiaCruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    PanAsiaDestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                PanAsiaPremium.put(x.get("name"), x);
            }
        }
        else if (x.get("nation").equals("poland"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    PolandAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    PolandBattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    PolandCruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    PolandDestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                PolandPremium.put(x.get("name"), x);
            }
        }
        else if (x.get("nation").equals("uk"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    UKAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    UKBattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    UKCruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    UKDestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                UKPremium.put(x.get("name"), x);
            }
        }
        else if (x.get("nation").equals("usa"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    USAAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    USABattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    USACruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    USADestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                USAPremium.put(x.get("name"), x);
            }
        }
        else if (x.get("nation").equals("ussr"))
        {
            if (x.get("is_premium").equals(false))
            {
                if (x.get("type").equals("AirCarrier"))
                {
                    USSRAirCarrier.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Battleship"))
                {
                    USSRBattleship.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Cruiser"))
                {
                    USSRCruiser.put(x.get("name"), x);
                }
                else if (x.get("type").equals("Destroyer"))
                {
                    USSRDestroyer.put(x.get("name"), x);
                }
            }
            else if (x.get("is_premium").equals(true))
            {
                USSRPremium.put(x.get("name"), x);
            }
        }
    }

    private void setModulesTree(JSONObject x)
    {
        Set<String> modules_tree_keySet = modules_tree.keySet();
        JSONObject jsonObject = new JSONObject();

        modules_tree_keySet.forEach(mtk ->
        {
            JSONObject jsonObject1 = (JSONObject) modules_tree.get(String.valueOf(mtk));
            JSONObject jsonObject2 = new JSONObject();

            JSONArray arrayNM = (JSONArray) jsonObject1.get("next_modules");
            JSONArray array2 = new JSONArray();
            if (arrayNM != null)
            {
                arrayNM.forEach(a ->
                {
                    JSONObject obj = (JSONObject) modules_tree.get(String.valueOf(a));

                    String name = (String) obj.get("name");
                    array2.add(name);
                });
                jsonObject1.remove("next_modules");
                jsonObject1.put("next_modules", array2);
            }

            if (jsonObject1.get("type").equals("Artillery"))
            {
                jsonObject2 = (JSONObject) API_Modules_ArtilleryJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("DiveBomber"))
            {
                jsonObject2 = (JSONObject) API_Modules_DiveBomberJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("Engine"))
            {
                jsonObject2 = (JSONObject) API_Modules_EngineJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("Fighter"))
            {
                jsonObject2 = (JSONObject) API_Modules_FighterJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("Suo"))
            {
                jsonObject2 = (JSONObject) API_Modules_FireControlJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("FlightControl"))
            {
                jsonObject2 = (JSONObject) API_Modules_FlightControlJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("Hull"))
            {
                jsonObject2 = (JSONObject) API_Modules_HullJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("Torpedoes"))
            {
                jsonObject2 = (JSONObject) API_Modules_TorpedoesJSON.get(String.valueOf(mtk));
            }
            else if (jsonObject1.get("type").equals("TorpedoBomber"))
            {
                jsonObject2 = (JSONObject) API_Modules_TorpedoBomberJSON.get(String.valueOf(mtk));
            }

            Set<String> keySet = jsonObject2.keySet();
            JSONObject finalJsonObject = jsonObject2;
            keySet.forEach(k -> jsonObject1.putIfAbsent(k, finalJsonObject.get(k)));

            JSONArray arrayNS = (JSONArray) jsonObject1.get("next_ships");
            JSONArray array1 = new JSONArray();
            if (arrayNS != null)
            {
                arrayNS.forEach(a ->
                {
                    JSONObject obj = (JSONObject) API_WarshipsJSON.get(String.valueOf(a));
                    String name = (String) obj.get("name");
                    array1.add(name);
                });
                jsonObject1.remove("next_ships");
                jsonObject1.put("next_ships", array1);
            }
            jsonObject.put(jsonObject1.get("name"), jsonObject1);
        });
        x.remove("modules_tree");
        x.put("modules_tree", jsonObject);
    }

    private void setArtillery()
    {
        JSONObject artilleryJSON = new JSONObject();
        artillery.forEach(a ->
        {
            JSONObject jsonObject1 = (JSONObject) API_Modules_ArtilleryJSON.get(String.valueOf(a));
            JSONObject jsonObject2 = (JSONObject) modules_tree.get(String.valueOf(a));

            Set<String> keySet = jsonObject2.keySet();
            keySet.forEach(k -> jsonObject1.put(k, jsonObject2.get(k)));

            String name1 = (String) jsonObject1.get("name");
            artilleryJSON.put(name1, jsonObject1);
        });
        modules.remove("artillery");
        modules.put("artillery", artilleryJSON);
    }

    private void setDiveBomber()
    {
        JSONObject diveBomberJSON = new JSONObject();
        dive_bomber.forEach(d ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_DiveBomberJSON.get(String.valueOf(d));
            String name = (String) jsonObject.get("name");
            diveBomberJSON.put(name, API_Modules_DiveBomberJSON.get(String.valueOf(d)));
        });
        modules.remove("dive_bomber");
        modules.put("dive_bomber", diveBomberJSON);
    }

    private void setEngine()
    {
        JSONObject engineJSON = new JSONObject();
        engine.forEach(e ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_EngineJSON.get(String.valueOf(e));
            String name = (String) jsonObject.get("name");
            engineJSON.put(name, API_Modules_EngineJSON.get(String.valueOf(e)));
        });
        modules.remove("engine");
        modules.put("engine", engineJSON);
    }

    private void setFighter()
    {
        JSONObject fighterJSON = new JSONObject();
        fighter.forEach(f ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_FighterJSON.get(String.valueOf(f));
            String name = (String) jsonObject.get("name");
            fighterJSON.put(name, API_Modules_FighterJSON.get(String.valueOf(f)));
        });
        modules.remove("fighter");
        modules.put("fighter", fighterJSON);
    }

    private void setFireControl()
    {
        JSONObject fireControlJSON = new JSONObject();
        fire_control.forEach(fc ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_FireControlJSON.get(String.valueOf(fc));
            String name = (String) jsonObject.get("name");
            fireControlJSON.put(name, API_Modules_FireControlJSON.get(String.valueOf(fc)));
        });
        modules.remove("fire_control");
        modules.put("fire_control", fireControlJSON);
    }

    private void setFlightControl()
    {
        JSONObject flightControlJSON = new JSONObject();
        flight_control.forEach(fc ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_FlightControlJSON.get(String.valueOf(fc));
            String name = (String) jsonObject.get("name");
            flightControlJSON.put(name, API_Modules_FlightControlJSON.get(String.valueOf(fc)));
        });
        modules.remove("flight_control");
        modules.put("flight_control", flightControlJSON);
    }

    private void setHull()
    {
        JSONObject hullJSON = new JSONObject();
        hull.forEach(h ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_HullJSON.get(String.valueOf(h));
            String name = (String) jsonObject.get("name");
            hullJSON.put(name, API_Modules_HullJSON.get(String.valueOf(h)));
        });
        modules.remove("hull");
        modules.put("hull", hullJSON);
    }

    private void setTorpedoes()
    {
        JSONObject torpedoesJSON = new JSONObject();
        torpedoes.forEach(t ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_TorpedoesJSON.get(String.valueOf(t));
            String name = (String) jsonObject.get("name");
            torpedoesJSON.put(name, API_Modules_TorpedoesJSON.get(String.valueOf(t)));
        });
        modules.remove("torpedoes");
        modules.put("torpedoes", torpedoesJSON);
    }

    private void setTorpedoBomber()
    {
        JSONObject torpedoBomberJSON = new JSONObject();
        torpedo_bomber.forEach(tb ->
        {
            JSONObject jsonObject = (JSONObject) API_Modules_TorpedoBomberJSON.get(String.valueOf(tb));
            String name = (String) jsonObject.get("name");
            torpedoBomberJSON.put(name, API_Modules_TorpedoBomberJSON.get(String.valueOf(tb)));
        });
        modules.remove("torpedo_bomber");
        modules.put("torpedo_bomber", torpedoBomberJSON);
    }

    private void setUpgrades(JSONObject x)
    {
        JSONObject upgradesJSON = new JSONObject();
        JSONObject Slot1 = new JSONObject();
        JSONObject Slot2 = new JSONObject();
        JSONObject Slot3 = new JSONObject();
        JSONObject Slot4 = new JSONObject();
        JSONObject Slot5 = new JSONObject();
        JSONObject Slot6 = new JSONObject();

        upgrades.forEach(u -> 
        {
            JSONObject y = (JSONObject) API_UpgradesJSON.get(String.valueOf(u));
            
            if ((long) y.get("price_credit") == 125000)
            {
                Slot1.put(y.get("name"), y);
            }
            else if ((long) y.get("price_credit") == 250000)
            {
                Slot2.put(y.get("name"), y);
            }
            else if ((long) y.get("price_credit") == 500000)
            {
                Slot3.put(y.get("name"), y);
            }
            else if ((long) y.get("price_credit") == 1000000)
            {
                Slot4.put(y.get("name"), y);
            }
            else if ((long) y.get("price_credit") == 2000000)
            {
                Slot5.put(y.get("name"), y);
            }
            else if ((long) y.get("price_credit") == 3000000)
            {
                Slot6.put(y.get("name"), y);
            }
        });        

        upgradesJSON.put("Slot 1", Slot1);
        upgradesJSON.put("Slot 2", Slot2);
        upgradesJSON.put("Slot 3", Slot3);
        upgradesJSON.put("Slot 4", Slot4);
        upgradesJSON.put("Slot 5", Slot5);
        upgradesJSON.put("Slot 6", Slot6);

        x.remove("upgrades");
        x.put("upgrades", upgradesJSON);
    }
}
