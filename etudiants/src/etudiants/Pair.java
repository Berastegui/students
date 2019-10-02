package etudiants;

public class Pair {

    private int left;
    private int right;

    public Pair(int left, int right) {
      this.left = left;
      this.right = right;
    }

    public int getLeft() { return left; }
    public int getRight() { return right; }

    @Override
    public int hashCode() { return left ^ right; }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Pair)) return false;
      Pair pairo = (Pair) o;
      return this.left==pairo.getLeft() &&
             this.right==pairo.getRight();
    }
    
    public void afficher() {
        System.out.println(left+"; "+right);
    }
    
    public boolean isPresent(int i) {
        return (i == left || i == right);
    }
    
    public void exchange() {
        int i = left;
        left = right;
        right = i;
    }

  }