package java2novice.dp.abstractfactory;
 
public class MacOsFactory implements ButtonFactory {
 
    @Override
    public Button createButton() {
        /**
         * this method creates a button for MAC OS
         * button
         */
        System.out.println("creating mac os button...");
        return new MacButton();
    }
 
}
