public class Wuerfel implements Codec {

    private String losung;
    private int[]inverseLosung;

    public Wuerfel(String s){
        this.setzeLosung(s);
    }

    public Wuerfel(){

    }

    public String kodiere(String klartext) {
        StringBuilder geheimtext = new StringBuilder();
        int charPosition;
        for ( int i=0; i<losung.length(); i++ ) {
            for ( int y=0; y<=(klartext.length() / losung.length()); y++) {
                charPosition = inverseLosung[i] + (y * losung.length());
                if (charPosition < klartext.length()) {
                    geheimtext.append(klartext.charAt(charPosition));
                }
            }
        }
        return geheimtext.toString();
    }

    public String dekodiere(String geheimtext){
        StringBuilder klartext = new StringBuilder(geheimtext);
        int position = 0;
        for(int i=0; i<losung.length(); i++){
            for(int y=0; y<=geheimtext.length()/losung.length(); y++){
                if(inverseLosung[i] + (y*losung.length()) < geheimtext.length()){
                    int pos = (inverseLosung[i] + y*losung.length());
                    klartext.setCharAt(pos, geheimtext.charAt(position));
                    position++;
                }
            }
        }
        return klartext.toString();
    }

    public String gibLosung(){
        return losung;
    }

    public void setzeLosung(String schluessel) throws IllegalArgumentException{
        losung = schluessel;
        String charSchluessel = schluessel.toLowerCase();
        int[] zahlenLosung  = new int[losung.length()];
        inverseLosung = new int[losung.length()];
        for(int i=0; i<losung.length(); i++){
            for(int y=0; y<losung.length(); y++){
                if( ( charSchluessel.charAt(i) > charSchluessel.charAt(y)) || ( charSchluessel.charAt(i) == charSchluessel.charAt(y) && i>y ) ){
                    zahlenLosung[i]++;
                }
                int temp = zahlenLosung[y];
                inverseLosung[temp] = y;
            }
        }
    }
}
