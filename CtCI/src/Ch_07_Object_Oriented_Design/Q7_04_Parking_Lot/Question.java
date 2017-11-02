package Ch_07_Object_Oriented_Design.Q7_04_Parking_Lot;

import CtCILibrary.AssortedMethods;

public class Question {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParkingLot lot = new ParkingLot();
		
		Vehicle v = null;
		while (v == null || lot.parkVehicle(v)) {
			lot.print();
			int r = AssortedMethods.randomIntInRange(0, 10);
			if (r < 2) {
				v = new Bus();
			} else if (r < 4) {
				v = new Motorcycle();
			} else {
				v = new Car();
			}
			System.out.print("\nParking a ");
			v.print();
			System.out.println("");
		}
		System.out.println("Parking Failed. Final state: ");
		lot.print();
	}
/*
                Level0:   lllllllccc  cccccccccc  cccmmmmmmm
                Level1:   lllllllccc  cccccccccc  cccmmmmmmm
                Level2:   lllllllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   Cllllllccc  cccccccccc  cccmmmmmmm
                Level1:   lllllllccc  cccccccccc  cccmmmmmmm
                Level2:   lllllllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CClllllccc  cccccccccc  cccmmmmmmm
                Level1:   lllllllccc  cccccccccc  cccmmmmmmm
                Level2:   lllllllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCllllccc  cccccccccc  cccmmmmmmm
                Level1:   lllllllccc  cccccccccc  cccmmmmmmm
                Level2:   lllllllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a B
                Level0:   CCCllllccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   lllllllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a B
                Level0:   CCCllllccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCClllccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCCCllccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   lllllllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a B
                Level0:   CCCCCllccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCCCClccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   lllllllccc  cccccccccc  cccmmmmmmm


                Parking a B
                Level0:   CCCCCClccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCCCCCccc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCCCCCCcc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCCCCCCCc  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a M
                Level0:   CCCCCCCCCM  cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCCCCCCCM  Cccccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a M
                Level0:   CCCCCCCCCM  CMcccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a M
                Level0:   CCCCCCCCCM  CMMccccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a M
                Level0:   CCCCCCCCCM  CMMMcccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a C
                Level0:   CCCCCCCCCM  CMMMCccccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a M
                Level0:   CCCCCCCCCM  CMMMCMcccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm


                Parking a B
                Parking Failed. Final state:
                Level0:   CCCCCCCCCM  CMMMCMcccc  cccmmmmmmm
                Level1:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level2:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level3:   BBBBBllccc  cccccccccc  cccmmmmmmm
                Level4:   BBBBBllccc  cccccccccc  cccmmmmmmm
 */
}
