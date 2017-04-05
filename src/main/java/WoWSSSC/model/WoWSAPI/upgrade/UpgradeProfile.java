package WoWSSSC.model.WoWSAPI.upgrade;

import WoWSSSC.model.WoWSAPI.upgrade.profile.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by Qualson-Lee on 2016-11-17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpgradeProfile
{
    private Anti_Aircraft anti_aircraft;
    private Artillery artillery;
    private ATBA atba;
    private Concealment concealment;
    private Damage_Control damage_control;
    private Engine engine;
    private Fire_Control fire_control;
    private Flight_Control flight_control;
    private Guidance guidance;
    private Mainweapon mainweapon;
    private Planes planes;
    private Powder powder;
    private Secondweapon secondweapon;
    private Spotting spotting;
    private Steering steering;
    private Torpedoes torpedoes;
}