package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftSolenoid extends Command {

	public ShiftSolenoid() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if ((Robot.currentSolenoid) instanceof Solenoid) {
			
			System.out.println(((Solenoid)Robot.currentSolenoid).get());
			
			((Solenoid)Robot.currentSolenoid).set(!((Solenoid)Robot.currentSolenoid).get());
		}
		else if((Robot.currentSolenoid) instanceof DoubleSolenoid) {
			System.out.println(((DoubleSolenoid)Robot.currentSolenoid).get());
			switch (((DoubleSolenoid)Robot.currentSolenoid).get()){
			case kForward:
				((DoubleSolenoid)Robot.currentSolenoid).set(Value.kOff);
				break;
			case kOff:
				((DoubleSolenoid)Robot.currentSolenoid).set(Value.kReverse);
				break;
			case kReverse:
				((DoubleSolenoid)Robot.currentSolenoid).set(Value.kForward);
				break;
			}
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
