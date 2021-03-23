
public class InterfacePhoto {

	public static Photo creationPhotoDepuisImage(String path)
	{
		Photo p = new Photo(squellete_appli.conn);
		p.setChemin(path);
		return p;
	}
}
