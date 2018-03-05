/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3574.robot.commands.DriveSelectedMotor;
import org.usfirst.frc.team3574.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem
	= new ExampleSubsystem();
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();


	DigitalInput iRCubeSensor1 = new DigitalInput(RobotMap.IRCubeSensor1);
	DigitalInput iRCubeSensor2 = new DigitalInput(RobotMap.IRCubeSensor2);

	DigitalInput SonicGuardrailSensor1 = new DigitalInput(RobotMap.SonicGuardrailSensor1);
	DigitalInput SonicGuardrailSensor2 = new DigitalInput(RobotMap.SonicGuardrailSensor2);

	DigitalInput ColorSensorForFloor = new DigitalInput(RobotMap.ColorSensorForFloor);

	DigitalInput ClawLimitSwitch1 = new DigitalInput(RobotMap.ClawLimitSwitch1);
	DigitalInput ClawLimitSwitch2 = new DigitalInput(RobotMap.ClawLimitSwitch2);

	I2C timeOfFlightForScale = new I2C(Port.kOnboard, 0x29);

	public static TalonSRX currentTalon;
	public static Object currentSolenoid;

	DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.Solenoid0, RobotMap.Solenoid1);

	Solenoid solenoidArray[] = new Solenoid[6];

	SendableChooser<Object> solenoidChooser = new SendableChooser<Object>();

	TalonSRX talonArray[] = new TalonSRX[6];

	SendableChooser<TalonSRX> talonChooser = new SendableChooser<TalonSRX>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		talonArray[0] = new TalonSRX(RobotMap.Motor1);
		talonArray[1] = new TalonSRX(RobotMap.Motor2);
		talonArray[2] = new TalonSRX(RobotMap.Motor3);
		talonArray[3] = new TalonSRX(RobotMap.Motor4);
		talonArray[4] = new TalonSRX(RobotMap.Motor5);
		talonArray[5] = new TalonSRX(RobotMap.Motor6);

		solenoidArray[1] = new Solenoid(RobotMap.Solenoid2);
		solenoidArray[2] = new Solenoid(RobotMap.Solenoid3);
		solenoidArray[3] = new Solenoid(RobotMap.Solenoid4);
		solenoidArray[4] = new Solenoid(RobotMap.Solenoid5);
		solenoidArray[5] = new Solenoid(RobotMap.Solenoid6);


		talonChooser.addDefault("Motor 1", talonArray[0]);
		for (int i = 1; i < talonArray.length; i++) {			
			talonChooser.addObject("Motor " +(i +1), talonArray[i]);
		}
		
		solenoidChooser.addObject("Shifter", shifter);
		solenoidChooser.addDefault("Solenoid 1", solenoidArray[0]);
		for (int i = 1; i < solenoidArray.length; i++) {			
			solenoidChooser.addObject("Solenoid " +(i +1), solenoidArray[i]);
		}

		SmartDashboard.putData("Solenoids", solenoidChooser);
		SmartDashboard.putData("Motors", talonChooser);

		m_oi = new OI();
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		(new DriveSelectedMotor()).start();

		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		currentTalon = talonChooser.getSelected();
		currentSolenoid = solenoidChooser.getSelected();

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public static double getLeftStickYPlusDeadzone(Joystick stickToTest) {
		if (stickToTest.getRawAxis(1) > -0.05 && stickToTest.getRawAxis(1) < 0.05) {
			return 0.0;
		}
		else {
			return -stickToTest.getRawAxis(1);
		}
	}

	public void log () {
		SmartDashboard.putData(iRCubeSensor1);
		SmartDashboard.putData(iRCubeSensor2);

		SmartDashboard.putData(SonicGuardrailSensor1);
		SmartDashboard.putData(SonicGuardrailSensor2);

		SmartDashboard.putData(ClawLimitSwitch1);
		SmartDashboard.putData(ClawLimitSwitch1);
	}
}
