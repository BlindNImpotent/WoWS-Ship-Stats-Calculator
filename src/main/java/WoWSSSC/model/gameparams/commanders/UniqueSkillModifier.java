package WoWSSSC.model.gameparams.commanders;

import lombok.Data;

@Data
public class UniqueSkillModifier
{
    private int numConsumables;
    private float regenerationHPSpeed;
    private float reloadCoeff;
    private int workTime;

    private int uniqueType;
}