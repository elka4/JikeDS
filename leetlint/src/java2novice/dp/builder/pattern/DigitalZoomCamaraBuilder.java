package java2novice.dp.builder.pattern;
 
public class DigitalZoomCamaraBuilder extends CamaraBuilder {
 
    @Override
    public void buildOpticalZome() {
        this.cam.setOpticalZoom("None");
    }
 
    @Override
    public void buildDigitalZome() {
        this.cam.setDigitalZoom("10X Optical Zoom");
    }
 
    @Override
    public void buildDisplay() {
        this.cam.setDisplaySize("4 inch LED screen");
    }
 
    @Override
    public void buildMemory() {
        this.cam.setInMemorySize("16GB inbuilt memory");
    }
}
