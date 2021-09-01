package web.in312019.entity.DTO;

public class TreningDTO {
    private Long id;

    private String naziv;

    private String opis;

    private String tip;



    public TreningDTO(Long id, String naziv, String opis, String tip) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.tip = tip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }



}