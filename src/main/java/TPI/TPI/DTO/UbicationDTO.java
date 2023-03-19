package TPI.TPI.DTO;

import lombok.Data;

@Data
public class UbicationDTO {
    private Integer id;
    private String lat;
    private String lng;

    public boolean isNull(){
        return this.id == null || this.lat == null || this.lng == null;
    }
}
