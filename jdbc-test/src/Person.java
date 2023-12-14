import java.sql.*;

public class Person {
    private int id;
    private String nom;
    private String prenom;
    private int age;

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String get_nom() {
        return nom;
    }

    public void set_nom(String nom) {
        this.nom = nom;
    }

    public String get_prenom() {
        return prenom;
    }

    public void set_prenom(String prenom) {
        this.prenom = prenom;
    }

    public int get_age() {
        return age;
    }

    public void set_age(int age) {
        this.age = age;
    }

    public Person(int id, String nom, String prenom, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Person() {
    }

    public void insertEtudiant(String nom, String prenom, int age) {
        String sqlInsert = "INSERT INTO etudiants (nom, prenom, age) VALUES (?, ?, ?)";
        try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_jdbc", "root", "");
             PreparedStatement insertStatement = connexion.prepareStatement(sqlInsert)) {

            insertStatement.setString(1, nom);
            insertStatement.setString(2, prenom);
            insertStatement.setInt(3, age);
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void display(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_jdbc", "root", "");

                if (connexion != null) {
                    System.out.println("Connecté à la base de données !");
                }

                String request = "SELECT * FROM etudiants";
                try (PreparedStatement sql = connexion.prepareStatement(request);
                     ResultSet res = sql.executeQuery()) {
                    while (res.next()) {
                        int id = res.getInt("id");
                        String nom = res.getString("nom");
                        String prenom = res.getString("prenom");
                        int age = res.getInt("age");
                        System.out.println("id: " + id + " nom: " + nom + " prenom: " + prenom + " age: " + age);
                    }
                }

            } catch (ClassNotFoundException e) {
                System.out.println("Pilote JDBC introuvable : " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            }

        }


        public void supprimeretudiant(int id ){
            String req="DELETE  FROM etudiants WHERE id = ? ";
            try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_jdbc", "root", "");
                 PreparedStatement deleteStatement = connexion.prepareStatement(req)) {
                deleteStatement.setInt(1, id);
                int lignesSupprimees = deleteStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

        public void update( int id,String nNom, String nPrenom, int nAge){
            String sqlUpdate = "UPDATE etudiants SET nom = ?, prenom = ?, age = ? WHERE id = ?";
            try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_jdbc", "root", "");

                 PreparedStatement updateStatement = connexion.prepareStatement(sqlUpdate)) {

                updateStatement.setString(1, nNom);
                updateStatement.setString(2, nPrenom);
                updateStatement.setInt(3, nAge);
                updateStatement.setInt(4, id);


                int lignesModifiees = updateStatement.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }



            }
}

