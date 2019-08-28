package id.go.lapan.majalahlapan.data.array.current;

// Model Data Detail
// idPart, TitleChapter, editors, startPages, endPages, pdfUrl

public class DataCurrentDetail {
    private int idPart;
    private String titleChapter;
    private String editors;
    private String startPages;
    private String endPages;
    private String pdfUrl;

    public DataCurrentDetail(int idPart, String titleChapter, String editors, String startPages, String endPages, String pdfUrl) {
        this.titleChapter = titleChapter;
        this.editors = editors;
        this.startPages = startPages;
        this.endPages = endPages;
        this.pdfUrl = pdfUrl;
        this.idPart = idPart;
    }

    public String getTitleChapter() {
        return titleChapter;
    }


    public String getEditors() {
        return editors;
    }


    public String getStartPages() {
        return startPages;
    }


    public String getEndPages() {
        return endPages;
    }


    public String getPdfUrl() {
        return pdfUrl;
    }

}
