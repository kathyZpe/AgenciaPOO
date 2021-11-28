package db.statements;

public class AgencyStatements {
    public static final String TABLE_NAME = "agencies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "agency_name";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("+COLUMN_ID+"" +
        " INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE, "+ COLUMN_NAME + " NVARCHAR(30) NOT NULL);";
}
