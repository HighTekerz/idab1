/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.robot.commands.DriveSelectedMotor;
import org.usfirst.frc.team3574.robot.commands.ShiftSolenoid;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
//	public static Joystick testerStick = new Joystick(0);
	public OI(){
//		JoystickButton shiftSolenoid = new JoystickButton(testerStick, 1);
//		shiftSolenoid.whenPressed(new ShiftSolenoid());
		
		SmartDashboard.putData(new ShiftSolenoid());
		SmartDashboard.putData(new DriveSelectedMotor());
	}	
}
