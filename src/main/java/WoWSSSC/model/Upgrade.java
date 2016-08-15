package WoWSSSC.model;

import lombok.Data;
import org.json.simple.JSONObject;

/**
 * Created by Aesis on 2016-08-15.
 */
@Data
public class Upgrade
{
    private String code;
    private String name;
    private long slot;
    private JSONObject json;
    private String image;

    public Upgrade(String code, String name, long slot, JSONObject json, String image)
    {
        this.code = code;
        this.name = name;
        this.slot = slot;
        this.json = json;
        this.image = image;
    }


}