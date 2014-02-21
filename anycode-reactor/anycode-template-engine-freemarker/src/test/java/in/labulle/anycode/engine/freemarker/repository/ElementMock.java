package in.labulle.anycode.engine.freemarker.repository;


public class ElementMock {
    private String pack;
    
	private String name;



	
	public ElementMock(String pack, String name) {
        super();
        this.pack = pack;
        this.name = name;
    }

    public String getName() {
		return name;
	}
    
    public void setName(String name) {
        this.name = name;
    }
	
	public String getPackage() {
        return pack;
    }
	
	public void setPack(String pack) {
        this.pack = pack;
    }

	

}
