/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int Solenoid0 = 0;
	public static int Solenoid1 = 1;
	public static int Solenoid2 = 2;
	public static int Solenoid3 = 3;
	public static int Solenoid4 = 4;
	public static int Solenoid5 = 5;
	public static int Solenoid6 = 6;

	public static int Motor1 = 1;
	public static int Motor2 = 2;
	public static int Motor3 = 3;
	public static int Motor4 = 4;
	public static int Motor5 = 5;
	public static int Motor6 = 6;
	
	public static int Spike1 = 1;
	public static int Spike2 = 2;
		
	public static int Pigeon = Motor2;
	
	public static int TimeOfFlightForScale = 1;
	public static int IRCubeSensor1 = 2;
	public static int IRCubeSensor2 = 3;
	public static int SonicGuardrailSensor1 = 4;
	public static int SonicGuardrailSensor2 = 5;
	public static int ColorSensorForFloor = 6;
	public static int ClawLimitSwitch1 = 7;
	public static int ClawLimitSwitch2 = 8;
}
