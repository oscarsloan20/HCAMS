@Document("users")
public class User {
    @Id
    private String id;

    private String email;
    private String password;
    private String role;
    private String patientId;
    private String doctorId;
}
