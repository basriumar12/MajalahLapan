package id.go.lapan.majalahlapan.data.array.archive;

// Model Data Archive
// id, year, expandable(true/false)

public class DataArchive {
    private int id;
    private String year;
    private boolean expandable;

    public DataArchive(int id, String year, Boolean expandable) {
        this.id = id;
        this.year = year;
        this.expandable = expandable;
    }

    public int getId() {
        return id;
    }


    public String getYear() {
        return year;
    }


    public boolean isExpandable() {
        return expandable;
    }

}
