//package CS4125.Model.Database;
//
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.database.DatabaseReference;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileInputStream;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class AccessDBTest {
//
//    private AccessDB accessDBUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        accessDBUnderTest = new AccessDB();
//        accessDBUnderTest.serviceAccount = mock(FileInputStream.class);
//        accessDBUnderTest.options = FirebaseOptions.builder().build();
//        accessDBUnderTest.app = mock(FirebaseApp.class);
//        accessDBUnderTest.ref = mock(DatabaseReference.class);
//        accessDBUnderTest.id = "id";
//    }
//
//    @Test
//    void testWrite() {
//        // Setup
//        when(accessDBUnderTest.ref.push()).thenReturn(null);
//        when(accessDBUnderTest.ref.child("pathString")).thenReturn(null);
//
//        // Run the test
//        accessDBUnderTest.write("in");
//
//        // Verify the results
//    }
//}
