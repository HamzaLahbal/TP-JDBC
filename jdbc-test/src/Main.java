
public class Main {
    public static void main(String[] args) {
        Person person = new Person();


        person.insertEtudiant("oualid", "fata", 20);
        person.display();
        person.supprimeretudiant(3);
        person.display();
        person.update(5,"ziad","essaouiri",22);
        person.display();
    }
}
