
package javafxdashtest;


public class Meter {
    private final int ean_nummer;
    private final String postal_code;
    private final String created_at;
    
  
  public Meter( int ean_nummer, String postal_code, String created_at) {
    this.ean_nummer = ean_nummer;
    this.postal_code = postal_code;
    this.created_at = created_at;
 
  }

    public int getEan_nummer() {
        return ean_nummer;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getCreated_at() {
        return created_at;
    }
    
  @Override
  public String toString() {
    return String.format( "ean_nummer: %d  postal_code: %s created_at: %s", ean_nummer, postal_code, created_at);
  }
}