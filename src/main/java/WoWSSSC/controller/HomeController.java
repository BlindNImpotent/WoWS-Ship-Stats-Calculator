package WoWSSSC.controller;

import WoWSSSC.service.HomeService;
import WoWSSSC.service.JSONService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;

/**
 * Created by Aesis on 2016-08-04.
 */
@Controller
public class HomeController
{
    @Autowired
    private HomeService homeService;

    @Autowired
    private JSONService jsonService;

//    @RequestMapping (value = "/", method = RequestMethod.GET)
//    public String home(Model model) throws IOException, ParseException
//    {
//        model.addAttribute("nameList", homeService.getNameList());
//
//        return "home";
//    }

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String ship(Model model, @RequestParam(required = false, defaultValue = "") String shipName) throws IOException, ParseException
    {
        model.addAttribute("nameList", homeService.getNameList());

        if (!shipName.equals(""))
        {
            jsonService.setShipJSON(shipName);

            model.addAttribute("name", shipName);
            model.addAttribute("shipType", jsonService.getShipType());

            model.addAttribute("imagesMedium", jsonService.getImagesMedium());
            model.addAttribute("ship_id_str", jsonService.getShip_id_str());

            model.addAttribute("nation", jsonService.getNation());
            model.addAttribute("tier", jsonService.getTier());

            model.addAttribute("gpShipJSON", jsonService.getGpShipJSON());

            model.addAttribute("turretUpgradeList", jsonService.getAPI_ArtilleryModule());
            model.addAttribute("hullUpgradeList", jsonService.getAPI_HullModule());
            model.addAttribute("engineUpgradeList", jsonService.getAPI_EngineModule());
            model.addAttribute("radarUpgradeList", jsonService.getAPI_RadarModule());
            model.addAttribute("torpedoUpgradeList", jsonService.getAPI_TorpedoModule());
            model.addAttribute("flightControlUpgradeList", jsonService.getAPI_FlightControlModule());
            model.addAttribute("fighterUpgradeList", jsonService.getAPI_FighterModule());
            model.addAttribute("torpedoBomberUpgradeList", jsonService.getAPI_TorpedoBomberModule());
            model.addAttribute("diveBomberUpgradeList", jsonService.getAPI_DiveBomberModule());

            model.addAttribute("apiTurretJSON", jsonService.getAPI_ArtilleryUpgradeJSON());
            model.addAttribute("apiHullJSON", jsonService.getAPI_HullUpgradeJSON());
            model.addAttribute("apiEngineJSON", jsonService.getAPI_EngineUpgradeJSON());
            model.addAttribute("apiRadarJSON", jsonService.getAPI_RadarUpgradeJSON());
            model.addAttribute("apiTorpedoJSON", jsonService.getAPI_TorpedoUpgradeJSON());
            model.addAttribute("apiFlightControlJSON", jsonService.getAPI_FlightControlUpgradeJSON());
            model.addAttribute("apiFighterJSON", jsonService.getAPI_FighterUpgradeJSON());
            model.addAttribute("apiTorpedoBomberJSON", jsonService.getAPI_TorpedoBomberUpgradeJSON());
            model.addAttribute("apiDiveBomberJSON", jsonService.getAPI_DiveBomberUpgradeJSON());

            model.addAttribute("turretIndexList", jsonService.getAPI_ArtilleryUpgradeIndexList());
            model.addAttribute("hullIndexList", jsonService.getAPI_HullUpgradeIndexList());
            model.addAttribute("engineIndexList", jsonService.getAPI_EngineUpgradeIndexList());
            model.addAttribute("radarIndexList", jsonService.getAPI_RadarUpgradeIndexList());
            model.addAttribute("torpedoIndexList", jsonService.getAPI_TorpedoUpgradeIndexList());
            model.addAttribute("flightControlIndexList", jsonService.getAPI_FlightControlUpgradeIndexList());
            model.addAttribute("fighterIndexList", jsonService.getAPI_FighterUpgradeIndexList());
            model.addAttribute("torpedoBomberIndexList", jsonService.getAPI_TorpedoBomberUpgradeIndexList());
            model.addAttribute("diveBomberIndexList", jsonService.getAPI_DiveBomberUpgradeIndexList());

//            model.addAttribute("modSlot1", jsonService.getModSlot1());
//            model.addAttribute("modSlot2", jsonService.getModSlot2());
//            model.addAttribute("modSlot3", jsonService.getModSlot3());
//            model.addAttribute("modSlot4", jsonService.getModSlot4());
//            model.addAttribute("modSlot5", jsonService.getModSlot5());
//            model.addAttribute("modSlot6", jsonService.getModSlot6());

            model.addAttribute("upgrades1", jsonService.getUpgrades1());
            model.addAttribute("upgrades2", jsonService.getUpgrades2());
            model.addAttribute("upgrades3", jsonService.getUpgrades3());
            model.addAttribute("upgrades4", jsonService.getUpgrades4());
            model.addAttribute("upgrades5", jsonService.getUpgrades5());
            model.addAttribute("upgrades6", jsonService.getUpgrades6());

            model.addAttribute("consume1List", jsonService.getAbil0());
            model.addAttribute("consume2List", jsonService.getAbil1());
            model.addAttribute("consume3List", jsonService.getAbil2());
            model.addAttribute("consume4List", jsonService.getAbil3());

            model.addAttribute("camouflages", jsonService.getCamouflages());
            model.addAttribute("camouflagesIdList", jsonService.getCamouflagesIdList());

            model.addAttribute("flags", jsonService.getFlagsList());
            model.addAttribute("flagsIdList", jsonService.getFlagsIdList());

            model.addAttribute("skills", jsonService.getSkillsList());

        }

        return "home";
    }
}