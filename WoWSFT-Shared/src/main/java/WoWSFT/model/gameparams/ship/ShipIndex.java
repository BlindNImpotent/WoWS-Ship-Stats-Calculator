package WoWSFT.model.gameparams.ship;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShipIndex
{
    private String identifier;
    private String index;
    private String prevShipIndex;
    private String prevShipName;
    private boolean research;
    private int costXP;
    private int prevShipXP;
    private int prevShipCompXP;
    private int position;
    private List<String> arties;

    public ShipIndex(String identifier, String index, String prevShipIndex, String prevShipName, boolean research, int costXP, int prevShipXP, int prevShipCompXP, List<String> arties)
    {
        this.identifier = identifier;
        this.index = index;
        this.prevShipIndex = prevShipIndex;
        this.prevShipName = prevShipName;
        this.research = research;
        this.costXP = costXP;
        this.prevShipXP = prevShipXP;
        this.prevShipCompXP = prevShipCompXP;
        this.arties = arties;
    }
}
