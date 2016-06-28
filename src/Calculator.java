import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Calculator 
{
	private Calc calc;
	
	/**
	 * Calculates ship stats with parameter.
	 * @param aShip
	 * @param mod1
	 * @param mod2
	 * @param mod3
	 * @param mod4
	 * @param mod5
	 * @param mod6
	 * @param conceal
	 * @param survivability
	 * @param AFT
	 * @param EM
	 * @param BFT
	 * @param TAE
	 * @param TA
	 * @param BoS
	 * @param concealCamo
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Calc calculate(
			String aShip, String mod1, String mod2, String mod3, String mod4, String mod5, String mod6, 
			boolean conceal, boolean survivability, boolean AFT, boolean EM, boolean BFT, boolean TAE, boolean TA, boolean BoS,
			boolean concealCamo) 
					throws FileNotFoundException, IOException, ParseException
	{
		calc = new Calc(aShip);
		
		if (calc.getModule1() != null)
		{
			if (mod1.equals("MainWeapon_Mod_I"))
			{
				calc.calcMainArmamentsMod1();
			}
			else if (mod1.equals("SecondaryWeapon_Mod_I"))
			{
				calc.calcAuxiliaryArmamentsMod1();
			}
			else if (mod1.equals("PowderMagazine_Mod_I"))
			{
				calc.calcMagazineMod1();
			}
			else if (mod1.equals("MainGun_Mod_II"))
			{
				calc.calcMainBatteryMod2();
			}
			else if (mod1.equals("AirDefense_Mod_II"))
			{
				calc.calcAAGunsMod2();
			}
			else if (mod1.equals("SecondaryGun_Mod_II"))
			{
				calc.calcSecondaryBatteryMod2();
			}
			else if (mod1.equals("FireControl_Mod_I_US"))
			{
				calc.calcArtilleryPlottingRoomMod1US();
			}
			else if (mod1.equals("Guidance_Mod_I"))
			{
				calc.calcAimingSystemsModification1();
			}
			else if (mod1.equals("MainGun_Mod_III"))
			{
				calc.calcMainBatteryMod3();
			}
			else if (mod1.equals("AirDefense_Mod_III"))
			{
				calc.calcAAGunsMod3();
			}
			else if (mod1.equals("SecondaryGun_Mod_III"))
			{
				calc.calcSecondaryBatteryMod3();
			}
			else if (mod1.equals("FireControl_Mod_II_US"))
			{
				calc.calcArtilleryPlottingRoomMod2US();
			}
			else if (mod1.equals("FireControl_Mod_II"))
			{
				calc.calcGunFireControlSystemMod2();
			}
			else if (mod1.equals("Torpedo_Mod_III"))
			{
				
			}
			else if (mod1.equals("DamageControl_Mod_I"))
			{
				calc.calcDamageControlSystemMod1();
			}
			else if (mod1.equals("Engine_Mod_I"))
			{
				calc.calcPropulsionMod1();
			}
			else if (mod1.equals("SteeringGear_Mod_I"))
			{
				calc.calcSteeringGearsMod1();
			}
			else if (mod1.equals("DamageControl_Mod_II"))
			{
				calc.calcDamageControlSystemMod2();
			}
			else if (mod1.equals("Engine_Mod_II"))
			{
				calc.calcPropulsionMod2();
			}
			else if (mod1.equals("SteeringGear_Mod_II"))
			{
				calc.calcSteeringGearsMod2();
			}
			else if (mod1.equals("LookoutStation_Mod_I"))
			{
				calc.calcTargetAcquisitionSystemMod1();
			}
			else if (mod1.equals("ConcealmentMeasures_Mod_I"))
			{
				calc.calcConcealSystemMod1();
			}
		}
		if (calc.getModule2() != null)
		{
			if (mod2.equals("MainGun_Mod_II"))
			{
				calc.calcMainBatteryMod2();
			}
			else if (mod2.equals("AirDefense_Mod_II"))
			{
				calc.calcAAGunsMod2();
			}
			else if (mod2.equals("SecondaryGun_Mod_II"))
			{
				calc.calcSecondaryBatteryMod2();
			}
			else if (mod2.equals("FireControl_Mod_I_US"))
			{
				calc.calcArtilleryPlottingRoomMod1US();
			}
			else if (mod2.equals("Guidance_Mod_I"))
			{
				calc.calcAimingSystemsModification1();
			}
			else if (mod2.equals("MainGun_Mod_III"))
			{
				calc.calcMainBatteryMod3();
			}
			else if (mod2.equals("AirDefense_Mod_III"))
			{
				calc.calcAAGunsMod3();
			}
			else if (mod2.equals("SecondaryGun_Mod_III"))
			{
				calc.calcSecondaryBatteryMod3();
			}
			else if (mod2.equals("FireControl_Mod_II_US"))
			{
				calc.calcArtilleryPlottingRoomMod2US();
			}
			else if (mod2.equals("FireControl_Mod_II"))
			{
				calc.calcGunFireControlSystemMod2();
			}
			else if (mod2.equals("Torpedo_Mod_III"))
			{
				
			}
			else if (mod2.equals("DamageControl_Mod_I"))
			{
				calc.calcDamageControlSystemMod1();
			}
			else if (mod2.equals("Engine_Mod_I"))
			{
				calc.calcPropulsionMod1();
			}
			else if (mod2.equals("SteeringGear_Mod_I"))
			{
				calc.calcSteeringGearsMod1();
			}
			else if (mod2.equals("DamageControl_Mod_II"))
			{
				calc.calcDamageControlSystemMod2();
			}
			else if (mod2.equals("Engine_Mod_II"))
			{
				calc.calcPropulsionMod2();
			}
			else if (mod2.equals("SteeringGear_Mod_II"))
			{
				calc.calcSteeringGearsMod2();
			}
			else if (mod2.equals("LookoutStation_Mod_I"))
			{
				calc.calcTargetAcquisitionSystemMod1();
			}
			else if (mod2.equals("ConcealmentMeasures_Mod_I"))
			{
				calc.calcConcealSystemMod1();
			}
		}
		if (calc.getModule3() != null)
		{
			if (mod3.equals("MainGun_Mod_III"))
			{
				calc.calcMainBatteryMod3();
			}
			else if (mod3.equals("AirDefense_Mod_III"))
			{
				calc.calcAAGunsMod3();
			}
			else if (mod3.equals("SecondaryGun_Mod_III"))
			{
				calc.calcSecondaryBatteryMod3();
			}
			else if (mod3.equals("FireControl_Mod_II_US"))
			{
				calc.calcArtilleryPlottingRoomMod2US();
			}
			else if (mod3.equals("FireControl_Mod_II"))
			{
				calc.calcGunFireControlSystemMod2();
			}
			else if (mod3.equals("Torpedo_Mod_III"))
			{
				
			}
			else if (mod3.equals("DamageControl_Mod_I"))
			{
				calc.calcDamageControlSystemMod1();
			}
			else if (mod3.equals("Engine_Mod_I"))
			{
				calc.calcPropulsionMod1();
			}
			else if (mod3.equals("SteeringGear_Mod_I"))
			{
				calc.calcSteeringGearsMod1();
			}
			else if (mod3.equals("DamageControl_Mod_II"))
			{
				calc.calcDamageControlSystemMod2();
			}
			else if (mod3.equals("Engine_Mod_II"))
			{
				calc.calcPropulsionMod2();
			}
			else if (mod3.equals("SteeringGear_Mod_II"))
			{
				calc.calcSteeringGearsMod2();
			}
			else if (mod3.equals("LookoutStation_Mod_I"))
			{
				calc.calcTargetAcquisitionSystemMod1();
			}
			else if (mod3.equals("ConcealmentMeasures_Mod_I"))
			{
				calc.calcConcealSystemMod1();
			}
		}
		if (calc.getModule4() != null)
		{
			if (mod4.equals("DamageControl_Mod_I"))
			{
				calc.calcDamageControlSystemMod1();
			}
			else if (mod4.equals("Engine_Mod_I"))
			{
				calc.calcPropulsionMod1();
			}
			else if (mod4.equals("SteeringGear_Mod_I"))
			{
				calc.calcSteeringGearsMod1();
			}
			else if (mod4.equals("DamageControl_Mod_II"))
			{
				calc.calcDamageControlSystemMod2();
			}
			else if (mod4.equals("Engine_Mod_II"))
			{
				calc.calcPropulsionMod2();
			}
			else if (mod4.equals("SteeringGear_Mod_II"))
			{
				calc.calcSteeringGearsMod2();
			}
			else if (mod4.equals("LookoutStation_Mod_I"))
			{
				calc.calcTargetAcquisitionSystemMod1();
			}
			else if (mod4.equals("ConcealmentMeasures_Mod_I"))
			{
				calc.calcConcealSystemMod1();
			}
		}
		if (calc.getModule5() != null)
		{
			if (mod5.equals("DamageControl_Mod_II"))
			{
				calc.calcDamageControlSystemMod2();
			}
			else if (mod5.equals("Engine_Mod_II"))
			{
				calc.calcPropulsionMod2();
			}
			else if (mod5.equals("SteeringGear_Mod_II"))
			{
				calc.calcSteeringGearsMod2();
			}
			else if (mod5.equals("LookoutStation_Mod_I"))
			{
				calc.calcTargetAcquisitionSystemMod1();
			}
			else if (mod5.equals("ConcealmentMeasures_Mod_I"))
			{
				calc.calcConcealSystemMod1();
			}
		}
		if (calc.getModule6() != null)
		{
			if (mod6.equals("LookoutStation_Mod_I"))
			{
				calc.calcTargetAcquisitionSystemMod1();
			}
			else if (mod6.equals("ConcealmentMeasures_Mod_I"))
			{
				calc.calcConcealSystemMod1();
			}
		}		
		if (conceal == true)
		{
			calc.calcConcealmentExpert();
		}		
		if (survivability == true)
		{
			calc.calcSurvivabilityExpert();
		}
		if (BFT == true)
		{
			calc.calcBasicFiringTraining();
		}
		if (TAE == true)
		{
			calc.calcTorpedoArmamentExpertise();
		}
		if (AFT == true)
		{
			calc.calcAdvancedFiringTraining();
		}
		if (EM == true)
		{
			calc.calcExpertMarksman();
		}
		if (TA == true)
		{
			calc.calcTorpedoAcceleration();
		}
		if (BoS == true)
		{
			calc.calcBasicsOfSurvivability();
		}
		
		
		if (concealCamo == true)
		{
			calc.calcConcealCamo();
		}
		
		return calc;
	}
	

	/**
	 * Searches for ship name and returns Calc class
	 * @param aShip Ship name
	 * @return calc Calc class
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Calc search(String aShip) throws FileNotFoundException, IOException, ParseException 
	{		
		calc = new Calc(aShip);
		return calc;
	}
}
