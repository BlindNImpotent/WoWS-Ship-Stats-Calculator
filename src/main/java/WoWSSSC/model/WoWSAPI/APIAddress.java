package WoWSSSC.model.WoWSAPI;

import lombok.Data;

/**
 * Created by Qualson-Lee on 2017-06-09.
 */
@Data
public class APIAddress
{
    private final String API_RU = "https://api.worldofwarships.ru/wows/encyclopedia";
    private final String API_NA = "https://api.worldofwarships.com/wows/encyclopedia";

    private String API_Starter;

    public void setAddress(String country)
    {
        if ("ru".equalsIgnoreCase(country))
        {
            API_Starter = API_RU;
        }
        else
        {
            API_Starter = API_NA;
        }
    }
}