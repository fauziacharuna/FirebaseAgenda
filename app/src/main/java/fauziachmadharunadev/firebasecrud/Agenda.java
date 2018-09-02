package fauziachmadharunadev.firebasecrud;

/**
 * Created by fauziachmadharuna on 02/09/18.
 */

public class Agenda {

    //class model sebagai representasi database (blueprint struktur database)


    //constructor dengan parameter untuk membangun objek baru sesuai dengan parameter
    public Agenda(String id, String item) {
        this.id = id;
        this.item = item;
    }
    public Agenda(){

    }

    public String id, item;

    //getter-setter untuk memasukkan nilai ke properti database

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
