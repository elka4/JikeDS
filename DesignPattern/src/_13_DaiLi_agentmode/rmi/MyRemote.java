package _13_DaiLi_agentmode.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote{

	public String sayHello() throws RemoteException;
	
}
