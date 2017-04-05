package WoWSSSC.model.WoWSAPI.shipprofile.profile;

import WoWSSSC.model.WoWSAPI.shipprofile.profile.dive_bomber.Dive_Bomber_Accuracy;
import WoWSSSC.model.WoWSAPI.shipprofile.profile.dive_bomber.Dive_Bomber_Count_In_Squadron;
import lombok.Data;

/**
 * Created by Aesis on 2016-11-18.
 */
@Data
public class Dive_Bomber
{
    private long bomb_bullet_mass;
    private float bomb_burn_probability;
    private long bomb_damage;
    private String bomb_name;
    private float cruise_speed;
    private long dive_bomber_id;
    private String dive_bomber_id_str;
    private float gunner_damage;
    private long max_damage;
    private float max_health;
    private String name;
    private long plane_level;
    private float prepare_time;
    private long squadrons;
    private Dive_Bomber_Accuracy accuracy;
    private Dive_Bomber_Count_In_Squadron count_in_squadron;
}