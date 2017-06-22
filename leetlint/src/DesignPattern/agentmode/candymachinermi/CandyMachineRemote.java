package DesignPattern.agentmode.candymachinermi;

import DesignPattern.agentmode.candymachine.State;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CandyMachineRemote extends Remote{
	public String  getLocation() throws RemoteException;
	public int getCount() throws RemoteException;
	public State getstate() throws RemoteException;
}
