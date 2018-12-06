public class Caesar implements Codec {

    private String schluesselwort;

    public Caesar(String s){
        setzeLosung(s);
    }

    public Caesar(){}

    // Diese Methode verschlüsselt auch Sonderzeichen etc.
    public String kodiere2(String klartext){
        StringBuilder sb = new StringBuilder(klartext.length());
        String geheimtext;
        int alterChar;
        int neuerChar;
        for(int i=0; i<klartext.length(); i++) {
            alterChar = klartext.charAt(i);
            if( ((alterChar + schluesselwort.length()) % 127) <= 31){
                neuerChar = ((alterChar + schluesselwort.length()) % 126) + 32;
            }else {
                neuerChar = ((alterChar + schluesselwort.length()) % 127);
            }
            sb.append((char)(neuerChar));
        }
        geheimtext = sb.toString();
        return geheimtext;
    }

    public String kodiere(String klartext) {
        StringBuilder sb = new StringBuilder(klartext.length());
        String geheimtext;
        int alterChar;
        int neuerChar;
        for (int i = 0; i < klartext.length(); i++) {
            alterChar = klartext.charAt(i);
            if (alterChar >= 97 && alterChar <= 122) {
                neuerChar = (char) ((alterChar - 97 + schluesselwort.length()) % 26 + 97);
            } else if (alterChar >= 65 && alterChar <= 90) {
                neuerChar = (char) ((alterChar - 65 + schluesselwort.length()) % 26 + 65);
            } else {
                neuerChar = alterChar;
            }
            sb.append((char)(neuerChar));
        }
        geheimtext = sb.toString();
        return geheimtext;
    }

    public String dekodiere(String geheimtext){
        StringBuilder sb = new StringBuilder(geheimtext.length());
        String klartext;
        int alterChar;
        int neuerChar;
        for(int i=0; i<geheimtext.length(); i++){
            alterChar = geheimtext.charAt(i);
            if(alterChar >= 65 && alterChar <= 90){
                neuerChar = (alterChar - 65 + (26 - schluesselwort.length())) % 26 + 65;
            }else if(alterChar >= 97 && alterChar <= 122){
                neuerChar = (alterChar - 97 + (26 - schluesselwort.length())) % 26 + 97;
            }else {
                neuerChar = alterChar;
            }
            sb.append((char)(neuerChar));
        }
        klartext = sb.toString();
        return klartext;
    }

    public String gibLosung(){
        return schluesselwort;
    }

    public void setzeLosung(String schluessel) throws IllegalArgumentException{
        schluesselwort = schluessel;
    }
}
