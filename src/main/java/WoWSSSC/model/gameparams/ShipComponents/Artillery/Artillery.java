package WoWSSSC.model.gameparams.ShipComponents.Artillery;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Aesis on 2017-05-03.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artillery
{
    private LinkedHashMap<String, Turret> turrets = new LinkedHashMap<>();
    private List<Turret> turretsList = new ArrayList<>();

    private LinkedHashMap<String, LinkedHashMap> AuraFar;
    private LinkedHashMap<String, LinkedHashMap> AuraMedium;
    private LinkedHashMap<String, LinkedHashMap> AuraNear;
    private int aimLevel;
    private LinkedHashMap<String, Integer> ammoPool;
    private float maxDist;
    private float minDistH;
    private float minDistV;
    private boolean normalDistribution;
    private float sigmaCount;
    private float taperDist;
    private List<String> targetTypeIgnore;
    private float targetWeightAllyAttacker;
    private float targetWeightDist;
    private float targetWeightHealth;
    private float targetWeightIncoming;
    private float targetWeightMaxDist;
    private float targetWeightMaxHealth;
    private float targetWeightMinDist;
    private float targetWeightMinHealth;
    private float targetWeightMyAttacker;
    private float targetWeightOutcoming;
    private float targetWeightType;
    private LinkedHashMap<String, List> targetWeightTypeTable;
    private float targetWeightYaw;

    private int barrelDiameter;
    private long penetrationHE;
    private long penetrationHEBefore;
    private float penetrationHEFloat;
    private long penetraionIFHEBefore;
    private long penetraionIFHEAfter;
    private int overmatch;

    private APShell APShell;

    @JsonIgnore
    private ObjectMapper mapper = new ObjectMapper();

    @JsonAnySetter
    public void setTurrets(String name, Object value)
    {
        if (mapper.convertValue(value, LinkedHashMap.class).get("HitLocationArtillery") != null)
        {
            Turret turret = mapper.convertValue(value, Turret.class);
            turrets.put(name, turret);
            turretsList.add(turret);

            if (barrelDiameter < turret.getBarrelDiameter() * 1000)
            {
                barrelDiameter = (int) (turret.getBarrelDiameter() * 1000);
                overmatch = (int) Math.floor(barrelDiameter / 14.3);
            }
        }
    }

    public void setPenetrationHE(float penetrationHE)
    {
        this.penetrationHEBefore = Math.round(penetrationHE);
        this.penetrationHE = Math.round(penetrationHE) - 1;
    }

    public void setPenetrationHEWithNation(String nation, String shipType, String shipName)
    {
        if (
                ("germany".equalsIgnoreCase(nation) && ("Battleship".equalsIgnoreCase(shipType) || "Cruiser".equalsIgnoreCase(shipType) || shipName.contains("Graf Spee")))
                || ("uk".equalsIgnoreCase(nation) && "Battleship".equalsIgnoreCase(shipType)))
        {
            penetrationHEBefore = Math.round((float) barrelDiameter / 4);
            penetrationHE = Math.round((float) barrelDiameter / 4) - 1;
            penetrationHEFloat = (float) barrelDiameter / 4 ;
            penetraionIFHEBefore = Math.round((float) barrelDiameter / 4 * 1.3);
            penetraionIFHEAfter = penetraionIFHEBefore - 1;
        }
        else
        {
            penetrationHEBefore = Math.round((float) barrelDiameter / 6);
            penetrationHE = Math.round((float) barrelDiameter / 6) - 1;
            penetrationHEFloat = (float) barrelDiameter / 6;
            penetraionIFHEBefore = Math.round((float) barrelDiameter / 6 * 1.3);
            penetraionIFHEAfter = penetraionIFHEBefore - 1;
        }
    }
}
