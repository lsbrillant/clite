package clite.AbstractSyntax;

public class Program { 
    // Progra m = Declarations decpart ; Block body
    Declarations decpart;
    Block body;

    public Program (Declarations d, Block b) {
        decpart = d;
        body = b;
    }

    public void display() {
        System.out.println("Program Display");
    }

}
