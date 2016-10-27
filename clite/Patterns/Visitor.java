package clite.Patterns;

public interface Visitor {
    public void visit(Visitable v);
    public void pushState();
    public void popState();
}
