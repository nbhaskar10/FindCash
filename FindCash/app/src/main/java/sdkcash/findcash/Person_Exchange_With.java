package sdkcash.findcash;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by Abby on 10/16/2016.
 */
public class Person_Exchange_With {
    public static String name;
    public static String phoneNumber;
    public static String email;
    public static Bitmap image;
    public static String age;
    public static String userName;

    public Person_Exchange_With(){}

    public Person_Exchange_With(String name, String phoneNumber, String email, Bitmap image,
                                String age, String userName)
    {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = image;
        this.age = age;
        this.userName = userName;
    }

    void setName(String name)
    {
        this.name = name;
    }
    String getName()
    {
        return this.name;
    }

    void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    void setEmail(String email)
    {
        this.email = email;
    }
    String getEmail()
    {
        return this.email;
    }

    void setImage(Bitmap image)
    {
        this.image = image;
    }
    Bitmap getImage()
    {
        return this.image;
    }

    void setAge(String age)
    {
        this.age = age;
    }
    String getAge()
    {
        return this.age;
    }

    void setUserName(String userName)
    {
        this.userName = userName;
    }
    String getUserName()
    {
        return this.userName;
    }
    
}
