package etudiants;

import java.util.ArrayList;
import java.util.List;

public class PairList
{
    private List<Pair> listDePaires = new ArrayList<Pair>();

    public PairList()
    {
        this.listDePaires = new ArrayList<Pair>();
    }

    public List<Pair> getListDePaires()
    {
        return listDePaires;
    }

    public void setListDePaires(List<Pair> listDePaires)
    {
        this.listDePaires = listDePaires;
    }
    
    public void addPair(int i, int j) {
        this.listDePaires.add(new Pair(i, j));
    }
    
    public void addPair(Pair paire) {
        this.listDePaires.add(paire);
    }
    
    public boolean remove(Pair paire) {
        return this.listDePaires.remove(paire);
    }
    
    public boolean remove(int i, int j) {
        for(Pair paire : listDePaires) {
            if( (paire.getLeft()==i && paire.getRight()==j) 
                            || (paire.getLeft()==j && paire.getRight()==i) )
                return this.listDePaires.remove(paire);
        }
        return false;
    }
    
    public int removeFirst(int i) {
        int j = i;
        for(Pair paire : listDePaires) {
            if(paire.getLeft()==i) {
                j = paire.getRight();
                this.listDePaires.remove(paire);
                return j;
            }
            if(paire.getRight()==i) {
                j = paire.getLeft();
                this.listDePaires.remove(paire);
                return j;
            }
        }
        return j;
    }
    
    public int separateFirst(int i, PairList listeDePaires) {
        if(listeDePaires == null) {
            listeDePaires = new PairList();
        }
        int j = i;
        for(Pair paire : listDePaires) {
            if(paire.getLeft()==i) {
                j = paire.getRight();
                listeDePaires.addPair(paire);
                this.listDePaires.remove(paire);
                return j;
            }
            if(paire.getRight()==i) {
                j = paire.getLeft();
                listeDePaires.addPair(paire);
                this.listDePaires.remove(paire);
                return j;
            }
        }
        return j;
    }
    
    public int removeAll(int i) {
        int j = removeFirst(i);
        
        while(j!=i) {
            j = removeFirst(i);
        }
        
        return j;
    }
    
    public Pair remove(int i) {
        return this.listDePaires.remove(i);
    }
    
    public Pair removeRandom() {
        int s = size();
        if(s>0) {
            int nbRemove = (int)(Math.random() * size());
            return this.listDePaires.remove(nbRemove);
        }
        return null;
    }
    
    public PairList clone()
    {
        PairList clone = new PairList();
        
        for(Pair paire : listDePaires) {
            clone.addPair(paire);
        }
        
        return clone;
        
    }
    
    public void afficher() {
        for(Pair paire : listDePaires) {
            paire.afficher();
        }
    }
    
    public int size() {
        return listDePaires.size();
    }
    
    public boolean isEmpty() {
        return listDePaires.isEmpty();
    }
    
    public int getFirstLeft()
    {
        return listDePaires.get(0).getLeft();
    }
    
    public int getNbOccurences(int etudiant) {
        int i = 0;
        for(Pair paire : getListDePaires()) {
            if(paire.isPresent(i)) {
                i++;
            }
        }
        return i;
    }
    
    public void putPairsFirst(int i) {
        List<Pair> paires = new ArrayList<Pair>();
        for(Pair pair : listDePaires) {
            if(pair.isPresent(i)) {
                listDePaires.remove(pair);
                if(i==pair.getRight()) {
                    pair.exchange();
                }
                paires.add(pair);
            }
        }
        listDePaires.addAll(0, paires);
    }
    
    public Pair get(int i) {
        return listDePaires.get(i);
    }
    
    public int separateAll(int i,List<Integer> etudiants, PairList listeDePaires) {
        int j = separateFirst(i, listeDePaires);
        
        while(j!=i) {
            if( !etudiants.contains(Integer.valueOf(j)))
                etudiants.add(Integer.valueOf(j));
            j = separateFirst(i, listeDePaires);
        }
        
        return j;
    }
}
