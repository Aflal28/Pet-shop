package com.example.dog;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Product.db";

    private static final String TABLE_SELLERS = "sellers";
    private static final String TABLE_BUYERS = "buyers";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String TABLE_SELLER_FEEDBACK = "seller_feedback";
    private static final String TABLE_BUYER_FEEDBACK="buyer_feedback";

    // Feedback table columns
    private static final String KEY_ID = "id";
    private static final String KEY_RATING = "rating";
    private static final String KEY_COMMENT = "comment";
    private static final String TABLE_PRODUCTS = "products";
    //private static final String COLUMN_ID = "_id";
    //private static final String COLUMN_NAME = "name";
   // private static final String COLUMN_USER_ID = "user_id";

    private static final  String COLUMN_DESCRIPTION="description";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_SEX = "sex";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_IMAGE = "image";
    private static final String TABLE_CONFIRMATION = "confirmation";
    private static final String COLUMN_CONFIRMATION_ID = "confirmation_id";
    private static final String COLUMN_PRODUCT_NAME = "pet_name";
    private static final String COLUMN_TOTAL_PAYMENT = "total_payment";

    private static final String COLUMN_BUYER_NAME = "buyer_name";
    private static final String COLUMN_CONTACT_NUMBER = "contact_number";
    private static final String COLUMN_ADDRESS = "address";


    // Constructor
    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_SELLERS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_CARETAKERS_TABLE = "CREATE TABLE " + TABLE_BUYERS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT" +
                ")";
        db.execSQL(CREATE_CARETAKERS_TABLE);

        String createTable = "CREATE TABLE " + TABLE_SELLER_FEEDBACK + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_RATING + " REAL," +
                    KEY_COMMENT + " TEXT" + ")";
            db.execSQL(createTable);
        String create_care_feed_Table = "CREATE TABLE " + TABLE_BUYER_FEEDBACK + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_RATING + " REAL," +
                KEY_COMMENT + " TEXT" + ")";
        db.execSQL(create_care_feed_Table);
        String CREATE_PETS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_AGE + " INTEGER," +
                COLUMN_SEX + " TEXT," +
                COLUMN_NOTE + " TEXT," +
                COLUMN_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_PETS_TABLE);
        String CREATE_CONFIRMATION_TABLE = "CREATE TABLE " + TABLE_CONFIRMATION + "("+
                 COLUMN_CONFIRMATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                 COLUMN_PRODUCT_NAME + " TEXT,"+
                 COLUMN_TOTAL_PAYMENT + " INTEGER,"+
                 COLUMN_BUYER_NAME + " TEXT,"+
                 COLUMN_CONTACT_NUMBER + " TEXT,"+
                 COLUMN_ADDRESS + " TEXT"
                + ")";
        db.execSQL(CREATE_CONFIRMATION_TABLE);
        }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELLERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELLER_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUYER_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONFIRMATION);

        // Create tables again
        onCreate(db);
    }


    // Method to add user credentials to the database
    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        // Inserting Row
        db.insert(TABLE_SELLERS, null, values);
        db.close();
    }

    // Method to check if a user exists in the database
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_SELLERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    public void addCaretaker(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        // Inserting Row
        db.insert(TABLE_BUYERS, null, values);
        db.close();
    }

    // Method to check if a caretaker exists in the database
    public boolean checkCaretaker(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_BUYERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }
    public void addFeedback(double rating, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RATING, rating);
        values.put(KEY_COMMENT, comment);
        db.insert(TABLE_SELLER_FEEDBACK, null, values);
        db.close();
    }
    public ArrayList<String> getAllFeedback() {
        ArrayList<String> feedbackList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SELLER_FEEDBACK, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") double rating = cursor.getDouble(cursor.getColumnIndex(KEY_RATING));
                @SuppressLint("Range") String comment = cursor.getString(cursor.getColumnIndex(KEY_COMMENT));

                String feedback = "Rating: " + rating + "\nComment: " + comment;

                feedbackList.add(feedback);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return feedbackList;
    }
    public void careFeedback(double rating, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RATING, rating);
        values.put(KEY_COMMENT, comment);
        db.insert(TABLE_BUYER_FEEDBACK, null, values);
        db.close();
    }
    public ArrayList<String> getAllCareFeedback() {
        ArrayList<String> feedbackList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BUYER_FEEDBACK, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") double rating = cursor.getDouble(cursor.getColumnIndex(KEY_RATING));
                @SuppressLint("Range") String comment = cursor.getString(cursor.getColumnIndex(KEY_COMMENT));

                String feedback = "Rating: " + rating + "\nComment: " + comment;

                feedbackList.add(feedback);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return feedbackList;
    }
    public long addPet(String name, String description, int age, String sex, int note,byte[] petImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_SEX, sex);
        values.put(COLUMN_NOTE, note);
        values.put(COLUMN_IMAGE,petImage);


        long newRowId = db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        return newRowId;
    }
    public ArrayList<String> getAllPets() {
        ArrayList<String> petsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
        if (cursor.moveToFirst()) {
            do {
                // Retrieve individual details of each pet from the cursor
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                @SuppressLint("Range") String sex = cursor.getString(cursor.getColumnIndex(COLUMN_SEX));
                @SuppressLint("Range") int note = cursor.getInt(cursor.getColumnIndex(COLUMN_NOTE));

                int total= note;


                // Create a Pet object with the retrieved details
                String pet = ("Name: " +name+"\n"+"Description: " +description+"\n"+"Age: " +age+"\n"+"Sex: " +sex+"\n"+"PerDay Payment: " +note+"\n"+"Total: " +total);

                // Add the pet object to the list
                petsList.add(pet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return petsList;
    }
    public ArrayList<Pet> getAllPetNames() {
        ArrayList<Pet> petsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query the database to fetch pet names (assuming 'name' is a column in your 'pets' table)
        Cursor cursor = db.rawQuery("SELECT id, name, image FROM " + TABLE_PRODUCTS, null);
        if (cursor.moveToFirst()) {
            do {
                // Retrieve ID, name, and image of each pet
                @SuppressLint("Range") int petId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String petName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") byte[] imageData = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

                // Create a Pet object with ID, name, and image
                Pet pet = new Pet(petId, petName);
                pet.setImage(imageBitmap);
                petsList.add(pet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return petsList;
    }

    public Pet getPetById(int petId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Pet pet = null;

        Cursor cursor = db.query(TABLE_PRODUCTS, null, COLUMN_ID + "=?", new String[]{String.valueOf(petId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
            @SuppressLint("Range") String sex = cursor.getString(cursor.getColumnIndex(COLUMN_SEX));
            @SuppressLint("Range") int note = cursor.getInt(cursor.getColumnIndex(COLUMN_NOTE));
            @SuppressLint("Range") byte[] imageData = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

            // Create a new Pet object with the retrieved details
            pet = new Pet(petId, name, description, age, sex, note,imageBitmap);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return pet;
    }

    public int updatePet( String name, String description, int age, String sex, int note, byte[] petImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_SEX, sex);
        values.put(COLUMN_NOTE, note);
        values.put(COLUMN_IMAGE, petImage);

        // Updating Row based on pet ID
        int numRowsAffected = db.update(TABLE_PRODUCTS, values, COLUMN_NAME + "=?", new String[]{String.valueOf(name)});
        db.close();

        return numRowsAffected;
    }


    // Method to delete a pet from the database
    public void deletePet(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Deleting Row
        db.delete(TABLE_PRODUCTS, COLUMN_NAME + "=?", new String[]{String.valueOf(name)});
        db.close();
    }
    public Pet getPetByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Pet pet = null;

        Cursor cursor = db.query(TABLE_PRODUCTS, null, COLUMN_NAME + "=?", new String[]{name},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve and set pet details similar to your existing getPetById method
            pet = createPetFromCursor(cursor);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return pet;
    }

    public void addConfirmationDetails(String petName,int total, String caretakerName, String contactNumber, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, petName);
        values.put(COLUMN_TOTAL_PAYMENT, total);
        values.put(COLUMN_BUYER_NAME, caretakerName);
        values.put(COLUMN_CONTACT_NUMBER, contactNumber);
        values.put(COLUMN_ADDRESS, address);

        // Insert row
        db.insert(TABLE_CONFIRMATION, null, values);
        db.close();
    }

    public ArrayList<String> getAllConfirm() {
        ArrayList<String> confirmList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONFIRMATION, null);
        if (cursor.moveToFirst()) {
            do {
                // Retrieve individual details of each confirmation from the cursor
                @SuppressLint("Range") String petName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") int totalPayment = cursor.getInt(cursor.getColumnIndex(COLUMN_TOTAL_PAYMENT));
                @SuppressLint("Range") String caretakerName = cursor.getString(cursor.getColumnIndex(COLUMN_BUYER_NAME));
                @SuppressLint("Range") String contactNumber = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NUMBER));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));

                // Create a confirmation string with the retrieved details
                String confirmation = petName +
                        "\nTotal Payment: " + totalPayment +
                        "\nBuyer Name: " + caretakerName +
                        "\nContact Number: " + contactNumber +
                        "\nAddress: " + address;

                // Add the confirmation string to the list
                confirmList.add(confirmation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return confirmList;
    }

    @NonNull
    private Pet createPetFromCursor(Cursor cursor) {
        @SuppressLint("Range") int petId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
        @SuppressLint("Range") String sex = cursor.getString(cursor.getColumnIndex(COLUMN_SEX));
        @SuppressLint("Range") int note = cursor.getInt(cursor.getColumnIndex(COLUMN_NOTE));
        @SuppressLint("Range") byte[] imageData = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));

        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

        return new Pet(petId, name, description, age, sex, note, imageBitmap);
    }

    public User getUserDetailsByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(TABLE_SELLERS, null, COLUMN_EMAIL + "=?", new String[]{email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

            // Create a User object with the retrieved details
            user = new User(id, name, email, password);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return user;
    }

    // Method to fetch user details by ID
    public User getUserDetailsById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(TABLE_SELLERS, null, COLUMN_ID + "=?", new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

            // Create a User object with the retrieved details
            user = new User(userId, name, email, password);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return user;
    }


}