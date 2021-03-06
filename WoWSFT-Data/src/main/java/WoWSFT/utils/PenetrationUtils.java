package WoWSFT.utils;

import WoWSFT.model.gameparams.ship.component.artillery.Shell;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PenetrationUtils
{
    // SHELL CONSTANTS
    private static final double G = 9.80665; // GRAVITY # N / kg
//    private static final double G = 9.8; // Gravity used by WG
    private static final double T_0 = 288.15; // TEMPERATURE AT SEA LEVEL # K
    private static final double L = 0.0065; // TEMPERATURE LAPSE RATE # K / m
    private static final double P_0 = 101325.0; // PRESSURE AT SEA LEVEL # Pa
    private static final double R = 8.31447; // UNIV GAS CONSTANT # N.m / (mol.K)
    private static final double M = 0.0289644; // MOLAR MASS OF AIR # kg / mol

    public static void setPenetration(Shell shell, double maxVertAngle, double minDistV, double maxDist, boolean apShell)
    {
        // SHELL CONSTANTS
        double C = 0.5561613; // PENETRATION
        double W = shell.getBulletMass(); // SHELL WEIGHT
        double D = shell.getBulletDiametr(); // SHELL DIAMETER
        double c_D = shell.getBulletAirDrag();  // SHELL DRAG
        double V_0 = shell.getBulletSpeed(); // SHELL MUZZLE VELOCITY
        double K = shell.getBulletKrupp(); // SHELL KRUPP
        double ricochet = (shell.getBulletAlwaysRicochetAt() + shell.getBulletCapNormalizeMaxAngle()) * Math.PI / 180.0; // Ignores after ricochet

        double cw_1 = 1.0; // QUADRATIC DRAG COEFFICIENT
        double cw_2 = 100.0 + (1000.0 / 3.0) * D; // LINEAR DRAG COEFFICIENT

        C = C * K / 2400.0; // KRUPP INCLUSION
        double k = 0.5 * c_D * Math.pow((D / 2.0), 2.0) * Math.PI / W; // CONSTANTS TERMS OF DRAG

//        List<Double> alpha = linspace(Math.PI * maxVertAngle / 360.0 * 2.0); // ELEV. ANGLES 0...MAX
        List<Double> alpha = linspace(maxVertAngle); // ELEV. ANGLES 0...MAX
        double dt = 0.03; // TIME STEP

        LinkedHashMap<String, Double> penetration = new LinkedHashMap<>();
        LinkedHashMap<String, Double> flightTime = new LinkedHashMap<>();
        LinkedHashMap<String, Double> impactAngle = new LinkedHashMap<>();
//        LinkedHashMap<String, Double> launchAngle = new LinkedHashMap<>();
        List<String> distanceList = new ArrayList<>();

        // for each alpha angle do:
        for (int i = 0; i < alpha.size(); i++) {
            double v_x = Math.cos(alpha.get(i)) * V_0;
            double v_y = Math.sin(alpha.get(i)) * V_0;

            double y = 0.0;
            double x = 0.0;
            double t = 0.0;

            while (y >= 0) { // follow flight path until shell hits ground again
                x = x + dt * v_x;
                y = y + dt * v_y;

                double T = T_0 - L * y;
                double p = P_0 * Math.pow(1 - L * y / T_0, (G * M / (R * L)));
                double rho = p * M / (R * T);

                v_x = v_x - dt * k * rho * (cw_1 * Math.pow(v_x, 2) + cw_2 * v_x);
                v_y = v_y - dt * G - dt * k * rho * (cw_1 * Math.pow(v_y, 2) + cw_2 * Math.abs(v_y)) * Math.signum(v_y);

                t = t + dt;
            }

            double v_total = Math.pow((Math.pow(v_y, 2.0) + Math.pow(v_x, 2.0)), 0.5);
            double p_athit = C * Math.pow(v_total, 1.1) * Math.pow(W, 0.55) / Math.pow(D * 1000.0, 0.65); // PENETRATION FORMULA
            double IA = Math.atan(Math.abs(v_y) / Math.abs(v_x)); // IMPACT ANGLE ON BELT ARMOR

            if (IA > ricochet || x > maxDist * 1.25 || x > 25000) {
                break;
            }

            x = CommonUtils.getDecimalRounded(x, 4);
            String xString = String.valueOf(x);

            flightTime.put(xString, CommonUtils.getDecimalRounded(t / 3.0, 5));

            if (apShell) {
                penetration.put(xString, CommonUtils.getDecimalRounded(Math.cos(IA) * p_athit, 5));
                impactAngle.put(xString, CommonUtils.getDecimalRounded(IA * 180.0 / Math.PI, 5));
                distanceList.add(xString);
//                launchAngle.put(xString, alpha.get(i));
            }
        }

        if (apShell) {
            shell.setShell(flightTime, penetration, impactAngle, distanceList, null, minDistV, true);
        } else {
            shell.setShell(flightTime, null, null, null, null, minDistV, false);
        }
    }

    public static double getMidAtY(double x1, double y1, double x2, double y2, double yAxis)
    {
        double a = (y2 - y1) / (x2 - x1);
        double c = y1 - (a * x1);

        return (yAxis - c) / a;
    }

    private static List<Double> linspace(double end)
    {
        double begin = 0.0;
        double countDeg = 0.0;
        double intervalDeg = 0.05;
        List<Double> alpha = new ArrayList<>();

        while (countDeg <= end) {
            alpha.add(begin);
            countDeg += intervalDeg;
            begin = CommonUtils.getDecimalRounded(Math.PI * countDeg / 180.0, 9);
        }

        return alpha;
    }
}
