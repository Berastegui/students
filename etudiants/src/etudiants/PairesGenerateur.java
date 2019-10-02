package etudiants;

import java.util.ArrayList;
import java.util.List;

public class PairesGenerateur
{
    private int nbEtudiants = 400;
    
    private int nbPlaces = 100;
    
    private PairList toutesLesPaires = new PairList();
    
    

    public PairesGenerateur(int nbEtudiants, int nbPlaces)
    {
        super();
        this.nbEtudiants = nbEtudiants;
        this.nbPlaces = nbPlaces;
        creerToutesLesPaires(nbEtudiants);
    }

    public int getNbEtudiants()
    {
        return nbEtudiants;
    }

    public void setNbEtudiants(int nbEtudiants)
    {
        this.nbEtudiants = nbEtudiants;
    }

    public int getNbPlaces()
    {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces)
    {
        this.nbPlaces = nbPlaces;
    }
    
    private void creerToutesLesPaires(int nb) {
        toutesLesPaires = new PairList();
        for(int i=0;i<nb-1;i++) {
            for(int j = i+1;j<nb;j++) {
                toutesLesPaires.addPair(new Pair(i, j));
            }
        }
    }
    
    public void afficherToutesLesPaires() {
        toutesLesPaires.afficher();
    }
    
    public PairList genererPairesAleatoire(){
        PairList listeDePaires = toutesLesPaires.clone();
        
        int nbRemove = (int)(Math.random() * toutesLesPaires.size());
        
        for(int i=0; i<nbRemove; i++) {
            listeDePaires.removeRandom();
        }
        
        return listeDePaires;
    }
    
    public Combinaison genererCombinaison(){
        
        return new Combinaison(genererPairesAleatoire(), initialiserEtudiants(),nbPlaces);
        
    }
    
    public Combinaison genererCombinaisonWithMax(int max){
        
        return new Combinaison(genererPairesAleatoireWithMax(max), initialiserEtudiants(),nbPlaces);
        
    }
    
    public Combinaison genererCombinaisonWithMaxAndMin(int max, int min){
        
        return new Combinaison(genererPairesAleatoireWithMaxAndMin(max, min), initialiserEtudiants(),nbPlaces);
        
    }
    
    public PairList genererPairesAleatoire(int nbPaires){
        PairList listeDePaires = toutesLesPaires.clone();
        
        int nbRemove = toutesLesPaires.size() - nbPaires;
        
        for(int i=0; i<nbRemove; i++) {
            listeDePaires.removeRandom();
        }
        
        return listeDePaires;
    }
    
    public PairList genererPairesAleatoireWithMax(int max){
        PairList listeDePaires = toutesLesPaires.clone();
        
        int nbRemove = toutesLesPaires.size() - (int)(Math.random() * max);
        
        for(int i=0; i<nbRemove; i++) {
            listeDePaires.removeRandom();
        }
        
        return listeDePaires;
    }
    
    public PairList genererPairesAleatoireWithMaxAndMin(int max, int min){
        PairList listeDePaires = toutesLesPaires.clone();
        
        int nbRemove = toutesLesPaires.size() - (int)(Math.random() * (max-min) + min);
        
        for(int i=0; i<nbRemove; i++) {
            listeDePaires.removeRandom();
        }
        
        return listeDePaires;
    }
    
    private List<Integer> initialiserEtudiants() {
        List<Integer> etudiants = new ArrayList<Integer>();
        
        for(int i=0; i<nbEtudiants; i++) {
            etudiants.add(i);
        }
        
        return etudiants;
    }
    
    
}
