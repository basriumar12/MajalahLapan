package id.go.lapan.majalahlapan.data.array.current;

// Model Data Current
// idPart, title Part, pdfUrl, editors

public class DataCurrent {
    private int idPart;
    private String titlePart;
    private String pdfUrl;
    private String editors;

    public DataCurrent(int idPart, String titlePart, String pdfUrl, String editors) {
        this.idPart = idPart;
        this.titlePart = titlePart;
        this.pdfUrl = pdfUrl;
        this.editors = editors;
    }

    public int getIdPart() {
        return idPart;
    }


    public String getTitlePart() {
        return titlePart;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public String getEditors() {
        return editors;
    }

}
