package com.patterns.design.structural;

import java.io.IOException;

//proxy object provide a surrogate or placeholder for another object to control access to it
//proxy is created due to many reasons e.g. security reasons or cost associated with creating fully initialized original object
//Proxy is heavily used to implement lazy loading related usecases where we do not want to create full object until it is actually needed.
//proxy can be used to add an additional security layer around the original object as well

public class ProxyDesignPatternTester {

	public static void main(String[] args) {
		CommandExecutor executor = new CommandExecutorProxy("Guru", "7211821");
		try {
			executor.runCommand("add guru");
			executor.runCommand("remove guru");
		} catch (Exception e) {
			System.out.println("Exception Message::"+e.getMessage());
		}
	}
}

interface CommandExecutor {

	public void runCommand(String cmd) throws Exception;
}

class CommandExecutorImpl implements CommandExecutor {

	@Override
	public void runCommand(String cmd) throws IOException {
                //some heavy implementation
		//Runtime.getRuntime().exec(cmd);
		System.out.println("'" + cmd + "' command executed.");
	}

}

class CommandExecutorProxy implements CommandExecutor {

	private boolean isAdmin;
	private CommandExecutor executor;
	
	public CommandExecutorProxy(String user, String pwd){
		if("Guru".equals(user) && "7211821".equals(pwd)) isAdmin=true;
		executor = new CommandExecutorImpl();
	}
	
	@Override
	public void runCommand(String cmd) throws Exception {
		if(isAdmin){
			executor.runCommand(cmd);
		}else{
			if(cmd.trim().startsWith("remove")){
				throw new Exception("remove command is not allowed for non-admin users.");
			}else{
				executor.runCommand(cmd);
			}
		}
	}

}


