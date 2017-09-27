package java2novice.dp.builder.pattern;
 
public abstract class CamaraBuilder {
 
    protected Camara cam;
     
    public Camara getCamara(){
        return this.cam;
    }
     
    public void createNewCamara(){
        this.cam = new Camara();
    }
     
    public abstract void buildOpticalZome();
    public abstract void buildDigitalZome();
    public abstract void buildDisplay();
    public abstract void buildMemory();
}
