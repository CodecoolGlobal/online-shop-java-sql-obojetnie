package classes.categories;

import java.util.Date;

public class FeaturedCategory extends Category {

    private Date expirationDate;

    public FeaturedCategory(int id, String name, Date expirationDate) {
        super(id, name);
        this.expirationDate = expirationDate;
    }

}
