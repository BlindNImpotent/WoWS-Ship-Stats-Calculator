package WoWSSSC.model.WoWSAPI.info;

import WoWSSSC.model.WoWSAPI.info.ShipTypeImages.ShipTypeImages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Qualson-Lee on 2016-11-24.
 */
@Data
public class Encyclopedia
{
    private long ships_updated_at;
    private HashMap<String, String> ship_types;
    private HashMap<String, String> languages;
    private HashMap<String, String> ship_modifications;
    private HashMap<String, String> ship_modules;
    private ShipTypeImages ship_type_images;
    private LinkedHashMap<String, String> ship_nations = new LinkedHashMap<>();
    private String game_version;
    private int version;
    private String region;

    private String shipUpdatedDate;

    public void setShip_nations(HashMap<String, String> ship_nations)
    {
        List<String> shipKey = new ArrayList<>(ship_nations.keySet());
        shipKey.sort(Comparator.naturalOrder());

        shipKey.forEach(nation -> this.ship_nations.put(nation, ship_nations.get(nation).replace(".", "")));
    }

    public String getShipUpdatedDate()
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(ships_updated_at * 1000));
    }
}
