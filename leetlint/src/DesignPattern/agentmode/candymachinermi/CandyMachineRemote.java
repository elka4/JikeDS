package DesignPattern.agentmode.candymachinermi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import DesignPattern.agentmode.candymachine.State;

public interface CandyMachineRemote extends Remote{
	public String  getLocation() throws RemoteException;
	public int getCount() throws RemoteException;
	public State getstate() throws RemoteException;
}
