package WoWSFT.model.gameparams.ship.component.atba;

import WoWSFT.config.WoWSFT;
import WoWSFT.model.gameparams.ship.component.airdefense.Aura;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@WoWSFT
@JsonIgnoreProperties(ignoreUnknown = true)
public class ATBA
{
    private Aura auraFar;
    private Aura auraMedium;
    private Aura auraNear;
    private List<Secondary> turrets = new ArrayList<>();

    private float maxDist;
    private float minDistH;
    private float minDistV;
    private float sigmaCount;
    private float taperDist;

    @JsonIgnore
    private ObjectMapper mapper = new ObjectMapper();

    @JsonAnySetter
    public void setGuns(String name, Object value)
    {
        if (name.contains("Far")) {
            auraFar = mapper.convertValue(value, Aura.class);
        } else if (name.contains("Medium")) {
            auraMedium = mapper.convertValue(value, Aura.class);
        } else if (name.contains("Near")) {
            auraNear = mapper.convertValue(value, Aura.class);
        } else if (value instanceof HashMap) {
            HashMap<String, Object> tempObject = mapper.convertValue(value, new TypeReference<HashMap<String, Object>>(){});

            if (tempObject.containsKey("HitLocationATBA")) {
                turrets.add(mapper.convertValue(value, Secondary.class));
            }
        }
    }
}