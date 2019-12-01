package CS4125.Model.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;

public class AccessDB {
    FileInputStream serviceAccount;
    FirebaseOptions options;
    FirebaseApp app;
    DatabaseReference ref;
    String id;
    public AccessDB() {
        try {
            serviceAccount = new FileInputStream("cs4125-2c28e-firebase-adminsdk-4v6u7-ca9f7a3067.json");
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://cs4125-2c28e.firebaseio.com")
                    .build();
            app=FirebaseApp.initializeApp(options);
            ref=FirebaseDatabase.getInstance().getReference();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(String in) {
        TestData quote=new TestData(in);
        id=ref.push().getKey();
        ref.child(id).setValueAsync(quote);
    }
}